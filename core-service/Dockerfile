FROM azul/zulu-openjdk:21.0.3-jdk
LABEL authors="ahuynh359"
ARG JAR_FILE=target/*.jar
COPY ./target/core-service-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
