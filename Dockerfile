FROM maven:3.8.1-openjdk-17-slim

COPY target/socktets-1.0-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]