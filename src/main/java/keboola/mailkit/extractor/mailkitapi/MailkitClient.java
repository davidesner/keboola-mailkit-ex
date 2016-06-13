/*
 */
package keboola.mailkit.extractor.mailkitapi;

import keboola.mailkit.extractor.mailkitapi.requests.MailkitRequest;

/**
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2016
 */
public interface MailkitClient {

    public MailkitResponse executeRequest(MailkitRequest rq, boolean log) throws ClientException;
}
