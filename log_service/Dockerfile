FROM adoptopenjdk/openjdk11

ARG JAR_FOLDER=target
ARG JAR_NAME=log-service-1.2-SNAPSHOT.jar
WORKDIR /app
COPY ${JAR_FOLDER}/${JAR_NAME} .

ENTRYPOINT ["java","-jar", "log-service-1.2-SNAPSHOT.jar"]