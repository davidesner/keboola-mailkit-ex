/*
 */
package keboola.mailkit.extractor.mailkitapi.requests;

/**
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2016
 */
public class ReportMessage extends MailkitJsonRequest {

    public ReportMessage(String idSend) {

        super("mailkit.report.message");
        addParameter("ID_send", idSend);
    }

}
