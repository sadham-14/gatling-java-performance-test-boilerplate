# Use Maven + JDK base image
FROM maven:3.9.6-eclipse-temurin-11

# Set working directory
WORKDIR /usr/src/app

# Copy project files
COPY pom.xml .
COPY src ./src

# Download dependencies + build
RUN mvn clean compile -DskipTests

# Expose port (if needed for report or other services)
EXPOSE 8080

# Run Gatling simulations
CMD ["mvn", "gatling:test"]
