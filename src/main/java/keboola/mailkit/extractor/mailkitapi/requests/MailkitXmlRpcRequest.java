/*
 */
package keboola.mailkit.extractor.mailkitapi.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2016
 */
public abstract class MailkitXmlRpcRequest implements MailkitRequest {

    private final String function;

    @JsonProperty("id")
    private String client_id;
    @JsonProperty("md5")
    private String client_md5;
    private final List<Object> parameters;

    public MailkitXmlRpcRequest(String function) {
        this.function = function;

        this.parameters = new ArrayList<>();
    }

    public final void addParameter(Object value) {

        this.parameters.add(value);
    }

    @Override
    public String getStringRepresentation() throws Exception {
        return "";
    }

    public String getFunction() {
        return function;
    }

    @Override
    public String getClient_id() {
        return client_id;
    }

    @Override
    public String getClient_md5() {
        return client_md5;
    }

    @Override
    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    @Override
    public void setClient_md5(String client_md5) {
        this.client_md5 = client_md5;
    }

    public List<Object> getParameters() {
        return parameters;
    }

}
