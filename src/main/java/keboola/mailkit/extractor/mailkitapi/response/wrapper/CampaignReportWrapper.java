package keboola.mailkit.extractor.mailkitapi.response.wrapper;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import keboola.mailkit.extractor.mailkitapi.response.ReportCampaignResp;

/**
 * @author David Esner
 */
public class CampaignReportWrapper {

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
    
    private String ID_MESSAGE;
    
	public CampaignReportWrapper() {
		// TODO Auto-generated constructor stub
	}

	public CampaignReportWrapper(ReportCampaignResp resp, String idMessage) {
		this.ID_MESSAGE = idMessage;
		SEND_COUNT = resp.getSEND_COUNT();
		READ_COUNT = resp.getREAD_COUNT();
		SPAM_REPORT = resp.getSPAM_REPORT();
		CONVERSION_COUNT_U = resp.getCONVERSION_COUNT_U();
		ID_SEND = resp.getID_SEND();
		PRINT_COUNT = resp.getPRINT_COUNT();
		FROM = resp.getFROM();
		SUBJECT = resp.getSUBJECT();
		DATE = resp.getDATE();
		CONVERSION_COUNT = resp.getCONVERSION_COUNT();
		DATE2 = resp.getDATE2();
		READ_COUNT_U = resp.getREAD_COUNT_U();
		UNSUBSCRIBE = resp.getUNSUBSCRIBE();
		CLICK_RATE = resp.getCLICK_RATE();
		DELIVERED_COUNT = resp.getDELIVERED_COUNT();
		CLICK_COUNT_U = resp.getCLICK_COUNT_U();
		CLICK_RATE2 = resp.getCLICK_RATE2();
		CLICK_COUNT = resp.getCLICK_COUNT();
		FORWARD_COUNT = resp.getFORWARD_COUNT();
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

	public String getID_MESSAGE() {
		return ID_MESSAGE;
	}

	public static class Builder {
		public static List<CampaignReportWrapper> build(List<ReportCampaignResp> cResp, String id) {
			List<CampaignReportWrapper> result = new ArrayList<>();
			if (cResp == null) {
				return result;
			}
			cResp.forEach(t -> result.add(new CampaignReportWrapper(t,id)));
			return result;
		}
	}
	
}
