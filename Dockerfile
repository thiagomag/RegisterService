FROM eclipse-temurin:21-jdk-alpine
MAINTAINER Thiago Magdalena
COPY build/libs/registerservice-0.0.1-SNAPSHOT.jar registerservice-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/registerservice-0.0.1-SNAPSHOT.jar"]