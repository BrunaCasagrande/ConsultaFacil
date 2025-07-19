FROM eclipse-temurin:21-jdk-jammy

# instala netcat para aguardar o DB
RUN apt-get update \
 && apt-get install -y netcat \
 && rm -rf /var/lib/apt/lists/*

WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8080

ENTRYPOINT ["sh","-c","until nc -z consultafacil_mysql 3306; do echo 'Aguardando MySQL...'; sleep 3; done; exec java -jar app.jar"]