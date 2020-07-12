-- MySQL dump 10.13  Distrib 8.0.20, for Linux (x86_64)
--
-- Host: localhost    Database: book_store
-- ------------------------------------------------------
-- Server version	8.0.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `id` int NOT NULL AUTO_INCREMENT,
  `postal_code` varchar(10) DEFAULT NULL,
  `country` varchar(60) DEFAULT NULL,
  `city` varchar(85) DEFAULT NULL,
  `street` varchar(100) DEFAULT NULL,
  `building` varchar(25) DEFAULT NULL,
  `apartment` varchar(25) DEFAULT NULL,
  `info` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` (`id`, `postal_code`, `country`, `city`, `street`, `building`, `apartment`, `info`) VALUES (1,'603000','Russia','N.Novgorod','Udmurdskaya','1','1','Please call before came'),(2,'603000','Russia','N.Novgorod','Rozdestvenskaya','2','1','Please bring Items only after 12:00 am'),(3,'77018','USA','Houston','Main','5','2','We have a problem');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `id` int NOT NULL AUTO_INCREMENT,
  `person_id` int DEFAULT NULL,
  `promo_id` int DEFAULT NULL,
  `applied_bonuses` int DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_person_id` (`person_id`),
  KEY `promo_id` (`promo_id`),
  CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`),
  CONSTRAINT `cart_ibfk_2` FOREIGN KEY (`promo_id`) REFERENCES `promo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` (`id`, `person_id`, `promo_id`, `applied_bonuses`) VALUES (1,1,NULL,10),(2,2,10,10),(3,3,NULL,0),(4,4,NULL,0),(5,5,NULL,0);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_item`
--

DROP TABLE IF EXISTS `cart_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_item` (
  `no` int NOT NULL AUTO_INCREMENT,
  `cart_id` int NOT NULL,
  `item_id` int NOT NULL,
  `count` int DEFAULT '1',
  PRIMARY KEY (`no`),
  KEY `cart_id` (`cart_id`,`item_id`),
  KEY `item_id` (`item_id`),
  CONSTRAINT `cart_item_ibfk_1` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `cart_item_ibfk_2` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_item`
--

LOCK TABLES `cart_item` WRITE;
/*!40000 ALTER TABLE `cart_item` DISABLE KEYS */;
INSERT INTO `cart_item` (`no`, `cart_id`, `item_id`, `count`) VALUES (1,1,1,1),(2,1,2,1),(3,1,3,1),(4,3,2,1),(8,2,1,1),(9,2,4,1);
/*!40000 ALTER TABLE `cart_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` (`id`, `name`) VALUES (4,'Adventure'),(1,'Detective'),(2,'Documentary'),(3,'Fantasy'),(5,'Romance');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `price` decimal(10,0) DEFAULT NULL,
  `description` text,
  `count` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` (`id`, `name`, `price`, `description`, `count`) VALUES (1,'NewItem Super-mega-items4',210,'Some very importnant description',17),(2,'NewItem2',100,'Some very important description2',0),(3,'The best NewItem2',100,'Some very important description5',5),(4,'Telephone S1',100,'Some very important description5',12),(5,'Telephone S2',200,'Some very important description5',15),(6,'Telephone S3',300,'Some very important description5',14),(7,'Telephone S4',400,'Some very important description5',13),(8,'NewItem Super-mega-items25',100,'Some very importnant description',6),(9,'NewItem Super-mega-items25',100,'Some very importnant description',6),(10,'NewItem Super-mega-items25',100,'Some very importnant description',6),(11,'NewItem Super-mega-items25',100,'Some very importnant description',6),(12,'NewItem Super-mega-items25',100,'Some very importnant description',6);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_category`
--

DROP TABLE IF EXISTS `item_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item_category` (
  `no` int NOT NULL AUTO_INCREMENT,
  `item_id` int NOT NULL,
  `category_id` int NOT NULL,
  PRIMARY KEY (`no`),
  KEY `item_id` (`item_id`,`category_id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `item_category_ibfk_1` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `item_category_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_category`
--

LOCK TABLES `item_category` WRITE;
/*!40000 ALTER TABLE `item_category` DISABLE KEYS */;
INSERT INTO `item_category` (`no`, `item_id`, `category_id`) VALUES (1,1,1),(2,1,2),(3,2,4),(4,2,5);
/*!40000 ALTER TABLE `item_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `id` int NOT NULL AUTO_INCREMENT,
  `person_id` int NOT NULL,
  `address_id` int DEFAULT NULL,
  `state` varchar(10) DEFAULT NULL,
  `total` decimal(10,0) DEFAULT NULL,
  `placed_on` timestamp NULL DEFAULT NULL,
  `used_bonuses` int DEFAULT '0',
  `earned_bonuses` int DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `person_id` (`person_id`),
  KEY `address_id` (`address_id`),
  CONSTRAINT `order_ibfk_1` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`),
  CONSTRAINT `order_ibfk_2` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` (`id`, `person_id`, `address_id`, `state`, `total`, `placed_on`, `used_bonuses`, `earned_bonuses`) VALUES (1,1,1,'CANCELED',500,'2020-06-21 19:05:59',0,0),(2,1,2,'COMPLETED',0,'2020-06-21 19:05:59',0,0),(3,3,3,'CANCELED',0,'2020-06-21 19:05:59',0,0),(4,1,1,'PLACED',0,'2020-07-04 18:09:00',0,0),(5,1,1,'PLACED',0,'2020-07-04 18:09:22',0,0),(6,1,1,'PLACED',0,'2020-07-04 18:09:24',0,0),(7,1,1,'PLACED',0,'2020-07-04 18:14:46',0,0),(8,1,1,'PLACED',0,'2020-07-04 18:20:02',0,0),(9,1,1,'PLACED',0,'2020-07-04 18:21:11',0,0),(10,1,1,'CANCELED',0,'2020-07-04 18:22:26',0,0),(11,1,1,'CANCELED',0,'2020-07-04 18:27:44',0,0),(13,1,1,'PLACED',0,'2020-07-04 18:36:00',0,0),(14,1,1,'COMPLETED',0,'2020-07-04 18:36:26',0,0),(16,2,2,'CANCELED',300,'2020-07-05 19:22:11',0,0),(17,2,2,'PLACED',300,'2020-07-05 19:24:49',0,0),(18,2,2,'PLACED',300,'2020-07-05 19:28:29',0,0),(19,2,2,'PLACED',285,'2020-07-05 19:36:23',0,0),(20,2,2,'CANCELED',285,'2020-07-05 21:09:16',0,13),(21,2,2,'COMPLETED',285,'2020-07-05 21:11:08',10,13);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_item`
--

DROP TABLE IF EXISTS `order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_item` (
  `id` int NOT NULL AUTO_INCREMENT,
  `order_id` int NOT NULL,
  `item_id` int NOT NULL,
  `item_price` decimal(10,0) DEFAULT NULL,
  `item_count` int DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`,`item_id`),
  KEY `order_id_2` (`order_id`),
  KEY `item_id` (`item_id`),
  CONSTRAINT `order_item_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `order_item_ibfk_2` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
INSERT INTO `order_item` (`id`, `order_id`, `item_id`, `item_price`, `item_count`) VALUES (1,1,1,NULL,1),(2,1,2,NULL,1),(3,1,3,NULL,1),(4,3,2,NULL,1),(5,2,1,NULL,1),(6,13,3,100,1),(7,13,1,200,1),(8,14,3,100,1),(9,14,1,200,1),(12,16,4,100,1),(13,16,1,200,1),(14,17,4,100,1),(15,17,1,200,1),(16,18,4,100,1),(17,18,1,200,1),(18,19,1,190,1),(19,19,4,95,1),(20,20,1,190,1),(21,20,4,95,1),(22,21,1,190,1),(23,21,4,95,1);
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `person` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `address_id` int DEFAULT NULL,
  `bonus` int DEFAULT '0',
  `password` varchar(128) NOT NULL,
  `role` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_email` (`email`),
  KEY `address_id` (`address_id`),
  CONSTRAINT `person_ibfk_1` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` (`id`, `first_name`, `last_name`, `email`, `address_id`, `bonus`, `password`, `role`) VALUES (1,'First','Fourth_last','first@mail.com',1,50,'$2a$10$rfPNQj/IP.syuY1GcmOnaeeZOSvLLZ4AzeNuBoMa1kp.eaLcQ9IWi','USER'),(2,'Second2','Second_last3','new_name1@mail.com',2,53,'$2a$10$rfPNQj/IP.syuY1GcmOnaeeZOSvLLZ4AzeNuBoMa1kp.eaLcQ9IWi','USER'),(3,'Second2','Second_last3','last_name2@mail.com',3,50,'$2a$10$rfPNQj/IP.syuY1GcmOnaeeZOSvLLZ4AzeNuBoMa1kp.eaLcQ9IWi','USER'),(4,'first_name3','last_name3','last_name3@mail.com',NULL,50,'$2a$10$rfPNQj/IP.syuY1GcmOnaeeZOSvLLZ4AzeNuBoMa1kp.eaLcQ9IWi','USER'),(5,'FN_other','Second_last','fn_other@mail.com',NULL,50,'$2a$10$rfPNQj/IP.syuY1GcmOnaeeZOSvLLZ4AzeNuBoMa1kp.eaLcQ9IWi','USER'),(6,'FN_six','Second_last','fn_six@mail.com',NULL,50,'$2a$10$rfPNQj/IP.syuY1GcmOnaeeZOSvLLZ4AzeNuBoMa1kp.eaLcQ9IWi','USER'),(7,'FN_seven','Second_last','fn_seven@mail.com',NULL,50,'$2a$10$rfPNQj/IP.syuY1GcmOnaeeZOSvLLZ4AzeNuBoMa1kp.eaLcQ9IWi','USER'),(8,'FN_nine','Second_last','fn_nine@mail.com',NULL,50,'$2a$10$rfPNQj/IP.syuY1GcmOnaeeZOSvLLZ4AzeNuBoMa1kp.eaLcQ9IWi','USER'),(11,'Second23','Second_last33','new_name12@mail.com',NULL,50,'$2a$10$rfPNQj/IP.syuY1GcmOnaeeZOSvLLZ4AzeNuBoMa1kp.eaLcQ9IWi','USER'),(12,NULL,NULL,'email@email.com',NULL,0,'$2a$10$rfPNQj/IP.syuY1GcmOnaeeZOSvLLZ4AzeNuBoMa1kp.eaLcQ9IWi','USER'),(20,NULL,NULL,'admin@email.com',NULL,0,'$2a$10$A12HPO0tabAxdC.M37sU/eMgDKZ4UJIdEkQR9GdjaCB5A99IT6eiq','ADMIN'),(21,NULL,NULL,'supervisor@email.com',NULL,0,'$2a$10$4a2d5GQgTUMPbjItHVg6beSQYGtnFyPtV3o7FbeiAco.urCVIQGoi','USER');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promo`
--

DROP TABLE IF EXISTS `promo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `promo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(25) DEFAULT NULL,
  `start_date` timestamp NULL DEFAULT NULL,
  `end_date` timestamp NULL DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT '1',
  `discount` smallint DEFAULT NULL,
  `description` text,
  `type` varchar(15) DEFAULT NULL,
  `person_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_code` (`code`),
  KEY `person_id` (`person_id`),
  CONSTRAINT `promo_ibfk_1` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promo`
--

LOCK TABLES `promo` WRITE;
/*!40000 ALTER TABLE `promo` DISABLE KEYS */;
INSERT INTO `promo` (`id`, `code`, `start_date`, `end_date`, `is_active`, `discount`, `description`, `type`, `person_id`) VALUES (1,'MayDay','2020-05-21 19:05:59','2020-07-21 19:05:59',1,2,NULL,'COMMON',NULL),(2,'MegaSail','2020-05-21 19:05:59','2020-07-21 19:05:59',1,5,NULL,'COMMON',NULL),(3,'t_oidar','2020-05-21 19:05:59','2020-07-21 19:05:59',1,10,NULL,'COMMON',NULL),(4,'JulyDay','2020-05-21 19:06:59','2020-07-21 19:07:59',0,3,NULL,'COMMON',NULL),(9,'RandomGenerated1','2020-05-28 12:25:15','2020-07-28 12:25:15',1,2,NULL,'PERSONAL',1),(10,'RandomGenerated2','2020-05-28 12:25:15','2020-07-28 12:25:15',1,5,NULL,'PERSONAL',2);
/*!40000 ALTER TABLE `promo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promo_category`
--

DROP TABLE IF EXISTS `promo_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `promo_category` (
  `no` int NOT NULL AUTO_INCREMENT,
  `promo_id` int NOT NULL,
  `category_id` int NOT NULL,
  PRIMARY KEY (`no`),
  KEY `promo_id` (`promo_id`,`category_id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `promo_category_ibfk_1` FOREIGN KEY (`promo_id`) REFERENCES `promo` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `promo_category_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promo_category`
--

LOCK TABLES `promo_category` WRITE;
/*!40000 ALTER TABLE `promo_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `promo_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wish_list`
--

DROP TABLE IF EXISTS `wish_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wish_list` (
  `person_id` int NOT NULL,
  `item_id` int NOT NULL,
  PRIMARY KEY (`person_id`,`item_id`),
  KEY `item_id` (`item_id`),
  CONSTRAINT `wish_list_ibfk_1` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`),
  CONSTRAINT `wish_list_ibfk_2` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wish_list`
--

LOCK TABLES `wish_list` WRITE;
/*!40000 ALTER TABLE `wish_list` DISABLE KEYS */;
INSERT INTO `wish_list` (`person_id`, `item_id`) VALUES (1,2),(1,3),(2,4);
/*!40000 ALTER TABLE `wish_list` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-07-12 18:54:44
