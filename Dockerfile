FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/filebox.jar filebox.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "filebox.jar"]