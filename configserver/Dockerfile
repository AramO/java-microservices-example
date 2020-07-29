FROM openjdk:11-jdk-slim
COPY target/configserver.jar /opt/configserver.jar
RUN useradd -ms /bin/sh example123 \
    && echo "root:Docker!" | chpasswd \
    && chown -R example123 /opt/
USER example123

ENTRYPOINT ["java", "-Djava.net.preferIPv4Stack=true", "-jar", "/opt/configserver.jar"]
EXPOSE 8888
