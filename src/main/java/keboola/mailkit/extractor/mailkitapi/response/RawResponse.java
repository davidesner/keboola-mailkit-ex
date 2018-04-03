
package keboola.mailkit.extractor.mailkitapi.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "date",
    "email",
    "ID_email",
    "ID_user_list",
    "ID_message",
    "ID_send",
    "ID_send_message",
    "ID_log",
    "ab_version",
    "domain",
    "tld",
    "type",
    "ID_url",
    "link_type",
    "link_url",
    "link_text",
    "link_title",
    "referer",
    "unsubscribe_answer",
    "IP",
    "IP_orig",
    "continent",
    "country",
    "code3",
    "code",
    "region",
    "region_code",
    "city",
    "user_agent",
    "browser",
    "browser_major",
    "browser_minor",
    "browser_type",
    "os",
    "os_version",
    "device",
    "org"
})
public class RawResponse {

    @JsonProperty("date")
    private String date;
    @JsonProperty("email")
    private String email;
    @JsonProperty("ID_email")
    private String ID_email;
    @JsonProperty("ID_user_list")
    private String ID_user_list;
    @JsonProperty("ID_message")
    private String ID_message;
    @JsonProperty("ID_send")
    private String ID_send;
    @JsonProperty("ID_send_message")
    private String ID_send_message;
    @JsonProperty("ID_log")
    private String ID_log;
    @JsonProperty("ab_version")
    private String ab_version;
    @JsonProperty("domain")
    private String domain;
    @JsonProperty("tld")
    private String tld;
    @JsonProperty("type")
    private String type;
    @JsonProperty("ID_url")
    private String ID_url;
    @JsonProperty("link_type")
    private String link_type;
    @JsonProperty("link_url")
    private String link_url;
    @JsonProperty("link_text")
    private String link_text;
    @JsonProperty("link_title")
    private String link_title;
    @JsonProperty("referer")
    private String referer;
    @JsonProperty("unsubscribe_answer")
    private String unsubscribe_answer;
    @JsonProperty("IP")
    private String IP;
    @JsonProperty("IP_orig")
    private String IP_orig;
    @JsonProperty("continent")
    private String continent;
    @JsonProperty("country")
    private String country;
    @JsonProperty("code3")
    private String code3;
    @JsonProperty("code")
    private String code;
    @JsonProperty("region")
    private String region;
    @JsonProperty("region_code")
    private String region_code;
    @JsonProperty("city")
    private String city;
    @JsonProperty("user_agent")
    private String user_agent;
    @JsonProperty("browser")
    private String browser;
    @JsonProperty("browser_major")
    private String browser_major;
    @JsonProperty("browser_minor")
    private String browser_minor;
    @JsonProperty("browser_type")
    private String browser_type;
    @JsonProperty("os")
    private String os;
    @JsonProperty("os_version")
    private String os_version;
    @JsonProperty("device")
    private String device;
    @JsonProperty("org")
    private String org;

    /**
     * No args constructor for use in serialization
     * 
     */
    public RawResponse() {
    }

    /**
     * 
     * @param region
     * @param unsubscribeAnswer
     * @param linkUrl
     * @param userAgent
     * @param iDLog
     * @param iDUserList
     * @param linkTitle
     * @param org
     * @param type
     * @param date
     * @param iP
     * @param city
     * @param iDSendMessage
     * @param linkText
     * @param browser
     * @param domain
     * @param iDUrl
     * @param iDMessage
     * @param os
     * @param regionCode
     * @param iDSend
     * @param osVersion
     * @param browserMajor
     * @param browserType
     * @param abVersion
     * @param linkType
     * @param code
     * @param country
     * @param iDEmail
     * @param email
     * @param browserMinor
     * @param device
     * @param iPOrig
     * @param continent
     * @param referer
     * @param code3
     * @param tld
     */
    public RawResponse(String date, String email, String iDEmail, String iDUserList, String iDMessage, String iDSend, String iDSendMessage, String iDLog, String abVersion, String domain, String tld, String type, String iDUrl, String linkType, String linkUrl, String linkText, String linkTitle, String referer, String unsubscribeAnswer, String iP, String iPOrig, String continent, String country, String code3, String code, String region, String regionCode, String city, String userAgent, String browser, String browserMajor, String browserMinor, String browserType, String os, String osVersion, String device, String org) {
        super();
        this.date = date;
        this.email = email;
        this.ID_email = iDEmail;
        this.ID_user_list = iDUserList;
        this.ID_message = iDMessage;
        this.ID_send = iDSend;
        this.ID_send_message = iDSendMessage;
        this.ID_log = iDLog;
        this.ab_version = abVersion;
        this.domain = domain;
        this.tld = tld;
        this.type = type;
        this.ID_url = iDUrl;
        this.link_type = linkType;
        this.link_url = linkUrl;
        this.link_text = linkText;
        this.link_title = linkTitle;
        this.referer = referer;
        this.unsubscribe_answer = unsubscribeAnswer;
        this.IP = iP;
        this.IP_orig = iPOrig;
        this.continent = continent;
        this.country = country;
        this.code3 = code3;
        this.code = code;
        this.region = region;
        this.region_code = regionCode;
        this.city = city;
        this.user_agent = userAgent;
        this.browser = browser;
        this.browser_major = browserMajor;
        this.browser_minor = browserMinor;
        this.browser_type = browserType;
        this.os = os;
        this.os_version = osVersion;
        this.device = device;
        this.org = org;
    }

	public String getDate() {
		return date;
	}

	public String getEmail() {
		return email;
	}

	public String getID_email() {
		return ID_email;
	}

	public String getID_user_list() {
		return ID_user_list;
	}

	public String getID_message() {
		return ID_message;
	}

	public String getID_send() {
		return ID_send;
	}

	public String getID_send_message() {
		return ID_send_message;
	}

	public String getID_log() {
		return ID_log;
	}

	public String getAb_version() {
		return ab_version;
	}

	public String getDomain() {
		return domain;
	}

	public String getTld() {
		return tld;
	}

	public String getType() {
		return type;
	}

	public String getID_url() {
		return ID_url;
	}

	public String getLink_type() {
		return link_type;
	}

	public String getLink_url() {
		return link_url;
	}

	public String getLink_text() {
		return link_text;
	}

	public String getLink_title() {
		return link_title;
	}

	public String getReferer() {
		return referer;
	}

	public String getUnsubscribe_answer() {
		return unsubscribe_answer;
	}

	public String getIP() {
		return IP;
	}

	public String getIP_orig() {
		return IP_orig;
	}

	public String getContinent() {
		return continent;
	}

	public String getCountry() {
		return country;
	}

	public String getCode3() {
		return code3;
	}

	public String getCode() {
		return code;
	}

	public String getRegion() {
		return region;
	}

	public String getRegion_code() {
		return region_code;
	}

	public String getCity() {
		return city;
	}

	public String getUser_agent() {
		return user_agent;
	}

	public String getBrowser() {
		return browser;
	}

	public String getBrowser_major() {
		return browser_major;
	}

	public String getBrowser_minor() {
		return browser_minor;
	}

	public String getBrowser_type() {
		return browser_type;
	}

	public String getOs() {
		return os;
	}

	public String getOs_version() {
		return os_version;
	}

	public String getDevice() {
		return device;
	}

	public String getOrg() {
		return org;
	}


}
