# Use uma imagem base do Java
FROM openjdk:17-jdk-alpine

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o arquivo JAR da sua aplicação para o diretório de trabalho
COPY target/*.jar app.jar

# Expõe a porta na qual a aplicação vai rodar
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
