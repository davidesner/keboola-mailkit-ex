
package keboola.mailkit.extractor.mailkitapi.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "SEND_COUNT", "SUBJECT", "FAILED_COUNT", "READ_COUNT", "CLICK_COUNT",
		"MINIPAGE_CLICK_COUNT", "CONVERSION_COUNT_U", "CONVERSION_COUNT", "DELIVERED_COUNT",
		"READ_COUNT_U", "SEND_DATE", "CLICK_COUNT_U" })
public class ReportMsgResp {

	@JsonProperty("SEND_COUNT")
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
	@JsonProperty("UTM")
	private String UTM;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public ReportMsgResp() {
	}

	/**
	 * 
	 * @param rEADCOUNTU
	 * @param rEADCOUNT
	 * @param sENDCOUNT
	 * @param sUBJECT
	 * @param cLICKCOUNTU
	 * @param fAILEDCOUNT
	 * @param dELIVEREDCOUNT
	 * @param cLICKCOUNT
	 * @param mINIPAGECLICKCOUNT
	 * @param sENDDATE
	 * @param cONVERSIONCOUNTU
	 * @param cONVERSIONCOUNT
	 * @param UTM
	 */
	public ReportMsgResp(String sENDCOUNT, String sUBJECT, String fAILEDCOUNT, String rEADCOUNT,
			String cLICKCOUNT, String mINIPAGECLICKCOUNT, String cONVERSIONCOUNTU,
			String cONVERSIONCOUNT, String dELIVEREDCOUNT, String rEADCOUNTU, String sENDDATE,
			String cLICKCOUNTU, String utm) {
		super();
		this.SEND_COUNT = sENDCOUNT;
		this.SUBJECT = sUBJECT;
		this.FAILED_COUNT = fAILEDCOUNT;
		this.READ_COUNT = rEADCOUNT;
		this.CLICK_COUNT = cLICKCOUNT;
		this.MINIPAGE_CLICK_COUNT = mINIPAGECLICKCOUNT;
		this.CONVERSION_COUNT_U = cONVERSIONCOUNTU;
		this.CONVERSION_COUNT = cONVERSIONCOUNT;
		this.DELIVERED_COUNT = dELIVEREDCOUNT;
		this.READ_COUNT_U = rEADCOUNTU;
		this.SEND_DATE = sENDDATE;
		this.CLICK_COUNT_U = cLICKCOUNTU;
		this.UTM = utm;
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

	public String getUTM() {
		return UTM;
	}

}
