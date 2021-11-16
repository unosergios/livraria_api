FROM openjdk:11-jre-slim

WORKDIR /app

COPY target/livraria-api-1.0.jar /app/livraria-api.jar

EXPOSE 8080

CMD java -XX:+UseContainerSupport -Xmx512m -Dserver.port=${PORT} -jar livraria-api.jar