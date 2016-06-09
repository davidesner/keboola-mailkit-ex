/*
 */
package keboola.mailkit.extractor.mailkitapi.requests;

/**
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2016
 */
public class ReportCampaign extends MailkitJsonRequest {

    public ReportCampaign(String dateFrom, String dateTo, String id_message) {

        super("mailkit.report.campaign");
        addParameter("range_from", dateFrom);
        addParameter("range_to", dateTo);
        addParameter("ID_message", id_message);
    }

}
