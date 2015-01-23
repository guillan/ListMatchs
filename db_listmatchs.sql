-- phpMyAdmin SQL Dump
-- version 4.2.10
-- http://www.phpmyadmin.net
--
-- Client :  localhost
-- Généré le :  Ven 23 Janvier 2015 à 07:39
-- Version du serveur :  5.5.38
-- Version de PHP :  5.6.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `db_listmatchs`
--
CREATE DATABASE IF NOT EXISTS `db_listmatchs` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `db_listmatchs`;

-- --------------------------------------------------------

--
-- Structure de la table `T_MATCH`
--

DROP TABLE IF EXISTS `T_MATCH`;
CREATE TABLE `T_MATCH` (
  `MATCH_ID` bigint(20) NOT NULL,
  `MATCH_DATE` varchar(10) NOT NULL,
  `MATCH_TIME` varchar(5) NOT NULL,
  `MATCH_HOME_TEAM` varchar(255) NOT NULL,
  `MATCH_AWAY_TEAM` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `T_ROLE`
--

DROP TABLE IF EXISTS `T_ROLE`;
CREATE TABLE `T_ROLE` (
`ROLE_ID` bigint(20) NOT NULL,
  `ROLE_NAME` varchar(50) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `T_ROLE`
--

INSERT INTO `T_ROLE` (`ROLE_ID`, `ROLE_NAME`) VALUES
(1, 'ROLE_GUEST');

-- --------------------------------------------------------

--
-- Structure de la table `T_USER`
--

DROP TABLE IF EXISTS `T_USER`;
CREATE TABLE `T_USER` (
`USER_ID` bigint(20) NOT NULL,
  `USER_LOGIN` varchar(50) NOT NULL,
  `USER_PASSWORD` varchar(60) NOT NULL,
  `USER_FK_ROLE_ID` bigint(20) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `T_USER`
--

INSERT INTO `T_USER` (`USER_ID`, `USER_LOGIN`, `USER_PASSWORD`, `USER_FK_ROLE_ID`) VALUES
(1, 'root', '$2a$10$oQk6NI/LsEBFFzZS6caML.uA0nOAJaxR7sviOoPGNZt3jZi8dbk9G', 1);

--
-- Index pour les tables exportées
--

--
-- Index pour la table `T_MATCH`
--
ALTER TABLE `T_MATCH`
 ADD PRIMARY KEY (`MATCH_ID`);

--
-- Index pour la table `T_ROLE`
--
ALTER TABLE `T_ROLE`
 ADD PRIMARY KEY (`ROLE_ID`);

--
-- Index pour la table `T_USER`
--
ALTER TABLE `T_USER`
 ADD PRIMARY KEY (`USER_ID`), ADD UNIQUE KEY `USER_FK_ROLE_ID` (`USER_FK_ROLE_ID`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `T_ROLE`
--
ALTER TABLE `T_ROLE`
MODIFY `ROLE_ID` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `T_USER`
--
ALTER TABLE `T_USER`
MODIFY `USER_ID` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `T_USER`
--
ALTER TABLE `T_USER`
ADD CONSTRAINT `t_user_ibfk_1` FOREIGN KEY (`USER_FK_ROLE_ID`) REFERENCES `T_ROLE` (`ROLE_ID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
