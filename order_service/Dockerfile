FROM adoptopenjdk/openjdk11

ARG JAR_FOLDER=target
ARG JAR_NAME=order-service-1.3-SNAPSHOT.jar
WORKDIR /app
COPY ${JAR_FOLDER}/${JAR_NAME} .

ENTRYPOINT ["java","-jar", "order-service-1.2-SNAPSHOT.jar"]