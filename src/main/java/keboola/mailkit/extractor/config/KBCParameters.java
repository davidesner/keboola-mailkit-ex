/*
 */
package keboola.mailkit.extractor.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2015
 */
public class KBCParameters {

    private final static String[] REQUIRED_FIELDS = {"clientId", "clientMd5", "datasets"};
    private final Map<String, Object> parametersMap;

    private Date date_from;
    private Date date_to;

    @JsonProperty("clientId")
    private String clientId;
    @JsonProperty("#clientMd5")
    private String clientMd5;
    @JsonProperty("campaignIds")
    private List<String> campaignIds;
    @JsonProperty("datasets")
    private Set<String> datasets;
    //end date of fetched interval in format: YYYY-MM-DD
    @JsonProperty("dateTo")
    private String dateTo;
    //start date of fetched interval in format: YYYY-MM-DD
    @JsonProperty("dateFrom")
    private String dateFrom;
    @JsonProperty("daysPeriod")
    private Integer period;
    @JsonProperty("sinceLastRun")
    private boolean sinceLastRun;

    public static enum REQUEST_TYPE {
        CAMPAIGNS, REPORT, REPORT_CAMPAIGN, REPORT_MSG, MSG_RECIPIENTS, MSG_FEEDBACK,
        MSG_LINKS, LINKS_VISITORS, MSG_BOUNCES, ALL
    }

    public KBCParameters() {
        parametersMap = new HashMap();

    }

    @JsonCreator
    public KBCParameters(@JsonProperty("clientId") String clientId, @JsonProperty("#clientMd5") String clientMd5,
            @JsonProperty("dateFrom") String dateFrom, @JsonProperty("dateTo") String dateTo, @JsonProperty("daysPeriod") String period,
            @JsonProperty("campaignIds") List<String> campaignIds, @JsonProperty("datasets") Set<String> datasets,
            @JsonProperty("sinceLastRun") boolean sinceLastRun
    ) throws ParseException {
        parametersMap = new HashMap();
        this.clientId = clientId;
        this.clientMd5 = clientMd5;
        this.sinceLastRun = sinceLastRun;
        this.campaignIds = campaignIds;
        //set range
        if (period != null) {

            setDateTo(Instant.now());
            setDateFrom(Instant.now().minus(Long.valueOf(period), ChronoUnit.DAYS));
        } else {
            if (dateFrom.equals("")) {
                this.dateFrom = null;
            } else {
                this.dateFrom = dateFrom;
            }

            if (dateTo == null || dateTo.equals("") || dateTo.equalsIgnoreCase("Now")) {
                setDateTo(Instant.now());
            } else {
                this.dateTo = dateTo;
            }
        }
        this.datasets = datasets;

        //add REPORT if not specified campaignIds
        if (datasets != null && (campaignIds == null || campaignIds.isEmpty())) {
            this.datasets.add("REPORT");
        }

        //add report_msg if msg based request specified
        if (datasets != null && !datasets.contains("REPORT_CAMPAIGN")) {
            if (datasets.contains("MSG_RECIPIENTS") || datasets.contains("MSG_FEEDBACK")
                    || datasets.contains("MSG_LINKS") || datasets.contains("MSG_BOUNCES")
                    || datasets.contains("REPORT_MSG")) {
                this.datasets.add("REPORT_CAMPAIGN");
            }
        }

        /*Download all datasets by default*/
        if (datasets == null || datasets.contains("ALL")) {
            this.datasets = new HashSet();
            for (REQUEST_TYPE r : REQUEST_TYPE.values()) {
                this.datasets.add(r.name());
            }
        }

        if (datasets != null && !datasets.contains("LINKS_VISITORS")) {
            this.datasets.add("MSG_LINKS");
        }
        //set param map
        parametersMap.put("clientId", clientId);
        parametersMap.put("clientMd5", clientMd5);
        parametersMap.put("datasets", datasets);

    }

    /**
     * Returns list of required fields missing in config
     *
     * @return
     */
    private List<String> getMissingFields() {
        List<String> missing = new ArrayList<String>();
        for (int i = 0; i < REQUIRED_FIELDS.length; i++) {
            Object value = parametersMap.get(REQUIRED_FIELDS[i]);
            if (value == null) {
                missing.add(REQUIRED_FIELDS[i]);
            }
        }

        if (missing.isEmpty()) {
            return null;
        }
        return missing;
    }

    private String missingFieldsMessage() {
        List<String> missingFields = getMissingFields();
        String msg = "";
        if (missingFields != null && missingFields.size() > 0) {
            msg = "Required config fields are missing: ";
            int i = 0;
            for (String fld : missingFields) {
                if (i < missingFields.size()) {
                    msg += fld + ", ";
                } else {
                    msg += fld;
                }
            }
        }
        return msg;
    }

    private boolean isValidDateFormat(String datef, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false);

        try {
            //if not valid, it will throw ParseException
            Date date = sdf.parse(datef);
            return true;

        } catch (ParseException e) {
            return false;
        }
    }

    public boolean validateParametres() throws ValidationException {
        //validate date format
        String error = "";
        if (dateTo != null && dateFrom != null) {
            if (!isValidDateFormat(dateTo, "yyyy-MM-dd")) {
                error += "Invalid date range format: " + dateTo + " (YYYY-MM-DD expected)";
            }
            if (!isValidDateFormat(dateFrom, "yyyy-MM-dd")) {
                error += "Invalid date range format: " + dateFrom + " (YYYY-MM-DD expected)";
            }
        }

        error += missingFieldsMessage();

        try {
            validateDatasetList();
        } catch (ValidationException ex) {
            error += ex.getMessage();
        }

        if (error.equals("")) {
            return true;
        } else {

            throw new ValidationException("Validation error: " + error);
        }
    }

    public boolean validateDatasetList() throws ValidationException {
        List<String> invalidCols = new ArrayList();
        if (this.datasets != null) {
            for (String colName : this.datasets) {
                if (!isValidRequest(colName)) {
                    invalidCols.add(colName);
                }
            }
        }
        if (!invalidCols.isEmpty()) {
            throw new ValidationException("Some query columns are not supported: " + invalidCols.toString());
        }
        return true;
    }

    private boolean isValidRequest(String col) {
        for (REQUEST_TYPE c : REQUEST_TYPE.values()) {
            if (c.name().equals(col)) {
                return true;
            }
        }
        return false;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Set<String> getDatasets() {
        return datasets;
    }

    public void setDatasets(Set<String> datasets) {
        this.datasets = datasets;
    }

    public String getClientMd5() {
        return clientMd5;
    }

    public void setClientMd5(String clientMd5) {
        this.clientMd5 = clientMd5;
    }

    public boolean isSinceLastRun() {
        return sinceLastRun;
    }

    public List<String> getCampaignIds() {
        return campaignIds;
    }

    public void setCampaignIds(List<String> campaignIds) {
        this.campaignIds = campaignIds;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public final void setDateFrom(Instant date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        this.dateFrom = format.format(Date.from(date));

    }

    public final void setDateTo(Instant date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        this.dateTo = format.format(Date.from(date));

    }

    public Map<String, Object> getParametersMap() {
        return parametersMap;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

}
