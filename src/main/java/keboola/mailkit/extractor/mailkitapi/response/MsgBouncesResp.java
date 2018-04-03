
package keboola.mailkit.extractor.mailkitapi.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "ID_SEND_MESSAGE",
    "ID_USER_LIST",
    "DATE",
    "STATUS",
    "ID_EMAIL",
    "EMAIL",
    "EMAIL_STATUS"
})
public class MsgBouncesResp {

    @JsonProperty("ID_SEND_MESSAGE")
    private String ID_SEND_MESSAGE;
    @JsonProperty("ID_USER_LIST")
    private String ID_USER_LIST;
    @JsonProperty("DATE")
    private String DATE;
    @JsonProperty("STATUS")
    private String STATUS;
    @JsonProperty("ID_EMAIL")
    private String ID_EMAIL;
    @JsonProperty("EMAIL")
    private String EMAIL;
    @JsonProperty("EMAIL_STATUS")
    private String EMAIL_STATUS;

    /**
     * No args constructor for use in serialization
     * 
     */
    public MsgBouncesResp() {
    }

    /**
     * 
     * @param iDSENDMESSAGE
     * @param sTATUS
     * @param eMAILSTATUS
     * @param eMAIL
     * @param iDUSERLIST
     * @param iDEMAIL
     * @param dATE
     */
    public MsgBouncesResp(String iDSENDMESSAGE, String iDUSERLIST, String dATE, String sTATUS, String iDEMAIL, String eMAIL, String eMAILSTATUS) {
        super();
        this.ID_SEND_MESSAGE = iDSENDMESSAGE;
        this.ID_USER_LIST = iDUSERLIST;
        this.DATE = dATE;
        this.STATUS = sTATUS;
        this.ID_EMAIL = iDEMAIL;
        this.EMAIL = eMAIL;
        this.EMAIL_STATUS = eMAILSTATUS;
    }

	public String getID_SEND_MESSAGE() {
		return ID_SEND_MESSAGE;
	}

	public String getID_USER_LIST() {
		return ID_USER_LIST;
	}

	public String getDATE() {
		return DATE;
	}

	public String getSTATUS() {
		return STATUS;
	}

	public String getID_EMAIL() {
		return ID_EMAIL;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public String getEMAIL_STATUS() {
		return EMAIL_STATUS;
	}


}
