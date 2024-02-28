FROM amazoncorretto:17-alpine-jdk
MAINTAINER djmckay.tech

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} weather-service.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/weather-service.jar"]
