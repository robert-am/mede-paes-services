FROM openjdk:11
VOLUME /tmp
ADD ./target/oauth-service-0.0.1-SNAPSHOT.jar oauth-service.jar
ENTRYPOINT ["java", "-jar", "/oauth-service.jar"]