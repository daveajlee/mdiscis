# README #

Thank you for your interest in MDISCIS - Minidisc Indexing Software. This repository contains the current version of MDISCIS and the current source code in Java. In this document you can read the steps that are necessary to get MDISCIS up and running.

### What is this repository for? ###

* This repository contains the current version of MDISCIS and the current source code in Java. The master branch builds the current version of MDISCIS (2.1.2). The develop branch builds the next version of MDISCIS (currently 2.2.0-SNAPSHOT).

### How do I get set up? ###

* Clone the git branch and import the project in your favourite IDE.
* The current main class is MDISCISGUI.
* Dependencies are managed through the pom.xml file and Apache Maven.
* No database configuration is currently used.
* JUnit tests can be run either individually or collectively in the IDE or through maven.
* Releases can be built through Maven - this should happen only after permission from the Repo Owner!

### Contribution guidelines ###

* Before developing a feature please discuss your ideas with the Repo Owner.
* Functional Tests should be written through JUnit and ensure a code coverage of at least 70% - use SonarQube to check code coverage.
* Code review should take place before a release is built and involve the Repo Owner and the Contributor.

### Who do I talk to? ###

* Dave Lee (Repo Owner)