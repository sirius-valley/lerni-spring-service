
FROM openjdk:20-jdk-slim

WORKDIR /app

ARG JWT_SECRET
ARG GITLAB_TOKEN

ENV JWT_SECRET=${JWT_SECRET}
ENV GITLAB_TOKEN=${GITLAB_TOKEN}

COPY build.gradle .
COPY settings.gradle .

COPY gradle/ gradle/
COPY gradlew .

COPY src/ ./src/

RUN ./gradlew build

EXPOSE 8080

CMD ["java", "-jar", "build/libs/lerni-spring-service-0.0.1-SNAPSHOT.jar", "--spring.config.location=classpath:/application.properties,file:/app/src/main/resources/application.properties"]