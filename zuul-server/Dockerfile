FROM openjdk:11
VOLUME /tmp
EXPOSE 8890
ADD ./target/zuul-server-0.0.1-SNAPSHOT.jar zuul-server.jar
ENTRYPOINT ["java", "-jar", "/zuul-server.jar"]