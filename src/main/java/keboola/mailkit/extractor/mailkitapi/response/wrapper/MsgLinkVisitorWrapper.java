package keboola.mailkit.extractor.mailkitapi.response.wrapper;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import keboola.mailkit.extractor.mailkitapi.response.MsgLinkVisitorData;
import keboola.mailkit.extractor.mailkitapi.response.MsgLinksVisitorResp;

/**
 * @author David Esner
 */
public class MsgLinkVisitorWrapper {
	private String ID_SEND;
	private String ID_URL;
	@JsonProperty("ID_EMAIL")
	private String iDEMAIL;
	@JsonProperty("COUNT")
	private String cOUNT;
	@JsonProperty("EMAIL")
	private String eMAIL;
	@JsonProperty("COUNTRY")
	private String cOUNTRY;
	@JsonProperty("REGION_CODE")
	private String rEGIONCODE;
	@JsonProperty("USER_AGENT")
	private String uSERAGENT;
	@JsonProperty("CITY")
	private String cITY;
	@JsonProperty("ORG")
	private String oRG;
	@JsonProperty("BROWSER_MINOR")
	private String bROWSERMINOR;
	@JsonProperty("CODE")
	private String cODE;
	@JsonProperty("DEVICE")
	private String dEVICE;
	@JsonProperty("CONTINENT")
	private String cONTINENT;
	@JsonProperty("BROWSER_MAJOR")
	private String bROWSERMAJOR;
	@JsonProperty("REFERER")
	private String rEFERER;
	@JsonProperty("BROWSER")
	private String bROWSER;
	@JsonProperty("OS")
	private String oS;
	@JsonProperty("IP")
	private String iP;
	@JsonProperty("IP_ORIG")
	private String iPORIG;
	@JsonProperty("DATETIME")
	private String dATETIME;
	@JsonProperty("REGION")
	private String rEGION;
	@JsonProperty("OS_VERSION")
	private String oSVERSION;
	@JsonProperty("BROWSER_TYPE")
	private String bROWSERTYPE;

	public MsgLinkVisitorWrapper() {
		// TODO Auto-generated constructor stub
	}

	public MsgLinkVisitorWrapper(MsgLinksVisitorResp linkVis, String sendId, String idUrl) {
		this.ID_SEND = sendId;
		this.ID_URL = idUrl;
		this.iDEMAIL = linkVis.getIDEMAIL();
		this.cOUNT = linkVis.getCOUNT();
		this.eMAIL = linkVis.getEMAIL();
		MsgLinkVisitorData data = linkVis.getDATA();
		if (data != null) {
			this.bROWSER = data.getBROWSER();
			this.bROWSERMAJOR = data.getBROWSERMAJOR();
			this.bROWSERMINOR = data.getBROWSERMINOR();
			this.bROWSERTYPE = data.getBROWSERTYPE();
			this.cITY = data.getCITY();
			this.cODE = data.getCODE();
			this.cONTINENT = data.getCONTINENT();
			this.cOUNTRY = data.getCOUNTRY();
			this.dATETIME = data.getDATETIME();
			this.dEVICE = data.getDEVICE();
			this.iP = data.getIP();
			this.iPORIG = data.getIPORIG();
			this.oRG = data.getORG();
			this.oS = data.getOS();
			this.oSVERSION = data.getOSVERSION();
			this.rEFERER = data.getREFERER();
			this.rEGION = data.getREGION();
			this.rEGIONCODE = data.getREGIONCODE();
			this.uSERAGENT = data.getUSERAGENT();
		}
	}

	public String getiDEMAIL() {
		return iDEMAIL;
	}

	public String getcOUNT() {
		return cOUNT;
	}

	public String geteMAIL() {
		return eMAIL;
	}

	public String getcOUNTRY() {
		return cOUNTRY;
	}

	public String getrEGIONCODE() {
		return rEGIONCODE;
	}

	public String getuSERAGENT() {
		return uSERAGENT;
	}

	public String getcITY() {
		return cITY;
	}

	public String getoRG() {
		return oRG;
	}

	public String getbROWSERMINOR() {
		return bROWSERMINOR;
	}

	public String getcODE() {
		return cODE;
	}

	public String getdEVICE() {
		return dEVICE;
	}

	public String getcONTINENT() {
		return cONTINENT;
	}

	public String getbROWSERMAJOR() {
		return bROWSERMAJOR;
	}

	public String getrEFERER() {
		return rEFERER;
	}

	public String getbROWSER() {
		return bROWSER;
	}

	public String getoS() {
		return oS;
	}

	public String getiP() {
		return iP;
	}

	public String getiPORIG() {
		return iPORIG;
	}

	public String getdATETIME() {
		return dATETIME;
	}

	public String getrEGION() {
		return rEGION;
	}

	public String getoSVERSION() {
		return oSVERSION;
	}

	public String getbROWSERTYPE() {
		return bROWSERTYPE;
	}

	public String getID_SEND() {
		return ID_SEND;
	}

	public String getID_URL() {
		return ID_URL;
	}

	public static class Builder {
		public static List<MsgLinkVisitorWrapper> build(List<MsgLinksVisitorResp> cResp, String id, String idUrl) {
			List<MsgLinkVisitorWrapper> result = new ArrayList<>();
			if (cResp == null) {
				return result;
			}
			cResp.forEach(t -> result.add(new MsgLinkVisitorWrapper(t, id, idUrl)));
			return result;
		}
	}

}
