FROM openjdk:11
EXPOSE 8080
ARG JAR_FILE=target/post_service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} /post_service.jar
ENTRYPOINT ["java", "-jar", "post_service.jar"]