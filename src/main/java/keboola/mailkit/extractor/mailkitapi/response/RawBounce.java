package keboola.mailkit.extractor.mailkitapi.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"ID_MESSAGE",
"TEXT_STATUS",
"STATUS",
"ID_EMAIL",
"ID_SEND",
"EMAIL",
"ID_SEND_MESSGE",
"ID_USER_LIST",
"REMOTE_MTA",
"ID_UNDELIVERED_LOG"
})
public class RawBounce {

@JsonProperty("ID_MESSAGE")
private String ID_MESSAGE;
@JsonProperty("TEXT_STATUS")
private String TEXT_STATUS;
@JsonProperty("STATUS")
private String STATUS;
@JsonProperty("ID_EMAIL")
private String ID_EMAIL;
@JsonProperty("ID_SEND")
private String ID_SEND;
@JsonProperty("EMAIL")
private String EMAIL;
@JsonProperty("ID_SEND_MESSGE")
private String ID_SEND_MESSGE;
@JsonProperty("ID_USER_LIST")
private String ID_USER_LIST;
@JsonProperty("REMOTE_MTA")
private String REMOTE_MTA;
@JsonProperty("ID_UNDELIVERED_LOG")
private String ID_UNDELIVERED_LOG;

/**
* No args constructor for use in serialization
* 
*/
public RawBounce() {
}

/**
* 
* @param iD_EMAIL
* @param iD_SEND_MESSGE
* @param iD_USER_LIST
* @param sTATUS
* @param tEXT_STATUS
* @param iD_SEND
* @param rEMOTE_MTA
* @param eMAIL
* @param iD_MESSAGE
* @param iD_UNDELIVERED_LOG
*/
public RawBounce(String iD_MESSAGE, String tEXT_STATUS, String sTATUS, String iD_EMAIL, String iD_SEND, String eMAIL, String iD_SEND_MESSGE, String iD_USER_LIST, String rEMOTE_MTA, String iD_UNDELIVERED_LOG) {
super();
this.ID_MESSAGE = iD_MESSAGE;
this.TEXT_STATUS = tEXT_STATUS;
this.STATUS = sTATUS;
this.ID_EMAIL = iD_EMAIL;
this.ID_SEND = iD_SEND;
this.EMAIL = eMAIL;
this.ID_SEND_MESSGE = iD_SEND_MESSGE;
this.ID_USER_LIST = iD_USER_LIST;
this.REMOTE_MTA = rEMOTE_MTA;
this.ID_UNDELIVERED_LOG = iD_UNDELIVERED_LOG;
}

@JsonProperty("ID_MESSAGE")
public String getID_MESSAGE() {
return ID_MESSAGE;
}

@JsonProperty("ID_MESSAGE")
public void setID_MESSAGE(String iD_MESSAGE) {
this.ID_MESSAGE = iD_MESSAGE;
}

@JsonProperty("TEXT_STATUS")
public String getTEXT_STATUS() {
return TEXT_STATUS;
}

@JsonProperty("TEXT_STATUS")
public void setTEXT_STATUS(String tEXT_STATUS) {
this.TEXT_STATUS = tEXT_STATUS;
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

@JsonProperty("ID_SEND")
public String getID_SEND() {
return ID_SEND;
}

@JsonProperty("ID_SEND")
public void setID_SEND(String iD_SEND) {
this.ID_SEND = iD_SEND;
}

@JsonProperty("EMAIL")
public String getEMAIL() {
return EMAIL;
}

@JsonProperty("EMAIL")
public void setEMAIL(String eMAIL) {
this.EMAIL = eMAIL;
}

@JsonProperty("ID_SEND_MESSGE")
public String getID_SEND_MESSGE() {
return ID_SEND_MESSGE;
}

@JsonProperty("ID_SEND_MESSGE")
public void setID_SEND_MESSGE(String iD_SEND_MESSGE) {
this.ID_SEND_MESSGE = iD_SEND_MESSGE;
}

@JsonProperty("ID_USER_LIST")
public String getID_USER_LIST() {
return ID_USER_LIST;
}

@JsonProperty("ID_USER_LIST")
public void setID_USER_LIST(String iD_USER_LIST) {
this.ID_USER_LIST = iD_USER_LIST;
}

@JsonProperty("REMOTE_MTA")
public String getREMOTE_MTA() {
return REMOTE_MTA;
}

@JsonProperty("REMOTE_MTA")
public void setREMOTE_MTA(String rEMOTE_MTA) {
this.REMOTE_MTA = rEMOTE_MTA;
}

@JsonProperty("ID_UNDELIVERED_LOG")
public String getID_UNDELIVERED_LOG() {
return ID_UNDELIVERED_LOG;
}

@JsonProperty("ID_UNDELIVERED_LOG")
public void setID_UNDELIVERED_LOG(String iD_UNDELIVERED_LOG) {
this.ID_UNDELIVERED_LOG = iD_UNDELIVERED_LOG;
}

}