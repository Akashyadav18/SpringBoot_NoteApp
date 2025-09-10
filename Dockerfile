FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/TodoApp-1.0.jar todoapp.jar

# Run with shell so ${PORT} is actually passed to the app
ENTRYPOINT ["sh", "-c", "java -jar /todoapp.jar --server.port=${PORT}"]
