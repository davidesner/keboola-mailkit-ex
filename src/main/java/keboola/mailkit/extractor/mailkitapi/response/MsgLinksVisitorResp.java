
package keboola.mailkit.extractor.mailkitapi.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "ID_EMAIL",
    "DATA",
    "COUNT",
    "EMAIL"
})
public class MsgLinksVisitorResp {

    @JsonProperty("ID_EMAIL")
    private String iDEMAIL;
    @JsonProperty("DATA")
    private MsgLinkVisitorData dATA;
    @JsonProperty("COUNT")
    private String cOUNT;
    @JsonProperty("EMAIL")
    private String eMAIL;

    /**
     * No args constructor for use in serialization
     * 
     */
    public MsgLinksVisitorResp() {
    }

    /**
     * 
     * @param dATA
     * @param eMAIL
     * @param cOUNT
     * @param iDEMAIL
     */
    public MsgLinksVisitorResp(String iDEMAIL, MsgLinkVisitorData dATA, String cOUNT, String eMAIL) {
        super();
        this.iDEMAIL = iDEMAIL;
        this.dATA = dATA;
        this.cOUNT = cOUNT;
        this.eMAIL = eMAIL;
    }

    @JsonProperty("ID_EMAIL")
    public String getIDEMAIL() {
        return iDEMAIL;
    }

    @JsonProperty("ID_EMAIL")
    public void setIDEMAIL(String iDEMAIL) {
        this.iDEMAIL = iDEMAIL;
    }

    @JsonProperty("DATA")
    public MsgLinkVisitorData getDATA() {
        return dATA;
    }

    @JsonProperty("DATA")
    public void setDATA(MsgLinkVisitorData dATA) {
        this.dATA = dATA;
    }

    @JsonProperty("COUNT")
    public String getCOUNT() {
        return cOUNT;
    }

    @JsonProperty("COUNT")
    public void setCOUNT(String cOUNT) {
        this.cOUNT = cOUNT;
    }

    @JsonProperty("EMAIL")
    public String getEMAIL() {
        return eMAIL;
    }

    @JsonProperty("EMAIL")
    public void setEMAIL(String eMAIL) {
        this.eMAIL = eMAIL;
    }

}
