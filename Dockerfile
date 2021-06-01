FROM openjdk:latest

EXPOSE 8080

RUN mkdir /app

COPY build/libs/servico-votacao-0.0.1-SNAPSHOT.jar /app/servico-votacao-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","/app/servico-votacao-0.0.1-SNAPSHOT.jar"]
