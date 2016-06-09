/*
 */
package keboola.mailkit.extractor.mailkitapi.requests;

/**
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2016
 */
public class MsgRecipients extends MailkitJsonRequest {

    public MsgRecipients(String idSend) {

        super("mailkit.report.message.recipients");
        addParameter("ID_send", idSend);
    }

}
