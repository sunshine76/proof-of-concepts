# proof-of-concepts

Repository for various proof of concepts.


##### Pre-requisites:

1. JAVA ( 1.6 or greater) 
2. Maven 3
3. Any IDE of your choice( for example Eclipse or IntelliJ)


##Environment Based properties
### Problem Description
How does one  inject different values for keys  defined in the properties file for various evnvironments. ( Dev, QA, Test)
### Solution:
Create maven profiles and define the values for keys for each environment. ( See pom.xml for more details)

