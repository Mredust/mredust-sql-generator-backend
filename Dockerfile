# Docker 镜像构建
FROM maven:3.5-jdk-8-alpine AS builder

LABEL author="Mredust"

# Copy local code to the container image.
WORKDIR /app
COPY pom.xml .
COPY src ./src

# Build a release artifact.
RUN mvn clean package -DskipTests


# Run the web service on container startup.
CMD ["java","-jar","/app/target/mredust-sql-generator-backend-1.0.0.jar"]
