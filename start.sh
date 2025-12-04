#!/bin/bash
cd /Users/Aeztic/Downloads/service-voiture
echo "=== Nettoyage et compilation du projet ==="
mvn clean package -DskipTests

if [ $? -eq 0 ]; then
    echo ""
    echo "=== Build réussi ! Lancement du service... ==="
    echo ""
    mvn spring-boot:run
else
    echo "=== Erreur lors de la compilation ==="
    exit 1
fi

