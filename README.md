# CustomerStatementProcessor
Requirement 
This application would be responsible for receiving monthly deliveries of customer statement records. This information is delivered in two formats, CSV and XML. These records need to be validated.

About the Application
Application has been developed keeping in mind that 
 > Can handle huge input files, even GB's !! that too without running out of memory
 > Minimal hard coding
 > Full concentration on business logic by being the only Java code in the application
 > Spring handles the reading of input file by Spring provided classes
 > Spring handles the writing of output file by Spring provided classes
 > Can detect automatically the file type and process the file accordingly
 > Input file location needs to the a parameter passed to the program increasing the application re-usability
 > Output file location needs to the a parameter passed to the program increasing the application re-usability
 > Application Testability not limited to only business logic but extended to end to end testing
 
 How to Launch the application:
  Local Machine:
  > Import the project as existing Maven project
  > Pass the following parameters as Program Arguments for csv file run
  		classpath:record.csv file:ERROR_REPORT.csv
  > Pass the following parameters as Program Arguments for xml file run
  		classpath:record.xml file:ERROR_REPORT.csv
  		
  Linux Box:
  > Perform a maven build and collect the jar
  > Pass the input file location and output file location while instantiating the application 