FROM openjdk:21-jdk-slim
MAINTAINER Thiago Magdalena
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]