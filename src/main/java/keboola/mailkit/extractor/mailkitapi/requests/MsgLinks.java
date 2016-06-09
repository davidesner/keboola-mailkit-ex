/*
 */
package keboola.mailkit.extractor.mailkitapi.requests;

/**
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2016
 */
public class MsgLinks extends MailkitJsonRequest {

    public MsgLinks(String idSend) {

        super("mailkit.report.message.links");
        addParameter("ID_send", idSend);
    }

}
