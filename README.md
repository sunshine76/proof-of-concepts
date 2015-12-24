# Proof of Concepts

Repository for various proof of concepts.


##### Pre-requisites:

1. JAVA ( 1.6 or greater) 
2. Maven 3
3. Any IDE of your choice( for example Eclipse or IntelliJ)

##### Downloading source code
1. git clone https://github.com/sunshine76/proof-of-concepts.git proof-of-concepts
2. Import as Maven project using Eclipse or IntelliJ. (I have used IntelliJ)


##Environment Based properties
### Problem Description
How does one  inject different values for keys  defined in the properties file for various evnvironments. ( Dev, QA, Test)
### Solution:
Create maven profiles and define the values for keys for each environment. ( See pom.xml for more details)

### Running the project
1. Select the maven profile as test
2. Run the test case PropertyFileTestCase.java


##Reference Application
Build a reference application to experiment various concepts. 
Reference app uses
  1. spring boot for developing the reference app.
  2. Embedded h2 database.
  3. Spring MVC 
  4. Spring JPA

### Running the project
1. mvn spring-boot:run ( If you are running inside IntelliJ open Maven Projects Plugins->spring-boot->spring-boot:run)

Note : for other ways to run spring boot refer to https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-running-your-application.html

Goal (Work in Progress)
  1. Define and implement exception handling 
  2. Write pluggable aspects
  3. 90+ code coverage.





