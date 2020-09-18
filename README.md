# Digital Organization API's

[![CircleCI](https://circleci.com/gh/abhayshukla04/digital-org.svg?style=svg&circle-token=31bf8b5fd55119ac94b0c8bd9b04713467d392bb)](https://circleci.com/gh/abhayshukla04/digital-org)

[![Build Status](https://travis-ci.com/digital-team-dto/digital-org.svg?token=uk63pxdAgoFGezW3mmw9&branch=master)](https://travis-ci.com/digital-team-dto/digital-org)

[![Codacy Badge](https://app.codacy.com/project/badge/Grade/e96517f467ff49e38e839728dd71d8fa)](https://www.codacy.com?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=abhayshukla04/digital-org&amp;utm_campaign=Badge_Grade)

[![codecov](https://codecov.io/gh/abhayshukla04/digital-org/branch/master/graph/badge.svg?token=2UCY3W0QLK)](https://codecov.io/gh/abhayshukla04/digital-org)

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
## 3. Design Diagram
