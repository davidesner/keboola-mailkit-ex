/*
 */
package keboola.mailkit.extractor.mailkitapi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.SSLException;

import org.apache.commons.io.FileUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.ServiceUnavailableRetryStrategy;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.NoConnectionReuseStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import keboola.mailkit.extractor.mailkitapi.requests.MailkitRequest;

/**
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2016
 */
public class MailkitJsonAPIClient implements MailkitClient {

	private static final String ENDPOINT_URL = "https://api.mailkit.eu/json.fcgi";

	private static final int MAX_RETRIES = 15;
	private static final long RETRY_INTERVAL = 5000;
	private static final int[] RETRY_STATUS_CODES = {443, 500, 501, 502, 503, 504};
	private static final int MAX_REQ_TIMEOUT = -1;


	private final CloseableHttpClient httpClient;
	private final String persistFolderPath;
	private final String client_id;
	private final String client_md5;
	private File logFile;

	private Logger logger;

	/**
	 * Mailkit Json Api client Uses Apache commons HttpClient and persists the
	 * result in temporary files stored in filesystem.
	 *
	 * @param client_id
	 * @param client_md5
	 * @param persistFolderPath
	 *            - temporary folder for result data
	 */
	public MailkitJsonAPIClient(String client_id, String client_md5, String persistFolderPath) {
		this.client_id = client_id;
		this.client_md5 = client_md5;
		this.logger = Logger.getLogger(MailkitJsonAPIClient.class.getName());
		// set default headers
		List<Header> headers = new ArrayList();
		headers.add(new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json"));
		headers.add(new BasicHeader(HttpHeaders.ACCEPT, "application/json"));

		RequestConfig config = RequestConfig.custom().setConnectTimeout(MAX_REQ_TIMEOUT)
				.setConnectionRequestTimeout(MAX_REQ_TIMEOUT)
				.setSocketTimeout(MAX_REQ_TIMEOUT).build();

		HttpClientBuilder builder = HttpClientBuilder.create().setDefaultRequestConfig(config)
				.setRetryHandler(getRetryHandler(MAX_RETRIES)).setServiceUnavailableRetryStrategy(
						getServiceUnavailableRetryStrategy(MAX_RETRIES, RETRY_STATUS_CODES));
		builder.setDefaultHeaders(headers);	
		builder.setConnectionReuseStrategy(new NoConnectionReuseStrategy());
		this.httpClient = builder.build();

		this.persistFolderPath = persistFolderPath;
	}

	  private HttpRequestRetryHandler getRetryHandler(int maxRetryCount){
	        return (exception, executionCount, context) -> {

	            logger.warning(exception + " Retrying for: " + executionCount + ". time");

	            if (executionCount >= maxRetryCount) {
	                // Do not retry if over max retry count
	                return false;
	            }
	            if (exception instanceof InterruptedIOException) {
	                // Timeout
	            	return true;
	            }
	            if (exception instanceof UnknownHostException) {
	                // Unknown host
	                return false;
	            }
	            if (exception instanceof SSLException) {
	                // SSL handshake exception
	                return false;
	            }
	            HttpClientContext clientContext = HttpClientContext.adapt(context);
	            HttpRequest request = clientContext.getRequest();
	            boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
	            if (idempotent) {
	                // Retry if the request is considered idempotent
	                return true;
	            }
	            return false;
	        };
	    }

	    private ServiceUnavailableRetryStrategy getServiceUnavailableRetryStrategy(final int maxRetryCount, int[] allowedCodes){
	    	return new ServiceUnavailableRetryStrategy() {
	            @Override
	            public boolean retryRequest(
	                    final HttpResponse response, final int executionCount, final HttpContext context) {
	                int statusCode = response.getStatusLine().getStatusCode();
	                if (statusCode < 300) {
	                	return false;
	                }
	                String urlPath = context.getAttribute("http.request").toString();
	                urlPath = urlPath.substring(0, urlPath.indexOf("["));
	                logger.warning(urlPath + " returned " + response.getStatusLine() + " Retrying for: " + executionCount + ". time");
	                boolean isRetriable = Arrays.stream(allowedCodes).anyMatch(i -> i==statusCode);
	                return isRetriable && executionCount < maxRetryCount;
	            }

	            @Override
	            public long getRetryInterval() {
	                return RETRY_INTERVAL;
	            }
	        };
	    }
	/**
	 *
	 * @param req
	 *            - mailkit json request as instance of MailkitJsonRequest class
	 * @return MailkitJsonResponse instance implementing MailkitResponse
	 *         interface
	 * @throws ClientException
	 */
	@Override
	public MailkitResponse executeRequest(MailkitRequest req, boolean log) throws ClientException {

		// set credentials header in request
		req.setClient_id(client_id);
		req.setClient_md5(client_md5);

		// build request
		HttpPost httppost = new HttpPost(ENDPOINT_URL);
		StringEntity stringEntity = null;
		try {

			stringEntity = new StringEntity(req.getStringRepresentation());
		} catch (Exception ex) {
			throw new ClientException("Error parsing the request. " + ex.getLocalizedMessage());
		}
		httppost.setEntity(stringEntity);
		CloseableHttpResponse response;
		String rqString = httppost.toString() + " Request: " + stringEntity.toString();
		try {
			response = httpClient.execute(httppost);
		} catch (IOException ex) {
			throw new ClientException("Error sending request to API. " + ex.getLocalizedMessage()
					+ " Method: " + rqString);
		}
		// check response code
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode >= 300) {
			if (statusCode != 404) {
				throw new ClientException(
						"API error executing function:" + rqString + ". \n Http Response code:"
								+ statusCode + " - " + response.getStatusLine().getReasonPhrase());
			} else {

			}
		}

		HttpEntity entity = response.getEntity();

		String shortResp = null;
		String longResp = null;

		try {

			if (entity != null) {
				long len = entity.getContentLength();

				if (len != -1 && len < 2048) {
					try {
						// possibly error response
						shortResp = EntityUtils.toString(entity);

					} catch (IOException ex) {
						throw new ClientException("Unable to read the API response. For "
								+ req.getClass().getSimpleName() + "\n" + ex.getMessage());
					} catch (ParseException ex) {
						throw new ClientException("Unable to read the API response.For "
								+ req.getClass().getSimpleName() + "\n" + ex.getMessage());
					}
					longResp = shortResp;

				} else {
					longResp = EntityUtils.toString(entity);
				}

			}
		} catch (FileNotFoundException ex) {
			throw new ClientException("Unable to write response to filesystem. For "
					+ req.getClass().getSimpleName() + "\n" + ex.getMessage());
		} catch (IOException ex) {
			throw new ClientException("Unable to write response to filesystem. For "
					+ req.getClass().getSimpleName() + "\n" + ex.getMessage());
		} finally {
			try {
				response.close();
			} catch (Exception ex) {

			}

		}
		if (log) {
			FileInputStream fis = null;
			BufferedWriter out = null;
			try {
				String resTmpFilePath = getUniqueTmpFilePath(req.getClass().getSimpleName());
				File fin = new File(resTmpFilePath);
				fis = new FileInputStream(fin);
				BufferedReader in = new BufferedReader(new InputStreamReader(fis));
				FileWriter fs = new FileWriter(logFile, true);
				out = new BufferedWriter(fs);
				// write request
				out.write("Request:");
				out.newLine();
				out.write(req.getStringRepresentation());
				// write response
				out.newLine();
				out.write("Response:");
				out.newLine();
				String aLine = null;
				while ((aLine = in.readLine()) != null) {
					// Process each line and add output to Dest.txt file
					out.write(aLine);
					out.newLine();
				}
			} catch (FileNotFoundException ex) {
				Logger.getLogger(MailkitJsonAPIClient.class.getName()).log(Level.SEVERE, null, ex);
			} catch (IOException ex) {
				Logger.getLogger(MailkitJsonAPIClient.class.getName()).log(Level.SEVERE, null, ex);
			} catch (Exception ex) {
				Logger.getLogger(MailkitJsonAPIClient.class.getName()).log(Level.SEVERE, null, ex);
			} finally {
				try {
					fis.close();
					out.close();
				} catch (Exception ex) {
					Logger.getLogger(MailkitJsonAPIClient.class.getName()).log(Level.SEVERE, null,
							ex);
				}
			}
		}

		return new MailkitJsonResponse(longResp, shortResp, req.getFunction());

	}

	public void setLogFile(String path) {
		try {
			this.logFile = new File(path);
			logFile.createNewFile();
		} catch (IOException ex) {
			Logger.getLogger(MailkitJsonAPIClient.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * Deletes all files from temporary folder
	 */
	public void cleanupTempFolder() {
		try {
			FileUtils.cleanDirectory(new File(this.persistFolderPath));
		} catch (IOException ex) {
		}
	}

	/**
	 * Returns unique file name that doesnt already exist in temp direcotry
	 *
	 * @param name
	 *            - prefix of the temporary file
	 * @return
	 */
	private String getUniqueTmpFilePath(String name) {
		String path = this.persistFolderPath + File.separator + name + UUID.randomUUID() + ".tmp";
		File f = new File(path);
		if (f.exists() && !f.isDirectory()) {
			return getUniqueTmpFilePath(name);
		} else {
			f.getParentFile().mkdirs();
			return path;
		}
	}

}
