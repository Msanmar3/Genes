CREATE DATABASE  IF NOT EXISTS `genevalidator` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `genevalidator`;
-- MySQL dump 10.13  Distrib 5.7.24, for Linux (x86_64)
--
-- Host: localhost    Database: genevalidator
-- ------------------------------------------------------
-- Server version	5.7.24-0ubuntu0.18.10.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `files`
--

DROP TABLE IF EXISTS `files`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `files` (
  `id_file` int(10) NOT NULL AUTO_INCREMENT,
  `id_user` int(10) NOT NULL,
  `name_file` varchar(255) NOT NULL,
  `isHead` tinyint(1) NOT NULL,
  `url` varchar(255) NOT NULL,
  `created` datetime NOT NULL,
  PRIMARY KEY (`id_file`),
  UNIQUE KEY `id_file_UNIQUE` (`id_file`),
  KEY `fk_files_users1_idx` (`id_user`),
  CONSTRAINT `fk_files_users1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `files`
--

LOCK TABLES `files` WRITE;
/*!40000 ALTER TABLE `files` DISABLE KEYS */;
/*!40000 ALTER TABLE `files` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menus`
--

DROP TABLE IF EXISTS `menus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menus` (
  `id_menu` int(10) NOT NULL AUTO_INCREMENT,
  `id_parent` int(10) DEFAULT NULL,
  `name_menu` varchar(45) NOT NULL,
  `icon` varchar(45) DEFAULT NULL,
  `position` int(10) DEFAULT NULL,
  `hasChilds` tinyint(1) DEFAULT NULL,
  `action` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_menu`),
  UNIQUE KEY `id_menu_UNIQUE` (`id_menu`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menus`
--

LOCK TABLES `menus` WRITE;
/*!40000 ALTER TABLE `menus` DISABLE KEYS */;
INSERT INTO `menus` VALUES (1,0,'Usuarios',NULL,1,1,NULL),(2,1,'Crear usuario',NULL,2,0,'servletFormCreateUser'),(3,1,'Modificar usuario',NULL,3,0,''),(4,1,'Listar usuarios',NULL,4,0,'servletListUsers'),(5,1,'Borrar usuario',NULL,5,0,'servletDeleteUser'),(6,0,'Load Specie',NULL,6,0,'servletLoadSpecie'),(7,0,'Load Data',NULL,7,0,'servletLoadData'),(8,0,'Form Load Data',NULL,8,0,'servletFormLoadData'),(9,0,'Form Compare Genes',NULL,9,0,'sertvletFormCompareGenes'),(10,0,'Perfil',NULL,10,0,'servletFormPerfil');
/*!40000 ALTER TABLE `menus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `id_role` int(10) NOT NULL AUTO_INCREMENT,
  `name_role` varchar(45) NOT NULL,
  PRIMARY KEY (`id_role`),
  UNIQUE KEY `id_role_UNIQUE` (`id_role`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'Administrador'),(2,'Usuario');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles_menus`
--

DROP TABLE IF EXISTS `roles_menus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles_menus` (
  `id_role_menu` int(10) NOT NULL AUTO_INCREMENT,
  `id_role` int(10) NOT NULL,
  `id_menu` int(10) NOT NULL,
  PRIMARY KEY (`id_role_menu`),
  UNIQUE KEY `id_role_menu_UNIQUE` (`id_role_menu`),
  KEY `fk_roles_menus_roles1_idx` (`id_role`),
  KEY `fk_roles_menus_menus1_idx` (`id_menu`),
  CONSTRAINT `fk_roles_menus_menus1` FOREIGN KEY (`id_menu`) REFERENCES `menus` (`id_menu`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_roles_menus_roles1` FOREIGN KEY (`id_role`) REFERENCES `roles` (`id_role`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles_menus`
--

LOCK TABLES `roles_menus` WRITE;
/*!40000 ALTER TABLE `roles_menus` DISABLE KEYS */;
INSERT INTO `roles_menus` VALUES (1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,1,5),(6,1,6),(7,1,7),(8,1,8),(9,1,9),(10,1,10);
/*!40000 ALTER TABLE `roles_menus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id_user` int(10) NOT NULL AUTO_INCREMENT,
  `id_role` int(10) NOT NULL,
  `name_user` varchar(255) NOT NULL,
  `user` varchar(225) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `id_user_UNIQUE` (`id_user`),
  KEY `fk_users_roles1_idx` (`id_role`),
  CONSTRAINT `fk_users_roles1` FOREIGN KEY (`id_role`) REFERENCES `roles` (`id_role`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,1,'root','root','1234','root@gmail.com'),(2,2,'monica','monica','monica','monica@gmail.com'),(3,2,'prueba','prueba','prueba','prueba@gmail.com'),(4,2,'otro','otro','otro','otro@gmail.com');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-20 20:17:27
