FROM openjdk:17.0.1-jdk-slim
COPY build/libs/deliveryService-1.0-SNAPSHOT.jar /app/deliveryService.jar
EXPOSE 8002
CMD ["java", "-jar", "/app/deliveryService.jar"]