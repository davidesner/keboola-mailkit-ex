
package keboola.mailkit.extractor.mailkitapi.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "ID_MESSAGE",
    "CLICK_COUNT",
    "NAME",
    "SEND_COUNT",
    "READ_COUNT_U",
    "READ_COUNT",
    "CLICK_RATE",
    "DELIVERED_COUNT",
    "CLICK_COUNT_U"
})
public class ReportResp {

    @JsonProperty("ID_MESSAGE")
    private Integer ID_MESSAGE;
    @JsonProperty("CLICK_COUNT")
    private String CLICK_COUNT;
    @JsonProperty("NAME")
    private String NAME;
    @JsonProperty("SEND_COUNT")
    private String SEND_COUNT;
    @JsonProperty("READ_COUNT_U")
    private String READ_COUNT_U;
    @JsonProperty("READ_COUNT")
    private String READ_COUNT;
    @JsonProperty("CLICK_RATE")
    private String CLICK_RATE;
    @JsonProperty("DELIVERED_COUNT")
    private Integer DELIVERED_COUNT;
    @JsonProperty("CLICK_COUNT_U")
    private Integer CLICK_COUNT_U;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ReportResp() {
    }

    /**
     * 
     * @param rEADCOUNT
     * @param rEADCOUNTU
     * @param sENDCOUNT
     * @param cLICKCOUNTU
     * @param iDMESSAGE
     * @param nAME
     * @param dELIVEREDCOUNT
     * @param cLICKRATE
     * @param cLICKCOUNT
     */
    public ReportResp(Integer iDMESSAGE, String cLICKCOUNT, String nAME, String sENDCOUNT, String rEADCOUNTU, String rEADCOUNT, String cLICKRATE, Integer dELIVEREDCOUNT, Integer cLICKCOUNTU) {
        super();
        this.ID_MESSAGE = iDMESSAGE;
        this.CLICK_COUNT = cLICKCOUNT;
        this.NAME = nAME;
        this.SEND_COUNT = sENDCOUNT;
        this.READ_COUNT_U = rEADCOUNTU;
        this.READ_COUNT = rEADCOUNT;
        this.CLICK_RATE = cLICKRATE;
        this.DELIVERED_COUNT = dELIVEREDCOUNT;
        this.CLICK_COUNT_U = cLICKCOUNTU;
    }

	public Integer getID_MESSAGE() {
		return ID_MESSAGE;
	}

	public String getCLICK_COUNT() {
		return CLICK_COUNT;
	}

	public String getNAME() {
		return NAME;
	}

	public String getSEND_COUNT() {
		return SEND_COUNT;
	}

	public String getREAD_COUNT_U() {
		return READ_COUNT_U;
	}

	public String getREAD_COUNT() {
		return READ_COUNT;
	}

	public String getCLICK_RATE() {
		return CLICK_RATE;
	}

	public Integer getDELIVERED_COUNT() {
		return DELIVERED_COUNT;
	}

	public Integer getCLICK_COUNT_U() {
		return CLICK_COUNT_U;
	}


}
