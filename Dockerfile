# Use an official JAVA 1.8 runtime as a parent image
FROM openjdk:8-jre

# Set the working directory to /app
WORKDIR /app

# Copy the current .jar into the container at /app
COPY target/api-rest-spring-boot-jwt-0.0.1-SNAPSHOT.jar movie-api-0.1.0.jar

# Make port 80 available to the world outside this container
EXPOSE 80

# Run movie-app-0.1.0.jar when the container launches
CMD ["java", "-jar", "movie-api-0.1.0.jar"]