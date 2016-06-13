package keboola.mailkit.extractor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import keboola.mailkit.extractor.config.JsonConfigParser;
import keboola.mailkit.extractor.config.KBCConfig;
import keboola.mailkit.extractor.config.KBCParameters;
import keboola.mailkit.extractor.config.tableconfig.ManifestBuilder;
import keboola.mailkit.extractor.config.tableconfig.ManifestFile;
import keboola.mailkit.extractor.mailkitapi.ClientException;
import keboola.mailkit.extractor.mailkitapi.MailkitJsonAPIClient;
import keboola.mailkit.extractor.mailkitapi.MailkitJsonResponse;
import keboola.mailkit.extractor.mailkitapi.MailkitResponse;
import keboola.mailkit.extractor.mailkitapi.MailkitXmlRpcAPIClient;
import keboola.mailkit.extractor.mailkitapi.XmlRpcCampaignListResponse;
import keboola.mailkit.extractor.mailkitapi.requests.CampaignListXmlRpc;
import keboola.mailkit.extractor.mailkitapi.requests.MailkitJsonRequest;
import keboola.mailkit.extractor.mailkitapi.requests.MailkitRequest;
import keboola.mailkit.extractor.mailkitapi.requests.MailkitXmlRpcRequest;
import keboola.mailkit.extractor.mailkitapi.requests.MsgBounces;
import keboola.mailkit.extractor.mailkitapi.requests.MsgFeedback;
import keboola.mailkit.extractor.mailkitapi.requests.MsgLinks;
import keboola.mailkit.extractor.mailkitapi.requests.MsgLinksVisitors;
import keboola.mailkit.extractor.mailkitapi.requests.MsgRecipients;
import keboola.mailkit.extractor.mailkitapi.requests.Report;
import keboola.mailkit.extractor.mailkitapi.requests.ReportCampaign;
import keboola.mailkit.extractor.mailkitapi.requests.ReportMessage;
import keboola.mailkit.extractor.state.JsonStateWriter;
import keboola.mailkit.extractor.state.LastState;
import keboola.mailkit.extractor.utils.JsonToCsvConvertor;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvMapWriter;
import org.supercsv.prefs.CsvPreference;

/*
 */
/**
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2016
 */
public class Extractor {

    private final static int REQUEST_WAIT_INTERVAL = 1000;

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.print("No parameters provided.");
            System.exit(1);
        }

        String dataPath = args[0];
        String outTablesPath = dataPath + File.separator + "out" + File.separator + "tables";

        KBCConfig config = null;
        File confFile = new File(args[0] + File.separator + "config.json");
        if (!confFile.exists()) {
            System.out.println("config.json does not exist!");
            System.err.println("config.json does not exist!");
            System.exit(1);
        }
        //Parse config file
        try {
            if (confFile.exists() && !confFile.isDirectory()) {
                config = JsonConfigParser.parseFile(confFile);
            }
        } catch (Exception ex) {
            System.out.println("Failed to parse config file");
            System.err.println(ex.getMessage());
            System.exit(1);
        }
        if (!config.validate()) {
            System.out.println(config.getValidationError());
            System.err.println(config.getValidationError());
            System.exit(1);
        }
//retrieve stateFile
        File stateFile = new File(dataPath + File.separator + "in" + File.separator + "state.json");
        LastState lastState = null;
        if (stateFile.exists()) {
            try {
                lastState = (LastState) JsonConfigParser.parseFile(stateFile, LastState.class);
            } catch (IOException ex) {
                System.err.println("Unable to parse state file!");
            }
        } else {
            lastState = new LastState(Instant.now());
        }
        /*Set period from previous state*/
        if (config.getParams().isSinceLastRun()) {
            try {
                if (lastState.getLastRunDate() == null) {
                    System.out.println("Empty state file, first run?");
                    config.getParams().setDateFrom(config.getParams().getDateFrom());
                } else {
                    config.getParams().setDateFrom(lastState.getLastRunDate());
                }
                config.getParams().setDateTo(Instant.now());
            } catch (ParseException ex) {
                System.err.println("Unable to set date from statefile!");
            } catch (RuntimeException re) {
                re.printStackTrace();
                System.exit(1);
            }
        }

        //get campaign list via xml rpc endpoint if requested
        if (config.getParams().getDatasets().contains(KBCParameters.REQUEST_TYPE.CAMPAIGNS.name())) {
            System.out.println("Downloading campaigns.");
            XmlRpcCampaignListResponse res = null;
            try {
                MailkitXmlRpcAPIClient xmlClient = new MailkitXmlRpcAPIClient(config.getParams().getClientId(), config.getParams().getClientMd5());
                MailkitXmlRpcRequest rq = new CampaignListXmlRpc(null);

                res = (XmlRpcCampaignListResponse) xmlClient.executeRequest(rq);
                checkResponseStatus(res, rq);

            } catch (ClientException ex) {
                System.err.println(ex.getMessage());
                System.exit(ex.getSeverity());
            } catch (MalformedURLException ex) {
                System.err.println(ex.getMessage());
                System.exit(2);
            }

            /*Write campaigns csv*/
            File campaignsFile = new File(outTablesPath + File.separator + "campaigns.csv");
            CsvMapWriter mapWriter = null;
            List<String> campaignIdsAll = new ArrayList();

            try {
                mapWriter = new CsvMapWriter(new BufferedWriter(new FileWriter(campaignsFile)),
                        CsvPreference.STANDARD_PREFERENCE);
                final String[] header = res.getResponseData()[0].keySet().toArray(new String[0]);
                final CellProcessor[] processors = getProcessors(header.length);

                // write the header
                mapWriter.writeHeader(header);
                //write to file
                for (Map row : res.getResponseData()) {
                    campaignIdsAll.add((String) row.get("ID_MESSAGE").toString());
                    mapWriter.write(row, header, processors);
                }
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
                System.exit(1);
            } catch (RuntimeException re) {
                re.printStackTrace();
                System.exit(1);
            } finally {
                try {
                    mapWriter.close();
                } catch (Exception ex) {
                }
            }

        }

        /*Get datasets from JsonApi*/
        List<String> campaignIds = new ArrayList<>();
        MailkitJsonResponse jsResp;
        MailkitJsonRequest jsonRq;
        JsonToCsvConvertor jc = new JsonToCsvConvertor();
        MailkitJsonAPIClient jsonClient = new MailkitJsonAPIClient(config.getParams().getClientId(), config.getParams().getClientMd5(), dataPath + File.separator + "tmp");

        /*Get REPORT dataset */
        try {
            if (config.getParams().getDatasets().contains(KBCParameters.REQUEST_TYPE.REPORT.name())) {
                System.out.println("Downloading summary report.");
                jsonRq = new Report(config.getParams().getDateFrom(), config.getParams().getDateTo());
                jsResp = (MailkitJsonResponse) jsonClient.executeRequest(jsonRq);
                checkResponseStatus(jsResp, jsonRq);
                /*process REPORT*/
                try {
                    campaignIds = jc.convert(jsResp.getFilePath(), outTablesPath + File.separator + "summaryReport.csv", "ID_MESSAGE");
                } catch (Exception ex) {
                    Logger.getLogger(Extractor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            /*Check if campaign ids specified*/
            if (config.getParams().getCampaignIds() != null && config.getParams().getCampaignIds().size() > 0) {
                campaignIds = config.getParams().getCampaignIds();
            }

            /*get all messages for each campaign*/
            List<String> sendIds = new ArrayList<>();
            int i = 0;
            Map<String, String> keyCols = new HashMap<>();
            boolean append = false;

            System.out.println("Downloading campaign report.");
            for (String cId : campaignIds) {
                Thread.sleep(REQUEST_WAIT_INTERVAL);
                jsonRq = new ReportCampaign(config.getParams().getDateFrom(), config.getParams().getDateTo(), cId);
                jsResp = (MailkitJsonResponse) jsonClient.executeRequest(jsonRq);
                if (!checkResponseStatus(jsResp, jsonRq)) {
                    continue;
                }
                keyCols.clear();
                keyCols.put("ID_MESSAGE", cId);
                append = i > 0;
                sendIds.addAll(jc.convertAndAdd(jsResp.getFilePath(), outTablesPath + File.separator + "campaignReports.csv", keyCols, append, "ID_SEND"));
                i++;
            }

            /*Get data for all messages*/
            List<String> linkIds;
            boolean firstRun = true;
            System.out.println("Downloading campaign send reports.");
            for (String sId : sendIds) {

                if (config.getParams().getDatasets().contains(KBCParameters.REQUEST_TYPE.MSG_BOUNCES.name())) {
                    Thread.sleep(REQUEST_WAIT_INTERVAL);
                    jsonRq = new MsgBounces(sId);
                    jsResp = (MailkitJsonResponse) jsonClient.executeRequest(jsonRq);
                    if (!checkResponseStatus(jsResp, jsonRq)) {
                        continue;
                    }
                    keyCols.clear();
                    keyCols.put("ID_SEND", sId);
                    append = !firstRun;
                    String resPath = outTablesPath + File.separator + "bounces.csv";
                    jc.convertAndAdd(jsResp.getFilePath(), resPath, keyCols, append);
                }

                if (config.getParams().getDatasets().contains(KBCParameters.REQUEST_TYPE.MSG_FEEDBACK.name())) {
                    Thread.sleep(REQUEST_WAIT_INTERVAL);
                    jsonRq = new MsgFeedback(sId, null);
                    jsResp = (MailkitJsonResponse) jsonClient.executeRequest(jsonRq);
                    if (!checkResponseStatus(jsResp, jsonRq)) {
                        continue;
                    }
                    keyCols.clear();
                    keyCols.put("ID_SEND", sId);
                    append = !firstRun;
                    String resPath = outTablesPath + File.separator + "feedback.csv";
                    jc.convertAndAdd(jsResp.getFilePath(), resPath, keyCols, append);
                }

                if (config.getParams().getDatasets().contains(KBCParameters.REQUEST_TYPE.MSG_RECIPIENTS.name())) {
                    Thread.sleep(REQUEST_WAIT_INTERVAL);
                    jsonRq = new MsgRecipients(sId);
                    jsResp = (MailkitJsonResponse) jsonClient.executeRequest(jsonRq);
                    if (!checkResponseStatus(jsResp, jsonRq)) {
                        continue;
                    }
                    keyCols.clear();
                    keyCols.put("ID_SEND", sId);
                    append = !firstRun;
                    String resPath = outTablesPath + File.separator + "recipients.csv";
                    jc.convertAndAdd(jsResp.getFilePath(), resPath, keyCols, append);
                }

                if (config.getParams().getDatasets().contains(KBCParameters.REQUEST_TYPE.REPORT_MSG.name())) {
                    Thread.sleep(REQUEST_WAIT_INTERVAL);
                    jsonRq = new ReportMessage(sId);
                    jsResp = (MailkitJsonResponse) jsonClient.executeRequest(jsonRq);
                    if (!checkResponseStatus(jsResp, jsonRq)) {
                        continue;
                    }
                    keyCols.clear();
                    keyCols.put("ID_SEND", sId);
                    append = !firstRun;
                    String resPath = outTablesPath + File.separator + "messageReport.csv";
                    jc.singleJsonObjectToCsv(jsResp.getFilePath(), resPath, keyCols, append);
                }

                if (config.getParams().getDatasets().contains(KBCParameters.REQUEST_TYPE.MSG_LINKS.name())) {
                    Thread.sleep(REQUEST_WAIT_INTERVAL);
                    jsonRq = new MsgLinks(sId);
                    jsResp = (MailkitJsonResponse) jsonClient.executeRequest(jsonRq);
                    checkResponseStatus(jsResp, jsonRq);
                    keyCols.clear();
                    keyCols.put("ID_SEND", sId);
                    append = !firstRun;
                    String resPath = outTablesPath + File.separator + "links.csv";
                    linkIds = jc.convertAndAdd(jsResp.getFilePath(), resPath, keyCols, append, "ID_URL");

                    //link visitors
                    if (config.getParams().getDatasets().contains(KBCParameters.REQUEST_TYPE.LINKS_VISITORS.name())) {
                        for (String urlId : linkIds) {
                            Thread.sleep(REQUEST_WAIT_INTERVAL);
                            jsonRq = new MsgLinksVisitors(sId, urlId);
                            jsResp = (MailkitJsonResponse) jsonClient.executeRequest(jsonRq);
                            if (!checkResponseStatus(jsResp, jsonRq)) {
                                continue;
                            }
                            keyCols.clear();
                            keyCols.put("ID_SEND", sId);
                            keyCols.put("ID_URL", urlId);
                            append = !firstRun;
                            resPath = outTablesPath + File.separator + "linksVisitors.csv";
                            jc.convertAndAdd(jsResp.getFilePath(), resPath, keyCols, append, "ID_URL");
                        }
                    }
                }

                firstRun = false;
            }
        } catch (ClientException ex) {
            System.err.println(ex.getMessage());
            System.exit(ex.getSeverity());
        } catch (JsonToCsvConvertor.ConvertException ex) {
            System.err.println(ex.getMessage());
            System.exit(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(Extractor.class.getName()).log(Level.SEVERE, null, ex);
        }
        jsonClient.cleanupTempFolder();

        /*Build manifest files*/
        File folder = new File(outTablesPath);
        File[] fileList = folder.listFiles();
        for (File f : fileList) {
            try {
                //build manifest file
                ManifestFile man = new ManifestFile(null, false, null, ",", "\"");
                ManifestBuilder.buildManifestFile(man, outTablesPath, f.getName());
            } catch (IOException ex) {
                System.err.println("Unable to write manifest file " + f.getName());
                System.exit(1);
            }
        }

        try {
            /*Write state file*/
            JsonStateWriter.writeStateFile(dataPath + File.separator + "out", lastState);
        } catch (IOException ex) {
            System.err.println("Unable to write state file");
        }
        System.out.println("Download completed successfuly..");
    }

    private static boolean checkResponseStatus(MailkitResponse res, MailkitRequest rq) {
        if (res.isError()) {
            String err = "";
            if (res.getErrorMessage().equalsIgnoreCase("Unauthorized")) {
                System.err.println("Unauthorized. Check credentials.");
                System.exit(1);
            } else {
                err = "WARNING:" + res.getErrorMessage() + "\n Call failed with parameter: ";
                for (Map.Entry entry : rq.getParameters().entrySet()) {
                    err += "\n" + entry.getKey() + ", " + entry.getValue();
                }
                System.out.println(err);
                return false;
            }
        }
        return true;
    }

    /**
     * get cell processors with dynamic size
     *
     * @param length
     * @return
     */
    private static CellProcessor[] getProcessors(int length) {
        CellProcessor[] processors = new CellProcessor[length];
        for (int i = 0; i < length; i++) {
            processors[i] = new Optional();

        }

        return processors;
    }
}
