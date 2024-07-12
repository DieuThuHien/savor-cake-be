FROM openjdk:17-jdk-slim
WORKDIR /app
COPY ./target/savor-cake-be-1.0-SNAPSHOT.jar /app
EXPOSE 8081
CMD ["java", "-jar", "savor-cake-be-1.0-SNAPSHOT.jar"]