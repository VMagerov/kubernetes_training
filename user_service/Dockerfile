FROM openjdk:11
EXPOSE 8080
ARG JAR_FILE=target/user_service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} /user_service.jar
ENTRYPOINT ["java", "-jar", "user_service.jar"]