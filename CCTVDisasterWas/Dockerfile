FROM openjdk:8
RUN apt update
RUN apt install -y ffmpeg
RUN mkdir cctvdisaster
RUN mkdir thumbnailimg
RUN chmod 777 cctvdisaster
RUN chmod 777 thumbnailimg
ENV TZ=Asia/Seoul
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
ARG JAR_FILE=build/libs/ytncctvdisaster.jar
COPY ${JAR_FILE} myboot.jar
ENV	USE_PROFILE local

ENTRYPOINT ["java","-jar","-Dspring.profiles.active=${USE_PROFILE}","/myboot.jar"]