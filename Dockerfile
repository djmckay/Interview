FROM amazoncorretto:17-alpine-jdk
MAINTAINER djmckay.tech

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} weather-service.jar
ENTRYPOINT ["java","-jar","/weather-service.jar"]
