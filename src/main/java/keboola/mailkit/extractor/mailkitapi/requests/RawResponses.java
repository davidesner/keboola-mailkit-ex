/*
 */
package keboola.mailkit.extractor.mailkitapi.requests;

/**
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2016
 */
public class RawResponses extends MailkitJsonRequest {

    /**
     *
     * @param idMessage - Campaign ID
     * @param idSendMessage
     * @param idSend -
     * @param idLog ID of last record to start the iteration, if records
     * retrieved from the start
     * @param limit - limit of records returned, if null default value (25k) is
     * used
     */
    public RawResponses(String idMessage, Long idSendMessage, String idSend, Long idLog, Integer limit) {

        super("mailkit.report.raw.responses");
        if (idMessage != null && !idMessage.equals("")) {
        	addParameter("ID_message", idMessage);
        }
        if (idSend != null && !idSend.equals("")) {
            addParameter("ID_send", idSend);
        }
        if (idSendMessage != null) {
            addParameter("ID_send_message", idSendMessage.toString());
        }
        if (idLog != null) {
            addParameter("ID_log", idLog.toString());
        }
        if (limit != null) {
            addParameter("limit", limit.toString());
        }
    }

}
