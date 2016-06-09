/*
 */
package keboola.mailkit.extractor.mailkitapi.requests;

/**
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2016
 */
public class MsgLinksVisitors extends MailkitJsonRequest {

    public MsgLinksVisitors(String idSend, String idUrl) {

        super("mailkit.report.message.links.visitors");
        addParameter("ID_send", idSend);
        addParameter("ID_url", idUrl);
    }

}
