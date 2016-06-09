/*
 */
package keboola.mailkit.extractor.mailkitapi.requests;

/**
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2016
 */
public class CampaignListXmlRpc extends MailkitXmlRpcRequest {

    public CampaignListXmlRpc(String campaignId) {
        super("mailkit.campaigns.list");
        if (campaignId != null) {
            this.addParameter(campaignId);
        }

    }

    @Override
    public String getFunctionCall() {
        String m = this.getFunction() + " with parameters:";
        for (Object par : this.getParameters()) {
            m += "\n" + par + ", " + par;
        }
        return m;
    }

}
