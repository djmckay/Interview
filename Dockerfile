FROM amazoncorretto:17-alpine-jdk
MAINTAINER djmckay.tech
COPY target/github-action-artifact.jar github-action-artifact.jar
ENTRYPOINT ["java","-jar","/weather-service-0.0.1-SNAPSHOT.jar"]
