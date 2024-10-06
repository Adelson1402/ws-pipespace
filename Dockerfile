# Etapa de construção
FROM  maven:3.8.5-openjdk-17 AS build

RUN apt-get update

# Copiar todo o código para o contêiner
COPY . .

# Construir a aplicação
RUN mvn clean package -DskipTests

# Etapa de execução
FROM openjdk:17.0.1-jdk-slim

# Expor a porta 8080
EXPOSE 8080

# Copiar o arquivo jar gerado da etapa de construção
COPY --from=build /target/pipespace-0.0.1-SNAPSHOT.jar /app.jar

# Definir o ponto de entrada para executar a aplicação
ENTRYPOINT ["java", "-jar", "/app.jar"]
