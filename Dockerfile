FROM amazoncorretto:17-alpine-jdk
MAINTAINER djmckay.tech
COPY target/weather-service-0.0.1-SNAPSHOT.jar weather-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/weather-service-0.0.1-SNAPSHOT.jar"]
