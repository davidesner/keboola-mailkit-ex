/*
 */
package keboola.mailkit.extractor.mailkitapi.requests;

/**
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2016
 */
public class MsgFeedback extends MailkitJsonRequest {

    public MsgFeedback(String idSend, String period) {

        super("mailkit.report.message.feedback");
        addParameter("ID_send", idSend);
        if (period != null) {
            addParameter("period", period);
        }
    }

}
