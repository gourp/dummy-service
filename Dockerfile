FROM openjdk:8

COPY target/sample-1.0-SNAPSHOT.jar sample-1.0-SNAPSHOT.jar

COPY application.yaml application.yaml

CMD ["java","-jar","sample-1.0-SNAPSHOT.jar","server","application.yaml"]

EXPOSE 9000 9001


