# CustomerStatementProcessor
Requirement<br/>
This application would be responsible for receiving monthly deliveries of customer statement records. This information is delivered in two formats, CSV and XML. These records need to be validated.

<b>About the Application</b><br/>
Application has been developed keeping in mind that 
 <br/> Can handle huge input files, even GB's !! that too without running out of memory
 <br/> Minimal hard coding
 <br/> Full concentration on business logic by being the only Java code in the application
 <br/> Spring handles the reading of input file by Spring provided classes
 <br/> Spring handles the writing of output file by Spring provided classes
 <br/> Can detect automatically the file type and process the file accordingly
 <br/> Input file location needs to the a parameter passed to the program increasing the application re-usability
 <br/> Output file location needs to the a parameter passed to the program increasing the application re-usability
 <br/> Application Testability not limited to only business logic but extended to end to end testing
 
 How to Launch the application:<br/>
  Local Machine:
  <br/> Import the project as existing Maven project
  <br/> Pass the following parameters as Program Arguments for csv file run
  		classpath:record.csv file:ERROR_REPORT.csv
  <br/> Pass the following parameters as Program Arguments for xml file run
  		classpath:record.xml file:ERROR_REPORT.csv
  		
  Linux Box:
  <br/> Perform a maven build and collect the jar
  <br/> Pass the input file location and output file location while instantiating the application 
  
  
  
  