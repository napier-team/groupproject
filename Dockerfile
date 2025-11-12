# Stage 1: Build the application
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -B package -DskipTests

# Stage 2: Run the application
# Uses Amazon Corretto 17 as required by course specifications
FROM amazoncorretto:17
WORKDIR /app
COPY --from=build /app/target/app.jar app.jar
# Defines the entry point for the container
ENTRYPOINT ["java", "-jar", "app.jar"]