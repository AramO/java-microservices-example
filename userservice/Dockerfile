FROM openjdk:11-jdk-slim
COPY target/userservice.jar /opt/userservice.jar
RUN useradd -ms /bin/sh example123 \
    && echo "root:Docker!" | chpasswd \
    && chown -R example123 /opt/
USER example123

ENTRYPOINT ["java", "-Djava.net.preferIPv4Stack=true", "-jar", "/opt/userservice.jar"]
EXPOSE 8081