/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50557
Source Host           : localhost:3306
Source Database       : system-orderfood

Target Server Type    : MYSQL
Target Server Version : 50557
File Encoding         : 65001

Date: 2023-11-03 16:09:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `customer_name` varchar(255) NOT NULL COMMENT '用户姓名',
  `password` varchar(255) NOT NULL DEFAULT '666' COMMENT '‘666’',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(255) DEFAULT NULL COMMENT '手机',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `cimage` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('1', '张三', '202cb962ac59075b964b07152d234b70', '3513683871@qq.com', '1234567', '河北省邢台市邢台县', '26179307739300.png');
INSERT INTO `customer` VALUES ('4', '李四', '202cb962ac59075b964b07152d234b70', '3513683871@qq.com', '19591581167', '陕西省西安市', '26891355180200.jpeg');
INSERT INTO `customer` VALUES ('5', '王五', '202cb962ac59075b964b07152d234b70', '3513683871@qq.com', '19591581167111', '陕西省西安市', '27155921549200.jpeg');
INSERT INTO `customer` VALUES ('9', '小张', '202cb962ac59075b964b07152d234b70', null, null, null, null);

-- ----------------------------
-- Table structure for food
-- ----------------------------
DROP TABLE IF EXISTS `food`;
CREATE TABLE `food` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜品id',
  `food_name` varchar(255) NOT NULL COMMENT '菜品名称',
  `store` varchar(255) NOT NULL COMMENT '所属店铺',
  `price` double(5,2) NOT NULL COMMENT '单价',
  `stock` int(11) NOT NULL COMMENT '容量',
  `descr` varchar(255) NOT NULL COMMENT '简介',
  `fimage` varchar(255) NOT NULL COMMENT '图像',
  `status` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of food
-- ----------------------------
INSERT INTO `food` VALUES ('1', '韩式炸鸡', '炸鸡店', '15.00', '109', '很好吃', 'Snipaste_2023-10-15_17-32-32.png', '1');
INSERT INTO `food` VALUES ('4', '香辣鸡腿堡', '炸鸡店', '12.00', '93', '香辣美味', 'Snipaste_2023-10-14_23-22-32.png', '1');
INSERT INTO `food` VALUES ('5', '肉夹馍a', '陕西小吃', '5.00', '120', '肉多', 'Snipaste_2023-10-14_23-15-00.png', '2');
INSERT INTO `food` VALUES ('6', '脆皮五花肉', '五花肉熟食店', '20.00', '97', '非常酥脆', 'Snipaste_2023-10-14_23-18-40.png', '1');
INSERT INTO `food` VALUES ('7', '重庆小面', '面馆', '15.00', '100', '性价比最高面食', 'Snipaste_2023-10-14_23-26-59.png', '2');
INSERT INTO `food` VALUES ('8', '黄焖鸡米饭', '米饭店', '20.00', '100', '换门鸡米饭', '132621058461800.png', '1');
INSERT INTO `food` VALUES ('9', '凉皮', '陕西小吃', '10.00', '22', '清爽好吃', 'Snipaste_2023-10-15_16-05-05.png', '1');
INSERT INTO `food` VALUES ('10', '蛋炒饭', '米饭店', '12.00', '102', '蛋炒饭', '193943415735000.png', '1');
INSERT INTO `food` VALUES ('11', '羊肉泡馍', '陕西小吃', '15.00', '32', '羊肉泡馍', '194239711237400.png', '2');
INSERT INTO `food` VALUES ('12', '胡辣汤', '陕西小吃', '13.00', '21', '胡辣汤', '194384927791300.png', '1');
INSERT INTO `food` VALUES ('13', '宫保鸡丁', '川菜小馆', '15.00', '22', '宫保鸡丁', '194612610572700.png', '1');
INSERT INTO `food` VALUES ('14', '麻婆豆腐', '川菜小馆', '15.00', '104', '麻婆豆腐', '194910400163400.png', '1');
INSERT INTO `food` VALUES ('15', '鱼香肉丝', '川菜小馆', '15.00', '23', '鱼香肉丝', '195149641809100.png', '1');
INSERT INTO `food` VALUES ('16', '水煮鱼', '川菜小馆', '20.00', '23', '水煮鱼', '195597470241000.png', '0');
INSERT INTO `food` VALUES ('17', '油泼面', '面馆', '12.00', '32', '油泼面', '195756310722300.png', '0');
INSERT INTO `food` VALUES ('18', '香辣鸡翅', '炸鸡店', '15.00', '100', '香辣鸡翅', '195900439033900.png', '1');
INSERT INTO `food` VALUES ('19', '劲爆鸡米花', '炸鸡店', '15.00', '100', '劲爆鸡米花', '195978900857400.png', '1');
INSERT INTO `food` VALUES ('20', '薯条', '炸鸡店', '8.00', '97', '薯条', '196435008037800.png', '0');
INSERT INTO `food` VALUES ('21', '北京烤鸭', '北京特色店', '30.00', '23', '北京烤鸭', '196607334164200.png', '1');
INSERT INTO `food` VALUES ('22', '老北京炸酱面', '北京特色店', '12.00', '98', '老北京炸酱面', '196750065484900.png', '1');
INSERT INTO `food` VALUES ('23', '羊蝎子火锅', '北京特色店', '30.00', '23', '羊蝎子火锅', '197325798577700.png', '1');
INSERT INTO `food` VALUES ('24', '红烧牛肉面', '面馆', '12.00', '23', '红烧牛肉面', '197676341364500.png', '0');
INSERT INTO `food` VALUES ('25', '西红柿鸡蛋面', '面馆', '10.00', '23', '西红柿鸡蛋面', '197959877333800.png', '1');
INSERT INTO `food` VALUES ('26', '海南鸡饭', '米饭店', '20.00', '22', '海南鸡饭', '198491002260900.png', '1');
INSERT INTO `food` VALUES ('27', '煲仔饭', '米饭店', '20.00', '32', '煲仔饭', '198597392686600.png', '1');

-- ----------------------------
-- Table structure for f_order
-- ----------------------------
DROP TABLE IF EXISTS `f_order`;
CREATE TABLE `f_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单编号',
  `cid` int(11) NOT NULL COMMENT '顾客id',
  `fid` int(11) NOT NULL COMMENT '菜品id',
  `order_time` datetime NOT NULL COMMENT '下单日期',
  `total` double(5,2) NOT NULL,
  `count` int(11) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  `isorder` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `cid` (`cid`),
  KEY `fid` (`fid`),
  CONSTRAINT `order_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `customer` (`id`),
  CONSTRAINT `order_ibfk_2` FOREIGN KEY (`fid`) REFERENCES `food` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of f_order
-- ----------------------------
INSERT INTO `f_order` VALUES ('4', '4', '4', '2023-10-10 10:03:17', '60.00', '5', '0', '1');
INSERT INTO `f_order` VALUES ('5', '4', '5', '2023-10-10 14:35:57', '45.00', '9', '0', '1');
INSERT INTO `f_order` VALUES ('7', '1', '4', '2023-10-11 16:44:02', '25.00', '2', '0', '1');
INSERT INTO `f_order` VALUES ('11', '1', '4', '2023-10-14 13:39:35', '12.00', '1', '0', '1');
INSERT INTO `f_order` VALUES ('24', '1', '4', '2023-10-14 14:16:34', '12.00', '1', '1', '2');
INSERT INTO `f_order` VALUES ('26', '1', '5', '2023-10-14 14:34:02', '5.00', '1', '1', '2');
INSERT INTO `f_order` VALUES ('29', '1', '4', '2023-10-14 16:22:14', '36.00', '3', '2', '1');
INSERT INTO `f_order` VALUES ('32', '1', '8', '2023-10-14 23:11:19', '20.00', '1', '0', '2');
INSERT INTO `f_order` VALUES ('34', '1', '13', '2023-10-15 17:28:57', '30.00', '2', '0', '1');
INSERT INTO `f_order` VALUES ('36', '4', '13', '2023-10-15 20:41:03', '15.00', '1', '0', '1');
INSERT INTO `f_order` VALUES ('37', '4', '26', '2023-10-15 20:41:37', '40.00', '2', '0', '1');
INSERT INTO `f_order` VALUES ('38', '4', '6', '2023-10-15 20:42:20', '80.00', '4', '1', '1');
INSERT INTO `f_order` VALUES ('39', '5', '21', '2023-10-15 20:43:10', '30.00', '1', '0', '1');
INSERT INTO `f_order` VALUES ('40', '5', '22', '2023-10-15 20:43:25', '36.00', '3', '0', '1');
INSERT INTO `f_order` VALUES ('41', '5', '4', '2023-10-15 20:45:30', '12.00', '1', '0', '0');
INSERT INTO `f_order` VALUES ('43', '5', '4', '2023-10-15 20:47:03', '12.00', '1', '0', '2');
INSERT INTO `f_order` VALUES ('45', '1', '8', '2023-10-22 12:12:48', '20.00', '1', '0', '1');
INSERT INTO `f_order` VALUES ('46', '1', '19', '2023-10-22 12:13:26', '15.00', '1', '0', '1');
INSERT INTO `f_order` VALUES ('47', '1', '9', '2023-10-22 12:13:46', '20.00', '2', '0', '1');
INSERT INTO `f_order` VALUES ('49', '1', '12', '2023-10-22 20:49:19', '39.00', '3', '0', '1');
INSERT INTO `f_order` VALUES ('51', '1', '26', '2023-10-22 20:58:00', '20.00', '1', '0', '1');
INSERT INTO `f_order` VALUES ('53', '1', '9', '2023-10-22 21:01:40', '10.00', '1', '0', '0');
INSERT INTO `f_order` VALUES ('54', '1', '1', '2023-11-03 16:04:43', '15.00', '1', '0', '0');
INSERT INTO `f_order` VALUES ('55', '1', '6', '2023-11-03 16:04:51', '20.00', '1', '0', '0');

-- ----------------------------
-- Table structure for store
-- ----------------------------
DROP TABLE IF EXISTS `store`;
CREATE TABLE `store` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '店铺id',
  `store_name` varchar(255) NOT NULL COMMENT '店铺名称',
  `descr` varchar(255) NOT NULL COMMENT '店铺简介',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of store
-- ----------------------------
INSERT INTO `store` VALUES ('1', '炸鸡店', '做各种跟鸡相关的食品');
INSERT INTO `store` VALUES ('8', '陕西小吃', '陕西流行小吃');
INSERT INTO `store` VALUES ('9', '川菜小馆', '辣的菜');
INSERT INTO `store` VALUES ('10', '熟食店', '猪五花大全');
INSERT INTO `store` VALUES ('11', '面馆', '各种面食');
INSERT INTO `store` VALUES ('12', '米饭店', '做大米饭');
INSERT INTO `store` VALUES ('13', '北京特色店', '北京小吃');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `email` varchar(255) NOT NULL COMMENT '邮箱',
  `phone` varchar(255) NOT NULL COMMENT '手机',
  `image` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '21232f297a57a5a743894a0e4a801fc3', '234760595@qq.com', '123445', '3116944295000.png');
