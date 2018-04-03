package keboola.mailkit.extractor.mailkitapi.response.wrapper;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import keboola.mailkit.extractor.mailkitapi.response.MsgLinksResp;

/**
 * @author David Esner
 */
public class MsgLinksWrapper {

	private String ID_SEND;
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
	public MsgLinksWrapper() {
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
	public MsgLinksWrapper(MsgLinksResp r, String idSend) {
		ID_SEND = idSend;
		CONVERSION = r.getCONVERSION();
		TITLE_SHORT = r.getTITLE_SHORT();
		URL = r.getURL();
		VISIT = r.getVISIT();
		ID_URL = r.getID_URL();
		TITLE = r.getTITLE();
		ID_GROUP = r.getID_GROUP();
		COUNT = r.getCOUNT();
	}

	public String getID_SEND() {
		return ID_SEND;
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

	public static class Builder {
		public static List<MsgLinksWrapper> build(List<MsgLinksResp> cResp, String id) {
			List<MsgLinksWrapper> result = new ArrayList<>();
			if (cResp == null) {
				return result;
			}
			cResp.forEach(t -> result.add(new MsgLinksWrapper(t, id)));
			return result;
		}
	}
}
