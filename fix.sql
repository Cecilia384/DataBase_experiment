CREATE DATABASE  IF NOT EXISTS `system-orderfood` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `system-orderfood`;
-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: system-orderfood
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `customer_name` varchar(255) NOT NULL COMMENT '用户姓名',
  `password` varchar(255) NOT NULL DEFAULT '666' COMMENT '‘666’',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(255) DEFAULT NULL COMMENT '手机',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `cimage` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'张三','202cb962ac59075b964b07152d234b70','3513683871@qq.com','1234567','河北省邢台市邢台县','26179307739300.png'),(4,'李四','202cb962ac59075b964b07152d234b70','3513683871@qq.com','19591581167','陕西省西安市','26891355180200.jpeg'),(5,'王五','202cb962ac59075b964b07152d234b70','3513683871@qq.com','19591581167111','陕西省西安市','27155921549200.jpeg'),(9,'小张','202cb962ac59075b964b07152d234b70',NULL,NULL,NULL,NULL),(10,'12345','827ccb0eea8a706c4c34a16891f84e7b',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `f_order`
--

DROP TABLE IF EXISTS `f_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `f_order` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '订单编号',
  `cid` int NOT NULL COMMENT '顾客id',
  `fid` int NOT NULL COMMENT '菜品id',
  `order_time` datetime NOT NULL COMMENT '下单日期',
  `total` double(5,2) NOT NULL,
  `count` int NOT NULL,
  `status` int NOT NULL DEFAULT '0',
  `isorder` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `cid` (`cid`),
  KEY `fid` (`fid`),
  CONSTRAINT `order_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `customer` (`id`),
  CONSTRAINT `order_ibfk_2` FOREIGN KEY (`fid`) REFERENCES `food` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `f_order`
--

LOCK TABLES `f_order` WRITE;
/*!40000 ALTER TABLE `f_order` DISABLE KEYS */;
INSERT INTO `f_order` VALUES (4,4,4,'2023-10-10 10:03:17',60.00,5,0,1),(5,4,5,'2023-10-10 14:35:57',45.00,9,0,1),(7,1,4,'2023-10-11 16:44:02',25.00,2,0,1),(11,1,4,'2023-10-14 13:39:35',12.00,1,0,1),(24,1,4,'2023-10-14 14:16:34',12.00,1,1,2),(26,1,5,'2023-10-14 14:34:02',5.00,1,1,2),(29,1,4,'2023-10-14 16:22:14',36.00,3,2,1),(32,1,8,'2023-10-14 23:11:19',20.00,1,0,2),(34,1,13,'2023-10-15 17:28:57',30.00,2,0,1),(36,4,13,'2023-10-15 20:41:03',15.00,1,0,1),(37,4,26,'2023-10-15 20:41:37',40.00,2,0,1),(38,4,6,'2023-10-15 20:42:20',80.00,4,1,1),(39,5,21,'2023-10-15 20:43:10',30.00,1,0,1),(40,5,22,'2023-10-15 20:43:25',36.00,3,0,1),(41,5,4,'2023-10-15 20:45:30',12.00,1,0,0),(43,5,4,'2023-10-15 20:47:03',12.00,1,0,2),(45,1,8,'2023-10-22 12:12:48',20.00,1,0,1),(46,1,19,'2023-10-22 12:13:26',15.00,1,0,1),(47,1,9,'2023-10-22 12:13:46',20.00,2,0,1),(49,1,12,'2023-10-22 20:49:19',39.00,3,0,1),(51,1,26,'2023-10-22 20:58:00',20.00,1,0,1),(53,1,9,'2023-10-22 21:01:40',10.00,1,0,0),(54,1,1,'2023-11-03 16:04:43',15.00,1,0,0),(55,1,6,'2023-11-03 16:04:51',20.00,1,0,0);
/*!40000 ALTER TABLE `f_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `food`
--

DROP TABLE IF EXISTS `food`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `food` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '菜品id',
  `food_name` varchar(255) NOT NULL COMMENT '菜品名称',
  `store` varchar(255) NOT NULL COMMENT '所属店铺',
  `price` double(5,2) NOT NULL COMMENT '单价',
  `stock` int NOT NULL COMMENT '容量',
  `descr` varchar(255) NOT NULL COMMENT '简介',
  `fimage` varchar(255) NOT NULL COMMENT '图像',
  `status` int NOT NULL DEFAULT '0',
  `store_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_store_id` (`store_id`),
  CONSTRAINT `fk_store_id` FOREIGN KEY (`store_id`) REFERENCES `store` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food`
--

LOCK TABLES `food` WRITE;
/*!40000 ALTER TABLE `food` DISABLE KEYS */;
INSERT INTO `food` VALUES (1,'韩式炸鸡','餐饮G区:北京特色',15.00,109,'很好吃','Snipaste_2023-10-15_17-32-32.png',1,1),(4,'香辣鸡腿堡','餐饮A区:炸鸡',12.00,93,'香辣美味','Snipaste_2023-10-14_23-22-32.png',1,1),(5,'肉夹馍a','餐饮B区:陕西小吃',5.00,120,'肉多','Snipaste_2023-10-14_23-15-00.png',2,8),(6,'脆皮五花肉','餐饮D区:熟食',20.00,97,'非常酥脆','Snipaste_2023-10-14_23-18-40.png',1,10),(7,'重庆小面','餐饮E区:面',15.00,100,'性价比最高面食','Snipaste_2023-10-14_23-26-59.png',2,11),(8,'黄焖鸡米饭','餐饮F区:米饭',20.00,100,'换门鸡米饭','132621058461800.png',1,12),(9,'凉皮','餐饮B区:陕西小吃',10.00,22,'清爽好吃','Snipaste_2023-10-15_16-05-05.png',1,8),(10,'蛋炒饭','餐饮F区:米饭',12.00,102,'蛋炒饭','193943415735000.png',1,12),(11,'羊肉泡馍','餐饮B区:陕西小吃',15.00,32,'羊肉泡馍','194239711237400.png',2,8),(12,'胡辣汤','餐饮B区:陕西小吃',13.00,21,'胡辣汤','194384927791300.png',1,8),(13,'宫保鸡丁','餐饮C区:川菜',15.00,22,'宫保鸡丁','194612610572700.png',1,9),(14,'麻婆豆腐','餐饮C区:川菜',15.00,104,'麻婆豆腐','194910400163400.png',1,9),(15,'鱼香肉丝','餐饮C区:川菜',15.00,23,'鱼香肉丝','195149641809100.png',1,9),(16,'水煮鱼','餐饮C区:川菜',20.00,23,'水煮鱼','195597470241000.png',0,9),(17,'油泼面','餐饮E区:面',12.00,32,'油泼面','195756310722300.png',0,11),(18,'香辣鸡翅','餐饮A区:炸鸡',15.00,100,'香辣鸡翅','195900439033900.png',1,1),(19,'劲爆鸡米花','餐饮A区:炸鸡',15.00,100,'劲爆鸡米花','195978900857400.png',1,1),(20,'薯条','餐饮A区:炸鸡',8.00,97,'薯条','196435008037800.png',0,1),(21,'北京烤鸭','餐饮G区:北京特色',30.00,23,'北京烤鸭','196607334164200.png',1,13),(22,'老北京炸酱面','餐饮G区:北京特色',12.00,98,'老北京炸酱面','196750065484900.png',1,13),(23,'羊蝎子火锅','餐饮G区:北京特色',30.00,23,'羊蝎子火锅','197325798577700.png',1,13),(24,'红烧牛肉面','餐饮E区:面',12.00,23,'红烧牛肉面','197676341364500.png',0,11),(25,'西红柿鸡蛋面','餐饮E区:面',10.00,23,'西红柿鸡蛋面','197959877333800.png',1,11),(26,'海南鸡饭','餐饮F区:米饭',20.00,22,'海南鸡饭','198491002260900.png',1,12),(27,'煲仔饭','餐饮F区:米饭',20.00,32,'煲仔饭','198597392686600.png',1,12);
/*!40000 ALTER TABLE `food` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `store`
--

DROP TABLE IF EXISTS `store`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `store` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '店铺id',
  `store_name` varchar(255) NOT NULL COMMENT '店铺名称',
  `descr` varchar(255) NOT NULL COMMENT '店铺简介',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `store`
--

LOCK TABLES `store` WRITE;
/*!40000 ALTER TABLE `store` DISABLE KEYS */;
INSERT INTO `store` VALUES (1,'餐饮A区:炸鸡','做各种跟鸡相关的食品'),(8,'餐饮B区:陕西小吃','陕西流行小吃'),(9,'餐饮C区:川菜','辣的菜'),(10,'餐饮D区:熟食','猪五花大全'),(11,'餐饮E区:面','各种面食'),(12,'餐饮F区:米饭','做大米饭'),(13,'餐饮G区:北京特色','北京小吃');
/*!40000 ALTER TABLE `store` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `email` varchar(255) NOT NULL COMMENT '邮箱',
  `phone` varchar(255) NOT NULL COMMENT '手机',
  `image` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','21232f297a57a5a743894a0e4a801fc3','234760595@qq.com','123445','3116944295000.png');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-15 17:04:12
