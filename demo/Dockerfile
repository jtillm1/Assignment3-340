# ---- Build stage: compile the JAR with the Maven wrapper ----
FROM eclipse-temurin:25-jdk-alpine AS build
WORKDIR /app
COPY .mvn .mvn
COPY mvnw pom.xml ./
# Windows line endings would break the wrapper script on Linux
RUN sed -i 's/\r$//' mvnw && chmod +x mvnw
RUN ./mvnw -q dependency:go-offline
COPY src src
RUN ./mvnw -q clean package -DskipTests

# ---- Run stage: small JRE image ----
FROM eclipse-temurin:25-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
# Port comes from the PORT env var via server.port=${PORT:8080}
ENTRYPOINT ["java", "-jar", "app.jar"]
