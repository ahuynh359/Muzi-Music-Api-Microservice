FROM azul/zulu-openjdk:21.0.3-jdk
LABEL authors="ahuynh359"
ARG JAR_FILE=target/*.jar
COPY ./target/api-gateway-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8765
ENTRYPOINT ["java","-jar","/app.jar"]
