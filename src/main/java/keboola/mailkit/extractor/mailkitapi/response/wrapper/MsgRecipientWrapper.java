package keboola.mailkit.extractor.mailkitapi.response.wrapper;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import keboola.mailkit.extractor.mailkitapi.response.MsgRecipientsResp;

/**
 * @author David Esner
 */
public class MsgRecipientWrapper {

	private String ID_SEND;
	@JsonProperty("DATE")
	private String DATE;
	@JsonProperty("READ_COUNT")
	private String READ_COUNT;
	@JsonProperty("STATUS")
	private String STATUS;
	@JsonProperty("ID_EMAIL")
	private String ID_EMAIL;
	@JsonProperty("EMAIL")
	private String EMAIL;
	@JsonProperty("CLICK_COUNT")
	private String CLICK_COUNT;
	@JsonProperty("ID_SEND_MESSAGE")
	private String ID_SEND_MESSAGE;
	@JsonProperty("AB_VERSION")
	private String AB_VERSION;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public MsgRecipientWrapper() {
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
	public MsgRecipientWrapper(MsgRecipientsResp r, String idSend) {
		ID_SEND = idSend;
		DATE = r.getDATE();
		READ_COUNT = r.getREAD_COUNT();
		STATUS = r.getSTATUS();
		ID_EMAIL = r.getID_EMAIL();
		EMAIL = r.getEMAIL();
		CLICK_COUNT = r.getCLICK_COUNT();
		ID_SEND_MESSAGE = r.getID_SEND_MESSAGE();
		AB_VERSION = r.getAB_VERSION();
	}

	public String getID_SEND() {
		return ID_SEND;
	}

	public String getDATE() {
		return DATE;
	}

	public String getREAD_COUNT() {
		return READ_COUNT;
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

	public String getCLICK_COUNT() {
		return CLICK_COUNT;
	}

	public String getID_SEND_MESSAGE() {
		return ID_SEND_MESSAGE;
	}

	public String getAB_VERSION() {
		return AB_VERSION;
	}

	public static class Builder {
		public static List<MsgRecipientWrapper> build(List<MsgRecipientsResp> cResp, String id) {
			List<MsgRecipientWrapper> result = new ArrayList<>();
			if (cResp == null) {
				return result;
			}
			cResp.forEach(t -> result.add(new MsgRecipientWrapper(t, id)));
			return result;
		}
	}
}
