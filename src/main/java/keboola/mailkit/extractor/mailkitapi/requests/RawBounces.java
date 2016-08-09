/*
 */
package keboola.mailkit.extractor.mailkitapi.requests;

/**
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2016
 */
public class RawBounces extends MailkitJsonRequest {

    /**
     *
     * @param idMessage - Campaign ID
     * @param idSend -
     * @param idLog ID of last record to start the iteration, if records
     * retrieved from the start
     * @param limit - limit of records returned, if null default value (25k) is
     * used
     */
    public RawBounces(String idMessage, String idSend, Long idLog, Integer limit) {

        super("mailkit.report.raw.bounces");
        addParameter("ID_message", idMessage);
        if (idSend != null && !idSend.equals("")) {
            addParameter("ID_send", idSend);
        }
        if (idLog != null) {
            addParameter("ID_undelivered_log", idLog.toString());
        }
        if (limit != null) {
            addParameter("limit", limit.toString());
        }
    }

}
