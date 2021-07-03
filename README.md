# README #

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/08a75b0abb064c78863218778917c385)](https://app.codacy.com/manual/dave_33/mdiscis?utm_source=github.com&utm_medium=referral&utm_content=daveajlee/mdiscis&utm_campaign=Badge_Grade_Dashboard)

![example workflow](https://github.com/daveajlee/mdiscis/actions/workflows/maven.yml/badge.svg)

MDISCIS is a java program which acts as an information system for Minidiscs. Minidiscs do not allow artists and titles to be saved as track information. As a consequence, I developed MDISCIS in January 2003 as a solution to this problem. It acts as an information system for Minidiscs. The program has since then been regularly updated for new Java versions up to and including Java 11. MDISCIS is an open source project but is no longer in active development.

## Repository contains current version and source code of MDISCIS ##

*   This repository contains the current version of MDISCIS and the current source code in Java.

### How to run MDISCIS without checking out the code base ###

You need the Java 11 Runtime Enviroment (or later) installed on your computer. If you are not sure which version of Java you have, run “java -version” from the command prompt (Windows) or from a terminal (Linux/Mac). You can download the latest version of the Java Runtime Environment <a href="http://java.sun.com/">here</a>.

*   Windows/Linux/Mac: <a href="https://github-registry-files.githubusercontent.com/248596452/b26cf100-c57d-11eb-8554-dcea56993dad?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAIWNJYAX4CSVEH53A%2F20210604%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210604T194818Z&X-Amz-Expires=300&X-Amz-Signature=605e140ff7a17e74a56533895c967e6625860c8ec161d5bf3fa255b954fa365c&X-Amz-SignedHeaders=host&actor_id=0&key_id=0&repo_id=248596452&response-content-disposition=filename%3Dmdiscis-2.2.0.jar&response-content-type=application%2Foctet-stream">JAR File</a> (Download the file to a directory of your choice). After installation, open a console or terminal and navigate to the chosen folder. Once in this directory, run java -jar “mdiscis-2.2.0.jar”  MDISCIS contains an on-line help system to answer queries and provide further help during use of MDISCIS.

### Checkout and Contribute ###

This repository contains the current version of MDISCIS and the current source code in Java:

*   Clone the git branch and import the project in your favourite IDE.
*   The current main class is MDISCISGUI.
*   Dependencies are managed through the pom.xml file and Apache Maven.
*   No database configuration is currently used.
*   JUnit tests can be run either individually or collectively in the IDE or through maven.
*   Releases can be built through Maven - this should happen only after permission from the Repo Owner!

## Contribution guidelines ##

*   Before developing a feature please discuss your ideas with the Repo Owner.
*   Functional Tests should be written through JUnit and ensure a code coverage of at least 70%.
*   Code review should take place before a release is built and involve the Repo Owner and the Contributor.

## Contact Person ##

*   Dave Lee (Repo Owner)
