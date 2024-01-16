# syntax=docker/dockerfile:1.4
FROM openjdk:19-jdk
ARG JAR_FILE=target/*.jar
COPY target/sb-app.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]