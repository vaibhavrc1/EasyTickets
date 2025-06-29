# Use the official OpenJDK 17 image as the base image
FROM eclipse-temurin:17-jdk-jammy

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/gateway-service-*.jar gateway-service.jar

# Expose the port the app runs on
EXPOSE 8081

# Command to run the application
ENTRYPOINT ["java", "-jar", "gateway-service.jar"]
