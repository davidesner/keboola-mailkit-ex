
package keboola.mailkit.extractor.mailkitapi.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "COUNTRY",
    "REGION_CODE",
    "USER_AGENT",
    "CITY",
    "ORG",
    "BROWSER_MINOR",
    "CODE",
    "DEVICE",
    "CONTINENT",
    "BROWSER_MAJOR",
    "REFERER",
    "BROWSER",
    "OS",
    "IP",
    "IP_ORIG",
    "DATETIME",
    "REGION",
    "OS_VERSION",
    "BROWSER_TYPE"
})
public class MsgLinkVisitorData {

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

    /**
     * No args constructor for use in serialization
     * 
     */
    public MsgLinkVisitorData() {
    }

    /**
     * 
     * @param cONTINENT
     * @param oS
     * @param oRG
     * @param bROWSER
     * @param rEGION
     * @param cOUNTRY
     * @param dATETIME
     * @param uSERAGENT
     * @param rEFERER
     * @param iP
     * @param cITY
     * @param dEVICE
     * @param rEGIONCODE
     * @param bROWSERMINOR
     * @param cODE
     * @param iPORIG
     * @param oSVERSION
     * @param bROWSERTYPE
     * @param bROWSERMAJOR
     */
    public MsgLinkVisitorData(String cOUNTRY, String rEGIONCODE, String uSERAGENT, String cITY, String oRG, String bROWSERMINOR, String cODE, String dEVICE, String cONTINENT, String bROWSERMAJOR, String rEFERER, String bROWSER, String oS, String iP, String iPORIG, String dATETIME, String rEGION, String oSVERSION, String bROWSERTYPE) {
        super();
        this.cOUNTRY = cOUNTRY;
        this.rEGIONCODE = rEGIONCODE;
        this.uSERAGENT = uSERAGENT;
        this.cITY = cITY;
        this.oRG = oRG;
        this.bROWSERMINOR = bROWSERMINOR;
        this.cODE = cODE;
        this.dEVICE = dEVICE;
        this.cONTINENT = cONTINENT;
        this.bROWSERMAJOR = bROWSERMAJOR;
        this.rEFERER = rEFERER;
        this.bROWSER = bROWSER;
        this.oS = oS;
        this.iP = iP;
        this.iPORIG = iPORIG;
        this.dATETIME = dATETIME;
        this.rEGION = rEGION;
        this.oSVERSION = oSVERSION;
        this.bROWSERTYPE = bROWSERTYPE;
    }

    @JsonProperty("COUNTRY")
    public String getCOUNTRY() {
        return cOUNTRY;
    }

    @JsonProperty("COUNTRY")
    public void setCOUNTRY(String cOUNTRY) {
        this.cOUNTRY = cOUNTRY;
    }

    @JsonProperty("REGION_CODE")
    public String getREGIONCODE() {
        return rEGIONCODE;
    }

    @JsonProperty("REGION_CODE")
    public void setREGIONCODE(String rEGIONCODE) {
        this.rEGIONCODE = rEGIONCODE;
    }

    @JsonProperty("USER_AGENT")
    public String getUSERAGENT() {
        return uSERAGENT;
    }

    @JsonProperty("USER_AGENT")
    public void setUSERAGENT(String uSERAGENT) {
        this.uSERAGENT = uSERAGENT;
    }

    @JsonProperty("CITY")
    public String getCITY() {
        return cITY;
    }

    @JsonProperty("CITY")
    public void setCITY(String cITY) {
        this.cITY = cITY;
    }

    @JsonProperty("ORG")
    public String getORG() {
        return oRG;
    }

    @JsonProperty("ORG")
    public void setORG(String oRG) {
        this.oRG = oRG;
    }

    @JsonProperty("BROWSER_MINOR")
    public String getBROWSERMINOR() {
        return bROWSERMINOR;
    }

    @JsonProperty("BROWSER_MINOR")
    public void setBROWSERMINOR(String bROWSERMINOR) {
        this.bROWSERMINOR = bROWSERMINOR;
    }

    @JsonProperty("CODE")
    public String getCODE() {
        return cODE;
    }

    @JsonProperty("CODE")
    public void setCODE(String cODE) {
        this.cODE = cODE;
    }

    @JsonProperty("DEVICE")
    public String getDEVICE() {
        return dEVICE;
    }

    @JsonProperty("DEVICE")
    public void setDEVICE(String dEVICE) {
        this.dEVICE = dEVICE;
    }

    @JsonProperty("CONTINENT")
    public String getCONTINENT() {
        return cONTINENT;
    }

    @JsonProperty("CONTINENT")
    public void setCONTINENT(String cONTINENT) {
        this.cONTINENT = cONTINENT;
    }

    @JsonProperty("BROWSER_MAJOR")
    public String getBROWSERMAJOR() {
        return bROWSERMAJOR;
    }

    @JsonProperty("BROWSER_MAJOR")
    public void setBROWSERMAJOR(String bROWSERMAJOR) {
        this.bROWSERMAJOR = bROWSERMAJOR;
    }

    @JsonProperty("REFERER")
    public String getREFERER() {
        return rEFERER;
    }

    @JsonProperty("REFERER")
    public void setREFERER(String rEFERER) {
        this.rEFERER = rEFERER;
    }

    @JsonProperty("BROWSER")
    public String getBROWSER() {
        return bROWSER;
    }

    @JsonProperty("BROWSER")
    public void setBROWSER(String bROWSER) {
        this.bROWSER = bROWSER;
    }

    @JsonProperty("OS")
    public String getOS() {
        return oS;
    }

    @JsonProperty("OS")
    public void setOS(String oS) {
        this.oS = oS;
    }

    @JsonProperty("IP")
    public String getIP() {
        return iP;
    }

    @JsonProperty("IP")
    public void setIP(String iP) {
        this.iP = iP;
    }

    @JsonProperty("IP_ORIG")
    public String getIPORIG() {
        return iPORIG;
    }

    @JsonProperty("IP_ORIG")
    public void setIPORIG(String iPORIG) {
        this.iPORIG = iPORIG;
    }

    @JsonProperty("DATETIME")
    public String getDATETIME() {
        return dATETIME;
    }

    @JsonProperty("DATETIME")
    public void setDATETIME(String dATETIME) {
        this.dATETIME = dATETIME;
    }

    @JsonProperty("REGION")
    public String getREGION() {
        return rEGION;
    }

    @JsonProperty("REGION")
    public void setREGION(String rEGION) {
        this.rEGION = rEGION;
    }

    @JsonProperty("OS_VERSION")
    public String getOSVERSION() {
        return oSVERSION;
    }

    @JsonProperty("OS_VERSION")
    public void setOSVERSION(String oSVERSION) {
        this.oSVERSION = oSVERSION;
    }

    @JsonProperty("BROWSER_TYPE")
    public String getBROWSERTYPE() {
        return bROWSERTYPE;
    }

    @JsonProperty("BROWSER_TYPE")
    public void setBROWSERTYPE(String bROWSERTYPE) {
        this.bROWSERTYPE = bROWSERTYPE;
    }

}
