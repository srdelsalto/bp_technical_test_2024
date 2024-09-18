FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/cliente_microservice.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]