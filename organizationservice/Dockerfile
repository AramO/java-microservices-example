FROM openjdk:11-jdk-slim
COPY target/organizationservice.jar /opt/organizationservice.jar
RUN useradd -ms /bin/sh example123 \
    && echo "root:Docker!" | chpasswd \
    && chown -R example123 /opt/
USER example123

ENTRYPOINT ["java", "-Djava.net.preferIPv4Stack=true", "-jar", "/opt/organizationservice.jar"]
EXPOSE 8080