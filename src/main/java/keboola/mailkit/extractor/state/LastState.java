/*
 */
package keboola.mailkit.extractor.state;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2016
 */
public class LastState {

    @JsonProperty("lastRunDate")
    private Instant lastRunDate;
    
    @JsonProperty("rawMessagesLastId")
    private Long rawMessagesLastId;
    
    @JsonProperty("rawResponsesLastId")
    private Long rawResponsesLastId;
    
    @JsonProperty("rawBouncesLastId")
    private Long rawBouncesLastId;

    public LastState(Instant lastRunDate) {
        this.rawMessagesLastId = null;
        this.rawBouncesLastId = null;
        this.rawResponsesLastId = null;
        this.lastRunDate = lastRunDate;
    }

    @JsonCreator
    public LastState(@JsonProperty("lastRunDate") Instant lastRunDate, @JsonProperty("rawMessagesLastId") Long rawMessagesLastId,
            @JsonProperty("rawResponsesLastId") Long rawResponsesLastId, @JsonProperty("rawBouncesLastId") Long rawBouncesLastId) {
        this.lastRunDate = lastRunDate;
        this.rawMessagesLastId = rawMessagesLastId;
        this.rawBouncesLastId = rawBouncesLastId;
        this.rawResponsesLastId = rawResponsesLastId;

    }

    public Instant getLastRunDate() {
        return lastRunDate;
    }

    public void setLastRunDate(Instant lastRunDate) {
        this.lastRunDate = lastRunDate;
    }

    public Long getRawMessagesLastId() {
        return rawMessagesLastId;
    }

    public void setRawMessagesLastId(Long rawMessagesLastId) {
        this.rawMessagesLastId = rawMessagesLastId;
    }

    public Long getRawResponsesLastId() {
        return rawResponsesLastId;
    }

    public void setRawResponsesLastId(Long rawResponsesLastId) {
        this.rawResponsesLastId = rawResponsesLastId;
    }

    public Long getRawBouncesLastId() {
        return rawBouncesLastId;
    }

    public void setRawBouncesLastId(Long rawBouncesLastId) {
        this.rawBouncesLastId = rawBouncesLastId;
    }

    public LastState() {
    }

}
