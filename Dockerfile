FROM openjdk:11-jre-slim

WORKDIR /app

COPY target/payment-app-backend.jar /app/payment-app-backend.jar

EXPOSE 8080

CMD ["java", "-jar", "payment-app-backend.jar"]