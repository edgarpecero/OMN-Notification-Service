# Use a Maven image to build the app
FROM maven:3.9.9-amazoncorretto-21 AS build

# Set the working directory
WORKDIR /app

# Copy the pom.xml and download dependencies
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Start a new stage to reduce image size
FROM amazoncorretto:21-alpine

# Copy the JAR file into the service directory from the previous stage
COPY --from=build /app/target/notification-service.jar notification-service.jar

# Expose the port your app runs on
EXPOSE 8081

# Command to run the app
ENTRYPOINT ["java", "-jar", "notification-service.jar"]
