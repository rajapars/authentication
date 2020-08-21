### For Deployment ###
# Build stage
#FROM maven:3.6.3-openjdk-8-slim AS build
#WORKDIR /app
#COPY pom.xml .
#COPY src src
#RUN mvn clean package -Dmaven.test.skip

#FROM openjdk:8-jdk-alpine
#COPY --from=build /app/target/authentication.jar authentication.jar
#ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "authentication.jar"]

### For Development ###
FROM openjdk:8-jdk-alpine
COPY target/authentication.jar authentication.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "authentication.jar"]