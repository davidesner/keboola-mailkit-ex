#Mailkit extractor
Mailkit extractor docker component for Keboola Connection.

##Funcionality

The component allows retrieving all datasets provided by Mailkit Reporting API along with list of all available campaigns. Retrieved datasets consits of basic statistics about campaings (`REPORT`,`REPORT_CAMPAIGN`, `REPORT_MSG`) and  `RAW`datasets. 

**NOTE:**
`RAW` functions are used for retrieval of large volume of data. Data for specified campaigns are downloaded from the begining of its existence, hence limiting retrieved campaigns by `CAMPAIGN IDs` parameter is recommended. At first run all data until current date is downloaded, each consecutive run retrieves data starting from the record retrieved last time. Raw datasets are uploaded **incrementaly**.

###List of supported datasets:

- **CAMPAIGNS**
    - List of all campaigns available. The period and campaignIds parameters do not apply.
      ([mailkit.campaigns.list](https://www.mailkit.eu/cz/napoveda-pomoc/dokumentace/api/sprava-kampani/mailkitcampaignslist/))

- **REPORT**
    - Basic campaign summary report. The period parameters do not apply.
      ([mailkit.report](https://www.mailkit.eu/cz/napoveda-pomoc/dokumentace/api/statistiky/mailkitreport/))
- **REPORT_CAMPAIGN**
    - Reports for specific campaigns. 
     ([mailkit.report.campaign](https://www.mailkit.eu/cz/napoveda-pomoc/dokumentace/api/statistiky/mailkitreportcampaign/))
- **REPORT_MSG**
    - Statistics about messages sent within campaigns
    ([mailkit.report.message](https://www.mailkit.eu/cz/napoveda-pomoc/dokumentace/api/statistiky/mailkitreportmessage/))
- **RAW_MESSAGES**
    - Raw records about all message recipients and sent messages. The period parameters do not apply.
    ([mailkit.report.message](https://www.mailkit.eu/en/help-support/documentation/api/reporting/mailkitreportrawmessages/)
- **RAW_BOUNCES**
    - Raw records about all message delivery failures of a campaign or campaign delivery. The period parameters do not apply.
    ([mailkit.report.message](https://www.mailkit.eu/en/help-support/documentation/api/reporting/mailkitreportrawbounces/)
- **RAW_RESPONSES**
 - Raw records about all responses by the recipients to the messages sent. The period parameters do not apply.
    ([mailkit.report.message](https://www.mailkit.eu/en/help-support/documentation/api/reporting/mailkitreportrawbounces/))

####Deprecated recursive functions
- **MSG_RECIPIENTS**
    - Statistics about message recipients
    ([mailkit.report.message.recipients](https://www.mailkit.eu/cz/napoveda-pomoc/dokumentace/api/statistiky/mailkitreportmessagerecipients/))

- **MSG_FEEDBACK**
    - Campaign messages feedback. Returns data on default DAILY aggregation level. ([mailkit.report.message.feedback](https://www.mailkit.eu/cz/napoveda-pomoc/dokumentace/api/statistiky/mailkitreportmessagefeedback/))

- **MSG_BOUNCES**
    - List of undelivered messages.
    ([mailkit.report.message.bounces](https://www.mailkit.eu/cz/napoveda-pomoc/dokumentace/api/statistiky/mailkitreportmessagebounces/))

- **MSG_LINKS**
    - Links performance report for each campaign message. 
    ([mailkit.report.message.links](https://www.mailkit.eu/cz/napoveda-pomoc/dokumentace/api/statistiky/mailkitreportmessagelinks/))

- **LINKS_VISITORS**
    - List of visitors who interacted with message links.
      ([mailkit.report.message.links.visitors](https://www.mailkit.eu/cz/napoveda-pomoc/dokumentace/api/statistiky/mailkitreportmessagelinksvisitors/))

**NOTE:** The use of RAW datasets is recommended over the old recursive function. The new RAW queries allows to retrieve large volume of data in relatively short time period. 

When using deprecated functions, the application might generate large number of API requests, especially when retrieving data within long time periods. For example to retrieve datasets `LINKS` and `LINKS_VISITORS` for all campaigns the app generates API request for each campaign to retrieve messages, then for each message to retrieve links and then for each link to retrieve link_visitors dataset. Therefore, the app run times may be longer.
##Configuration
###Parameters
- **Client ID** – *(REQ)* your Mailkit client_id
- **Client MD5** – *(REQ)* Mailkit client MD5
     hash.
- **Days Period** – *(OPT)* specifies the date
     period of returned data in number of days. For example if the value is 5
     and dateTo is not specified, data between today and five days ago will be
     retrieved. 
- **Date From** – *(OPT)* begin of time period
     of extracted data. Format `YYYY-MM-DD`
- **Date To** – *(OPT)* end of time period of
     extracted data. Format `YYYY-MM-DD`
- **Datasets** – list of datasets to download. Supported values:    [`ALL` ,`CAMPAIGNS`, `REPORT`, `REPORT_CAMPAIGN`, `REPORT_MSG`, `MSG_RECIPIENTS`, `MSG_FEEDBACK`, `MSG_LINKS`, `LINKS_VISITORS`, `MSG_BOUNCES`]. Description of each dataset is provided in former section.
- **Campaign IDs** – list of campaign IDs to download. Only data regarding specified campaigns will be downloaded. If not specified, data of all campaigns in specified interval is downloaded. 
- **sinceLastRun** – download data in time period since last run (retrieves data since last download was performed) *DEFAULT: FALSE*. ***NOTE***: If `dateFrom` is specified, all data since dateFrom until NOW
     will be downloaded on the first run, each other consequent run will ignore the dateFrom parameter and retrieve data in period since lastRun.

**NOTE:** If `Date From` and `Date To` parameters are not specified, the API retrieves data in default intervals as specified below:
- Last month for `REPORT` dataset
- last 3 months for `REPORT_CAMPAIGN` dataset

Moreover, if `Date From` is not specified, the `Date To` parameter is ignored.

The period parameters are not appliable to RAW functions. 

##Output
In current version output tables are NOT loaded into the Storage incrementally. Which means, that datasets in input bucket will be rewritten each run.

 

##Sample configurations / use cases
###Use case 1
Download all datasets and include data for last 30 days. 
![](https://raw.githubusercontent.com/davidesner/keboola-mailkit-ex/master/img/use_case1.png)

###Use case 2
Downloads ALL datasets and includes data since 1 of May 2016 until now on first run. Each other consequent run of this configuration will download data in period since last run until NOW.
![](https://raw.githubusercontent.com/davidesner/keboola-mailkit-ex/master/img/use_case2.png)

###Use case 3
Downloads ALL datasets and includes data for last 30 days only for campaign with ID `4321`. 
Note that datasets `REPORT` will always contain all campaigns in given period, the `campaignIds` parameter only applies to message report data.
![](https://raw.githubusercontent.com/davidesner/keboola-mailkit-ex/master/img/use_case3.png)
