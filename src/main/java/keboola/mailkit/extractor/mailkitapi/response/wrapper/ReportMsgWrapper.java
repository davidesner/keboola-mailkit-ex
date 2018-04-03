package keboola.mailkit.extractor.mailkitapi.response.wrapper;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import keboola.mailkit.extractor.mailkitapi.response.ReportMsgResp;

/**
 * @author David Esner
 */
public class ReportMsgWrapper {

	private String ID_SEND;
	private String SEND_COUNT;
	@JsonProperty("SUBJECT")
	private String SUBJECT;
	@JsonProperty("FAILED_COUNT")
	private String FAILED_COUNT;
	@JsonProperty("READ_COUNT")
	private String READ_COUNT;
	@JsonProperty("CLICK_COUNT")
	private String CLICK_COUNT;
	@JsonProperty("MINIPAGE_CLICK_COUNT")
	private String MINIPAGE_CLICK_COUNT;
	@JsonProperty("CONVERSION_COUNT_U")
	private String CONVERSION_COUNT_U;
	@JsonProperty("CONVERSION_COUNT")
	private String CONVERSION_COUNT;
	@JsonProperty("DELIVERED_COUNT")
	private String DELIVERED_COUNT;
	@JsonProperty("READ_COUNT_U")
	private String READ_COUNT_U;
	@JsonProperty("SEND_DATE")
	private String SEND_DATE;
	@JsonProperty("CLICK_COUNT_U")
	private String CLICK_COUNT_U;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public ReportMsgWrapper() {
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
	public ReportMsgWrapper(ReportMsgResp r, String idSend) {
		ID_SEND = idSend;
		SEND_COUNT = r.getSEND_COUNT();
		SUBJECT = r.getSUBJECT();
		FAILED_COUNT = r.getFAILED_COUNT();
		READ_COUNT = r.getREAD_COUNT();
		CLICK_COUNT = r.getCLICK_COUNT();
		MINIPAGE_CLICK_COUNT = r.getMINIPAGE_CLICK_COUNT();
		CONVERSION_COUNT_U = r.getCONVERSION_COUNT_U();
		CONVERSION_COUNT = r.getCONVERSION_COUNT();
		DELIVERED_COUNT = r.getDELIVERED_COUNT();
		READ_COUNT_U = r.getREAD_COUNT_U();
		SEND_DATE = r.getSEND_DATE();
		CLICK_COUNT_U = r.getCLICK_COUNT_U();
	}

	public String getID_SEND() {
		return ID_SEND;
	}

	public String getSEND_COUNT() {
		return SEND_COUNT;
	}

	public String getSUBJECT() {
		return SUBJECT;
	}

	public String getFAILED_COUNT() {
		return FAILED_COUNT;
	}

	public String getREAD_COUNT() {
		return READ_COUNT;
	}

	public String getCLICK_COUNT() {
		return CLICK_COUNT;
	}

	public String getMINIPAGE_CLICK_COUNT() {
		return MINIPAGE_CLICK_COUNT;
	}

	public String getCONVERSION_COUNT_U() {
		return CONVERSION_COUNT_U;
	}

	public String getCONVERSION_COUNT() {
		return CONVERSION_COUNT;
	}

	public String getDELIVERED_COUNT() {
		return DELIVERED_COUNT;
	}

	public String getREAD_COUNT_U() {
		return READ_COUNT_U;
	}

	public String getSEND_DATE() {
		return SEND_DATE;
	}

	public String getCLICK_COUNT_U() {
		return CLICK_COUNT_U;
	}

	public static class Builder {
		public static List<ReportMsgWrapper> build(List<ReportMsgResp> cResp, String id) {
			List<ReportMsgWrapper> result = new ArrayList<>();
			if (cResp == null) {
				return result;
			}
			cResp.forEach(t -> result.add(new ReportMsgWrapper(t, id)));
			return result;
		}
	}
}
