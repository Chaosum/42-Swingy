#!/bin/bash

PG_USER="postgres"
PG_PASSWORD="swingyroot"

psql -U "$PG_USER" -c "CREATE DATABASE swingy;" || exit 1

# Connexion à PostgreSQL en tant qu'utilisateur postgres pour créer un nouvel utilisateur avec un mot de passe
psql -U "$PG_USER" -d "swingy" -c "CREATE USER swingy WITH ENCRYPTED PASSWORD '$PG_PASSWORD';" || exit 1

# Attribution des privilèges à l'utilisateur sur la base de données
psql -U "$PG_USER" -d "swingy" -c "GRANT ALL PRIVILEGES ON DATABASE swingy TO swingy_user;" || exit 1

echo "La base de données 'swingy' a été créée avec succès."
echo "L'utilisateur 'swingy_user' avec le mot de passe spécifié a été créé et a les privilèges sur la base de données."
