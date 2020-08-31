# README #

MDISCIS is a java program which acts as an information system for Minidiscs. Minidiscs do not allow artists and titles to be saved as track information. As a consequence, I developed MDISCIS in January 2003 as a solution to this problem. It acts as an information system for Minidiscs. The program has since then been regularly updated for new Java versions up to and including Java 8. MDISCIS is an open source project but is no longer in active development.

## Repository contains current version and source code of MDISCIS ##

*   This repository contains the current version of MDISCIS and the current source code in Java. The master branch builds the current version of MDISCIS (2.1.2). The develop branch builds the next version of MDISCIS (currently 2.2.0-SNAPSHOT).

### How to run MDISCIS without checking out the code base ###

You need the Java 8 Runtime Enviroment (or later) installed on your computer. If you are not sure which version of Java you have, run “java -version” from the command prompt (Windows) or from a terminal (Linux/Mac). You can download the latest version of the Java Runtime Environment <a href="http://java.sun.com/">here</a>.

*   Windows: <a href="https://www.github.com/daveajlee/mdiscis/blob/master/downloads/mdiscis-2.1.2-setup.exe">Setup File</a> (Download and run this file and follow the onscreen instructions). After installation, you can run MDISCIS from the start menu or the desktop. MDISCIS contains an on-line help system to answer queries and provide further help during use of MDISCIS.
*   Linux/Mac: <a href="https://www.github.com/daveajlee/mdiscis/blob/master/downloads/mdiscis-2.1.2.jar">JAR File</a> (Download the file to a directory of your choice). After installation, open a console or terminal and navigate to the chosen folder. Once in this directory, run java -jar “mdiscis-2.1.2.jar”  MDISCIS contains an on-line help system to answer queries and provide further help during use of MDISCIS.

### Checkout and Contribute ###

This repository contains the current version of MDISCIS and the current source code in Java. The master branch builds the current version of MDISCIS (2.1.2). The develop branch builds the next version of MDISCIS (currently 2.2.0-SNAPSHOT):

*   Clone the git branch and import the project in your favourite IDE.
*   The current main class is MDISCISGUI.
*   Dependencies are managed through the pom.xml file and Apache Maven.
*   No database configuration is currently used.
*   JUnit tests can be run either individually or collectively in the IDE or through maven.
*   Releases can be built through Maven - this should happen only after permission from the Repo Owner!

## Contribution guidelines ##

*   Before developing a feature please discuss your ideas with the Repo Owner.
*   Functional Tests should be written through JUnit and ensure a code coverage of at least 70% - use SonarQube to check code coverage.
*   Code review should take place before a release is built and involve the Repo Owner and the Contributor.

## Contact Person ##

*   Dave Lee (Repo Owner)
