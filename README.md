Test task.
==========

It is a solution for the task described below.


Requirements
============

Here is the instructions for the 4-hour assessment task.
 
You can use your preferred IDE - however you work best.  If you are using Windows, please make sure you write the code in a platform independent way (e.g. System.getProperty(“path.separator”)).  It must be able to run on Linux.
 
Please create classes to perform as many as possible of the following items (*Please make sure to politely access the pages*):
 
Task 1: Create and initialize a Maven project
 
This is just a preparation for the next tasks.
 
Task 2: Detect & parse Ubuntu List of current releases
 
By using Apache HttpClient,JSoup (and regular expressions only if necessary), retrieve the following page and parse useful information about ubuntu’s current list of releases:
https://wiki.ubuntu.com/ReleaseTeam
 
It should include at least the following fields for each release:
*      Version
*      Code Name
*      Docs
*      Release date
*      End of life date

Print the parsed information in pretty format in console.
Optional Question:Is there a way you can detect if the page structure has changed ?
 
Task 3: Implement a unit test class
 
By using TestNG, create a unit test to test the parsing functions required in the task 1. Please DON’T include Http fetching logic in the unit test.
 
Task 4: Save the parsed result into HSQLDB
 
By using JPA+Hibernate, save the parsed result into HSQLDB, and then read all records from the HSQLDB and print in console.
 