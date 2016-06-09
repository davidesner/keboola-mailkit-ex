/*
 */
package keboola.mailkit.extractor.mailkitapi;

import keboola.mailkit.extractor.KBCException;

/**
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2015
 */
public class ClientException extends KBCException {

    public ClientException(String message) {
        super(message);
    }
}
