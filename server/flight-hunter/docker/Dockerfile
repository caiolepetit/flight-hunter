FROM openjdk:8-jre-alpine

ENV APP_NAME flight-hunter-0.0.1.jar

COPY ["flight-hunter-0.0.1.jar", "/application/"]

WORKDIR  /application

ENTRYPOINT java -jar $APP_NAME