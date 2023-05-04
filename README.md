<p align="center">
<img src="https://github.com/daveajlee/daveajlee.github.io/blob/master/common/assets/img/portfolio/mdisic-logo-white.png" alt="MDISCIS" width="300" height="300">
</p>

<p align=center><a href="https://app.codacy.com/manual/dave_33/mdiscis?utm_source=github.com&utm_medium=referral&utm_content=daveajlee/mdiscis&utm_campaign=Badge_Grade_Dashboard"><img src="https://api.codacy.com/project/badge/Grade/08a75b0abb064c78863218778917c385" alt="Codacy Badge"> </a>
</p>

MDISCIS is an application for indexing Minidiscs. Minidiscs do not allow artists and titles to be saved as track information. As a consequence, I developed MDISCIS in January 2003 as a solution to this problem. It acts as an information system for Minidiscs. The program has since then been regularly updated for new Java versions up to and including Java 11. MDISCIS is an open source project but is no longer in active development.

### How to run MDISCIS without checking out the code base ###

You need the Java 11 Runtime Enviroment (or later) installed on your computer. If you are not sure which version of Java you have, run “java -version” from the command prompt (Windows) or from a terminal (Linux/Mac). You can download the latest version of the Java Runtime Environment <a href="http://java.sun.com/">here</a>.

*   Windows/Linux/Mac: <a href="https://github.com/daveajlee/mdiscis/packages/829607)">JAR File</a> (Download the file to a directory of your choice). After installation, open a console or terminal and navigate to the chosen folder. Once in this directory, run java -jar “mdiscis-2.4.0.jar”  MDISCIS contains an on-line help system to answer queries and provide further help during use of MDISCIS.

### Checkout and Contribute ###

This repository contains the current version of MDISCIS and the current source code in Java:

*   Clone the git branch and import the project in your favourite IDE.
*   The current main class is MDISCISGUI.
*   Dependencies are managed through the pom.xml file and Apache Maven.
*   No database configuration is currently used.
*   JUnit tests can be run either individually or collectively in the IDE or through maven.
*   Releases can be built through Maven - this should happen only after permission from the Repo Owner!
