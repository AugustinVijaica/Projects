-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: galerie
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.10-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `institutii`
--

DROP TABLE IF EXISTS `institutii`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `institutii` (
  `institutie_id` int(11) NOT NULL AUTO_INCREMENT,
  `nume` varchar(256) NOT NULL,
  PRIMARY KEY (`institutie_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `institutii`
--

LOCK TABLES `institutii` WRITE;
/*!40000 ALTER TABLE `institutii` DISABLE KEYS */;
INSERT INTO `institutii` VALUES (1,'Muzeul Luvru'),(2,'Muzeul Hermitage'),(3,'Muzeul San Marco');
/*!40000 ALTER TABLE `institutii` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `opere`
--

DROP TABLE IF EXISTS `opere`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `opere` (
  `opera_id` int(11) NOT NULL AUTO_INCREMENT,
  `opera_tip` varchar(256) NOT NULL,
  `titlu` varchar(256) NOT NULL,
  `artist` varchar(256) NOT NULL,
  `data` varchar(256) NOT NULL,
  `imagine` varchar(256) NOT NULL,
  `gen/stil` varchar(256) NOT NULL,
  `tehnica` varchar(256) DEFAULT NULL,
  `institutie_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`opera_id`),
  KEY `institutie_id` (`institutie_id`),
  CONSTRAINT `opere_ibfk_1` FOREIGN KEY (`institutie_id`) REFERENCES `institutii` (`institutie_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `opere`
--

LOCK TABLES `opere` WRITE;
/*!40000 ALTER TABLE `opere` DISABLE KEYS */;
INSERT INTO `opere` VALUES (1,'tablou','Gioconda','Leonardo da Vinci','1503','gioconda.jpg','portret','ulei',1),(2,'tablou','Clovnul','Belbu Alexandru-Marian','14.11.2016','clovnul.png','portret','acuarela',1),(3,'tablou','Cina cea de taina','Leonardo da Vinci','1503','cina.jpg','portret','ulei',2),(4,'sculptura','David','Michelangelo Buonarotti','1501','david.jpg','statuie',NULL,1),(5,'tablou','Nasterea lui Venuz','Sandro Botticelli','1484','venus.jpg','peisaj','acuarela',2),(6,'tablou','Noaptea instelata','Vincent Van Gogh','1889','noaptea.jpg','peisaj','acuarela',3),(7,'tablou','Fata cu cercei de perla','Johannes Vermeer','1665','cercelperla.jpg','portret','acuarela',3),(8,'sculptura','Cumintenia pamantului','Constantin Brancusi','1905','cumintenia.jpg','statuie',NULL,1),(9,'sculptura','Pieta','Michelangelo Buonarotti','1498','pieta.jpg','statuie',NULL,2),(10,'tablou','Crearea lui Adam','Michelangelo Buonarotti','1508','adam.jpg','peisaj','acuarela',2);
/*!40000 ALTER TABLE `opere` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utilizatori`
--

DROP TABLE IF EXISTS `utilizatori`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utilizatori` (
  `cont` varchar(256) NOT NULL,
  `parola` varchar(256) NOT NULL,
  `nume` varchar(256) NOT NULL,
  `rol` varchar(256) NOT NULL,
  PRIMARY KEY (`cont`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utilizatori`
--

LOCK TABLES `utilizatori` WRITE;
/*!40000 ALTER TABLE `utilizatori` DISABLE KEYS */;
INSERT INTO `utilizatori` VALUES ('admin','admin','Admin','Admin'),('robotzi99','mazi99','Vijaica Paul-Augustin','Angajat'),('test','test','Test','Angajat');
/*!40000 ALTER TABLE `utilizatori` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-13  0:42:34
