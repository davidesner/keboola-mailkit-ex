package keboola.mailkit.extractor.mailkitapi.response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "NAME", "ID_MESSAGE", "SUBJECT", "SUBJECT_B", "MSG_TYPE", "CAMPAIGN_TYPE",
		"USE_AB", "TEST_MODE", "SEND_DATE", "TIME_ZONE", "LAST_SENT", "REPEAT", "REPEAT_COUNT",
		"PERIOD", "DAYS", "STATUS", "TYPE", "USER_LIST" })
public class CampaignResp {

	@JsonProperty("NAME")
	private String nAME;
	@JsonProperty("ID_MESSAGE")
	private int iDMESSAGE;
	@JsonProperty("SUBJECT")
	private String sUBJECT;
	@JsonProperty("SUBJECT_B")
	private String sUBJECTB;
	@JsonProperty("MSG_TYPE")
	private String mSGTYPE;
	@JsonProperty("CAMPAIGN_TYPE")
	private String cAMPAIGNTYPE;
	@JsonProperty("USE_AB")
	private boolean uSEAB;
	@JsonProperty("TEST_MODE")
	private boolean tESTMODE;
	@JsonProperty("SEND_DATE")
	private String sENDDATE;
	@JsonProperty("TIME_ZONE")
	private String tIMEZONE;
	@JsonProperty("LAST_SENT")
	private String lASTSENT;
	@JsonProperty("REPEAT")
	private boolean rEPEAT;
	@JsonProperty("REPEAT_COUNT")
	private int rEPEATCOUNT;
	@JsonProperty("PERIOD")
	private String pERIOD;
	@JsonProperty("DAYS")
	private String dAYS;
	@JsonProperty("STATUS")
	private String sTATUS;
	@JsonProperty("TYPE")
	private String tYPE;
	@JsonProperty("USER_LIST")
	private List<Integer> uSERLIST = null;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * No args constructor for use in serialization
	 *
	 */
	public CampaignResp() {
	}

	/**
	 *
	 * @param rEPEAT
	 * @param mSGTYPE
	 * @param cAMPAIGNTYPE
	 * @param tESTMODE
	 * @param sTATUS
	 * @param rEPEATCOUNT
	 * @param tYPE
	 * @param pERIOD
	 * @param sUBJECT
	 * @param lASTSENT
	 * @param tIMEZONE
	 * @param nAME
	 * @param dAYS
	 * @param iDMESSAGE
	 * @param sENDDATE
	 * @param sUBJECTB
	 * @param uSERLIST
	 * @param uSEAB
	 */
	public CampaignResp(String nAME, int iDMESSAGE, String sUBJECT, String sUBJECTB, String mSGTYPE,
			String cAMPAIGNTYPE, boolean uSEAB, boolean tESTMODE, String sENDDATE, String tIMEZONE,
			String lASTSENT, boolean rEPEAT, int rEPEATCOUNT, String pERIOD, String dAYS,
			String sTATUS, String tYPE, List<Integer> uSERLIST) {
		super();
		this.nAME = nAME;
		this.iDMESSAGE = iDMESSAGE;
		this.sUBJECT = sUBJECT;
		this.sUBJECTB = sUBJECTB;
		this.mSGTYPE = mSGTYPE;
		this.cAMPAIGNTYPE = cAMPAIGNTYPE;
		this.uSEAB = uSEAB;
		this.tESTMODE = tESTMODE;
		this.sENDDATE = sENDDATE;
		this.tIMEZONE = tIMEZONE;
		this.lASTSENT = lASTSENT;
		this.rEPEAT = rEPEAT;
		this.rEPEATCOUNT = rEPEATCOUNT;
		this.pERIOD = pERIOD;
		this.dAYS = dAYS;
		this.sTATUS = sTATUS;
		this.tYPE = tYPE;
		this.uSERLIST = uSERLIST;
	}

	@JsonProperty("NAME")
	public String getNAME() {
		return nAME;
	}

	@JsonProperty("NAME")
	public void setNAME(String nAME) {
		this.nAME = nAME;
	}

	@JsonProperty("ID_MESSAGE")
	public int getIDMESSAGE() {
		return iDMESSAGE;
	}

	@JsonProperty("ID_MESSAGE")
	public void setIDMESSAGE(int iDMESSAGE) {
		this.iDMESSAGE = iDMESSAGE;
	}

	@JsonProperty("SUBJECT")
	public String getSUBJECT() {
		return sUBJECT;
	}

	@JsonProperty("SUBJECT")
	public void setSUBJECT(String sUBJECT) {
		this.sUBJECT = sUBJECT;
	}

	@JsonProperty("SUBJECT_B")
	public String getSUBJECTB() {
		return sUBJECTB;
	}

	@JsonProperty("SUBJECT_B")
	public void setSUBJECTB(String sUBJECTB) {
		this.sUBJECTB = sUBJECTB;
	}

	@JsonProperty("MSG_TYPE")
	public String getMSGTYPE() {
		return mSGTYPE;
	}

	@JsonProperty("MSG_TYPE")
	public void setMSGTYPE(String mSGTYPE) {
		this.mSGTYPE = mSGTYPE;
	}

	@JsonProperty("CAMPAIGN_TYPE")
	public String getCAMPAIGNTYPE() {
		return cAMPAIGNTYPE;
	}

	@JsonProperty("CAMPAIGN_TYPE")
	public void setCAMPAIGNTYPE(String cAMPAIGNTYPE) {
		this.cAMPAIGNTYPE = cAMPAIGNTYPE;
	}

	@JsonProperty("USE_AB")
	public boolean isUSEAB() {
		return uSEAB;
	}

	@JsonProperty("USE_AB")
	public void setUSEAB(boolean uSEAB) {
		this.uSEAB = uSEAB;
	}

	@JsonProperty("TEST_MODE")
	public boolean isTESTMODE() {
		return tESTMODE;
	}

	@JsonProperty("TEST_MODE")
	public void setTESTMODE(boolean tESTMODE) {
		this.tESTMODE = tESTMODE;
	}

	@JsonProperty("SEND_DATE")
	public String getSENDDATE() {
		return sENDDATE;
	}

	@JsonProperty("SEND_DATE")
	public void setSENDDATE(String sENDDATE) {
		this.sENDDATE = sENDDATE;
	}

	@JsonProperty("TIME_ZONE")
	public String getTIMEZONE() {
		return tIMEZONE;
	}

	@JsonProperty("TIME_ZONE")
	public void setTIMEZONE(String tIMEZONE) {
		this.tIMEZONE = tIMEZONE;
	}

	@JsonProperty("LAST_SENT")
	public String getLASTSENT() {
		return lASTSENT;
	}

	@JsonProperty("LAST_SENT")
	public void setLASTSENT(String lASTSENT) {
		this.lASTSENT = lASTSENT;
	}

	@JsonProperty("REPEAT")
	public boolean isREPEAT() {
		return rEPEAT;
	}

	@JsonProperty("REPEAT")
	public void setREPEAT(boolean rEPEAT) {
		this.rEPEAT = rEPEAT;
	}

	@JsonProperty("REPEAT_COUNT")
	public int getREPEATCOUNT() {
		return rEPEATCOUNT;
	}

	@JsonProperty("REPEAT_COUNT")
	public void setREPEATCOUNT(int rEPEATCOUNT) {
		this.rEPEATCOUNT = rEPEATCOUNT;
	}

	@JsonProperty("PERIOD")
	public String getPERIOD() {
		return pERIOD;
	}

	@JsonProperty("PERIOD")
	public void setPERIOD(String pERIOD) {
		this.pERIOD = pERIOD;
	}

	@JsonProperty("DAYS")
	public String getDAYS() {
		return dAYS;
	}

	@JsonProperty("DAYS")
	public void setDAYS(String dAYS) {
		this.dAYS = dAYS;
	}

	@JsonProperty("STATUS")
	public String getSTATUS() {
		return sTATUS;
	}

	@JsonProperty("STATUS")
	public void setSTATUS(String sTATUS) {
		this.sTATUS = sTATUS;
	}

	@JsonProperty("TYPE")
	public String getTYPE() {
		return tYPE;
	}

	@JsonProperty("TYPE")
	public void setTYPE(String tYPE) {
		this.tYPE = tYPE;
	}

	@JsonProperty("USER_LIST")
	public List<Integer> getUSERLIST() {
		return uSERLIST;
	}

	@JsonProperty("USER_LIST")
	public void setUSERLIST(List<Integer> uSERLIST) {
		this.uSERLIST = uSERLIST;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
