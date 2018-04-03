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
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvMapWriter;
import org.supercsv.prefs.CsvPreference;

import esnerda.keboola.components.result.IResultWriter;
import esnerda.keboola.components.result.impl.DefaultBeanResultWriter;
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
import keboola.mailkit.extractor.mailkitapi.requests.RawBounces;
import keboola.mailkit.extractor.mailkitapi.requests.RawMessages;
import keboola.mailkit.extractor.mailkitapi.requests.RawResponses;
import keboola.mailkit.extractor.mailkitapi.requests.Report;
import keboola.mailkit.extractor.mailkitapi.requests.ReportCampaign;
import keboola.mailkit.extractor.mailkitapi.requests.ReportMessage;
import keboola.mailkit.extractor.mailkitapi.response.MsgBouncesResp;
import keboola.mailkit.extractor.mailkitapi.response.MsgFeedbackResp;
import keboola.mailkit.extractor.mailkitapi.response.MsgLinksResp;
import keboola.mailkit.extractor.mailkitapi.response.MsgLinksVisitorResp;
import keboola.mailkit.extractor.mailkitapi.response.MsgRecipientsResp;
import keboola.mailkit.extractor.mailkitapi.response.RawBounce;
import keboola.mailkit.extractor.mailkitapi.response.RawMessage;
import keboola.mailkit.extractor.mailkitapi.response.RawResponse;
import keboola.mailkit.extractor.mailkitapi.response.ReportCampaignResp;
import keboola.mailkit.extractor.mailkitapi.response.ReportMsgResp;
import keboola.mailkit.extractor.mailkitapi.response.ReportResp;
import keboola.mailkit.extractor.mailkitapi.response.wrapper.CampaignReportWrapper;
import keboola.mailkit.extractor.mailkitapi.response.wrapper.MsgBounceWrapper;
import keboola.mailkit.extractor.mailkitapi.response.wrapper.MsgFeedbackWrapper;
import keboola.mailkit.extractor.mailkitapi.response.wrapper.MsgLinkVisitorWrapper;
import keboola.mailkit.extractor.mailkitapi.response.wrapper.MsgLinksWrapper;
import keboola.mailkit.extractor.mailkitapi.response.wrapper.MsgRecipientWrapper;
import keboola.mailkit.extractor.mailkitapi.response.wrapper.ReportMsgWrapper;
import keboola.mailkit.extractor.state.JsonStateWriter;
import keboola.mailkit.extractor.state.LastState;
import keboola.mailkit.extractor.utils.JsonToCsvConvertor;

/*
 */
/**
 *
 * @author David Esner <esnerda at gmail.com>
 * @created 2016
 */
public class Extractor {

    private final static int REQUEST_WAIT_INTERVAL = 0;
    private final static boolean LOG = false;
    private final static String logName = "log.txt";

    //writers
    private static IResultWriter<keboola.mailkit.extractor.mailkitapi.response.ReportResp> reportWriter;
    private static IResultWriter<CampaignReportWrapper> campaignReportWriter;
    private static IResultWriter<RawMessage> rawMsgWriter;
    private static IResultWriter<RawResponse> rawResponseWriter;
    private static IResultWriter<RawBounce> rawBounceWriter;
    private static IResultWriter<MsgBounceWrapper> msgBounceWriter;
    private static IResultWriter<MsgFeedbackWrapper> msgFeedbackWriter;
    private static IResultWriter<MsgRecipientWrapper> msgRecipientsWriter;
    private static IResultWriter<ReportMsgWrapper> msgReportWriter;
    private static IResultWriter<MsgLinksWrapper> msgLinksWriter;
    private static IResultWriter<MsgLinkVisitorWrapper> msgLinkVisitorsWriter;
    
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
                lastState = new LastState(Instant.now());
                System.err.println("Unable to parse state file!");
            }
        } else {
            lastState = new LastState(null);
        }
        /*Set period from previous state*/
        if (config.getParams().isSinceLastRun()) {
            try {
                if (lastState.getLastRunDate() == null) {
                    lastState = new LastState(Instant.now());
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

        System.out.println("Initializing writers..");
        try {
			initWriters(config, outTablesPath);
		} catch (Exception e) {
			System.err.println("Unable to set init writers!");
			e.printStackTrace();
            System.exit(1);
		}

        System.out.println("Download started with range parameters: dateFrom:" + config.getParams().getDateFrom() + ", dateTo:" + config.getParams().getDateTo());
        //get campaign list via xml rpc endpoint if requested
        if (config.getParams().getDatasets().contains(KBCParameters.REQUEST_TYPE.CAMPAIGNS.name())) {

            System.out.println("Downloading campaigns.");
            XmlRpcCampaignListResponse res = null;
            try {
                MailkitXmlRpcAPIClient xmlClient = new MailkitXmlRpcAPIClient(config.getParams().getClientId(), config.getParams().getClientMd5());
                MailkitXmlRpcRequest rq = new CampaignListXmlRpc(null);

                res = (XmlRpcCampaignListResponse) xmlClient.executeRequest(rq, LOG);
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
        if (LOG) {
            jsonClient.setLogFile(dataPath + File.separator + logName);
        }
        /*Get REPORT dataset */
        try {
            if (config.getParams().getDatasets().contains(KBCParameters.REQUEST_TYPE.REPORT.name())) {
                System.out.println("Downloading summary report.");
                jsonRq = new Report(config.getParams().getDateFrom(), config.getParams().getDateTo());
                jsResp = (MailkitJsonResponse) jsonClient.executeRequest(jsonRq, LOG);
                checkResponseStatus(jsResp, jsonRq);
                /*process REPORT*/
                try {
                	List<ReportResp> reps = jsResp.getResponseObject(ReportResp.class);
                	for(ReportResp rp : reps) {
                		campaignIds.add(rp.getID_MESSAGE().toString());
                	}                	
                    reportWriter.writeAllResults(reps);
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
            Set<String> datasetsToGet = config.getParams().getDatasets();
            System.out.println("Downloading campaign report.");
            for (String cId : campaignIds) {
                Thread.sleep(REQUEST_WAIT_INTERVAL);
                jsonRq = new ReportCampaign(config.getParams().getDateFrom(), config.getParams().getDateTo(), cId);
                jsResp = (MailkitJsonResponse) jsonClient.executeRequest(jsonRq, LOG);
                if (!checkResponseStatus(jsResp, jsonRq)) {
                    continue;
                }
                List<ReportCampaignResp> reps = jsResp.getResponseObject(ReportCampaignResp.class);
            	for(ReportCampaignResp rp : reps) {
            		sendIds.add(rp.getID_SEND().toString());
            	}                	
                campaignReportWriter.writeAllResults(CampaignReportWrapper.Builder.build(reps, cId));

            }
            System.out.println("Downloading RAW data.");
            /*Retrieve data using RAW functions*/
            append = true;
            for (String cId : campaignIds) {

                //RAW MESSAGES
                if (datasetsToGet.contains(KBCParameters.REQUEST_TYPE.RAW_MESSAGES.name())) {
                    boolean hasNextData = true;

                    Long lastId;
                    if (lastState != null) {
                        lastId = lastState.getRawMessagesLastId().get(cId);
                    } else {
                        lastId = null;
                    }

                    while (hasNextData) {
                        jsonRq = new RawMessages(cId, lastId, null, null);
                        jsResp = (MailkitJsonResponse) jsonClient.executeRequest(jsonRq, LOG);
                        if (!checkResponseStatus(jsResp, jsonRq)) {
                            System.err.println("Unable to retrieve all records. Will retry on next run starting with ID: " + lastId);
                            break;
                            //TODO: persist last ID
                        }

                        List<String> ids = new ArrayList<>();
                       	List<RawMessage> reps = jsResp.getResponseObject(RawMessage.class);
                    	for(RawMessage rp : reps) {
                    		ids.add(rp.getID_send_message().toString());
                    	}                	
                        rawMsgWriter.writeAllResults(reps);
                        //has next data?
                        if (!ids.isEmpty()) {
                            lastId = Long.valueOf(ids.get(ids.size() - 1));
                            lastId++;
                        } else {
                            hasNextData = false;
                        }

                        Thread.sleep(REQUEST_WAIT_INTERVAL);
                    }
                    if (lastState != null) {
                        lastState.getRawMessagesLastId().put(cId, lastId);
                    }
                }
                //RAW RESPONSES
                if (datasetsToGet.contains(KBCParameters.REQUEST_TYPE.RAW_RESPONSES.name())) {
                    boolean hasNextData = true;

                    Long lastId;
                    if (lastState != null) {
                        lastId = lastState.getRawResponsesLastId().get(cId);
                    } else {
                        lastId = null;
                    }

                    while (hasNextData) {
                        jsonRq = new RawResponses(cId, null, null, lastId, null);
                        jsResp = (MailkitJsonResponse) jsonClient.executeRequest(jsonRq, LOG);
                        if (!checkResponseStatus(jsResp, jsonRq)) {
                            System.err.println("Unable to retrieve all records. Will retry on next run starting with ID: " + lastId);
                            break;
                            //TODO: persist last ID
                        }

                        List<String> ids = new ArrayList<>();
                       	List<RawResponse> reps = jsResp.getResponseObject(RawResponse.class);
                    	for(RawResponse rp : reps) {
                    		ids.add(rp.getID_log().toString());
                    	}                	
                        rawResponseWriter.writeAllResults(reps);
                        //has next data?
                        if (!ids.isEmpty()) {
                            lastId = Long.valueOf(ids.get(ids.size() - 1));
                            lastId++;
                        } else {
                            hasNextData = false;
                        }

                        Thread.sleep(REQUEST_WAIT_INTERVAL);
                    }
                    if (lastState != null) {
                        lastState.getRawResponsesLastId().put(cId, lastId);
                    }
                }
                //RAW BOUNCES
                if (datasetsToGet.contains(KBCParameters.REQUEST_TYPE.RAW_BOUNCES.name())) {
                    boolean hasNextData = true;

                    Long lastId;
                    if (lastState != null) {
                        lastId = lastState.getRawBouncesLastId().get(cId);
                    } else {
                        lastId = null;
                    }

                    while (hasNextData) {
                        jsonRq = new RawBounces(cId, null, lastId, null);
                        jsResp = (MailkitJsonResponse) jsonClient.executeRequest(jsonRq, LOG);
                        if (!checkResponseStatus(jsResp, jsonRq)) {
                            System.err.println("Unable to retrieve all records. Will retry on next run starting with ID: " + lastId);
                            break;
                            //TODO: persist last ID
                        }
                        List<String> ids = new ArrayList<>();
                       	List<RawBounce> reps = jsResp.getResponseObject(RawBounce.class);
                    	for(RawBounce rp : reps) {
                    		ids.add(rp.getID_UNDELIVERED_LOG().toString());
                    	}                	
                        rawBounceWriter.writeAllResults(reps);
                        //has next data?
                        if (!ids.isEmpty()) {
                            lastId = Long.valueOf(ids.get(ids.size() - 1));
                            lastId++;
                        } else {
                            hasNextData = false;
                        }

                        Thread.sleep(REQUEST_WAIT_INTERVAL);
                    }
                    if (lastState != null) {
                        lastState.getRawBouncesLastId().put(cId, lastId);
                    }
                }
            }

            /*Get data for all messages*/
            List<String> linkIds;
            boolean firstRun = true;

            System.out.println("Downloading campaign send reports.");
            for (String sId : sendIds) {
                if (config.getParams().getDatasets().contains(KBCParameters.REQUEST_TYPE.MSG_BOUNCES.name())) {
                    Thread.sleep(REQUEST_WAIT_INTERVAL);
                    jsonRq = new MsgBounces(sId);
                    jsResp = (MailkitJsonResponse) jsonClient.executeRequest(jsonRq, LOG);
                    if (!checkResponseStatus(jsResp, jsonRq)) {
                        continue;
                    }
                    List<MsgBouncesResp> reps = jsResp.getResponseObject(MsgBouncesResp.class);
                    msgBounceWriter.writeAllResults(MsgBounceWrapper.Builder.build(reps, sId));
                }

                if (config.getParams().getDatasets().contains(KBCParameters.REQUEST_TYPE.MSG_FEEDBACK.name())) {
                    Thread.sleep(REQUEST_WAIT_INTERVAL);
                    jsonRq = new MsgFeedback(sId, null);
                    jsResp = (MailkitJsonResponse) jsonClient.executeRequest(jsonRq, LOG);
                    if (!checkResponseStatus(jsResp, jsonRq)) {
                        continue;
                    }
                    List<MsgFeedbackResp> reps = jsResp.getResponseObject(MsgFeedbackResp.class);
                    msgFeedbackWriter.writeAllResults(MsgFeedbackWrapper.Builder.build(reps, sId));
                }

                if (config.getParams().getDatasets().contains(KBCParameters.REQUEST_TYPE.MSG_RECIPIENTS.name())) {
                    Thread.sleep(REQUEST_WAIT_INTERVAL);
                    jsonRq = new MsgRecipients(sId);
                    jsResp = (MailkitJsonResponse) jsonClient.executeRequest(jsonRq, LOG);
                    if (!checkResponseStatus(jsResp, jsonRq)) {
                        continue;
                    }
                    List<MsgRecipientsResp> reps = jsResp.getResponseObject(MsgRecipientsResp.class);
                    msgRecipientsWriter.writeAllResults(MsgRecipientWrapper.Builder.build(reps, sId));
                }

                if (config.getParams().getDatasets().contains(KBCParameters.REQUEST_TYPE.REPORT_MSG.name())) {
                    Thread.sleep(REQUEST_WAIT_INTERVAL);
                    jsonRq = new ReportMessage(sId);
                    jsResp = (MailkitJsonResponse) jsonClient.executeRequest(jsonRq, LOG);
                    if (!checkResponseStatus(jsResp, jsonRq)) {
                        continue;
                    }
                    List<ReportMsgResp> reps = jsResp.getResponseObject(ReportMsgResp.class);
                    msgReportWriter.writeAllResults(ReportMsgWrapper.Builder.build(reps, sId));
                }

                if (config.getParams().getDatasets().contains(KBCParameters.REQUEST_TYPE.MSG_LINKS.name())) {
                    Thread.sleep(REQUEST_WAIT_INTERVAL);
                    jsonRq = new MsgLinks(sId);
                    jsResp = (MailkitJsonResponse) jsonClient.executeRequest(jsonRq, LOG);
                    checkResponseStatus(jsResp, jsonRq);
                    List<MsgLinksResp> reps = jsResp.getResponseObject(MsgLinksResp.class);
                    linkIds = new ArrayList<>();
                    		for(MsgLinksResp rp : reps) {
                    			linkIds.add(rp.getID_URL().toString());
                        	} 
                    msgLinksWriter.writeAllResults(MsgLinksWrapper.Builder.build(reps, sId));

                    //link visitors
                    if (config.getParams().getDatasets().contains(KBCParameters.REQUEST_TYPE.LINKS_VISITORS.name())) {
                        for (String urlId : linkIds) {
                            Thread.sleep(REQUEST_WAIT_INTERVAL);
                            jsonRq = new MsgLinksVisitors(sId, urlId);
                            jsResp = (MailkitJsonResponse) jsonClient.executeRequest(jsonRq, LOG);
                            if (!checkResponseStatus(jsResp, jsonRq)) {
                                continue;
                            }     
                            List<MsgLinksVisitorResp> repsVis = jsResp.getResponseObject(MsgLinksVisitorResp.class);
                            msgLinkVisitorsWriter.writeAllResults(MsgLinkVisitorWrapper.Builder.build(repsVis, sId, urlId));
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
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        jsonClient.cleanupTempFolder();

        /*Build manifest files*/
        File folder = new File(outTablesPath);
        File[] fileList = folder.listFiles();
        for (File f : fileList) {
            try {
                //build manifest file
                ManifestFile man = null;
                /*check for special cases - QUICK FIX*/
                switch (f.getName()) {
                    case "raw_messages.csv":
                        man = new ManifestFile(null, true, new String[]{"ID_send_message"}, ",", "\"");
                        break;
                    case "raw_bounces.csv":
                    	 man = new ManifestFile(null, true, new String[]{"ID_UNDELIVERED_LOG"}, ",", "\"");
                         break;
                    case "raw_responses.csv":
                        man = new ManifestFile(null, true, new String[]{"ID_log"}, ",", "\"");
                        break;
                    default:
                        man = new ManifestFile(null, false, null, ",", "\"");
                        break;
                }
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
            System.err.println("Unable to write state file! " + ex.getMessage());
        }

        try {
			closeWriters(config);
		} catch (Exception e) {
			 System.err.println("Unable to write results! " + e.getMessage());
			 System.exit(1);
		}
        System.out.println("Download completed successfuly..");
    }

    private static void closeWriters(KBCConfig config) throws Exception {
    	campaignReportWriter.closeAndRetrieveMetadata();

		Set<String> datasetsToGet = config.getParams().getDatasets();
		if (config.getParams().getDatasets().contains(KBCParameters.REQUEST_TYPE.REPORT.name())) {
			reportWriter.closeAndRetrieveMetadata();
		}

		if (datasetsToGet.contains(KBCParameters.REQUEST_TYPE.RAW_MESSAGES.name())) {
			rawMsgWriter.closeAndRetrieveMetadata();
		}
		 //RAW RESPONSES
        if (datasetsToGet.contains(KBCParameters.REQUEST_TYPE.RAW_RESPONSES.name())) {
        	rawResponseWriter.closeAndRetrieveMetadata();
        }
      //RAW BOUNCES
        if (datasetsToGet.contains(KBCParameters.REQUEST_TYPE.RAW_BOUNCES.name())) {
        	rawBounceWriter.closeAndRetrieveMetadata();
        }

        //standard reports
        if (config.getParams().getDatasets().contains(KBCParameters.REQUEST_TYPE.MSG_BOUNCES.name())) {
        	msgBounceWriter.closeAndRetrieveMetadata();
        }
        if (config.getParams().getDatasets().contains(KBCParameters.REQUEST_TYPE.MSG_FEEDBACK.name())) {
        	msgFeedbackWriter.closeAndRetrieveMetadata();
        }
        if (config.getParams().getDatasets().contains(KBCParameters.REQUEST_TYPE.MSG_RECIPIENTS.name())) {
        	msgRecipientsWriter.closeAndRetrieveMetadata();
        }
        if (config.getParams().getDatasets().contains(KBCParameters.REQUEST_TYPE.REPORT_MSG.name())) {
        	msgReportWriter.closeAndRetrieveMetadata();
        }
        if (config.getParams().getDatasets().contains(KBCParameters.REQUEST_TYPE.MSG_LINKS.name())) {
        	msgLinksWriter.closeAndRetrieveMetadata();
        }
        if (config.getParams().getDatasets().contains(KBCParameters.REQUEST_TYPE.LINKS_VISITORS.name())) {
        	msgLinkVisitorsWriter.closeAndRetrieveMetadata();
        }
		
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

	private static void initWriters(KBCConfig config, String outTablesPath) throws Exception {
		campaignReportWriter = new DefaultBeanResultWriter<>("campaignReports.csv", null);
		campaignReportWriter.initWriter(outTablesPath, CampaignReportWrapper.class);

		Set<String> datasetsToGet = config.getParams().getDatasets();
		if (config.getParams().getDatasets().contains(KBCParameters.REQUEST_TYPE.REPORT.name())) {
			reportWriter = new DefaultBeanResultWriter<>("summaryReport.csv", null);
			reportWriter.initWriter(outTablesPath, keboola.mailkit.extractor.mailkitapi.response.ReportResp.class);
		}

		if (datasetsToGet.contains(KBCParameters.REQUEST_TYPE.RAW_MESSAGES.name())) {
			rawMsgWriter = new DefaultBeanResultWriter<>("raw_messages.csv", null);
			rawMsgWriter.initWriter(outTablesPath, RawMessage.class);
		}
		 //RAW RESPONSES
        if (datasetsToGet.contains(KBCParameters.REQUEST_TYPE.RAW_RESPONSES.name())) {
        	rawResponseWriter = new DefaultBeanResultWriter<>("raw_responses.csv", null);
        	rawResponseWriter.initWriter(outTablesPath, RawResponse.class);
        }
      //RAW BOUNCES
        if (datasetsToGet.contains(KBCParameters.REQUEST_TYPE.RAW_BOUNCES.name())) {
        	rawBounceWriter = new DefaultBeanResultWriter<>("raw_bounces.csv", null);
        	rawBounceWriter.initWriter(outTablesPath, RawBounce.class);
        }

        //standard reports
        if (config.getParams().getDatasets().contains(KBCParameters.REQUEST_TYPE.MSG_BOUNCES.name())) {
        	msgBounceWriter = new DefaultBeanResultWriter<>("bounces.csv", null);
        	msgBounceWriter.initWriter(outTablesPath, MsgBounceWrapper.class);
        }
        if (config.getParams().getDatasets().contains(KBCParameters.REQUEST_TYPE.MSG_FEEDBACK.name())) {
        	msgFeedbackWriter = new DefaultBeanResultWriter<>("feedback.csv", null);
        	msgFeedbackWriter.initWriter(outTablesPath, MsgFeedbackWrapper.class);
        }
        if (config.getParams().getDatasets().contains(KBCParameters.REQUEST_TYPE.MSG_RECIPIENTS.name())) {
        	msgRecipientsWriter = new DefaultBeanResultWriter<>("recipients.csv", null);
        	msgRecipientsWriter.initWriter(outTablesPath, MsgRecipientWrapper.class);
        }
        if (config.getParams().getDatasets().contains(KBCParameters.REQUEST_TYPE.REPORT_MSG.name())) {
        	msgReportWriter = new DefaultBeanResultWriter<>("messageReport.csv", null);
        	msgReportWriter.initWriter(outTablesPath, ReportMsgWrapper.class);
        }
        if (config.getParams().getDatasets().contains(KBCParameters.REQUEST_TYPE.MSG_LINKS.name())) {
        	msgLinksWriter = new DefaultBeanResultWriter<>("links.csv", null);
        	msgLinksWriter.initWriter(outTablesPath, MsgLinksWrapper.class);
        }
        if (config.getParams().getDatasets().contains(KBCParameters.REQUEST_TYPE.LINKS_VISITORS.name())) {
        	msgLinkVisitorsWriter = new DefaultBeanResultWriter<>("linksVisitors.csv", null);
        	msgLinkVisitorsWriter.initWriter(outTablesPath, MsgLinkVisitorWrapper.class);
        }

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
