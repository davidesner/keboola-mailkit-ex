
package keboola.mailkit.extractor.mailkitapi.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "CONVERSION",
    "TITLE_SHORT",
    "URL",
    "VISIT",
    "ID_URL",
    "TITLE",
    "ID_GROUP",
    "COUNT"
})
public class MsgLinksResp {

    @JsonProperty("CONVERSION")
    private String CONVERSION;
    @JsonProperty("TITLE_SHORT")
    private String TITLE_SHORT;
    @JsonProperty("URL")
    private String URL;
    @JsonProperty("VISIT")
    private String VISIT;
    @JsonProperty("ID_URL")
    private String ID_URL;
    @JsonProperty("TITLE")
    private String TITLE;
    @JsonProperty("ID_GROUP")
    private String ID_GROUP;
    @JsonProperty("COUNT")
    private String COUNT;

    /**
     * No args constructor for use in serialization
     * 
     */
    public MsgLinksResp() {
    }

    /**
     * 
     * @param iDGROUP
     * @param iDURL
     * @param tITLE
     * @param uRL
     * @param vISIT
     * @param cONVERSION
     * @param cOUNT
     * @param tITLESHORT
     */
    public MsgLinksResp(String cONVERSION, String tITLESHORT, String uRL, String vISIT, String iDURL, String tITLE, String iDGROUP, String cOUNT) {
        super();
        this.CONVERSION = cONVERSION;
        this.TITLE_SHORT = tITLESHORT;
        this.URL = uRL;
        this.VISIT = vISIT;
        this.ID_URL = iDURL;
        this.TITLE = tITLE;
        this.ID_GROUP = iDGROUP;
        this.COUNT = cOUNT;
    }

	public String getCONVERSION() {
		return CONVERSION;
	}

	public String getTITLE_SHORT() {
		return TITLE_SHORT;
	}

	public String getURL() {
		return URL;
	}

	public String getVISIT() {
		return VISIT;
	}

	public String getID_URL() {
		return ID_URL;
	}

	public String getTITLE() {
		return TITLE;
	}

	public String getID_GROUP() {
		return ID_GROUP;
	}

	public String getCOUNT() {
		return COUNT;
	}



}
