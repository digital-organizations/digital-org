# Digital Org Application API's

Circle CI   
[![Digital Org](https://circleci.com/gh/digital-organizations/digital-org.svg "CircleCI status")](https://circleci.com/gh/digital-organizations/digital-org)

Travis    
[![Build Status](https://travis-ci.com/digital-organizations/digital-org.svg?token=uk63pxdAgoFGezW3mmw9&branch=master)](https://travis-ci.com/digital-organizations/digital-org)

Note : Older builds are on [Circle CI](https://app.circleci.com/gh/digital-organizations/). Due to Codecov integration of migrated to Travis.

Codacy  
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/a0080fc3dbe7469281991a52bfa02247)](https://www.codacy.com/gh/digital-organizations/digital-org/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=digital-organizations/digital-org&amp;utm_campaign=Badge_Grade)

Codecov   
[![codecov](https://codecov.io/gh/digital-organizations/digital-org/branch/master/graph/badge.svg?token=2UCY3W0QLK)](https://codecov.io/gh/digital-organizations/digital-org)

NPM  
[![npm version](https://badge.fury.io/js/%40angular%2Fcore.svg)](https://www.npmjs.com/@angular/core)

## 1. How to start in local
```
$ git clone [https://github.com/abhayshukla04/digital-org.git](https://github.com/abhayshukla04/digital-org.git)
$ cd digital-org
$ mvn spring-boot:run

```

http://localhost:8080/swagger-ui/#/

Heroku CLI :
```
$heroku login
$ heroku apps
$ heroku logs -t --app digital-org
```
## 2. Heroku Swagger UI
Swagger UI :https://digital-org.herokuapp.com/swagger-ui/#/

## DB Design :

![digital](src/main/resources/digital.png)

SQL : (src/main/resources/sql/digital.sql)



## 3. Tech Stack
```
spring-boot : 2.3.3.RELEASE
PostgreSQL : 12
```
## 4. Design Diagram

Application URL : https://digital-organizations.github.io/digital-org.github.io/land

## 5. Monitoring 
```
Elastic  : https://c013054f3b784fcfa1caa48efc9d0f18.us-east-1.aws.found.io:9243
Kibana   : https://da02d396d35a43068d3ae7f1114cae90.us-east-1.aws.found.io:9243
username : elastic
password : a0Ny8XFNvg7u1znkZeeaO3mR
```

#####APM Configuration
```
java -javaagent:/Downloads/elastic-apm-agent-1.18.0.jar -Delastic.apm.service_name=digital-org -Delastic.apm.server_urls=https://fa91e1be347d4b319d59967c6d5d6b0e.apm.us-east-1.aws.cloud.es.io:443 -Delastic.apm.secret_token=gf5vlw7rzG1I01MAKv -Delastic.apm.application_packages=com.engg.digitalorg -jar digital-org-0.0.1-SNAPSHOT.jar
```
