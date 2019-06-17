CREATE DATABASE  IF NOT EXISTS `genevalidator` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `genevalidator`;
-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: genevalidator
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `authors`
--

DROP TABLE IF EXISTS `authors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `authors` (
  `id_author` int(10) NOT NULL AUTO_INCREMENT,
  `name_author` varchar(45) NOT NULL,
  PRIMARY KEY (`id_author`),
  UNIQUE KEY `name_author_UNIQUE` (`name_author`)
) ENGINE=InnoDB AUTO_INCREMENT=245 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authors`
--

LOCK TABLES `authors` WRITE;
/*!40000 ALTER TABLE `authors` DISABLE KEYS */;
INSERT INTO `authors` VALUES (1,'Abbott Pronk'),(2,'Abbott van Maris'),(3,'Aceituno Agosin'),(4,'Aghajan Huang'),(5,'Aguilera Pronk'),(134,'Alon Gothilf'),(6,'Alper Stephanopoulos'),(7,'Amit Horovitz'),(135,'Andreasen Tanguay'),(8,'Ansari Morse'),(9,'Azzouz Collart'),(119,'Babu Emili'),(10,'Barbosa Mendes Ferreira'),(208,'Baugh Hunter'),(209,'Baugh Sternberg'),(136,'Benner Robison'),(11,'Bessonov van der Merwe'),(12,'Bester Bauer'),(127,'BIOGRID SMALL SCALE STUDIES'),(13,'Bodi Fray'),(14,'Boer Daran'),(15,'Boer Pronk'),(210,'Bond Hanover'),(196,'Boulton Vidal'),(197,'Boxem Vidal'),(16,'Brauer Botstein'),(17,'Braun Young'),(18,'Busti Vanoni'),(180,'Byrne Roy'),(19,'Caba Aubrecht'),(211,'Calvert de Magalhaes'),(137,'Cameron Yurco'),(20,'Canelas Nielsen'),(181,'Ceron van den Heuvel'),(21,'Chattopadhyay Tabor'),(212,'Chen Hamza'),(138,'Chen Heideman'),(22,'Chin Li'),(23,'Chiu Dawes'),(24,'Chu Herskowitz'),(25,'Chua Hughes'),(26,'Chujo Ishikawa'),(27,'Chumnanpuen Nielsen'),(28,'Cipollina de Winde'),(213,'Clark Miska'),(139,'Craig Hitchcock'),(29,'Daran Lapujade Pronk'),(198,'Davy Vidal'),(214,'Dawe de Pomerai'),(115,'de Kok van Maris'),(30,'DeRisi Brown'),(31,'Dheur Javerzat'),(140,'Drew Robison'),(32,'Dymond Boeke'),(33,'Erlich Samson'),(215,'Estes Troemel'),(118,'Faith Gardner'),(216,'Falk Morgan'),(34,'Fendt Sauer'),(35,'Ferea Rosenzweig'),(36,'Ferreira Menezes'),(141,'Fish Srivastava'),(217,'Fox Miller'),(218,'Friday Keiper'),(37,'Fry Dedon'),(120,'Gagarinova Emili'),(38,'Gasch Brown'),(39,'Ge Li'),(40,'Gibson Smart'),(41,'Gil Viegas'),(142,'Giraldez Schier'),(42,'Gonzalez Aguilera Aguilera'),(199,'Gottschalk Schafer'),(219,'Greer Brunet'),(220,'Grishok Sharp'),(221,'Grompone Ramon'),(43,'Gross Winge'),(44,'Guan Culbertson'),(45,'Hazelwood Daran'),(46,'Hazelwood Dickinson'),(47,'Heer Sauer'),(48,'HochwagenAmon'),(143,'Hofsteen Heideman'),(49,'Hong Nielsen'),(222,'Honjoh Nishida'),(122,'Hu Emili'),(50,'Hughes Friend'),(125,'INTERPRO'),(128,'IREF BIND'),(129,'IREF BIOGRID'),(130,'IREF DIP'),(131,'IREF INTACT'),(132,'IREF SMALL SCALE STUDIES'),(51,'James May'),(223,'Jans Meyer'),(52,'Jin Wyrick'),(53,'Johansson Jacobson'),(144,'Jopling Izpisua Belmonte'),(224,'Kim Kimble'),(225,'Kirienko Fay'),(54,'Knijnenburg Wessels'),(55,'Komili Silver'),(226,'Krajacic Lamitina'),(56,'Kresnowati Daran'),(121,'Kumar Babu'),(57,'Lanza Alper'),(145,'Leacock Amatruda'),(58,'Lee Lee'),(59,'Lee Marcotte'),(175,'Lee MarcotteEE'),(182,'Lehner Fraser'),(200,'Lenfant Reboul'),(146,'Leung Dowling'),(227,'Lewis Jackson'),(60,'Li Klevecz'),(61,'Li Smith'),(201,'Li Vidal'),(62,'Lin Green'),(63,'Liu Nielsen'),(64,'Lu Gerton'),(147,'Ma Schachner'),(228,'Mair Dillin'),(65,'Martin Bellis'),(148,'Mathew Tanguay'),(66,'Matia Gonzalez Rodriguez Gabriel'),(149,'Maves Tapscott'),(150,'McCurley Callard'),(229,'McElwee Gems'),(67,'Medintz Thach'),(230,'Menzel Steinberg'),(68,'Mitchell Pilpel'),(151,'Monnich Horsfield'),(69,'Morillo Huesca Prado'),(231,'Nargund Haynes'),(123,'Nichols Gross'),(232,'Noble Kimble'),(70,'Ogawa Brown'),(152,'Okuda Kamachi'),(71,'Orlando Haase'),(72,'Ouyang Sutter'),(153,'Ozbudak Pourquie'),(154,'Packham Chico'),(155,'Park Sayler'),(73,'Parra Wyrick'),(126,'PFAM'),(233,'Phirke Swoboda'),(234,'Pietsch Sturzenbaum'),(74,'Pitkanen Renkonen'),(75,'Pizarro Agosin'),(133,'PPI mapped'),(156,'Qin Raymond'),(124,'Rajgopala Uetz'),(202,'Reboul Vidal'),(203,'Reece Hoyes Walhout'),(157,'Rhodes Horsfield'),(76,'Rintala Penttila'),(77,'Rintala Ruohonen'),(78,'Roberts Friend'),(79,'Roberts Hudson'),(158,'Robison Hardy'),(80,'Rodriguez Lombardero Cerdan'),(159,'Rogers Wilhelm'),(235,'Rohlfing Lamitina'),(81,'Ronald Kruglyak'),(82,'Rossouw Bauer'),(83,'Sabet Morse'),(84,'Salusjarvi Ruohonen'),(85,'Sanz Arroyo'),(160,'Sarmah Marrs'),(161,'Sarras Intine'),(86,'Shalem Pilpel'),(87,'Sheehan Hyman'),(162,'Shkumatava Bartel'),(88,'Simmons Kovacs Haase'),(204,'Simonis Vidal'),(89,'Simons Osbourn'),(90,'Singh Helm'),(163,'Sleep Izpisua Belmonte'),(164,'Small Jones'),(91,'Snoek Daran'),(165,'Soni Pillai'),(92,'Spellman Futcher'),(93,'Staschke Wek'),(166,'Storer Zon'),(236,'Stuart Kim'),(167,'Stuckenholz Bahary'),(94,'Sudarsanam Winston'),(237,'Sugi Mori'),(95,'Sun Yuan'),(96,'Suzuki Iwahashi'),(238,'Tabuchi Hagstrom'),(97,'Tai Daran'),(117,'Tai Pronk'),(98,'Takagi Kornberg'),(99,'Tauber Giorgini'),(168,'Tilton Gallagher'),(169,'Toyama Dawid'),(239,'Troemel Kim'),(100,'Tu McKnight'),(101,'Tyo Nielsen'),(102,'Urban Loewith'),(116,'van den Brink de Winde'),(244,'van der Linden Sengupta'),(103,'Verwaal van Ooyen'),(240,'Von Stetina Miller'),(104,'Vos Daran Lapujade'),(105,'Wade Auble'),(205,'Walhout Vidal'),(241,'Wang Corsi'),(170,'Weicksel Sagerstrom'),(106,'Wells Blackshear'),(171,'White Zon'),(107,'Willis Moir'),(242,'Wright Ciosk'),(206,'Xin Bader'),(207,'Xin Thierry Mieg'),(172,'Xiong Heideman'),(243,'Yanai Hunter'),(108,'Yarragudi Morse'),(109,'Yona Dahan'),(110,'Yu Morse'),(111,'Yvert Kruglyak'),(112,'Zhang Nielsen'),(113,'Zhu Bulyk'),(114,'Zhu Futcher');
/*!40000 ALTER TABLE `authors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eventos`
--

DROP TABLE IF EXISTS `eventos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `eventos` (
  `id_evento` int(10) NOT NULL,
  `id_user` int(10) NOT NULL,
  `type_process` varchar(255) NOT NULL,
  `name_process` varchar(255) DEFAULT NULL,
  `decription_process` varchar(255) DEFAULT NULL,
  `date_process` datetime NOT NULL,
  `state` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_evento`),
  UNIQUE KEY `id_evento_UNIQUE` (`id_evento`),
  KEY `fk_eventos_users1_idx` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eventos`
--

LOCK TABLES `eventos` WRITE;
/*!40000 ALTER TABLE `eventos` DISABLE KEYS */;
/*!40000 ALTER TABLE `eventos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `files`
--

DROP TABLE IF EXISTS `files`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `files` (
  `id_file` int(10) NOT NULL AUTO_INCREMENT,
  `id_user` int(10) NOT NULL,
  `name_file` varchar(255) NOT NULL,
  `isHead` tinyint(1) NOT NULL,
  `url` varchar(255) NOT NULL,
  `created` datetime NOT NULL,
  PRIMARY KEY (`id_file`),
  UNIQUE KEY `id_file_UNIQUE` (`id_file`),
  KEY `fk_files_users1_idx` (`id_user`)
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
-- Table structure for table `iterations`
--

DROP TABLE IF EXISTS `iterations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `iterations` (
  `id_iteration` int(10) NOT NULL AUTO_INCREMENT,
  `name_iteration` varchar(45) NOT NULL,
  `year` varchar(45) DEFAULT NULL,
  `id_author` int(10) NOT NULL,
  PRIMARY KEY (`id_iteration`),
  KEY `fk_iterations_authors1_idx` (`id_author`)
) ENGINE=InnoDB AUTO_INCREMENT=146 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iterations`
--

LOCK TABLES `iterations` WRITE;
/*!40000 ALTER TABLE `iterations` DISABLE KEYS */;
INSERT INTO `iterations` VALUES (1,'Co-expression','2008 Exp PCC',118),(2,'Genetic Interactions','2011-Aggravating-GI-CE-MM',119),(3,'Genetic Interactions','2011-Aggravating-GI-CE-RM',119),(4,'Genetic Interactions','2011-Alleviating-GI-CE-MM',119),(5,'Genetic Interactions','2011-Alleviating-GI-CE-RM',119),(6,'Genetic Interactions','2011-CE-MM-PCC',119),(7,'Genetic Interactions','2011-CE-RM-PCC',119),(8,'Genetic Interactions','2011 A',119),(9,'Genetic Interactions','2011 B',119),(10,'Genetic Interactions','2014-Aggravating-GI-WG',119),(11,'Genetic Interactions','2014-Alleviating-GI-WG',119),(12,'Genetic Interactions','2014-WG-PCC',119),(13,'Genetic Interactions','2014 A',119),(14,'Genetic Interactions','2014 B',119),(15,'Genetic Interactions','2016 A',120),(16,'Genetic Interactions','2016 B',120),(17,'Genetic Interactions','2016 A',121),(18,'Genetic Interactions','2016 B',121),(19,'Other','2009-GC-Int',122),(20,'Other','2011-Gene-Gene-PCC',123),(21,'Physical Interactions','2009-PPI',122),(22,'Physical Interactions','2014-Y2H-PPI',124),(23,'Shared protein domains','',125),(24,'Shared protein domains','',125),(25,'Physical_Interactions',NULL,127),(26,'Physical_Interactions',NULL,128),(27,'Physical_Interactions',NULL,129),(28,'Physical_Interactions',NULL,130),(29,'Physical_Interactions',NULL,131),(30,'Physical_Interactions',NULL,132),(31,'Physical_Interactions',NULL,133),(32,'Shared protein domains',NULL,125),(33,'Shared protein domains',NULL,126),(34,'Genetic Interactions',NULL,127),(35,'Co expression','2009',134),(36,'Co expression','2006',135),(37,'Co expression','2013',136),(38,'Co expression','2005',137),(39,'Co expression','2008',138),(40,'Co expression','2008',139),(41,'Co expression','2008',140),(42,'Co expression','2008',141),(43,'Co expression','2006',142),(44,'Co expression','2013',143),(45,'Co expression','2012',144),(46,'Co expression','2012',145),(47,'Co expression','2008',146),(48,'Co expression','2012',147),(49,'Co expression','2008',148),(50,'Co expression','2009',148),(51,'Co expression','2007',149),(52,'Co expression','2010',150),(53,'Co expression','2011',151),(54,'Co expression','2010',152),(55,'Co expression','2010',153),(56,'Co expression','2009',154),(57,'Co expression','2012',155),(58,'Co expression','2009',156),(59,'Co expression','2010',157),(60,'Co expression','2008',158),(61,'Co expression','2011',159),(62,'Co expression','2013',160),(63,'Co expression','2013 A',161),(64,'Co expression','2013 B',161),(65,'Co expression','2013 C',161),(66,'Co expression','2009',162),(67,'Co expression','2010',163),(68,'Co expression','2009',164),(69,'Co expression','2013',165),(70,'Co expression','2013',166),(71,'Co expression','2009',167),(72,'Co expression','2008',168),(73,'Co expression','2009',169),(74,'Co expression','2013',170),(75,'Co expression','2011 A',171),(76,'Co expression','2011 B',171),(77,'Co expression','2008',172),(78,'Shared protein domains',NULL,125),(79,'Shared protein domains',NULL,126),(80,'Other','2010 Co citation',59),(81,'Genetic Interactions',NULL,127),(82,'Genetic Interactions','2007',180),(83,'Genetic Interactions','2007',181),(84,'Genetic Interactions','2010',59),(85,'Genetic Interactions','2006',182),(86,'Physical_Interactions',NULL,127),(87,'Physical_Interactions','2002',196),(88,'Physical_Interactions','2008 A',197),(89,'Physical_Interactions','2008 B',197),(90,'Physical_Interactions','2001',198),(91,'Physical_Interactions','2005',199),(92,'Physical_Interactions',NULL,128),(93,'Physical_Interactions',NULL,129),(94,'Physical_Interactions',NULL,130),(95,'Physical_Interactions',NULL,131),(96,'Physical_Interactions',NULL,132),(97,'Physical_Interactions','2010 Protein interations',59),(98,'Physical_Interactions','2010 T2H',59),(99,'Physical_Interactions','2010',200),(100,'Physical_Interactions','2004',201),(101,'Physical_Interactions','2003',202),(102,'Physical_Interactions','2013',203),(103,'Physical_Interactions','2009',204),(104,'Physical_Interactions','2000',205),(105,'Physical_Interactions','2013',206),(106,'Physical_Interactions','2009',207),(107,'Co expression','2005',208),(108,'Co expression','2009',207),(109,'Co expression','2014',210),(110,'Co expression','2016',211),(111,'Co expression','2012',212),(112,'Co expression','2010',213),(113,'Co expression','2009',214),(114,'Co expression','2010',215),(115,'Co expression','2008',216),(116,'Co expression','2007 A',217),(117,'Co expression','2007 B',217),(118,'Co expression','2015',218),(119,'Co expression','2010',219),(120,'Co expression','2008',220),(121,'Co expression','2012',221),(122,'Co expression','2009',222),(123,'Co expression','2009',223),(124,'Co expression','2010',224),(125,'Co expression','2007',225),(126,'Co expression','2009',226),(127,'Co expression','2009',227),(128,'Co expression','2011',228),(129,'Co expression','2004',229),(130,'Co expression','2012',230),(131,'Co expression','2012',231),(132,'Co expression','2016',232),(133,'Co expression','2011',233),(134,'Co expression','2012',234),(135,'Co expression','2010',235),(136,'Co expression','2003',236),(137,'Co expression','2011',237),(138,'Co expression','2011',238),(139,'Co expression','2006',239),(140,'Co expression','2007',240),(141,'Co expression','2006',241),(142,'Co expression','2011',242),(143,'Co expression','2008',243),(144,'Co expression','2010',244),(145,'Co expression','2010',59);
/*!40000 ALTER TABLE `iterations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iterations_species`
--

DROP TABLE IF EXISTS `iterations_species`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `iterations_species` (
  `id_iteration_specie` int(10) NOT NULL AUTO_INCREMENT,
  `id_iteration` int(10) NOT NULL,
  `id_specie` int(10) NOT NULL,
  PRIMARY KEY (`id_iteration_specie`),
  KEY `fk_iterations_species_iterations1_idx` (`id_iteration`),
  KEY `fk_iterations_species_species1_idx` (`id_specie`)
) ENGINE=InnoDB AUTO_INCREMENT=146 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iterations_species`
--

LOCK TABLES `iterations_species` WRITE;
/*!40000 ALTER TABLE `iterations_species` DISABLE KEYS */;
INSERT INTO `iterations_species` VALUES (1,1,5),(2,2,5),(3,3,5),(4,4,5),(5,5,5),(6,6,5),(7,7,5),(8,8,5),(9,9,5),(10,10,5),(11,11,5),(12,12,5),(13,13,5),(14,14,5),(15,15,5),(16,16,5),(17,17,5),(18,18,5),(19,19,5),(20,20,5),(21,21,5),(22,22,5),(23,23,5),(24,24,5),(25,25,9),(26,26,9),(27,27,9),(28,28,9),(29,29,9),(30,30,9),(31,31,9),(32,32,9),(33,33,9),(34,34,9),(35,35,9),(36,36,9),(37,37,9),(38,38,9),(39,39,9),(40,40,9),(41,41,9),(42,42,9),(43,43,9),(44,44,9),(45,45,9),(46,46,9),(47,47,9),(48,48,9),(49,49,9),(50,50,9),(51,51,9),(52,52,9),(53,53,9),(54,54,9),(55,55,9),(56,56,9),(57,57,9),(58,58,9),(59,59,9),(60,60,9),(61,61,9),(62,62,9),(63,63,9),(64,64,9),(65,65,9),(66,66,9),(67,67,9),(68,68,9),(69,69,9),(70,70,9),(71,71,9),(72,72,9),(73,73,9),(74,74,9),(75,75,9),(76,76,9),(77,77,9),(78,78,2),(79,79,2),(80,80,2),(81,81,2),(82,82,2),(83,83,2),(84,84,2),(85,85,2),(86,86,2),(87,87,2),(88,88,2),(89,89,2),(90,90,2),(91,91,2),(92,92,2),(93,93,2),(94,94,2),(95,95,2),(96,96,2),(97,97,2),(98,98,2),(99,99,2),(100,100,2),(101,101,2),(102,102,2),(103,103,2),(104,104,2),(105,105,2),(106,106,2),(107,107,2),(108,108,2),(109,109,2),(110,110,2),(111,111,2),(112,112,2),(113,113,2),(114,114,2),(115,115,2),(116,116,2),(117,117,2),(118,118,2),(119,119,2),(120,120,2),(121,121,2),(122,122,2),(123,123,2),(124,124,2),(125,125,2),(126,126,2),(127,127,2),(128,128,2),(129,129,2),(130,130,2),(131,131,2),(132,132,2),(133,133,2),(134,134,2),(135,135,2),(136,136,2),(137,137,2),(138,138,2),(139,139,2),(140,140,2),(141,141,2),(142,142,2),(143,143,2),(144,144,2),(145,145,2);
/*!40000 ALTER TABLE `iterations_species` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menus`
--

DROP TABLE IF EXISTS `menus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menus`
--

LOCK TABLES `menus` WRITE;
/*!40000 ALTER TABLE `menus` DISABLE KEYS */;
INSERT INTO `menus` VALUES (1,0,'Usuarios','fa fa-user',1,1,NULL),(2,1,'Crear usuario',NULL,2,0,'servletFormCreateUser'),(4,1,'Listar usuarios',NULL,4,0,'servletListUsers'),(5,0,'Tool Data',NULL,5,1,NULL),(7,5,'Load Data',NULL,7,0,'servletFormLoadData'),(10,0,'Perfil',NULL,10,0,'servletFormPerfil');
/*!40000 ALTER TABLE `menus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nomenclatures`
--

DROP TABLE IF EXISTS `nomenclatures`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `nomenclatures` (
  `id_nomenclature` int(10) NOT NULL AUTO_INCREMENT,
  `name_nomenclature` varchar(45) NOT NULL,
  `id_specie` int(10) NOT NULL,
  PRIMARY KEY (`id_nomenclature`),
  KEY `fk_nomenclatures_species1_idx` (`id_specie`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nomenclatures`
--

LOCK TABLES `nomenclatures` WRITE;
/*!40000 ALTER TABLE `nomenclatures` DISABLE KEYS */;
INSERT INTO `nomenclatures` VALUES (1,'Ensembl Gene ID',1),(2,'Ensembl Protein ID',1),(3,'Entrez Gene ID',1),(4,'Gene Name',1),(5,'RefSeq Protein ID',1),(6,'RefSeq mRNA ID',1),(7,'Uniprot ID',1),(8,'Synonym',1),(9,'Ensembl Gene ID',2),(10,'Ensembl Protein ID',2),(11,'Gene Name',2),(12,'RefSeq Protein ID',2),(13,'RefSeq mRNA ID',2),(14,'Ensembl Protein ID',2),(15,'Entrez Gene ID',2),(16,'Uniprot ID',2),(17,'Ensembl Gene ID',3),(18,'Ensembl Protein ID',3),(19,'Entrez Gene ID',3),(20,'FlyBaseName Gene',3),(21,'Gene Name',3),(22,'RefSeq Protein ID',3),(23,'RefSeq mRNA ID',3),(24,'Uniprot ID',3),(25,'Ensembl Gene ID',4),(26,'Ensembl Protein ID',4),(27,'Gene Name',4),(28,'Synonym',4),(29,'Ensembl Gene ID',5),(30,'Ensembl Protein ID',5),(31,'Entrez Gene ID',5),(32,'Gene Name',5),(33,'RefSeq Protein ID',5),(34,'RefSeq mRNA ID',5),(35,'Synonym',5),(36,'Uniprot ID',5),(37,'Ensembl Gene ID',6),(38,'Ensembl Protein ID',6),(39,'Entrez Gene ID',6),(40,'Gene Name',6),(41,'MGI ID',6),(42,'RefSeq Protein ID',6),(43,'RefSeq mRNA ID',6),(44,'Synonym',6),(45,'Uniprot ID',6),(46,'Ensembl Gene ID',7),(47,'Ensembl Protein ID',7),(48,'Entrez Gene ID',7),(49,'Gene Name',7),(50,'RefSeq Protein ID',7),(51,'RefSeq mRNA ID',7),(52,'Synonym',7),(53,'Uniprot ID',7),(54,'Ensembl Gene ID',8),(55,'Synonym',8),(56,'Uniprot ID',8),(57,'Entrez Gene ID',8),(58,'Gene Name',8),(59,'RefSeq Protein ID',8),(60,'RefSeq mRNA ID',8);
/*!40000 ALTER TABLE `nomenclatures` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
 SET character_set_client = utf8mb4 ;
CREATE TABLE `roles_menus` (
  `id_role_menu` int(10) NOT NULL AUTO_INCREMENT,
  `id_role` int(10) NOT NULL,
  `id_menu` int(10) NOT NULL,
  PRIMARY KEY (`id_role_menu`),
  UNIQUE KEY `id_role_menu_UNIQUE` (`id_role_menu`),
  KEY `fk_roles_menus_roles1_idx` (`id_role`),
  KEY `fk_roles_menus_menus1_idx` (`id_menu`)
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
-- Table structure for table `species`
--

DROP TABLE IF EXISTS `species`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `species` (
  `id_specie` int(10) NOT NULL AUTO_INCREMENT,
  `name_specie` varchar(45) NOT NULL,
  PRIMARY KEY (`id_specie`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `species`
--

LOCK TABLES `species` WRITE;
/*!40000 ALTER TABLE `species` DISABLE KEYS */;
INSERT INTO `species` VALUES (1,'Arabidopsis_thaliana'),(2,'Caenorhabditis_elegans'),(3,'Drosophila_melanogaster'),(4,'Escherichia_coli'),(5,'Homo_sapiens'),(6,'Mus_musculus'),(7,'Rattus_norvegicus'),(8,'Saccharomyces_cerevisiae'),(9,'Danio rerio');
/*!40000 ALTER TABLE `species` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
  `id_user` int(10) NOT NULL AUTO_INCREMENT,
  `id_role` int(10) NOT NULL,
  `name_user` varchar(255) NOT NULL,
  `user` varchar(225) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `id_user_UNIQUE` (`id_user`),
  KEY `fk_users_roles1_idx` (`id_role`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,1,'root','root','7110eda4d09e062aa5e4a390b0a572ac0d2c0220','root@gmail.com'),(2,1,'monica','monica','7110eda4d09e062aa5e4a390b0a572ac0d2c0220','monica@gmail.com'),(3,2,'prueba','prueba','7110eda4d09e062aa5e4a390b0a572ac0d2c0220','prueba@gmail.com'),(4,2,'otro','otro','7110eda4d09e062aa5e4a390b0a572ac0d2c0220','otro@gmail.com');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'genevalidator'
--

--
-- Dumping routines for database 'genevalidator'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-17 21:18:05
