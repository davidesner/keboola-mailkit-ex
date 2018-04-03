
package keboola.mailkit.extractor.mailkitapi.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "VISIT",
    "OPEN",
    "UNSUBSCRIBE",
    "OPEN_P",
    "DATE",
    "CLICK",
    "SPAM_REPORT",
    "VISIT_P",
    "READ"
})
public class MsgFeedbackResp {

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
    public MsgFeedbackResp() {
    }

    /**
     * 
     * @param oPEN
     * @param sPAMREPORT
     * @param oPENP
     * @param vISITP
     * @param rEAD
     * @param cLICK
     * @param vISIT
     * @param uNSUBSCRIBE
     * @param dATE
     */
    public MsgFeedbackResp(Integer vISIT, String oPEN, Integer uNSUBSCRIBE, String oPENP, String dATE, Integer cLICK, Integer sPAMREPORT, Integer vISITP, String rEAD) {
        super();
        this.VISIT = vISIT;
        this.OPEN = oPEN;
        this.UNSUBSCRIBE = uNSUBSCRIBE;
        this.OPEN_P = oPENP;
        this.DATE = dATE;
        this.CLICK = cLICK;
        this.SPAM_REPORT = sPAMREPORT;
        this.VISIT_P = vISITP;
        this.READ = rEAD;
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

   
}
