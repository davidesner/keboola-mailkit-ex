
package keboola.mailkit.extractor.mailkitapi.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "SEND_COUNT",
    "READ_COUNT",
    "SPAM_REPORT",
    "CONVERSION_COUNT_U",
    "ID_SEND",
    "PRINT_COUNT",
    "FROM",
    "SUBJECT",
    "DATE",
    "CONVERSION_COUNT",
    "DATE2",
    "READ_COUNT_U",
    "UNSUBSCRIBE",
    "CLICK_RATE",
    "DELIVERED_COUNT",
    "CLICK_COUNT_U",
    "CLICK_RATE2",
    "CLICK_COUNT",
    "FORWARD_COUNT"
})
public class ReportCampaignResp {

    @JsonProperty("SEND_COUNT")
    private String SEND_COUNT;
    @JsonProperty("READ_COUNT")
    private String READ_COUNT;
    @JsonProperty("SPAM_REPORT")
    private Integer SPAM_REPORT;
    @JsonProperty("CONVERSION_COUNT_U")
    private Integer CONVERSION_COUNT_U;
    @JsonProperty("ID_SEND")
    private Integer ID_SEND;
    @JsonProperty("PRINT_COUNT")
    private Integer PRINT_COUNT;
    @JsonProperty("FROM")
    private String FROM;
    @JsonProperty("SUBJECT")
    private String SUBJECT;
    @JsonProperty("DATE")
    private String DATE;
    @JsonProperty("CONVERSION_COUNT")
    private Integer CONVERSION_COUNT;
    @JsonProperty("DATE2")
    private String DATE2;
    @JsonProperty("READ_COUNT_U")
    private String READ_COUNT_U;
    @JsonProperty("UNSUBSCRIBE")
    private Integer UNSUBSCRIBE;
    @JsonProperty("CLICK_RATE")
    private String CLICK_RATE;
    @JsonProperty("DELIVERED_COUNT")
    private Integer DELIVERED_COUNT;
    @JsonProperty("CLICK_COUNT_U")
    private String CLICK_COUNT_U;
    @JsonProperty("CLICK_RATE2")
    private String CLICK_RATE2;
    @JsonProperty("CLICK_COUNT")
    private Integer CLICK_COUNT;
    @JsonProperty("FORWARD_COUNT")
    private Integer FORWARD_COUNT;
    
	public ReportCampaignResp() {
		// TODO Auto-generated constructor stub
	}

	public ReportCampaignResp(String sEND_COUNT, String rEAD_COUNT, Integer sPAM_REPORT, Integer cONVERSION_COUNT_U,
			Integer iD_SEND, Integer pRINT_COUNT, String fROM, String sUBJECT, String dATE, Integer cONVERSION_COUNT,
			String dATE2, String rEAD_COUNT_U, Integer uNSUBSCRIBE, String cLICK_RATE, Integer dELIVERED_COUNT,
			String cLICK_COUNT_U, String cLICK_RATE2, Integer cLICK_COUNT, Integer fORWARD_COUNT) {
		super();
		SEND_COUNT = sEND_COUNT;
		READ_COUNT = rEAD_COUNT;
		SPAM_REPORT = sPAM_REPORT;
		CONVERSION_COUNT_U = cONVERSION_COUNT_U;
		ID_SEND = iD_SEND;
		PRINT_COUNT = pRINT_COUNT;
		FROM = fROM;
		SUBJECT = sUBJECT;
		DATE = dATE;
		CONVERSION_COUNT = cONVERSION_COUNT;
		DATE2 = dATE2;
		READ_COUNT_U = rEAD_COUNT_U;
		UNSUBSCRIBE = uNSUBSCRIBE;
		CLICK_RATE = cLICK_RATE;
		DELIVERED_COUNT = dELIVERED_COUNT;
		CLICK_COUNT_U = cLICK_COUNT_U;
		CLICK_RATE2 = cLICK_RATE2;
		CLICK_COUNT = cLICK_COUNT;
		FORWARD_COUNT = fORWARD_COUNT;
	}

	public String getSEND_COUNT() {
		return SEND_COUNT;
	}

	public String getREAD_COUNT() {
		return READ_COUNT;
	}

	public Integer getSPAM_REPORT() {
		return SPAM_REPORT;
	}

	public Integer getCONVERSION_COUNT_U() {
		return CONVERSION_COUNT_U;
	}

	public Integer getID_SEND() {
		return ID_SEND;
	}

	public Integer getPRINT_COUNT() {
		return PRINT_COUNT;
	}

	public String getFROM() {
		return FROM;
	}

	public String getSUBJECT() {
		return SUBJECT;
	}

	public String getDATE() {
		return DATE;
	}

	public Integer getCONVERSION_COUNT() {
		return CONVERSION_COUNT;
	}

	public String getDATE2() {
		return DATE2;
	}

	public String getREAD_COUNT_U() {
		return READ_COUNT_U;
	}

	public Integer getUNSUBSCRIBE() {
		return UNSUBSCRIBE;
	}

	public String getCLICK_RATE() {
		return CLICK_RATE;
	}

	public Integer getDELIVERED_COUNT() {
		return DELIVERED_COUNT;
	}

	public String getCLICK_COUNT_U() {
		return CLICK_COUNT_U;
	}

	public String getCLICK_RATE2() {
		return CLICK_RATE2;
	}

	public Integer getCLICK_COUNT() {
		return CLICK_COUNT;
	}

	public Integer getFORWARD_COUNT() {
		return FORWARD_COUNT;
	}

    

}
