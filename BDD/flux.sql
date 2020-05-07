-- phpMyAdmin SQL Dump
-- version 4.8.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  jeu. 07 mai 2020 à 04:56
-- Version du serveur :  10.1.33-MariaDB
-- Version de PHP :  7.2.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `flux`
--

-- --------------------------------------------------------

--
-- Structure de la table `aboncat`
--

CREATE TABLE `aboncat` (
  `identifiant` varchar(20) NOT NULL,
  `nomcat` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

--
-- Déchargement des données de la table `aboncat`
--

INSERT INTO `aboncat` (`identifiant`, `nomcat`) VALUES
('mark123', 'SPORT'),
('mark123', 'SPORT'),
('mark123', 'SPORT');

-- --------------------------------------------------------

--
-- Structure de la table `abonnement`
--

CREATE TABLE `abonnement` (
  `identifiant` int(11) NOT NULL,
  `url` varchar(100) NOT NULL,
  `datedebut` date NOT NULL,
  `datefin` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

CREATE TABLE `admin` (
  `prenom` varchar(20) NOT NULL,
  `nom` varchar(20) NOT NULL,
  `mail` varchar(40) NOT NULL,
  `identifiant` varchar(20) NOT NULL,
  `mdp` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

--
-- Déchargement des données de la table `admin`
--

INSERT INTO `admin` (`prenom`, `nom`, `mail`, `identifiant`, `mdp`) VALUES
('Herbert', 'Simonin', 'herbert.simonin@fluxrss.nc', 'admin', 'admin');

-- --------------------------------------------------------

--
-- Structure de la table `categories`
--

CREATE TABLE `categories` (
  `nomcat` varchar(40) NOT NULL,
  `nbabo` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

--
-- Déchargement des données de la table `categories`
--

INSERT INTO `categories` (`nomcat`, `nbabo`) VALUES
('Infos', 0),
('loisir', 0),
('voyage', 0),
('sport', 0),
('Catégorie', 1),
('astronomie', 3);

-- --------------------------------------------------------

--
-- Structure de la table `contraintes`
--

CREATE TABLE `contraintes` (
  `identifiant` varchar(20) NOT NULL,
  `nbabos` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

--
-- Déchargement des données de la table `contraintes`
--

INSERT INTO `contraintes` (`identifiant`, `nbabos`) VALUES
('dindin', 10),
('fab96', 8),
('lino123', 8),
('lucben', 6),
('admin', 100);

-- --------------------------------------------------------

--
-- Structure de la table `flux`
--

CREATE TABLE `flux` (
  `nomf` varchar(40) NOT NULL,
  `url` varchar(100) NOT NULL,
  `langue` varchar(20) NOT NULL,
  `dateajout` date NOT NULL,
  `localisation` varchar(40) NOT NULL,
  `nbabo` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

--
-- Déchargement des données de la table `flux`
--

INSERT INTO `flux` (`nomf`, `url`, `langue`, `dateajout`, `localisation`, `nbabo`) VALUES
('FrancePolitique', 'https://www.lemonde.fr/politique/rss_full.xml', 'Francais', '2020-04-27', 'France', 0),
('Actualite', 'https://www.lemonde.fr/rss/une.xml', 'Francais', '2020-04-27', 'International', 0),
('Football', 'https://www.lemonde.fr/football/rss_full.xml', 'Francais', '2020-04-28', 'France', 0),
('Futura-science', 'https://www.futura-sciences.com/rss/actualites.xml', 'Francais', '2020-04-30', 'International', 0);

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `prenom` varchar(20) NOT NULL,
  `nom` varchar(20) NOT NULL,
  `mail` varchar(40) NOT NULL,
  `identifiant` varchar(20) NOT NULL,
  `mdp` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`prenom`, `nom`, `mail`, `identifiant`, `mdp`) VALUES
('din', 'kama', 'din.kama@gmail.com', 'dindin', 'kamakama'),
('fab', 'marto', 'fab.marto@gmail.com', 'fab95', 'fab96'),
('lino', 'dany', 'lino.dany@gmail.com', 'lino123', 'lino123'),
('luc', 'ben', 'luc.ben@hotmail.com', 'lucben', '123456789'),
('kel', 'MARK', 'kel.mark@gmail.com', 'mark123', 'mark123');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`identifiant`);

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`identifiant`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
