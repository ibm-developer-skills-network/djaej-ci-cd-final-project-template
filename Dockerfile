FROM eclipse-temurin:21-jdk as builder

# Install Maven
RUN apt-get update && \
    apt-get install -y maven && \
    rm -rf /var/lib/apt/lists/*

# Set the working directory
WORKDIR /app

# Copy the Maven POM file
COPY pom.xml .

# Copy the source code
COPY src ./src

# Build the application without running tests
RUN mvn package -DskipTests

# Create a lightweight JRE image with the application
FROM eclipse-temurin:21-jre-alpine

# Set the working directory
WORKDIR /app

# Copy the JAR file from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Create a non-root user
RUN addgroup --system --gid 1001 appuser && \
    adduser --system --uid 1001 --ingroup appuser appuser
USER appuser

# Set the PORT environment variable
ENV PORT 5000
EXPOSE $PORT

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]