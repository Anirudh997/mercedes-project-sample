FROM openjdk:8
EXPOSE 8081
ADD target/mercedes-project-sample.jar mercedes-project-sample.jar
ENTRYPOINT ["java","-jar","/mercedes-project-sample.jar"]