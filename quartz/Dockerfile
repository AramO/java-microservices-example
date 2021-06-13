FROM openjdk:11-jdk-slim

COPY target/quartz.jar /opt/quartz.jar

ENTRYPOINT exec java $JAVA_OPTS -jar /opt/quartz.jar