# Counter Service - CI/CD Final Project

This repository contains a Java Spring Boot implementation of a simple Counter Service for the CI/CD Tools and Practices Final Project.

## Overview

The Counter Service is a RESTful web service that allows clients to create, read, update, and delete counters. Each counter has a name and a value that can be incremented.

## Technologies Used

- Java 21
- Spring Boot 3.2.0
- Maven
- JUnit 5 for testing
- GitHub Actions for CI/CD
- Tekton for Kubernetes-based CI/CD

## Prerequisites

- JDK 21
- Maven 3.8+
- Docker (for containerization)

## Setup

To set up the development environment, run:

```bash
# Clone the repository
git clone https://github.com/yourusername/ci-cd-final-project.git
cd ci-cd-final-project

# Build the project
./mvnw clean package
```

## Running the Application

### Using Maven

```bash
./mvnw spring-boot:run
```

### Using Java

```bash
java -jar target/counter-service-1.0.0.jar
```

### Using Docker

```bash
# Build the Docker image
docker build -t counter-service .

# Run the container
docker run -p 8000:8000 counter-service
```

The application will be available at http://localhost:8000

## API Endpoints

| Method | Endpoint          | Description                  |
|--------|-------------------|------------------------------|
| GET    | /                 | Service information          |
| GET    | /health           | Health check                 |
| GET    | /counters         | List all counters            |
| POST   | /counters/{name}  | Create a new counter         |
| GET    | /counters/{name}  | Get a counter by name        |
| PUT    | /counters/{name}  | Increment a counter          |
| DELETE | /counters/{name}  | Delete a counter             |

## Running Tests

```bash
./mvnw test
```

## CI/CD Pipeline

The project includes CI/CD configurations for:

1. GitHub Actions (.github/workflows/workflow.yml)
2. Tekton (.tekton/tasks.yml)

These pipelines handle:
- Building the application
- Running tests
- Generating test coverage reports
- Code quality analysis
- Building and scanning Docker images

## License

Licensed under the Apache License. See [LICENSE](/LICENSE)

## Author

Skills Network

## <h3 align="center"> © IBM Corporation 2025. All rights reserved. <h3/>