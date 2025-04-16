FROM openjdk:25-ea-4-jdk-oraclelinux9

WORKDIR /app

COPY target/sostthetraitor.jar sostthetraitor.jar

EXPOSE 8080

ENTRYPOINT ["java" ,"-jar","sostthetraitor.jar"]