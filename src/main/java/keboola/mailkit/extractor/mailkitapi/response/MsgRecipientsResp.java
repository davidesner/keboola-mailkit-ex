
package keboola.mailkit.extractor.mailkitapi.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "DATE",
    "READ_COUNT",
    "STATUS",
    "ID_EMAIL",
    "EMAIL",
    "CLICK_COUNT",
    "ID_SEND_MESSAGE",
    "AB_VERSION"
})
public class MsgRecipientsResp {

@JsonProperty("DATE")
private String DATE;
@JsonProperty("READ_COUNT")
private String READ_COUNT;
@JsonProperty("STATUS")
private String STATUS;
@JsonProperty("ID_EMAIL")
private String ID_EMAIL;
@JsonProperty("EMAIL")
private String EMAIL;
@JsonProperty("CLICK_COUNT")
private String CLICK_COUNT;
@JsonProperty("ID_SEND_MESSAGE")
private String ID_SEND_MESSAGE;
@JsonProperty("AB_VERSION")
private String AB_VERSION;

/**
* No args constructor for use in serialization
* 
*/
public MsgRecipientsResp() {
}

/**
* 
* @param iD_EMAIL
* @param rEAD_COUNT
* @param sTATUS
* @param iD_SEND_MESSAGE
* @param eMAIL
* @param cLICK_COUNT
* @param aB_VERSION
* @param dATE
*/
public MsgRecipientsResp(String dATE, String rEAD_COUNT, String sTATUS, String iD_EMAIL, String eMAIL, String cLICK_COUNT, String iD_SEND_MESSAGE, String aB_VERSION) {
super();
this.DATE = dATE;
this.READ_COUNT = rEAD_COUNT;
this.STATUS = sTATUS;
this.ID_EMAIL = iD_EMAIL;
this.EMAIL = eMAIL;
this.CLICK_COUNT = cLICK_COUNT;
this.ID_SEND_MESSAGE = iD_SEND_MESSAGE;
this.AB_VERSION = aB_VERSION;
}

@JsonProperty("DATE")
public String getDATE() {
return DATE;
}

@JsonProperty("DATE")
public void setDATE(String dATE) {
this.DATE = dATE;
}

@JsonProperty("READ_COUNT")
public String getREAD_COUNT() {
return READ_COUNT;
}

@JsonProperty("READ_COUNT")
public void setREAD_COUNT(String rEAD_COUNT) {
this.READ_COUNT = rEAD_COUNT;
}

@JsonProperty("STATUS")
public String getSTATUS() {
return STATUS;
}

@JsonProperty("STATUS")
public void setSTATUS(String sTATUS) {
this.STATUS = sTATUS;
}

@JsonProperty("ID_EMAIL")
public String getID_EMAIL() {
return ID_EMAIL;
}

@JsonProperty("ID_EMAIL")
public void setID_EMAIL(String iD_EMAIL) {
this.ID_EMAIL = iD_EMAIL;
}

@JsonProperty("EMAIL")
public String getEMAIL() {
return EMAIL;
}

@JsonProperty("EMAIL")
public void setEMAIL(String eMAIL) {
this.EMAIL = eMAIL;
}

@JsonProperty("CLICK_COUNT")
public String getCLICK_COUNT() {
return CLICK_COUNT;
}

@JsonProperty("CLICK_COUNT")
public void setCLICK_COUNT(String cLICK_COUNT) {
this.CLICK_COUNT = cLICK_COUNT;
}

@JsonProperty("ID_SEND_MESSAGE")
public String getID_SEND_MESSAGE() {
return ID_SEND_MESSAGE;
}

@JsonProperty("ID_SEND_MESSAGE")
public void setID_SEND_MESSAGE(String iD_SEND_MESSAGE) {
this.ID_SEND_MESSAGE = iD_SEND_MESSAGE;
}

@JsonProperty("AB_VERSION")
public String getAB_VERSION() {
return AB_VERSION;
}

@JsonProperty("AB_VERSION")
public void setAB_VERSION(String aB_VERSION) {
this.AB_VERSION = aB_VERSION;
}


}
