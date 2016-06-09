/*
 */
package keboola.mailkit.extractor.mailkitapi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import keboola.mailkit.extractor.mailkitapi.requests.MailkitJsonRequest;
import keboola.mailkit.extractor.mailkitapi.requests.MailkitRequest;
import org.apache.commons.io.FileUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2016
 */
public class MailkitJsonAPIClient implements MailkitClient {

    private static final String ENDPOINT_URL = "https://api.mailkit.eu/json.fcgi";

    private final static long BACKOFF_INTERVAL = 500;
    private final static int RETRIES = 5;

    private final CloseableHttpClient httpClient;
    private final String persistFolderPath;
    private final String client_id;
    private final String client_md5;

    /**
     * Mailkit Json Api client
     * Uses Apache commons HttpClient and persists the result in temporary files
     * stored in filesystem.
     *
     * @param client_id
     * @param client_md5
     * @param persistFolderPath - temporary folder for result data
     */
    public MailkitJsonAPIClient(String client_id, String client_md5, String persistFolderPath) {
        this.client_id = client_id;
        this.client_md5 = client_md5;

        //set default headers
        List<Header> headers = new ArrayList();
        headers.add(new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json"));
        headers.add(new BasicHeader(HttpHeaders.ACCEPT, "application/json"));
        this.httpClient = HttpClients.custom().setDefaultHeaders(headers).build();

        this.persistFolderPath = persistFolderPath;
    }

    /**
     *
     * @param req - mailkit json request as instance of MailkitJsonRequest class
     * @return MailkitJsonResponse instance implementing MailkitResponse
     * interface
     * @throws ClientException
     */
    public MailkitResponse executeRequest(MailkitRequest req) throws ClientException {

        //set credentials header in request
        req.setClient_id(client_id);
        req.setClient_md5(client_md5);

        //build request
        HttpPost httppost = new HttpPost(ENDPOINT_URL);
        StringEntity stringEntity = null;
        try {

            stringEntity = new StringEntity(req.getStringRepresentation());
        } catch (Exception ex) {
            throw new ClientException("Error parsing the request. " + ex.getLocalizedMessage());
        }
        httppost.setEntity(stringEntity);
        CloseableHttpResponse response;
        try {
            response = httpClient.execute(httppost);
        } catch (IOException ex) {
            throw new ClientException("Error sending request to API. " + ex.getLocalizedMessage());
        }
        //check response code
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode >= 300) {
            if (statusCode != 404) {
                throw new ClientException("API error executing function:" + req.getFunctionCall() + ". \n Http Response code:" + statusCode + " - " + response.getStatusLine().getReasonPhrase());
            }
        }

        HttpEntity entity = response.getEntity();

        String shortResp = null;

        FileOutputStream fos = null;

        String resTmpFilePath = getUniqueTmpFilePath(req.getClass().getSimpleName());
        try {
            fos = new FileOutputStream(resTmpFilePath);

            byte[] buffer = new byte[1024];

            if (entity != null) {
                long len = entity.getContentLength();

                if (len != -1 && len < 2048) {
                    try {
                        //possibly error response
                        shortResp = EntityUtils.toString(entity);

                    } catch (IOException ex) {
                        throw new ClientException("Unable to read the API response. For "
                                + req.getClass().getSimpleName() + "\n" + ex.getMessage());
                    } catch (ParseException ex) {
                        throw new ClientException("Unable to read the API response.For "
                                + req.getClass().getSimpleName() + "\n" + ex.getMessage());
                    }
                    //write to file as well
                    fos.write(shortResp.getBytes());

                } else {
                    InputStream is = entity.getContent();
                    int inByte;
                    while ((inByte = is.read(buffer)) != -1) {
                        fos.write(buffer, 0, inByte);
                    }
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
                fos.close();
            } catch (Exception ex) {

            }

        }

        return new MailkitJsonResponse(resTmpFilePath, shortResp, req.getFunction());

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
     * @param name - prefix of the temporary file
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
