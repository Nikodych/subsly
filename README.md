Subsly Test Framework
-------------

This framework is developed as part of a job task

#### MAIN GOAL:

- Recommend yourself

### Getting started:

01. install JDK (v. 11.0.19):
    * install jdk (v. 11.0.19)
    * set jdk path to 'JAVA_HOME' environment variable
    * add %JAVA_HOME%\bin to 'Path' environment variable
02. install maven:
    * install maven
    * set maven path to 'M2_HOME' environment variable
    * add %M2_HOME%\bin to 'Path' environment variable
03. install Allure (_optional_)
04. clone project
05. use test data from env.properties **OR** throw up as environment variables

### How to run:

* #### Locally:
    01. Launch all tests:
        - _mvn clean test_
    02. Launch specific test or test class:
        - _mvn clean test -Dtest="TEST_CLASS#TEST_METHOD"_ **OR** _mvn clean test -Dtest="TEST_CLASS"_
    03. Generate report, execute:
        - _mvn allure:report_ **OR(in case of installed allure)** _allure generate -c
          target/_ [location for the allure-report folder is determined based on the current directory where the command is executed]
    04. Open report, execute:
        - _mvn allure:serve_ **OR(in case of installed allure)** _allure serve target/allure-results/_ **OR** _allure
          open target/allure-report/_