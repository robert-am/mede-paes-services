FROM openjdk:11
VOLUME /tmp
ADD ./target/survey-pdf-service-0.0.1-SNAPSHOT.jar survey-pdf-service.jar
ENTRYPOINT ["java", "-jar", "/survey-pdf-service.jar"]