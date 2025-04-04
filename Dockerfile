# Use a base image with OpenJDK Java 17 installed
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app
# Install curl using apt-get
RUN apt-get update && apt-get install -y curl


# Define the URLs to fetch the JAR from Jenkins
ARG JOB_URL="http://host.docker.internal:8080/job/My-Shopify-Pipeline"

# Try downloading the latest successful build first, if it fails, fallback to the last build
RUN curl -f -o app.jar "$JOB_URL/lastSuccessfulBuild/artifact/target/shopify-0.0.1-SNAPSHOT.jar" \
    || curl -o app.jar "$JOB_URL/lastBuild/artifact/target/shopify-0.0.1-SNAPSHOT.jar"

# Download the latest JAR from Jenkins
RUN chmod +x app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
