# ----------------------------------------
# Stage 1: Build the Application
# ----------------------------------------
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
# Build the JAR file (skip tests to speed it up)
RUN mvn clean package -DskipTests

# ----------------------------------------
# Stage 2: Run the Application
# ----------------------------------------
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
# Copy the JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port 8080
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]