/*
 */
package keboola.mailkit.extractor.mailkitapi.requests;

/**
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2016
 */
public class Report extends MailkitJsonRequest {

    public Report(String dateFrom, String dateTo) {
        super("mailkit.report");
        if (dateFrom != null && dateTo != null) {
            addParameter("range_from", dateFrom);
            addParameter("range_to", dateTo);
        }
    }

}
