FROM azul/zulu-openjdk:21.0.3-jdk
LABEL authors="ahuynh359"
ARG JAR_FILE=target/*.jar
COPY ./target/registry-service-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
