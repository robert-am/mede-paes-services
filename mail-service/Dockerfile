FROM openjdk:11
VOLUME /tmp
ADD ./target/mail-service-0.0.1-SNAPSHOT.jar mail-service.jar
ENTRYPOINT ["java", "-jar", "/mail-service.jar"]