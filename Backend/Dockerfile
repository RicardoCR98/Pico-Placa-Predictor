# Use an official Java image as a base
FROM openjdk:17-jdk-alpine

# Set the working directory to /app
WORKDIR /app

# Copy the backend JAR file into the container
COPY target/Pico-Placa-Predictor-1.0-SNAPSHOT.jar /app/backend.jar

# Expose the port on which the backend will run
EXPOSE 8080

# Command to run the server when starting the container
CMD ["java", "-jar", "backend.jar"]
