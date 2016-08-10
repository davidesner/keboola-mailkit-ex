/*
 */
package keboola.mailkit.extractor.state;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2016
 */
public class LastState {

    @JsonProperty("lastRunDate")
    private Instant lastRunDate;

    private Map<String, Long> rawMessagesLastId;

    private Map<String, Long> rawResponsesLastId;

    private Map<String, Long> rawBouncesLastId;

    public LastState(Instant lastRunDate) {
        this.rawMessagesLastId = new HashMap<>();
        this.rawBouncesLastId = new HashMap<>();
        this.rawResponsesLastId = new HashMap();
        this.lastRunDate = lastRunDate;
    }

    public Instant getLastRunDate() {
        return lastRunDate;
    }

    public void setLastRunDate(Instant lastRunDate) {
        this.lastRunDate = lastRunDate;
    }

    public Map<String, Long> getRawMessagesLastId() {
        return rawMessagesLastId;
    }

    public void setRawMessagesLastId(Map<String, Long> rawMessagesLastId) {
        this.rawMessagesLastId = rawMessagesLastId;
    }

    public Map<String, Long> getRawResponsesLastId() {
        return rawResponsesLastId;
    }

    public void setRawResponsesLastId(Map<String, Long> rawResponsesLastId) {
        this.rawResponsesLastId = rawResponsesLastId;
    }

    public Map<String, Long> getRawBouncesLastId() {
        return rawBouncesLastId;
    }

    public void setRawBouncesLastId(Map<String, Long> rawBouncesLastId) {
        this.rawBouncesLastId = rawBouncesLastId;
    }

    public LastState() {
    }

}
