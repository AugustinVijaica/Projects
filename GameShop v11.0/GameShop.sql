-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: productdb
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
-- Table structure for table `basket`
--

DROP TABLE IF EXISTS `basket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `basket` (
  `client_id` varchar(36) NOT NULL,
  `product_id` varchar(36) NOT NULL,
  `rating` int(11) NOT NULL,
  KEY `client_id` (`client_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `basket_ibfk_1` FOREIGN KEY (`client_id`) REFERENCES `clients` (`client_id`),
  CONSTRAINT `basket_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `basket`
--

LOCK TABLES `basket` WRITE;
/*!40000 ALTER TABLE `basket` DISABLE KEYS */;
INSERT INTO `basket` VALUES ('847ef2b1-399b-43a4-ae4f-bd2bd3d1f803','0f65d23d-ecaf-4077-826f-54e52909ac10',4),('50c2fd8f-5051-4b9b-8e4a-63e3ff2c9dfa','6f717a09-fa7b-46b5-bcb7-ca5b3513bc57',4),('50c2fd8f-5051-4b9b-8e4a-63e3ff2c9dfa','273e2d91-8395-4905-86a3-b86e634a92d6',5),('50c2fd8f-5051-4b9b-8e4a-63e3ff2c9dfa','168cf144-c5c6-4174-94b8-d2db614b66ea',5);
/*!40000 ALTER TABLE `basket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clients`
--

DROP TABLE IF EXISTS `clients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clients` (
  `client_id` varchar(36) NOT NULL,
  `username` varchar(45) NOT NULL,
  `pw` varchar(45) NOT NULL,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phoneNumber` varchar(45) NOT NULL,
  `country` varchar(45) NOT NULL,
  `address` varchar(255) NOT NULL,
  `likedGenre1` varchar(45) NOT NULL,
  `likedGenre2` varchar(45) NOT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients`
--

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` VALUES ('50c2fd8f-5051-4b9b-8e4a-63e3ff2c9dfa','mazipuiu','amsterdam69','Andru','Mazilescu','mazilescuandru@gmail.com','0769420360','Romania','Piata Mica, 14','multiplayer','horror'),('847ef2b1-399b-43a4-ae4f-bd2bd3d1f803','irmaria','irmaria','Maria','Irimus','irmariaaa@yahoo.com','0745863140','Romania','George Baritiu Street, nr. 26-28','multiplayer','shooter');
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `country` (
  `name` varchar(36) NOT NULL,
  `product_id` varchar(36) NOT NULL,
  `productName` varchar(45) NOT NULL,
  PRIMARY KEY (`name`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `country_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES ('Argentina','f39af2ae-7a3c-4036-8570-b93b7eed151a','Conan Exiles | Isle of Siptah Edition'),('Australia','ad072024-fed3-4f6c-88a7-e8aea236c132','The Last of Us Part II'),('Austria','84e4a96c-de45-48e3-8467-5f30509086d5','DayZ'),('Belgium','9aeb6272-31c3-41f2-a7f9-71973e480b46','Pathologic 2'),('Brazil','6f717a09-fa7b-46b5-bcb7-ca5b3513bc57','Overwatch'),('Bulgaria','fcbe4a67-a666-468f-b6a8-24ff5c154f1b','Dead by Daylight - Silent Hill Edition'),('China','5166f025-ca9d-4c0c-98b4-29cb8258c286','Rust'),('Croatia','0f65d23d-ecaf-4077-826f-54e52909ac10',' The Forest'),('Cyprus','168cf144-c5c6-4174-94b8-d2db614b66ea','Battlefield V'),('Czechia','1c5263d6-d88a-4cec-a105-70736c791ae3','Counter-Strike: Global Offensive'),('Denmark','9c318f18-3c68-4017-ab1f-10c06ab6ecc3',' Resident Evil Triple Pack'),('Estonia','2518ccaf-0b1b-4ae5-b88c-c9161cc00101','EA SPORTS FIFA 21'),('Finland','31fe7890-b0f3-449c-b5a7-249093bf04be','Observer: System Redux | Deluxe Edition'),('France','168cf144-c5c6-4174-94b8-d2db614b66ea','Battlefield V'),('Germany','273e2d91-8395-4905-86a3-b86e634a92d6','Grand Theft Auto V: Premium Online Edition'),('Greece','9d43ac1e-0d59-4cdc-ba1e-e994a09fbf3c',' Call of Duty: Warzone'),('Hungary','273e2d91-8395-4905-86a3-b86e634a92d6','Grand Theft Auto V: Premium Online Edition'),('India','5f2e74ad-77aa-4421-8d62-ad0aed05d8ca','Destiny 2 | Legendary Edition '),('Ireland','5166f025-ca9d-4c0c-98b4-29cb8258c286','Rust'),('Italy','af092b5a-f3a6-4fe8-b399-9eececc669a2','Little Nightmares II'),('Japan','ad072024-fed3-4f6c-88a7-e8aea236c132','The Last of Us Part II'),('Lithuania','5f2e74ad-77aa-4421-8d62-ad0aed05d8ca','Destiny 2 | Legendary Edition '),('Luxembourg','84e4a96c-de45-48e3-8467-5f30509086d5','DayZ'),('Mexico','6f717a09-fa7b-46b5-bcb7-ca5b3513bc57','Overwatch'),('Moldova','52976c80-7f7d-411e-9722-1fbfe8ed16f2','DOOM Eternal'),('Netherlands','9aeb6272-31c3-41f2-a7f9-71973e480b46','Pathologic 2'),('Poland','9c318f18-3c68-4017-ab1f-10c06ab6ecc3',' Resident Evil Triple Pack'),('Polonia','c6de4d9b-fc9a-47dd-9703-677cb42053ef','CALL OF DUTY: MODERN WARFARE'),('Portugalia','f39af2ae-7a3c-4036-8570-b93b7eed151a','Conan Exiles | Isle of Siptah Edition'),('Romania','0f65d23d-ecaf-4077-826f-54e52909ac10',' The Forest'),('Russia','52976c80-7f7d-411e-9722-1fbfe8ed16f2','DOOM Eternal'),('Slovakia','c6de4d9b-fc9a-47dd-9703-677cb42053ef','CALL OF DUTY: MODERN WARFARE'),('Slovenia','fcbe4a67-a666-468f-b6a8-24ff5c154f1b','Dead by Daylight - Silent Hill Edition'),('Spain','af092b5a-f3a6-4fe8-b399-9eececc669a2','Little Nightmares II'),('Sweden','9d43ac1e-0d59-4cdc-ba1e-e994a09fbf3c',' Call of Duty: Warzone'),('Turkey','fdf850e6-4bda-4670-939d-17c4cf081dec','NBA 2K21'),('Ukraine','fdf850e6-4bda-4670-939d-17c4cf081dec','NBA 2K21'),('USA','2518ccaf-0b1b-4ae5-b88c-c9161cc00101','EA SPORTS FIFA 21');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `historic`
--

DROP TABLE IF EXISTS `historic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `historic` (
  `order_id` int(11) DEFAULT NULL,
  `client_id` varchar(36) NOT NULL,
  `client_username` varchar(45) NOT NULL,
  `product_id` varchar(36) NOT NULL,
  `product_name` varchar(45) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `listPrice` decimal(10,2) DEFAULT NULL,
  KEY `client_id` (`client_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `historic_ibfk_1` FOREIGN KEY (`client_id`) REFERENCES `clients` (`client_id`),
  CONSTRAINT `historic_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historic`
--

LOCK TABLES `historic` WRITE;
/*!40000 ALTER TABLE `historic` DISABLE KEYS */;
INSERT INTO `historic` VALUES (1,'847ef2b1-399b-43a4-ae4f-bd2bd3d1f803','irmaria','0f65d23d-ecaf-4077-826f-54e52909ac10',' The Forest',3,147.00),(2,'50c2fd8f-5051-4b9b-8e4a-63e3ff2c9dfa','mazipuiu','6f717a09-fa7b-46b5-bcb7-ca5b3513bc57','Overwatch',3,300.00),(3,'50c2fd8f-5051-4b9b-8e4a-63e3ff2c9dfa','mazipuiu','273e2d91-8395-4905-86a3-b86e634a92d6','Grand Theft Auto V: Premium Online Edition',1,119.00),(4,'50c2fd8f-5051-4b9b-8e4a-63e3ff2c9dfa','mazipuiu','168cf144-c5c6-4174-94b8-d2db614b66ea','Battlefield V',3,366.00);
/*!40000 ALTER TABLE `historic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_items`
--

DROP TABLE IF EXISTS `order_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_items` (
  `order_id` int(11) NOT NULL,
  `product_id` varchar(36) NOT NULL,
  `quantity` int(11) NOT NULL,
  `list_price` decimal(10,2) NOT NULL,
  `rating` int(11) NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `order_items_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`),
  CONSTRAINT `order_items_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_items`
--

LOCK TABLES `order_items` WRITE;
/*!40000 ALTER TABLE `order_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `client_id` varchar(36) NOT NULL,
  `order_date` date DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `client_id` (`client_id`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`client_id`) REFERENCES `clients` (`client_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `product_id` varchar(36) NOT NULL,
  `name` varchar(45) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `quantity` int(11) NOT NULL,
  `genre` varchar(45) NOT NULL,
  `image` varchar(45) NOT NULL,
  `description` varchar(1024) NOT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES ('0f65d23d-ecaf-4077-826f-54e52909ac10',' The Forest',49.00,52,'survival','https://i.imgur.com/Xa3pnnI.jpg','As the lone survivor of a passenger jet crash, you find yourself in a mysterious forest battling to stay alive against a society of cannibalistic mutants. \r\nBuild, explore, survive in this terrifying first person survival horror simulator.\r\n'),('168cf144-c5c6-4174-94b8-d2db614b66ea','Battlefield V',122.00,47,'multiplayer','https://i.imgur.com/M93CdHF.jpg','Developed by EA Dice, Battlefield V (BF 5) is the continuation of the classic FPS franchise and a return to its roots thanks to its focus on WWII. Prepare for a compelling solo campaign featuring the most famous battlefields and for a complex multiplayer mode.\r\n'),('1c5263d6-d88a-4cec-a105-70736c791ae3','Counter-Strike: Global Offensive',200.00,90,'shooter','https://i.imgur.com/Dg2ADuY.jpg','Counter-Strike: Global Offensive is one of the most popular multiplayer shooters with great impact on the way we look at online gaming. \r\nIn CS:GO you choose your team- terrorists or counter-terrorists, eliminate your enemies and earn money to buy better equipment at the beginning of every round. The Developers of CS: GO have added new maps, characters and weapons to deliver a better, improved gaming experience. What has also been introduced are the new gameplay modes, matchmaking and leader boards. Still, the most fervent fans of the franchise will not be disappointed as the game has changed only slightly and the classic CS content remains.'),('2518ccaf-0b1b-4ae5-b88c-c9161cc00101','EA SPORTS FIFA 21',199.00,200,'multiplayer','https://i.imgur.com/ho5f7eF.jpg','FIFA 21 is a football (soccer) sports game developed by EA Vancouver and published by EA Sports. The game is yet another installment of classic football simulation series, dating back to the late nineties of the last century. In FIFA21, the player will once again play as their favorite team, participating in matches with other players through online multiplayer or against the computer offline. The video game offers improvements to its various modes, including FIFA Ultimate Team, Volta, and Career modes. Gameplay mechanics have also been revamped, offering even more intuitive controls. FIFA 21 was received positively by the critics, who praised improved gameplay mechanics, additional players mode, and demanding Career mode.\r\n'),('273e2d91-8395-4905-86a3-b86e634a92d6','Grand Theft Auto V: Premium Online Edition',119.00,94,'multiplayer','https://i.imgur.com/rxf0mU7.png','Partner with legendary impresario Tony Prince to open and operate a top shelf Nightclub featuring world-class DJ acts Solomun, Tale Of Us, Dixon and The Black Madonna, and use it as a front for the most concentrated network of criminal enterprise ever to hit San Andreas.\r\nGrand Theft Auto V for PC offers players the option to explore the award-winning world of Los Santos and Blaine County in resolutions of up to 4k and beyond, as well as the chance to experience the game running at 60 frames per second.\r\n'),('31fe7890-b0f3-449c-b5a7-249093bf04be','Observer: System Redux | Deluxe Edition',148.00,110,'horror','https://i.imgur.com/hTyP1Xd.jpg','Observer: System Redux is the definitive vision of the award-winning cyberpunk thriller, rebuilt and augmented for the next generation. Veteran Observers can dive deeper into this dystopian reality thanks to three brand-new side cases to solve. \"Errant Signal\", \"Her Fearful Symmetry\" and \"It Runs in the Family\" add another layer of depth to the game\'s story while exploring thought-provoking themes of a future that soon might become our present.\r\nOn top of that, System Redux offers expanded gameplay, which includes new game mechanics, new secrets to find, redesigned stealth, additional neural interrogations, and quality-of-life improvements made with the help of the Observer community.Newcomers get the chance to experience one of the most thrilling cyberpunk stories in all its next-gen glory: boasting 4K resolution, upgraded textures, new animations, models and effects, and transformed with jaw-dropping Ray-Tracing and HDR lighting.\r\n'),('5166f025-ca9d-4c0c-98b4-29cb8258c286','Rust',124.00,40,'survival','https://i.imgur.com/NWKLYeH.jpg','The only aim in Rust is to survive.\r\nTo do this you will need to overcome struggles such as hunger, thirst and cold. Build a fire. Build a shelter. Kill animals for meat. Protect yourself from other players. Create alliances with other players and together form a town. \r\nWhatever it takes to survive.'),('52976c80-7f7d-411e-9722-1fbfe8ed16f2','DOOM Eternal',90.00,56,'shooter','https://i.imgur.com/YL3Zmm6.jpg','DOOM Eternal is a first-person shooter game developed by ID Software and published by Bethesda Softworks. The game is the direct sequel to the 2016 Doom and the fifth main installment of the Doom series. In the game, the player once again controls the Doom Slayer, an ancient warrior facing off against the forces of Hell. DOOM Eternal takes place sometime after the events of the previous one and features new gameplay mechanics, which include new monsters to slay and improved traversal mechanics. The game received positive initial reception and was hailed a worthy successor of the Doom series.\r\n'),('5f2e74ad-77aa-4421-8d62-ad0aed05d8ca','Destiny 2 | Legendary Edition ',234.00,30,'multiplayer','https://i.imgur.com/zCGOBbs.jpg','Experience the epic sci-fi action of Destiny 2 and all of its expansions with Destiny 2: Legendary Edition. Create your Guardian and enjoy a cinematic storyline in an ever-evolving universe. Join your fellow Guardians for challenging co-op missions, or compete against them in a variety of PvP modes. This edition includes the critically acclaimed expansions Forsaken, Shadowkeep, and Beyond Light, as well as access to the currently active season content.\r\n'),('6f717a09-fa7b-46b5-bcb7-ca5b3513bc57','Overwatch',100.00,97,'shooter','https://i.imgur.com/VwvO9sH.jpg','Overwatch is a 2016 first-person shooter multiplayer video game for PC, developed and published by Blizzard Entertainment. In the game, players take control of one of several characters, called heroes and complete various missions in teamwork-based gameplay.\r\n'),('84e4a96c-de45-48e3-8467-5f30509086d5','DayZ',120.00,150,'survival','https://i.imgur.com/ZiAMZ5t.jpg','DayZ is a gritty, authentic, open-world survival game, in which players follow a single goal: to survive in the harsh post-apocalyptic landscape as long as they can. Players can experience powerful events and emotions arising from the ever-evolving emergent gameplay. There are no superficial tips, waypoints, built in tutorials or help given to players. Every decision matters, there are no save games, no extra lives, every mistake can be lethal. If you fail, you lose everything and you need to play again from the beginning with nothing but your wits, and your two hands. Fight the hostile environment, where every other player can be friend or foe and nothing can be taken for granted.'),('9aeb6272-31c3-41f2-a7f9-71973e480b46','Pathologic 2',30.00,78,'horror','https://i.imgur.com/euBEEFx.jpg','Pathologic 2 is a survival game developed by Ice Pick Lodge and published by tinyBuild. The game is a remake of 2005 title Pathologic and features similar gameplay and storytelling aesthetics, while also providing a lot of new features. Meant as a series of three separate, but thematically connected stories, the first part of Pathologic 2, tells the story of Artemy Burakh, a surgeon known in his hometown as Haruspex. He returns after living in another city at the behest of his father to help with a series of strange occurrences. The game received solid reviews from the critics for its atmosphere and survival gameplay mechanics.\r\n'),('9c318f18-3c68-4017-ab1f-10c06ab6ecc3',' Resident Evil Triple Pack',276.00,120,'survival','https://i.imgur.com/TeUzYmG.jpg','Enjoy three of the most memorable and defining survival horror titles in all of gaming with Resident Evil 4, Resident Evil 5 and Resident Evil 6, coming to the PS4 in one amazing value pack.\r\n'),('9d43ac1e-0d59-4cdc-ba1e-e994a09fbf3c',' Call of Duty: Warzone',400.00,30,'shooter','https://i.imgur.com/jkRIAOJ.png','Welcome to Warzone, the massive free-to-play combat arena from the world of Modern Warfare.\r\nDrop In\r\nJoin forces with your friends and jump into a battleground with up to 150 players.\r\nLoot For Rewards\r\nDiscover supply boxes and complete contracts to build your arsenal and gain a tactical advantage.\r\nBattle Across Two Epic Modes\r\nExperience a new way to Battle Royale or play in a race to earn the most Cash in the match.\r\n'),('ad072024-fed3-4f6c-88a7-e8aea236c132','The Last of Us Part II',280.00,130,'horror','https://i.imgur.com/CnMl5tn.png','The Last of Us Part II is an action-adventure game with survival horror elements released as an exclusive for PlayStation 4 in 2020 as the sequel to the Last of Us released in 2013. The action of the game is set in the post-apocalyptic United States around five years after the events presented in the first game where the world has been devastated by a mysterious pandemic. \r\n'),('af092b5a-f3a6-4fe8-b399-9eececc669a2','Little Nightmares II',150.00,80,'horror','https://i.imgur.com/YaLnhal.jpg','Little Nightmares II is a suspense horror game in which you play as Mono, a young boy trapped in a world that has been distorted by an evil transmission. Together with new friend Six, he sets out to discover the source of the Transmission.\r\nReturn to a world of charming horror in Little Nightmares II, a suspense adventure game in which you play as Mono, a young boy trapped in a world that has been distorted by the humming transmission of a distant tower.'),('c6de4d9b-fc9a-47dd-9703-677cb42053ef','CALL OF DUTY: MODERN WARFARE',264.00,110,'shooter','https://i.imgur.com/f35qFxV.jpg','Developed by Infinity Ward, in Call of Duty: Modern Warfare you will take on the role of highly-skilled Tier One operators in an incredibly raw and gritty narrative affecting the global balance of power.\r\nThe new Modern Warfare delivers an emotionally charged and intense campaign that shines a light on the changing nature of modern war. Expect a single-player experience that is edgy, culturally relevant, and thought-provoking.\r\nPlayers will fight alongside a diverse cast of international special forces and freedom fighters working within the grey area of their rules of engagement, in a number of heart-pounding covert operations set throughout iconic European cities and into the volatile expanses of the Middle East.\r\nMissions include close-quarter, stealth operations and long-range combat that puts tactical decision-making to the ultimate test. \r\n'),('f39af2ae-7a3c-4036-8570-b93b7eed151a','Conan Exiles | Isle of Siptah Edition',125.00,70,'survival','https://i.imgur.com/8fyeAuL.jpg','Exiled and left for dead in a hostile land, you are freed by the legendary warrior Conan himself. Use your wits to survive and turn this situation around. Conan Exiles is a survival game based on the incredible world created by Robert E. Howard. Are you strong and determined enough to crush your enemies and see them driven before you?'),('fcbe4a67-a666-468f-b6a8-24ff5c154f1b','Dead by Daylight - Silent Hill Edition',208.00,100,'horror','https://i.imgur.com/m2poqlE.jpg','The Silent Hill Edition of Dead by Daylight includes the base game, 4 original add-ons (Of Flesh and Mud, Spark of Madness, Curtain Call and the Shattered Bloodline Chapters), the Silent Hill Chapter, the Alissa Gillespie outfit for Cheryl Mason and the Dark Wish outfit for The Executioner.\r\n'),('fdf850e6-4bda-4670-939d-17c4cf081dec','NBA 2K21',131.00,50,'multiplayer','https://i.imgur.com/gKAOX6k.jpg','NBA 2K21 is the freshest approach to the whole NBA 2K series. The developers obviously squeeze as much as possible of it, basing on the popularity of their franchise. In fact, the game stands for itself without any going into details as every NBA fan who is a gamer would surely die to have this title in his or her collection. Close-to-real graphics with excellent character models, animations and realistic basketball matches will definitely amaze the players. This game feels like the player is a real basketball coach or even a player and can compete against the best NBA players at the moment from the likes of LeBron James, Stephen Curry and Kyrie Irving to young talest such as Luka Doncic.');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-02 18:16:00
