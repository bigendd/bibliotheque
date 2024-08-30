# Utiliser une image Java de base
FROM openjdk:17-jdk-slim

# Créer un répertoire pour l'application
WORKDIR /app

# Copier le fichier JAR généré dans le conteneur
COPY target/bibliotheque-0.0.1-SNAPSHOT.jar app.jar

# Exposer le port sur lequel l'application va tourner
EXPOSE 8080

# Démarrer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
