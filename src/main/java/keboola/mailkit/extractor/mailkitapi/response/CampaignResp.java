package keboola.mailkit.extractor.mailkitapi.response;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "NAME", "ID_MESSAGE", "SUBJECT", "SUBJECT_B", "MSG_TYPE", "CAMPAIGN_TYPE",
		"USE_AB", "TEST_MODE", "SEND_DATE", "TIME_ZONE", "LAST_SENT", "REPEAT", "REPEAT_COUNT",
		"PERIOD", "DAYS", "STATUS", "TYPE", "USER_LIST" })
public class CampaignResp {

	@JsonProperty("NAME")
	private String NAME;
	@JsonProperty("ID_MESSAGE")
	private int ID_MESSAGE;
	@JsonProperty("SUBJECT")
	private String SUBJECT;
	@JsonProperty("SUBJECT_B")
	private String SUBJECT_B;
	@JsonProperty("MSG_TYPE")
	private String MSG_TYPE;
	@JsonProperty("CAMPAIGN_TYPE")
	private String CAMPAIGN_TYPE;
	@JsonProperty("USE_AB")
	private boolean USE_AB;
	@JsonProperty("TEST_MODE")
	private boolean TEST_MODE;
	@JsonProperty("SEND_DATE")
	private String SEND_DATE;
	@JsonProperty("TIME_ZONE")
	private String TIME_ZONE;
	@JsonProperty("LAST_SENT")
	private String LAST_SENT;
	@JsonProperty("REPEAT")
	private boolean REPEAT;
	@JsonProperty("REPEAT_COUNT")
	private int REPEAT_COUNT;
	@JsonProperty("PERIOD")
	private String PERIOD;
	@JsonProperty("DAYS")
	private String DAYS;
	@JsonProperty("STATUS")
	private String STATUS;
	@JsonProperty("TYPE")
	private String TYPE;
	@JsonProperty("USER_LIST")
	private List<Integer> USER_LIST = null;

	/**
	 * No args constructor for use in serialization
	 *
	 */
	public CampaignResp() {
	}

	/**
	 *
	 * @param SUBJECT_B
	 * @param USE_AB
	 * @param REPEAT
	 * @param TIME_ZONE
	 * @param REPEAT_COUNT
	 * @param LAST_SENT
	 * @param STATUS
	 * @param TYPE
	 * @param ID_MESSAGE
	 * @param PERIOD
	 * @param USER_LIST
	 * @param SUBJECT
	 * @param MSG_TYPE
	 * @param NAME
	 * @param CAMPAIGN_TYPE
	 * @param DAYS
	 * @param SEND_DATE
	 * @param TEST_MODE
	 */
	public CampaignResp(String nAME, int iD_MESSAGE, String sUBJECT, String sUBJECT_B,
			String mSG_TYPE, String cAMPAIGN_TYPE, boolean uSE_AB, boolean tEST_MODE,
			String sEND_DATE, String tIME_ZONE, String lAST_SENT, boolean rEPEAT, int rEPEAT_COUNT,
			String pERIOD, String dAYS, String sTATUS, String tYPE, List<Integer> uSER_LIST) {
		super();
		this.NAME = nAME;
		this.ID_MESSAGE = iD_MESSAGE;
		this.SUBJECT = sUBJECT;
		this.SUBJECT_B = sUBJECT_B;
		this.MSG_TYPE = mSG_TYPE;
		this.CAMPAIGN_TYPE = cAMPAIGN_TYPE;
		this.USE_AB = uSE_AB;
		this.TEST_MODE = tEST_MODE;
		this.SEND_DATE = sEND_DATE;
		this.TIME_ZONE = tIME_ZONE;
		this.LAST_SENT = lAST_SENT;
		this.REPEAT = rEPEAT;
		this.REPEAT_COUNT = rEPEAT_COUNT;
		this.PERIOD = pERIOD;
		this.DAYS = dAYS;
		this.STATUS = sTATUS;
		this.TYPE = tYPE;
		this.USER_LIST = uSER_LIST;
	}

	@JsonProperty("NAME")
	public String getNAME() {
		return NAME;
	}

	@JsonProperty("NAME")
	public void setNAME(String nAME) {
		this.NAME = nAME;
	}

	@JsonProperty("ID_MESSAGE")
	public int getID_MESSAGE() {
		return ID_MESSAGE;
	}

	@JsonProperty("ID_MESSAGE")
	public void setID_MESSAGE(int iD_MESSAGE) {
		this.ID_MESSAGE = iD_MESSAGE;
	}

	@JsonProperty("SUBJECT")
	public String getSUBJECT() {
		return SUBJECT;
	}

	@JsonProperty("SUBJECT")
	public void setSUBJECT(String sUBJECT) {
		this.SUBJECT = sUBJECT;
	}

	@JsonProperty("SUBJECT_B")
	public String getSUBJECT_B() {
		return SUBJECT_B;
	}

	@JsonProperty("SUBJECT_B")
	public void setSUBJECT_B(String sUBJECT_B) {
		this.SUBJECT_B = sUBJECT_B;
	}

	@JsonProperty("MSG_TYPE")
	public String getMSG_TYPE() {
		return MSG_TYPE;
	}

	@JsonProperty("MSG_TYPE")
	public void setMSG_TYPE(String mSG_TYPE) {
		this.MSG_TYPE = mSG_TYPE;
	}

	@JsonProperty("CAMPAIGN_TYPE")
	public String getCAMPAIGN_TYPE() {
		return CAMPAIGN_TYPE;
	}

	@JsonProperty("CAMPAIGN_TYPE")
	public void setCAMPAIGN_TYPE(String cAMPAIGN_TYPE) {
		this.CAMPAIGN_TYPE = cAMPAIGN_TYPE;
	}

	@JsonProperty("USE_AB")
	public boolean isUSE_AB() {
		return USE_AB;
	}

	@JsonProperty("USE_AB")
	public void setUSE_AB(boolean uSE_AB) {
		this.USE_AB = uSE_AB;
	}

	@JsonProperty("TEST_MODE")
	public boolean isTEST_MODE() {
		return TEST_MODE;
	}

	@JsonProperty("TEST_MODE")
	public void setTEST_MODE(boolean tEST_MODE) {
		this.TEST_MODE = tEST_MODE;
	}

	@JsonProperty("SEND_DATE")
	public String getSEND_DATE() {
		return SEND_DATE;
	}

	@JsonProperty("SEND_DATE")
	public void setSEND_DATE(String sEND_DATE) {
		this.SEND_DATE = sEND_DATE;
	}

	@JsonProperty("TIME_ZONE")
	public String getTIME_ZONE() {
		return TIME_ZONE;
	}

	@JsonProperty("TIME_ZONE")
	public void setTIME_ZONE(String tIME_ZONE) {
		this.TIME_ZONE = tIME_ZONE;
	}

	@JsonProperty("LAST_SENT")
	public String getLAST_SENT() {
		return LAST_SENT;
	}

	@JsonProperty("LAST_SENT")
	public void setLAST_SENT(String lAST_SENT) {
		this.LAST_SENT = lAST_SENT;
	}

	@JsonProperty("REPEAT")
	public boolean isREPEAT() {
		return REPEAT;
	}

	@JsonProperty("REPEAT")
	public void setREPEAT(boolean rEPEAT) {
		this.REPEAT = rEPEAT;
	}

	@JsonProperty("REPEAT_COUNT")
	public int getREPEAT_COUNT() {
		return REPEAT_COUNT;
	}

	@JsonProperty("REPEAT_COUNT")
	public void setREPEAT_COUNT(int rEPEAT_COUNT) {
		this.REPEAT_COUNT = rEPEAT_COUNT;
	}

	@JsonProperty("PERIOD")
	public String getPERIOD() {
		return PERIOD;
	}

	@JsonProperty("PERIOD")
	public void setPERIOD(String pERIOD) {
		this.PERIOD = pERIOD;
	}

	@JsonProperty("DAYS")
	public String getDAYS() {
		return DAYS;
	}

	@JsonProperty("DAYS")
	public void setDAYS(String dAYS) {
		this.DAYS = dAYS;
	}

	@JsonProperty("STATUS")
	public String getSTATUS() {
		return STATUS;
	}

	@JsonProperty("STATUS")
	public void setSTATUS(String sTATUS) {
		this.STATUS = sTATUS;
	}

	@JsonProperty("TYPE")
	public String getTYPE() {
		return TYPE;
	}

	@JsonProperty("TYPE")
	public void setTYPE(String tYPE) {
		this.TYPE = tYPE;
	}

	@JsonProperty("USER_LIST")
	public String getUSER_LIST() {
		if (this.USER_LIST != null) {
			return USER_LIST.stream().map(String::valueOf).collect(Collectors.joining(","));
		}
		return "";
	}

	@JsonProperty("USER_LIST")
	public void setUSER_LIST(List<Integer> uSER_LIST) {
		this.USER_LIST = uSER_LIST;
	}

}