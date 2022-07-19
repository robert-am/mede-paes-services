FROM openjdk:11
VOLUME /tmp
ADD ./target/survey-service-0.0.1-SNAPSHOT.jar survey-service.jar
ENTRYPOINT ["java", "-jar", "/survey-service.jar"]