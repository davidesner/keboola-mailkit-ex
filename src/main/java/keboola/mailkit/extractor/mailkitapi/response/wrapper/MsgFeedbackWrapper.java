package keboola.mailkit.extractor.mailkitapi.response.wrapper;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import keboola.mailkit.extractor.mailkitapi.response.MsgFeedbackResp;

/**
 * @author David Esner
 */
public class MsgFeedbackWrapper {

	private String ID_SEND;
	@JsonProperty("VISIT")
	private Integer VISIT;
	@JsonProperty("OPEN")
	private String OPEN;
	@JsonProperty("UNSUBSCRIBE")
	private Integer UNSUBSCRIBE;
	@JsonProperty("OPEN_P")
	private String OPEN_P;
	@JsonProperty("DATE")
	private String DATE;
	@JsonProperty("CLICK")
	private Integer CLICK;
	@JsonProperty("SPAM_REPORT")
	private Integer SPAM_REPORT;
	@JsonProperty("VISIT_P")
	private Integer VISIT_P;
	@JsonProperty("READ")
	private String READ;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public MsgFeedbackWrapper() {
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
	public MsgFeedbackWrapper(MsgFeedbackResp r, String idSend) {
		this.ID_SEND = idSend;
		this.VISIT = r.getVISIT();
		this.VISIT_P = r.getVISIT();
		this.DATE = r.getDATE();
		this.OPEN = r.getOPEN();
		this.UNSUBSCRIBE = r.getUNSUBSCRIBE();
		this.OPEN_P = r.getOPEN_P();
		this.CLICK = r.getCLICK();

		this.SPAM_REPORT = r.getSPAM_REPORT();
		this.READ = r.getREAD();

	}

	public String getID_SEND() {
		return ID_SEND;
	}

	public Integer getVISIT() {
		return VISIT;
	}

	public String getOPEN() {
		return OPEN;
	}

	public Integer getUNSUBSCRIBE() {
		return UNSUBSCRIBE;
	}

	public String getOPEN_P() {
		return OPEN_P;
	}

	public String getDATE() {
		return DATE;
	}

	public Integer getCLICK() {
		return CLICK;
	}

	public Integer getSPAM_REPORT() {
		return SPAM_REPORT;
	}

	public Integer getVISIT_P() {
		return VISIT_P;
	}

	public String getREAD() {
		return READ;
	}

	public static class Builder {
		public static List<MsgFeedbackWrapper> build(List<MsgFeedbackResp> cResp, String id) {
			List<MsgFeedbackWrapper> result = new ArrayList<>();
			if (cResp == null) {
				return result;
			}
			cResp.forEach(t -> result.add(new MsgFeedbackWrapper(t, id)));
			return result;
		}
	}
}
