FROM openjdk:8-jdk-alpine
ARG JAR_FILE=build/libs/ytncctvdisaster.jar
COPY ${JAR_FILE} myboot2.jar
ENTRYPOINT ["java","-jar","/myboot2.jar"]
