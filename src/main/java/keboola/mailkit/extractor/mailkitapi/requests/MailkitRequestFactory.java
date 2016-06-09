/*
 */
package keboola.mailkit.extractor.mailkitapi.requests;

/**
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2016
 */
public class MailkitRequestFactory {

    private final String client_id;
    private final String client_md5;
    private String range_from;
    private String range_to;

    public static enum REQUEST_TYPE {
        REPORT("mailkit.report"),
        REPORT_CAMPAIGN("mailkit.report.campaign"),
        REPORT_MSG("mailkit.report.message"),
        MSG_RECIPIENTS("mailkit.report.message.recipients"),
        MSG_FEEDBACK("mailkit.report.message.feedback"),
        MSG_LINKS("mailkit.report.message.links"),
        LINKS_VISITORS("mailkit.report.message.links.visitors"),
        MSG_BOUNCES("mailkit.report.message.bounces");

        private String type;

        private REQUEST_TYPE(String type) {
        }

        String getType() {
            return type;
        }

    }

    public MailkitRequestFactory(String client_id, String client_md5, String range_to, String range_from) {
        this.client_id = client_id;
        this.client_md5 = client_md5;
        this.range_to = range_to;
        this.range_from = range_from;
    }

    public String getRange_from() {
        return range_from;
    }

    public void setRange_from(String range_from) {
        this.range_from = range_from;
    }

    public String getRange_to() {
        return range_to;
    }

    public void setRange_to(String range_to) {
        this.range_to = range_to;
    }

}
