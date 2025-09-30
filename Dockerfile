# Base Image: openjdk:17-jdk-slim
FROM openjdk:17-jdk-slim

# Working Directory: /app
# Sets the default working directory for subsequent commands (COPY, ENTRYPOINT).
# Using '/app' is a standard convention for application files.
WORKDIR /app

# Copy Application JAR
# Copies the 'app.jar' created by the maven-assembly-plugin.
# This filename matches the <finalName> set in pom.xml.
COPY target/app.jar /app/app.jar

# Application Port (Optional but Recommended)
# Informs Docker which network port the application inside the container is listening on.
EXPOSE 8080

# Entrypoint Command
# Executes the copied 'app.jar' when the container starts.
ENTRYPOINT ["java", "-jar", "app.jar"]

#Build the app
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -B package -DskipTests

#Run the app
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
CMD ["java", "-jar", "app.jar"]
