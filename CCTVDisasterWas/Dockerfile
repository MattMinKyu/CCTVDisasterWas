FROM openjdk:8-jdk-alpine
ARG JAR_FILE=build/libs/ytncctvdisaster.jar
COPY ${JAR_FILE} myboot.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","/myboot.jar"]
