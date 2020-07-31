FROM maven:3-jdk-8-alpine as builder
ADD ./pom.xml /tmp
ADD src/ /tmp/src/
WORKDIR /tmp
RUN mvn dependency:go-offline
RUN mvn package
FROM openjdk:8-jdk-alpine
COPY --from=builder /tmp/target/poll-rest-api.jar /tmp/
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/tmp/poll-rest-api.jar"]