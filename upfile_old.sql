-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Client :  127.0.0.1
-- Généré le :  Jeu 23 Mars 2017 à 12:19
-- Version du serveur :  5.7.14
-- Version de PHP :  5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `upfile`
--

-- --------------------------------------------------------

--
-- Structure de la table `file`
--

CREATE TABLE `file` (
  `idfile` int(11) NOT NULL,
  `iduser` int(11) NOT NULL,
  `idtype` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `desciption` text NOT NULL,
  `createdat` datetime NOT NULL,
  `url` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

CREATE TABLE `role` (
  `idrole` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `type_file`
--

CREATE TABLE `type_file` (
  `idtype` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `user_upfile`
--

CREATE TABLE `user_upfile` (
  `iduser` int(11) NOT NULL,
  `idrole` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `surname` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Index pour les tables exportées
--

--
-- Index pour la table `file`
--
ALTER TABLE `file`
  ADD PRIMARY KEY (`idfile`),
  ADD KEY `iduser` (`iduser`),
  ADD KEY `idtype` (`idtype`);

--
-- Index pour la table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`idrole`);

--
-- Index pour la table `type_file`
--
ALTER TABLE `type_file`
  ADD PRIMARY KEY (`idtype`);

--
-- Index pour la table `user_upfile`
--
ALTER TABLE `user_upfile`
  ADD PRIMARY KEY (`iduser`),
  ADD KEY `idrole` (`idrole`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `file`
--
ALTER TABLE `file`
  MODIFY `idfile` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `role`
--
ALTER TABLE `role`
  MODIFY `idrole` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `type_file`
--
ALTER TABLE `type_file`
  MODIFY `idtype` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `user_upfile`
--
ALTER TABLE `user_upfile`
  MODIFY `iduser` int(11) NOT NULL AUTO_INCREMENT;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `file`
--
ALTER TABLE `file`
  ADD CONSTRAINT `fk_idtype` FOREIGN KEY (`idtype`) REFERENCES `type_file` (`idtype`),
  ADD CONSTRAINT `fk_iduser` FOREIGN KEY (`iduser`) REFERENCES `user_upfile` (`iduser`);

--
-- Contraintes pour la table `user_upfile`
--
ALTER TABLE `user_upfile`
  ADD CONSTRAINT `fk_idrole` FOREIGN KEY (`idrole`) REFERENCES `role` (`idrole`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
