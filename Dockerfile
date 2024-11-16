FROM maven:3.8.8-eclipse-temurin-21-alpine AS builder

# Copiar os arquivos de origem do projeto para o contêiner
COPY . /usr/src/app
WORKDIR /usr/src/app

# Compilar o projeto Maven
RUN mvn clean install package

# Fase de execução
FROM eclipse-temurin:21-jre-alpine

# Copiar o JAR gerado para o contêiner
COPY --from=builder /usr/src/app/target/teste-tecnico-buson-0.0.1-SNAPSHOT.jar /usr/app/app.jar

# Expor a porta em que o Spring Boot irá rodar (geralmente 8080)
EXPOSE 8080

# Comando para rodar o aplicativo Spring Boot
ENTRYPOINT ["java", "-jar", "/usr/app/app.jar"]
