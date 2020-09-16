/*
 */
package keboola.mailkit.extractor.mailkitapi.requests;

/**
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2016
 */
public class CampaignList extends MailkitJsonRequest {

    public CampaignList(String campaignId) {
        super("mailkit.campaigns.list");
        if (campaignId != null) {
            addParameter("ID_message", campaignId);
        }
    }

}
