/*
 */
package keboola.mailkit.extractor.mailkitapi.requests;

import java.util.Map;

/**
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2016
 */
public class MsgBounces extends MailkitJsonRequest {

    public MsgBounces(String idSend) {

        super("mailkit.report.message.bounces");
        addParameter("ID_send", idSend);
    }

}
