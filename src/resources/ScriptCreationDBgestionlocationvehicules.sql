CREATE DATABASE IF NOT EXISTS `gestionlocationvehicules` /*!40100 DEFAULT CHARACTER SET latin1 */;

CREATE USER 'glv'@'localhost' IDENTIFIED BY 'Password';
GRANT USAGE ON *.* TO 'glv'@'localhost' WITH MAX_QUERIES_PER_HOUR 0 MAX_CONNECTIONS_PER_HOUR 0 MAX_UPDATES_PER_HOUR 0 MAX_USER_CONNECTIONS 0;
GRANT SELECT,INSERT,UPDATE,DELETE ON `gestionlocationvehicules`.* TO 'glv'@'localhost';

DROP TABLE IF EXISTS `clients`;
CREATE TABLE `clients` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(45) DEFAULT NULL,
  `prenom` varchar(45) DEFAULT NULL,
  `permis` varchar(45) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_clients_id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `commerciaux`;
CREATE TABLE `commerciaux` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(45) NOT NULL,
  `prenom` varchar(45) NOT NULL,
  `matricule` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `locationvoiture`;
CREATE TABLE `locationvoiture` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_voiture` int(11) NOT NULL,
  `id_client` int(11) NOT NULL,
  `id_commercial` int(11) NOT NULL,
  `dateLocation` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `utilitaires`;
CREATE TABLE `utilitaires` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `couleur` varchar(45) NOT NULL,
  `modele` varchar(45) NOT NULL,
  `immatriculation` varchar(45) NOT NULL,
  `hauteur` int(11) NOT NULL,
  `largeur` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `voitures`;
CREATE TABLE `voitures` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `couleur` varchar(45) NOT NULL,
  `modele` varchar(45) NOT NULL,
  `immatriculation` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `voiturettes`;
CREATE TABLE `voiturettes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `couleur` varchar(45) NOT NULL,
  `modele` varchar(45) NOT NULL,
  `immatriculation` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;