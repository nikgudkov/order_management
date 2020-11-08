FROM adoptopenjdk/openjdk11

ARG JAR_FOLDER=target
ARG JAR_NAME=demo-0.0.1-SNAPSHOT.jar
WORKDIR /app
COPY ${JAR_FOLDER}/${JAR_NAME} .

ENTRYPOINT ["java","-jar", "demo-0.0.1-SNAPSHOT.jar"]