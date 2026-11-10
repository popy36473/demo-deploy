#stage 1: build
FROM eclipse-temurin:21-jdk-alpine AS builder
WORKDIR /app
# Copy gradle wrapper & configuration files
COPY gradlew .
COPY gradle ./gradle
COPY build.gradle settings.gradle ./

# Fix CRLF line endings from Windows & set execute permission
RUN chmod +x gradlew

# Download dependencies (cached layer)
RUN ./gradlew dependencies --no-daemon

# Copy source code and build jar
COPY src ./src
RUN ./gradlew bootJar -x test --no-daemon

#stage 2: run
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
# Security: Run as non-root user
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

EXPOSE 9999
ENTRYPOINT ["sh", "-c", "java -jar app.jar"]