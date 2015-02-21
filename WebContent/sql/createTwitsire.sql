CREATE DATABASE IF NOT EXISTS Twittsire;
USE Twittsire;
DROP TABLE IF EXISTS Users;
CREATE table Users(
	`idUser` INTEGER(10) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(100) NOT NULL,
    `surname` VARCHAR(100) NOT NULL,
	`username` VARCHAR(25) NOT NULL UNIQUE,
	`mail` VARCHAR(100) NOT NULL UNIQUE,
	`password` VARCHAR(100) NOT NULL,
	`idRole` INTEGER(10) NOT NULL,
	`created_at` TIMESTAMP NOT NULL DEFAULT NOW(),
	
	PRIMARY KEY (idUser) 
);

DROP TABLE IF EXISTS Tweets;
CREATE Tweets(
	`idTweet` INTEGER(10) NOT NULL AUTO_INCREMENT,
	`text` VARCHAR(150) NOT NULL,
	`created_at` TIMESTAMP NOT NULL DEFAULT NOW(),
	`edited_at` TIMESTAMP NOT NULL DEFAULT NOW(),
	`idUser` INTEGER(10) NOT NULL,
	`idTweetOrigin` INTEGER(10) DEFAULT NULL,
	`idTweetParent` INTEGER(10) DEFAULT NULL,
	
	PRIMARY KEY (idTweet)
);

DROP TABLE IF EXISTS Follows;
CREATE TABLE Follows(
	`idUserFollower` INTEGER(10) NOT NULL,
	`idUserFollowed` INTEGER(10) NOT NULL,
	`started_at` TIMESTAMP NOT NULL DEFAULT NOW(),
	
	PRIMARY KEY(idUserFollower, idUserFollowed)
);

DROP TABLE IF EXISTS Ratings;
CREATE TABLE Ratings(
	`idTweet` INTEGER(10) NOT NULL,
	`idUser` INTEGER(10) NOT NULL,
	`rate` BOOLEAN NOT NULL,
	`rated_at` TIMESTAMP NOT NULL DEFAULT NOW(),
	
	PRIMARY KEY(idTweet, idUser)
);

DROP TABLE IF EXISTS Roles;
CREATE TABLE Roles(
	`idRole` INTEGER(10) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(100) NOT NULL,
	
	PRIMARY KEY (idRol)
);

DROP TABLE IF EXISTS Capabilities;
CREATE TABLE Capabilities(
	`idCapability` INTEGER(10) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(100) NOT NULL,
	
	PRIMARY KEY (idCapability)
);

DROP TABLE IF EXISTS RoleCapabilities;
CREATE TABLE RoleCapabilities(
	`idRole` INTEGER(10) NOT NULL,
	`idCapability` INTEGER(10) NOT NULL,
	
	PRIMARY KEY(idRole, idCapability)
);
/*Capabilities*/
INSERT INTO Capabilities (`name`) VALUES ("Create Tweet");
INSERT INTO Capabilities (`name`) VALUES ("Edit Tweet");
INSERT INTO Capabilities (`name`) VALUES ("Delete Tweet");
INSERT INTO Capabilities (`name`) VALUES ("View Tweet");
INSERT INTO Capabilities (`name`) VALUES ("Create Role");
INSERT INTO Capabilities (`name`) VALUES ("Edit Role");
INSERT INTO Capabilities (`name`) VALUES ("Delete Role");
/*Roles*/
INSERT INTO Roles (`name`) VALUES ("Administrator");
INSERT INTO Roles (`name`) VALUES ("Registered User");
INSERT INTO Roles (`name`) VALUES ("Guest");
/*Administrator capabilities*/
INSERT INTO RoleCapabilities (`idRole`, `idCapability`) VALUES (1, 1);
INSERT INTO RoleCapabilities (`idRole`, `idCapability`) VALUES (1, 2);
INSERT INTO RoleCapabilities (`idRole`, `idCapability`) VALUES (1, 3);
INSERT INTO RoleCapabilities (`idRole`, `idCapability`) VALUES (1, 4);
INSERT INTO RoleCapabilities (`idRole`, `idCapability`) VALUES (1, 5);
INSERT INTO RoleCapabilities (`idRole`, `idCapability`) VALUES (1, 6);
INSERT INTO RoleCapabilities (`idRole`, `idCapability`) VALUES (1, 7);
/*Registered user capabilities*/
INSERT INTO RoleCapabilities (`idRole`, `idCapability`) VALUES (2, 1);
INSERT INTO RoleCapabilities (`idRole`, `idCapability`) VALUES (2, 2);
INSERT INTO RoleCapabilities (`idRole`, `idCapability`) VALUES (2, 3);
INSERT INTO RoleCapabilities (`idRole`, `idCapability`) VALUES (2, 4);
/*Guest capabilities*/
INSERT INTO RoleCapabilities (`idRole`, `idCapability`) VALUES (3, 4);
/*Administrator User*/
INSERT INTO users(`name`, `surname`, `username`, `mail`, `password`, `idRole`) VALUES("Super", "Admin",  "SuperAdmin", "admin@root.com", "rootTwittsire", 1);
/*Example User*/
INSERT INTO users(`name`, `surname`, `username`, `mail`, `password`, `idRole`) VALUES("Gastly", "Haunter Gengar",  "Ghost", "ghg@gmail.com", "pokemons", 2);