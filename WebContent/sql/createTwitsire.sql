CREATE DATABASE IF NOT EXISTS Twittsire;

USE Twittsire;

DROP TABLE Users;
CREATE table Users(
	idUser INTEGER(10) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(100) NOT NULL,
    surname VARCHAR(100) NOT NULL,
	username VARCHAR(25) NOT NULL UNIQUE,
	mail VARCHAR(100) NOT NULL UNIQUE,
	pwd VARCHAR(100) NOT NULL,

	PRIMARY KEY (idUser) 
);

INSERT INTO users(idUser, `name`, surname, username, mail, pwd)
			VALUES(1, "Gastly", "Haunter Gengar",  "Ghost", "ghg@gmail.com", "pokemons");