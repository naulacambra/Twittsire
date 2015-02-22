CREATE DATABASE IF NOT EXISTS Twittsire;
USE Twittsire;
DROP TABLE IF EXISTS User;
CREATE TABLE User(
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

DROP TABLE IF EXISTS Tweet;
CREATE TABLE Tweet(
	`idTweet` INTEGER(10) NOT NULL AUTO_INCREMENT,
	`text` VARCHAR(150) NOT NULL,
	`created_at` TIMESTAMP NOT NULL DEFAULT NOW(),
	`edited_at` TIMESTAMP NOT NULL DEFAULT NOW(),
	`idUser` INTEGER(10) NOT NULL,
	`idTweetOrigin` INTEGER(10) DEFAULT NULL,
	`idTweetParent` INTEGER(10) DEFAULT NULL,
	
	PRIMARY KEY (idTweet)
);

DROP TABLE IF EXISTS Follow;
CREATE TABLE Follow(
	`idUserFollower` INTEGER(10) NOT NULL,
	`idUserFollowed` INTEGER(10) NOT NULL,
	`started_at` TIMESTAMP NOT NULL DEFAULT NOW(),
	
	PRIMARY KEY(idUserFollower, idUserFollowed)
);

DROP TABLE IF EXISTS Rating;
CREATE TABLE Rating(
	`idTweet` INTEGER(10) NOT NULL,
	`idUser` INTEGER(10) NOT NULL,
	`rate` BOOLEAN NOT NULL,
	`rated_at` TIMESTAMP NOT NULL DEFAULT NOW(),
	
	PRIMARY KEY(idTweet, idUser)
);

DROP TABLE IF EXISTS Role;
CREATE TABLE Role(
	`idRole` INTEGER(10) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(100) NOT NULL,
	
	PRIMARY KEY (idRole)
);

DROP TABLE IF EXISTS Capability;
CREATE TABLE Capability(
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
/*Capability*/
INSERT INTO Capability (`name`) VALUES ("Create Tweet");
INSERT INTO Capability (`name`) VALUES ("Edit Tweet");
INSERT INTO Capability (`name`) VALUES ("Delete Tweet");
INSERT INTO Capability (`name`) VALUES ("View Tweet");
INSERT INTO Capability (`name`) VALUES ("Create Role");
INSERT INTO Capability (`name`) VALUES ("Edit Role");
INSERT INTO Capability (`name`) VALUES ("Delete Role");
/*Roles*/
INSERT INTO Role (`name`) VALUES ("Administrator");
INSERT INTO Role (`name`) VALUES ("Registered User");
INSERT INTO Role (`name`) VALUES ("Guest");
/*Administrator Capability*/
INSERT INTO RoleCapabilities (`idRole`, `idCapability`) VALUES (1, 1);
INSERT INTO RoleCapabilities (`idRole`, `idCapability`) VALUES (1, 2);
INSERT INTO RoleCapabilities (`idRole`, `idCapability`) VALUES (1, 3);
INSERT INTO RoleCapabilities (`idRole`, `idCapability`) VALUES (1, 4);
INSERT INTO RoleCapabilities (`idRole`, `idCapability`) VALUES (1, 5);
INSERT INTO RoleCapabilities (`idRole`, `idCapability`) VALUES (1, 6);
INSERT INTO RoleCapabilities (`idRole`, `idCapability`) VALUES (1, 7);
/*Registered user Capability*/
INSERT INTO RoleCapabilities (`idRole`, `idCapability`) VALUES (2, 1);
INSERT INTO RoleCapabilities (`idRole`, `idCapability`) VALUES (2, 2);
INSERT INTO RoleCapabilities (`idRole`, `idCapability`) VALUES (2, 3);
INSERT INTO RoleCapabilities (`idRole`, `idCapability`) VALUES (2, 4);
/*Guest Capability*/
INSERT INTO RoleCapabilities (`idRole`, `idCapability`) VALUES (3, 4);
/*Administrator User*/
INSERT INTO User(`name`, `surname`, `username`, `mail`, `password`, `idRole`) VALUES("Super", "Admin",  "SuperAdmin", "admin@root.com", "rootTwittsire", 1);
/*Example User*/
INSERT INTO User(`name`, `surname`, `username`, `mail`, `password`, `idRole`) VALUES("Gastly", "Haunter Gengar",  "Ghost", "ghg@gmail.com", "pokemons", 2);