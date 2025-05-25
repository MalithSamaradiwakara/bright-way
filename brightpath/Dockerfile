# Build stage
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Create a non-root user
RUN groupadd -r spring && useradd -r -g spring spring
USER spring:spring

# Copy the built jar
COPY --from=build /app/target/*.jar app.jar

# Set environment variables
ENV JAVA_OPTS="-Xmx512m -Xms256m"
ENV SPRING_PROFILES_ACTIVE="prod"
ENV TZ="UTC"
# Railway will provide PORT environment variable
ENV PORT=8080

# Create directory for file uploads
RUN mkdir -p /tmp/uploads && chown spring:spring /tmp/uploads

# Expose the port the app runs on
# Note: Railway will override this with their own port mapping
EXPOSE ${PORT}

# Run the application with proper JVM options
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]