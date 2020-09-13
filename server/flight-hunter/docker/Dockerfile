FROM registry.b2w.io/atlas/openjdk8:latest

ENV APP_NAME b2w-credit-api-1.0.0.jar

COPY ["b2w-credit-api-1.0.0.jar", "/application/"]

WORKDIR  /application

ENTRYPOINT java $JAVA_OPTS $EXTRA_PROPERTIES -jar $APP_NAME