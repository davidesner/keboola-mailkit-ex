FROM maven:3.5.0-jdk-8-alpine
MAINTAINER David Esner <esnerda@gmail.com>

ENV APP_VERSION 1.1.0
# install git
RUN apk add --no-cache git
WORKDIR /home
 # set switch that enables correct JVM memory allocation in containers
RUN export JAVA_OPTS="-XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap"
RUN export MAVEN_OPTS="-XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap"

RUN git clone https://github.com/davidesner/keboola-mailkit-ex.git ./  
RUN mvn -q install

ENTRYPOINT java -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -jar target/esnerda.keboola.ex.mailkit-1.1.0-jar-with-dependencies.jar /data  