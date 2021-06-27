FROM openjdk:16-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
EXPOSE 8082
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ARG testmessage
RUN echo $testmessage
USER root
RUN apk add --no-cache bash
USER spring:spring
ENTRYPOINT ["java", "-jar", "/app.jar"]
