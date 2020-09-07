FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} digital-org.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/digital-org.jar"]