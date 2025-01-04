FROM openjdk:17-jdk-slim

WORKDIR /app

COPY config.yml /app/config.yml

COPY target/CoinCalculator-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/app.jar", "server", "/app/config.yml"]
