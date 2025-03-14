CREATE TABLE utilisateur (
                             id SERIAL PRIMARY KEY,
                             nom VARCHAR(100) NOT NULL,
                             email VARCHAR(100) NOT NULL UNIQUE,
                             mot_de_passe VARCHAR(255) NOT NULL,
                             role VARCHAR(50) DEFAULT 'USER'
);

CREATE TABLE annonce (
                         id SERIAL PRIMARY KEY,
                         titre VARCHAR(255) NOT NULL,
                         description VARCHAR(255) NOT NULL,
                         date_annonce DATE NOT NULL,
                         disponible BOOLEAN DEFAULT true,
                         numero_telephone VARCHAR(255),
                         email VARCHAR(255),
                         utilisateur_id INTEGER,
                         image_url TEXT[],
                         FOREIGN KEY (utilisateur_id) REFERENCES utilisateur(id) ON DELETE CASCADE
);

CREATE TABLE annonce_image_urls (
                                    annonce_id BIGINT NOT NULL,
                                    image_urls VARCHAR(255),
                                    FOREIGN KEY (annonce_id) REFERENCES annonce(id)
);

CREATE TABLE context_message (
                                 id SERIAL PRIMARY KEY,
                                 name VARCHAR(255) NOT NULL,
                                 message_context VARCHAR(255) NOT NULL DEFAULT 'Default value'
);

CREATE TABLE message (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(100) NOT NULL,
                         message TEXT NOT NULL
);
