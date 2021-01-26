FROM openjdk:8-jdk-alpine
EXPOSE 8081
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /usr/app/bookstoreapp.jar
WORKDIR /usr/app
ENTRYPOINT ["java","-jar","./bookstoreapp.jar"]