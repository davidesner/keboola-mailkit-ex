package keboola.mailkit.extractor.mailkitapi.response.wrapper;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import keboola.mailkit.extractor.mailkitapi.response.MsgBouncesResp;

/**
 * @author David Esner
 */
public class MsgBounceWrapper {

	@JsonProperty("ID_SEND_MESSAGE")
	private String ID_SEND_MESSAGE;
	private String ID_SEND;
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
	public MsgBounceWrapper() {
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
	public MsgBounceWrapper(MsgBouncesResp r, String idSend) {
		this.ID_SEND = idSend;
		this.ID_SEND_MESSAGE = r.getID_SEND_MESSAGE();
		this.ID_USER_LIST = r.getID_USER_LIST();
		this.DATE = r.getDATE();
		this.STATUS = r.getSTATUS();
		this.ID_EMAIL = r.getID_EMAIL();
		this.EMAIL = r.getEMAIL();
		this.EMAIL_STATUS = r.getEMAIL_STATUS();
	}

	public String getID_SEND_MESSAGE() {
		return ID_SEND_MESSAGE;
	}

	public String getID_SEND() {
		return ID_SEND;
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

	public static class Builder {
		public static List<MsgBounceWrapper> build(List<MsgBouncesResp> cResp, String id) {
			List<MsgBounceWrapper> result = new ArrayList<>();
			if (cResp == null) {
				return result;
			}
			cResp.forEach(t -> result.add(new MsgBounceWrapper(t, id)));
			return result;
		}
	}
}
