#Mailkit extractor
Mailkit extractor docker component for Keboola Connection.

##Funcionality

The component allows retrieving all datasets provided by Mailkit Statistics API along with list of all available campaigns. 

**List of supported datasets:**

- **CAMPAIGNS**
    - List of all campaigns available. The period and campaignIds parameters do not apply.
      ([mailkit.campaigns.list](https://www.mailkit.eu/cz/napoveda-pomoc/dokumentace/api/sprava-kampani/mailkitcampaignslist/))

- **REPORT**
    - Basic campaign summary report.
      ([mailkit.report](https://www.mailkit.eu/cz/napoveda-pomoc/dokumentace/api/statistiky/mailkitreport/))
- **REPORT_CAMPAIGN**
    - Reports for specific campaigns.
     ([mailkit.report.campaign](https://www.mailkit.eu/cz/napoveda-pomoc/dokumentace/api/statistiky/mailkitreportcampaign/))

- **REPORT_MSG**
    - Statistics about messages sent within campaigns
    ([mailkit.report.message](https://www.mailkit.eu/cz/napoveda-pomoc/dokumentace/api/statistiky/mailkitreportmessage/))

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

**NOTE:** Due to the current API architecture, the application might generate large number of API requests, especially when retrieving data within long time periods. For example to retrieve datasets `LINKS` and `LINKS_VISITORS` for all campaigns the app generates API request for each campaign to retrieve messages, then for each message to retrieve links and then for each link to retrieve link_visitors dataset. Currently there is an interval of 1s between each API request. Therefore, the app run times may be very long (e.g. 2 hours). So please keep that in mind. 
##Configuration
###Parameters
- **clientId** – *(REQ)* your Mailkit client_id
- **#clientMd5** – *(REQ)* Mailkit client MD5
     hash.
- **daysPeriod** – *(OPT)* specifies the date
     period of returned data in number of days. For example if the value is 5
     and dateTo is not specified, data between today and five days ago will be
     retrieved. 
- **dateFrom** – *(OPT)* begin of time period
     of extracted data. Format `YYYY-MM-DD`
- **dateTo** – *(OPT)* end of time period of
     extracted data. Format `YYYY-MM-DD`
- **datasets** – list of datasets to download. Supported values:    [`ALL` ,`CAMPAIGNS`, `REPORT`, `REPORT_CAMPAIGN`, `REPORT_MSG`, `MSG_RECIPIENTS`, `MSG_FEEDBACK`, `MSG_LINKS`, `LINKS_VISITORS`,         `MSG_BOUNCES`]. Description of each dataset is provided in former section.
- **campaignIds** – list of campaign IDs to download. Only data regarding specified campaigns will be downloaded. If not specified, data of all campaigns in specified interval is downloaded. 
- **sinceLastRun** – download data in time period since last run (retrieves data since last download was performed) *DEFAULT: FALSE*. ***NOTE***: If `dateFrom` is specified, all data since dateFrom until NOW
     will be downloaded on the first run, each other consequent run will ignore the dateFrom parameter and retrieve data in period since lastRun.

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
