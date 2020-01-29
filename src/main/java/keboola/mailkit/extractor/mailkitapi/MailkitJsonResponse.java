/*
 */
package keboola.mailkit.extractor.mailkitapi;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2016
 */
public class MailkitJsonResponse implements MailkitResponse {

	private ErrorResponse error;
	private final String longResponse;
	private final String requestType;

	public MailkitJsonResponse(String longResponse, String shortResponse, String requestType) {
		// check if is error
		this.requestType = requestType;
		final ObjectMapper mapper = new ObjectMapper(new JsonFactory());
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
		if (shortResponse != null) {
			try {
				this.error = mapper.readValue(shortResponse, ErrorResponse.class);
				if (this.error.error == null && this.error.error_status == null) {
					this.error = null;
				}
			} catch (IOException ex) {
				this.error = null;
			}
		} else {
			this.error = null;
		}
		if (this.error != null) {
			this.longResponse = null;
		} else {
			this.longResponse = longResponse;
		}

	}

	@Override
	public boolean isError() {
		return this.error != null;
	}

	@Override
	public String getErrorMessage() {
		return "API response error invoking function: " + this.requestType + ". " + error.getError()
				+ ". Exited with error code:" + error.getError_status();
	}

	public InputStream getInputStream() throws Exception {
		if (longResponse != null) {
			return new ByteArrayInputStream(longResponse.getBytes());
		} else {
			return null;
		}
	}

	public <T> List<T> getResponseObject(Class<T> type) throws Exception {
		final ObjectMapper mapper = new ObjectMapper(new JsonFactory());
		mapper.findAndRegisterModules();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		if (isEmptyObj()) {
			return Collections.EMPTY_LIST;
		}
		if (isSingleObj()) {
			List<T> res = new ArrayList<>();
			res.add(mapper.readValue(getInputStream(), type));
			return res;
		}
		return mapper.readValue(getInputStream(),
				mapper.getTypeFactory().constructCollectionType(List.class, type));
	}

	private boolean isSingleObj() {
		if (this.longResponse != null) {
			return this.longResponse.startsWith("{");
		} else {
			return false;
		}
	}

	private boolean isEmptyObj() {
		return "{}".equals(this.longResponse);
	}

	public String getLongResponse() {
		return longResponse;
	}

	private static class ErrorResponse {

		@JsonProperty("error_status")
		private Integer error_status;
		@JsonProperty("error")
		private String error;

		public ErrorResponse(Integer error_status, String error) {
			this.error_status = error_status;
			this.error = error;
		}

		public ErrorResponse() {
		}

		public Integer getError_status() {
			return error_status;
		}

		public void setError_status(Integer error_status) {
			this.error_status = error_status;
		}

		public String getError() {
			return error;
		}

		public void setError(String error) {
			this.error = error;
		}

	}
}
