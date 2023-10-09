
FROM openjdk:17-alpine
EXPOSE 8091
ENV TZ Europe/Moscow
COPY target/MyCloudStorage-0.0.1-SNAPSHOT.jar app.jar
#ADD src/main/resources/static/users src/main/resources/static/users
ADD src/main/resources/application.yaml src/main/resources/application.yaml
CMD ["java", "-jar", "app.jar"]