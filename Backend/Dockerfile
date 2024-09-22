# Usa una imagen oficial de Java como base
FROM openjdk:17-jdk-alpine

# Establece el directorio de trabajo en /app
WORKDIR /app

# Copia el archivo JAR del backend en el contenedor
COPY target/Pico-Placa-Predictor-1.0-SNAPSHOT.jar /app/backend.jar

# Expone el puerto en el que correrá el backend
EXPOSE 8080

# Comando para ejecutar el servidor al iniciar el contenedor
CMD ["java", "-jar", "backend.jar"]
