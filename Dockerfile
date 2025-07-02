# Use an official Maven image with JDK 11
FROM maven:3.9.6-eclipse-temurin-11 AS builder

# Set working directory
WORKDIR /usr/src/app

# Copy project files
COPY pom.xml .
COPY src ./src

# Download dependencies & build the project
RUN mvn clean compile -DskipTests

# Final image to run Gatling
FROM eclipse-temurin:11-jre

# Set working directory
WORKDIR /usr/src/app

# Copy the built project from the builder image
COPY --from=builder /usr/src/app /usr/src/app

# Set entrypoint to run Gatling simulations (modify class name if needed)
ENTRYPOINT ["mvn", "gatling:test"]
