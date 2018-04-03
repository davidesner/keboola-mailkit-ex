
package keboola.mailkit.extractor.mailkitapi.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class RawMessage {

@JsonProperty("ID_message")
private String ID_message;
@JsonProperty("ID_send")
private String ID_send;
@JsonProperty("ID_send_message")
private String ID_send_message;
@JsonProperty("ID_user_list")
private String ID_user_list;
@JsonProperty("ID_email")
private String ID_email;
@JsonProperty("ID_template")
private String ID_template;
@JsonProperty("type_message")
private String type_message;
@JsonProperty("email")
private String email;
@JsonProperty("email_date")
private String email_date;
@JsonProperty("message_date")
private String message_date;
@JsonProperty("date_sent")
private String date_sent;
@JsonProperty("ab_version")
private String ab_version;
@JsonProperty("message_status")
private String message_status;
@JsonProperty("tld")
private String tld;
@JsonProperty("domain")
private String domain;
@JsonProperty("custom_1")
private String custom_1;
@JsonProperty("custom_2")
private String custom_2;
@JsonProperty("custom_3")
private String custom_3;
@JsonProperty("custom_4")
private String custom_4;
@JsonProperty("custom_5")
private String custom_5;
@JsonProperty("custom_6")
private String custom_6;
@JsonProperty("custom_7")
private String custom_7;
@JsonProperty("custom_8")
private String custom_8;
@JsonProperty("custom_9")
private String custom_9;
@JsonProperty("custom_10")
private String custom_10;
@JsonProperty("custom_11")
private String custom_11;
@JsonProperty("custom_12")
private String custom_12;
@JsonProperty("custom_13")
private String custom_13;
@JsonProperty("custom_14")
private String custom_14;
@JsonProperty("custom_15")
private String custom_15;
@JsonProperty("custom_16")
private String custom_16;
@JsonProperty("custom_17")
private String custom_17;
@JsonProperty("custom_18")
private String custom_18;
@JsonProperty("custom_19")
private String custom_19;
@JsonProperty("custom_20")
private String custom_20;
@JsonProperty("custom_21")
private String custom_21;
@JsonProperty("custom_22")
private String custom_22;
@JsonProperty("custom_23")
private String custom_23;
@JsonProperty("custom_24")
private String custom_24;
@JsonProperty("custom_25")
private String custom_25;
@JsonProperty("prefix")
private String prefix;
@JsonProperty("vocative")
private String vocative;
@JsonProperty("first_name")
private String first_name;
@JsonProperty("last_name")
private String last_name;
@JsonProperty("full_name")
private String full_name;
@JsonProperty("nick_name")
private String nick_name;
@JsonProperty("gender")
private String gender;
@JsonProperty("reply_to")
private String reply_to;
@JsonProperty("company")
private String company;
@JsonProperty("street")
private String street;
@JsonProperty("city")
private String city;
@JsonProperty("state")
private String state;
@JsonProperty("country")
private String country;
@JsonProperty("zip")
private String zip;
@JsonProperty("phone")
private String phone;
@JsonProperty("mobile")
private String mobile;
@JsonProperty("fax")
private String fax;
@JsonProperty("s_continent")
private String s_continent;
@JsonProperty("s_country")
private String s_country;
@JsonProperty("s_region")
private String s_region;
@JsonProperty("s_city")
private String s_city;
@JsonProperty("s_org")
private String s_org;
@JsonProperty("s_browser")
private String s_browser;
@JsonProperty("s_os")
private String s_os;

/**
* No args constructor for use in serialization
* 
*/
public RawMessage() {
}

/**
* 
* @param phone
* @param reply_to
* @param message_status
* @param street
* @param iD_send
* @param s_country
* @param city
* @param nick_name
* @param iD_send_message
* @param first_name
* @param iD_message
* @param gender
* @param custom_1
* @param message_date
* @param fax
* @param custom_5
* @param custom_4
* @param custom_3
* @param custom_2
* @param email_date
* @param custom_9
* @param custom_8
* @param custom_7
* @param custom_6
* @param custom_21
* @param custom_20
* @param custom_23
* @param country
* @param custom_22
* @param custom_24
* @param custom_25
* @param email
* @param company
* @param prefix
* @param last_name
* @param s_os
* @param s_org
* @param mobile
* @param state
* @param type_message
* @param custom_12
* @param custom_11
* @param s_browser
* @param custom_10
* @param custom_19
* @param custom_17
* @param custom_18
* @param custom_15
* @param custom_16
* @param vocative
* @param custom_13
* @param custom_14
* @param domain
* @param iD_template
* @param s_city
* @param s_region
* @param zip
* @param iD_email
* @param date_sent
* @param ab_version
* @param s_continent
* @param iD_user_list
* @param tld
* @param full_name
*/
public RawMessage(String iD_message, String iD_send, String iD_send_message, String iD_user_list, String iD_email, String iD_template, String type_message, String email, String email_date, String message_date, String date_sent, String ab_version, String message_status, String tld, String domain, String custom_1, String custom_2, String custom_3, String custom_4, String custom_5, String custom_6, String custom_7, String custom_8, String custom_9, String custom_10, String custom_11, String custom_12, String custom_13, String custom_14, String custom_15, String custom_16, String custom_17, String custom_18, String custom_19, String custom_20, String custom_21, String custom_22, String custom_23, String custom_24, String custom_25, String prefix, String vocative, String first_name, String last_name, String full_name, String nick_name, String gender, String reply_to, String company, String street, String city, String state, String country, String zip, String phone, String mobile, String fax, String s_continent, String s_country, String s_region, String s_city, String s_org, String s_browser, String s_os) {
super();
this.ID_message = iD_message;
this.ID_send = iD_send;
this.ID_send_message = iD_send_message;
this.ID_user_list = iD_user_list;
this.ID_email = iD_email;
this.ID_template = iD_template;
this.type_message = type_message;
this.email = email;
this.email_date = email_date;
this.message_date = message_date;
this.date_sent = date_sent;
this.ab_version = ab_version;
this.message_status = message_status;
this.tld = tld;
this.domain = domain;
this.custom_1 = custom_1;
this.custom_2 = custom_2;
this.custom_3 = custom_3;
this.custom_4 = custom_4;
this.custom_5 = custom_5;
this.custom_6 = custom_6;
this.custom_7 = custom_7;
this.custom_8 = custom_8;
this.custom_9 = custom_9;
this.custom_10 = custom_10;
this.custom_11 = custom_11;
this.custom_12 = custom_12;
this.custom_13 = custom_13;
this.custom_14 = custom_14;
this.custom_15 = custom_15;
this.custom_16 = custom_16;
this.custom_17 = custom_17;
this.custom_18 = custom_18;
this.custom_19 = custom_19;
this.custom_20 = custom_20;
this.custom_21 = custom_21;
this.custom_22 = custom_22;
this.custom_23 = custom_23;
this.custom_24 = custom_24;
this.custom_25 = custom_25;
this.prefix = prefix;
this.vocative = vocative;
this.first_name = first_name;
this.last_name = last_name;
this.full_name = full_name;
this.nick_name = nick_name;
this.gender = gender;
this.reply_to = reply_to;
this.company = company;
this.street = street;
this.city = city;
this.state = state;
this.country = country;
this.zip = zip;
this.phone = phone;
this.mobile = mobile;
this.fax = fax;
this.s_continent = s_continent;
this.s_country = s_country;
this.s_region = s_region;
this.s_city = s_city;
this.s_org = s_org;
this.s_browser = s_browser;
this.s_os = s_os;
}

@JsonProperty("ID_message")
public String getID_message() {
return ID_message;
}

@JsonProperty("ID_message")
public void setID_message(String iD_message) {
this.ID_message = iD_message;
}

@JsonProperty("ID_send")
public String getID_send() {
return ID_send;
}

@JsonProperty("ID_send")
public void setID_send(String iD_send) {
this.ID_send = iD_send;
}

@JsonProperty("ID_send_message")
public String getID_send_message() {
return ID_send_message;
}

@JsonProperty("ID_send_message")
public void setID_send_message(String iD_send_message) {
this.ID_send_message = iD_send_message;
}

@JsonProperty("ID_user_list")
public String getID_user_list() {
return ID_user_list;
}

@JsonProperty("ID_user_list")
public void setID_user_list(String iD_user_list) {
this.ID_user_list = iD_user_list;
}

@JsonProperty("ID_email")
public String getID_email() {
return ID_email;
}

@JsonProperty("ID_email")
public void setID_email(String iD_email) {
this.ID_email = iD_email;
}

@JsonProperty("ID_template")
public String getID_template() {
return ID_template;
}

@JsonProperty("ID_template")
public void setID_template(String iD_template) {
this.ID_template = iD_template;
}

@JsonProperty("type_message")
public String getType_message() {
return type_message;
}

@JsonProperty("type_message")
public void setType_message(String type_message) {
this.type_message = type_message;
}

@JsonProperty("email")
public String getEmail() {
return email;
}

@JsonProperty("email")
public void setEmail(String email) {
this.email = email;
}

@JsonProperty("email_date")
public String getEmail_date() {
return email_date;
}

@JsonProperty("email_date")
public void setEmail_date(String email_date) {
this.email_date = email_date;
}

@JsonProperty("message_date")
public String getMessage_date() {
return message_date;
}

@JsonProperty("message_date")
public void setMessage_date(String message_date) {
this.message_date = message_date;
}

@JsonProperty("date_sent")
public String getDate_sent() {
return date_sent;
}

@JsonProperty("date_sent")
public void setDate_sent(String date_sent) {
this.date_sent = date_sent;
}

@JsonProperty("ab_version")
public String getAb_version() {
return ab_version;
}

@JsonProperty("ab_version")
public void setAb_version(String ab_version) {
this.ab_version = ab_version;
}

@JsonProperty("message_status")
public String getMessage_status() {
return message_status;
}

@JsonProperty("message_status")
public void setMessage_status(String message_status) {
this.message_status = message_status;
}

@JsonProperty("tld")
public String getTld() {
return tld;
}

@JsonProperty("tld")
public void setTld(String tld) {
this.tld = tld;
}

@JsonProperty("domain")
public String getDomain() {
return domain;
}

@JsonProperty("domain")
public void setDomain(String domain) {
this.domain = domain;
}

@JsonProperty("custom_1")
public String getCustom_1() {
return custom_1;
}

@JsonProperty("custom_1")
public void setCustom_1(String custom_1) {
this.custom_1 = custom_1;
}

@JsonProperty("custom_2")
public String getCustom_2() {
return custom_2;
}

@JsonProperty("custom_2")
public void setCustom_2(String custom_2) {
this.custom_2 = custom_2;
}

@JsonProperty("custom_3")
public String getCustom_3() {
return custom_3;
}

@JsonProperty("custom_3")
public void setCustom_3(String custom_3) {
this.custom_3 = custom_3;
}

@JsonProperty("custom_4")
public String getCustom_4() {
return custom_4;
}

@JsonProperty("custom_4")
public void setCustom_4(String custom_4) {
this.custom_4 = custom_4;
}

@JsonProperty("custom_5")
public String getCustom_5() {
return custom_5;
}

@JsonProperty("custom_5")
public void setCustom_5(String custom_5) {
this.custom_5 = custom_5;
}

@JsonProperty("custom_6")
public String getCustom_6() {
return custom_6;
}

@JsonProperty("custom_6")
public void setCustom_6(String custom_6) {
this.custom_6 = custom_6;
}

@JsonProperty("custom_7")
public String getCustom_7() {
return custom_7;
}

@JsonProperty("custom_7")
public void setCustom_7(String custom_7) {
this.custom_7 = custom_7;
}

@JsonProperty("custom_8")
public String getCustom_8() {
return custom_8;
}

@JsonProperty("custom_8")
public void setCustom_8(String custom_8) {
this.custom_8 = custom_8;
}

@JsonProperty("custom_9")
public String getCustom_9() {
return custom_9;
}

@JsonProperty("custom_9")
public void setCustom_9(String custom_9) {
this.custom_9 = custom_9;
}

@JsonProperty("custom_10")
public String getCustom_10() {
return custom_10;
}

@JsonProperty("custom_10")
public void setCustom_10(String custom_10) {
this.custom_10 = custom_10;
}

@JsonProperty("custom_11")
public String getCustom_11() {
return custom_11;
}

@JsonProperty("custom_11")
public void setCustom_11(String custom_11) {
this.custom_11 = custom_11;
}

@JsonProperty("custom_12")
public String getCustom_12() {
return custom_12;
}

@JsonProperty("custom_12")
public void setCustom_12(String custom_12) {
this.custom_12 = custom_12;
}

@JsonProperty("custom_13")
public String getCustom_13() {
return custom_13;
}

@JsonProperty("custom_13")
public void setCustom_13(String custom_13) {
this.custom_13 = custom_13;
}

@JsonProperty("custom_14")
public String getCustom_14() {
return custom_14;
}

@JsonProperty("custom_14")
public void setCustom_14(String custom_14) {
this.custom_14 = custom_14;
}

@JsonProperty("custom_15")
public String getCustom_15() {
return custom_15;
}

@JsonProperty("custom_15")
public void setCustom_15(String custom_15) {
this.custom_15 = custom_15;
}

@JsonProperty("custom_16")
public String getCustom_16() {
return custom_16;
}

@JsonProperty("custom_16")
public void setCustom_16(String custom_16) {
this.custom_16 = custom_16;
}

@JsonProperty("custom_17")
public String getCustom_17() {
return custom_17;
}

@JsonProperty("custom_17")
public void setCustom_17(String custom_17) {
this.custom_17 = custom_17;
}

@JsonProperty("custom_18")
public String getCustom_18() {
return custom_18;
}

@JsonProperty("custom_18")
public void setCustom_18(String custom_18) {
this.custom_18 = custom_18;
}

@JsonProperty("custom_19")
public String getCustom_19() {
return custom_19;
}

@JsonProperty("custom_19")
public void setCustom_19(String custom_19) {
this.custom_19 = custom_19;
}

@JsonProperty("custom_20")
public String getCustom_20() {
return custom_20;
}

@JsonProperty("custom_20")
public void setCustom_20(String custom_20) {
this.custom_20 = custom_20;
}

@JsonProperty("custom_21")
public String getCustom_21() {
return custom_21;
}

@JsonProperty("custom_21")
public void setCustom_21(String custom_21) {
this.custom_21 = custom_21;
}

@JsonProperty("custom_22")
public String getCustom_22() {
return custom_22;
}

@JsonProperty("custom_22")
public void setCustom_22(String custom_22) {
this.custom_22 = custom_22;
}

@JsonProperty("custom_23")
public String getCustom_23() {
return custom_23;
}

@JsonProperty("custom_23")
public void setCustom_23(String custom_23) {
this.custom_23 = custom_23;
}

@JsonProperty("custom_24")
public String getCustom_24() {
return custom_24;
}

@JsonProperty("custom_24")
public void setCustom_24(String custom_24) {
this.custom_24 = custom_24;
}

@JsonProperty("custom_25")
public String getCustom_25() {
return custom_25;
}

@JsonProperty("custom_25")
public void setCustom_25(String custom_25) {
this.custom_25 = custom_25;
}

@JsonProperty("prefix")
public String getPrefix() {
return prefix;
}

@JsonProperty("prefix")
public void setPrefix(String prefix) {
this.prefix = prefix;
}

@JsonProperty("vocative")
public String getVocative() {
return vocative;
}

@JsonProperty("vocative")
public void setVocative(String vocative) {
this.vocative = vocative;
}

@JsonProperty("first_name")
public String getFirst_name() {
return first_name;
}

@JsonProperty("first_name")
public void setFirst_name(String first_name) {
this.first_name = first_name;
}

@JsonProperty("last_name")
public String getLast_name() {
return last_name;
}

@JsonProperty("last_name")
public void setLast_name(String last_name) {
this.last_name = last_name;
}

@JsonProperty("full_name")
public String getFull_name() {
return full_name;
}

@JsonProperty("full_name")
public void setFull_name(String full_name) {
this.full_name = full_name;
}

@JsonProperty("nick_name")
public String getNick_name() {
return nick_name;
}

@JsonProperty("nick_name")
public void setNick_name(String nick_name) {
this.nick_name = nick_name;
}

@JsonProperty("gender")
public String getGender() {
return gender;
}

@JsonProperty("gender")
public void setGender(String gender) {
this.gender = gender;
}

@JsonProperty("reply_to")
public String getReply_to() {
return reply_to;
}

@JsonProperty("reply_to")
public void setReply_to(String reply_to) {
this.reply_to = reply_to;
}

@JsonProperty("company")
public String getCompany() {
return company;
}

@JsonProperty("company")
public void setCompany(String company) {
this.company = company;
}

@JsonProperty("street")
public String getStreet() {
return street;
}

@JsonProperty("street")
public void setStreet(String street) {
this.street = street;
}

@JsonProperty("city")
public String getCity() {
return city;
}

@JsonProperty("city")
public void setCity(String city) {
this.city = city;
}

@JsonProperty("state")
public String getState() {
return state;
}

@JsonProperty("state")
public void setState(String state) {
this.state = state;
}

@JsonProperty("country")
public String getCountry() {
return country;
}

@JsonProperty("country")
public void setCountry(String country) {
this.country = country;
}

@JsonProperty("zip")
public String getZip() {
return zip;
}

@JsonProperty("zip")
public void setZip(String zip) {
this.zip = zip;
}

@JsonProperty("phone")
public String getPhone() {
return phone;
}

@JsonProperty("phone")
public void setPhone(String phone) {
this.phone = phone;
}

@JsonProperty("mobile")
public String getMobile() {
return mobile;
}

@JsonProperty("mobile")
public void setMobile(String mobile) {
this.mobile = mobile;
}

@JsonProperty("fax")
public String getFax() {
return fax;
}

@JsonProperty("fax")
public void setFax(String fax) {
this.fax = fax;
}

@JsonProperty("s_continent")
public String getS_continent() {
return s_continent;
}

@JsonProperty("s_continent")
public void setS_continent(String s_continent) {
this.s_continent = s_continent;
}

@JsonProperty("s_country")
public String getS_country() {
return s_country;
}

@JsonProperty("s_country")
public void setS_country(String s_country) {
this.s_country = s_country;
}

@JsonProperty("s_region")
public String getS_region() {
return s_region;
}

@JsonProperty("s_region")
public void setS_region(String s_region) {
this.s_region = s_region;
}

@JsonProperty("s_city")
public String getS_city() {
return s_city;
}

@JsonProperty("s_city")
public void setS_city(String s_city) {
this.s_city = s_city;
}

@JsonProperty("s_org")
public String getS_org() {
return s_org;
}

@JsonProperty("s_org")
public void setS_org(String s_org) {
this.s_org = s_org;
}

@JsonProperty("s_browser")
public String getS_browser() {
return s_browser;
}

@JsonProperty("s_browser")
public void setS_browser(String s_browser) {
this.s_browser = s_browser;
}

@JsonProperty("s_os")
public String getS_os() {
return s_os;
}

@JsonProperty("s_os")
public void setS_os(String s_os) {
this.s_os = s_os;
}

}