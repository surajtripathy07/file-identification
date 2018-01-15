 ## Overview:
The following is a maven project. 
The core logic of the project takes fileName as input and extracts fileName specific informations form various (in this case 2) data-sources.
The code can be tested via two input mechanisms.

## Project Structure:
The source code can be found under src/main/java
The functional code has been divided into logical units which acts as layers of access.
1. core
2. ws
3. console

1. core
	This package contains the core logic of taking a fileName and extracting information about that fileName from various data-sources.
	The package takes in 1 file-name and returns an Object containing List of Extracted Information.
	The package provides flexibility to add new DataSources and modify existing Datasources.
	The entry point class for this package is is "CollectInfoForFile.java".
	The DataSources from which information is fetched are:
	1. http://filext.com/alphalist.php - Short description. 
	Corresponding Java Class "FileExtensionFS.java"
	2. http://svn.apache.org/repos/asf/httpd/httpd/trunk/docs/conf/mime.types - MEDIA Type. 
	Corresponding Java Class "MimeTypeFS.java"

2. ws
	This package contains the web-api specific code. The code in this package exposes two api's.
a> v1/ping
	API-Type: GET
	HEADERS: NONE

	This api is just a test api to check if the server is up and running and handeling request.
	It is a GET api, and returns OK as response.

b> v1/file-info
	API-Type: POST
	HEADERS: NONE
	REQUEST-BODY: ["abc.png", ".gif", "ab.pdf"]
	SAMPLE-RESPONSE:
	{
    	"message": "Success",
    	"files": [
        	{
        	    "fileName": "0.jar",
        	    "shortDescription": "Java Archive (Sun Microsystems, Inc.)",
        	    "mediaType": "application/java-archive"
        	},
        	{
        	    "fileName": "0.jar",
       		    "shortDescription": "Java Archive (Sun Microsystems, Inc.)",
        	    "mediaType": "application/java-archive"
        	},
        	{
        	    "fileName": "0.png",
        	    "shortDescription": "Fireworks Image File (Macromedia)",
        	    "mediaType": "image/png"
        	}
	}

	This api is the file-info extracting api. It takes List of comma seperated fileNames as JSON input,
	and returns structured extracted information for the list. The returned list is sorted based on fileName.
	It proccess the list in a Multithreaded environment, where multiple threads simultaneously call the core
	package.

3. console
	This package provides a Java Main Application, which takes input from command-line and prints output on
	the configured log file. It proccesses the list in a Multi-Threaded approach.
	Class Name: MainApplication.java

Both the console and webservice end-points internally call core package with the appropriate fileName.


## Build steps:

1. Navigate to the directory which has pom.xml
2. Run the below command.
mvn clean install

3. To Run the console app:
Use the following command to run the Console App

mvn exec:java -Dexec.mainClass="fileInfo.console.MainApplication" 

Pass comma seperated list of fileNames when promted to insert fileNames.
After the build is successfull, you will see the corresponding processed info in
the log/file-info.log file.

4. To Run the code through web-service:
Pre-requisite: TOMCAT should be installed on the system.

a> Set up $CATALINA_HOME environment variable
export CATALINE_HOME={path where tomcat is available}

b> Copy the created war in $CATALINA_HOME/webapps directory.
cp target/fileInfo.war $CATALINA_HOME/webapps/

c> Start tomcat by executing the startup.sh executable.
$CATALINA_HOME/bin/startup.sh

d> Verify $CATALINA_HOME/logs/file-info.log to check if the application has started.

e> User curl or equivalent means to hit the end point:

curl -X POST \
  http://localhost:8080/fileInfo/v1/file-info \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -d '["launchConfigurationHistory.xml", "tags.properties", "MainApplication.java" ]'

Sample Response:
{"message":"Success","files":[{"fileName":"MainApplication.java","shortDescription":"Java Source Code (Sun)","mediaType":"text/x-java-source"},{"fileName":"launchConfigurationHistory.xml","shortDescription":"Extensible Markup Language File ","mediaType":"application/xml"},{"fileName":"tags.properties","shortDescription":"Java Properties File (Sun)","mediaType":"NOT_FOUND"}]}


## Future scope for enhancements:

1. Current API is a blocking API. Even though the files are proccessed by multiple threads there can be scenarios when this 
takes significatly large time, as behind the scenes it is making Get Call to get the HTML and parsing response out of that.
This can be handled by making the api asyncronous and taking a callback URL along with the resquest. 
So the current fileInfo api would only take request and respond immediately after validating the JSON.
And after the processing is done by the web-service, it would post the response on the call-back URL.
