# Use the official OpenJDK 17 image as the base image
FROM eclipse-temurin:17-jdk-jammy

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/event-service-*.jar event-service.jar

# Expose the port the app runs on
EXPOSE 8082

# Command to run the application
ENTRYPOINT ["java", "-jar", "event-service.jar"]
