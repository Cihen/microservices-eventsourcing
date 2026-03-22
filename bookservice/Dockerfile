# Stage 1: Use Maven for building
FROM maven:3.8.5-openjdk-17-slim as build
WORKDIR /app

COPY ./commonservice ./commonservice
COPY ./bookservice ./bookservice

WORKDIR /app/commonservice
RUN mvn clean install -DskipTests

WORKDIR /app/bookservice
RUN mvn clean package -DskipTests

# Stage 2: Use OpenJDK for running
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=build /app/bookservice/target/*.jar bookservice.jar
EXPOSE 9001
ENTRYPOINT ["java", "-jar", "bookservice.jar"]