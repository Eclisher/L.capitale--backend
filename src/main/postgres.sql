-- Création de la base de données
CREATE DATABASE annonce_app;

-- Connexion à la base de données
\c annonce_app;

-- Création de la table des annonces
CREATE TABLE annonce (
                         id SERIAL PRIMARY KEY,
                         titre VARCHAR(255) NOT NULL,
                         description TEXT NOT NULL,
                         image_url VARCHAR(255),
                         date_annonce DATE NOT NULL,
                         disponible BOOLEAN DEFAULT TRUE,
                         numero_telephone VARCHAR(20),
                         email VARCHAR(100)
);

-- Création de la table des utilisateurs (pour gérer les rôles)
CREATE TABLE utilisateur (
                             id SERIAL PRIMARY KEY,
                             nom VARCHAR(100) NOT NULL,
                             email VARCHAR(100) NOT NULL UNIQUE,
                             mot_de_passe VARCHAR(255) NOT NULL,
                             role VARCHAR(50) DEFAULT 'USER'
);

-- Insertion d'un administrateur par défaut
INSERT INTO utilisateur (nom, email, mot_de_passe, role)
VALUES ('Admin', 'admin@example.com', crypt('admin123', gen_salt('bf')), 'ADMIN');

CREATE EXTENSION IF NOT EXISTS pgcrypto;
ALTER TABLE annonce
    ADD COLUMN utilisateur_id INT REFERENCES utilisateur(id) ON DELETE CASCADE;

CREATE TABLE context_message(
    id SERIAL primary key ,
    name VARCHAR(100) NOT NULL ,
    message text not null
);
INSERT  INTO  context_message(
                      name,
                      message_context
) VALUES ('Paul', 'Votres blog a des beaux desings');