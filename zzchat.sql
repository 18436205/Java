/*
Navicat MySQL Data Transfer

Source Server         : zzchat
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : zzchat

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2019-05-16 13:30:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for relation
-- ----------------------------
DROP TABLE IF EXISTS `relation`;
CREATE TABLE `relation` (
  `majoruser` varchar(20) DEFAULT NULL,
  `slaveuser` varchar(20) DEFAULT NULL,
  `relationtype` varchar(2) CHARACTER SET utf8mb4 DEFAULT NULL,
  KEY `relation_ibfk_1` (`majoruser`),
  KEY `relation_ibfk_2` (`slaveuser`),
  CONSTRAINT `relation_ibfk_1` FOREIGN KEY (`majoruser`) REFERENCES `user` (`username`),
  CONSTRAINT `relation_ibfk_2` FOREIGN KEY (`slaveuser`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of relation
-- ----------------------------
INSERT INTO `relation` VALUES ('1', '2', '1');
INSERT INTO `relation` VALUES ('1', '3', '1');
INSERT INTO `relation` VALUES ('2', '3', '1');
INSERT INTO `relation` VALUES ('3', '1', '1');
INSERT INTO `relation` VALUES ('3', '2', '1');
INSERT INTO `relation` VALUES ('2', '1', '1');
INSERT INTO `relation` VALUES ('顾天浩', '1', '1');
INSERT INTO `relation` VALUES ('顾天浩', '2', '1');
INSERT INTO `relation` VALUES ('顾天浩', '3', '1');
INSERT INTO `relation` VALUES ('xzr', 'wjm', '1');
INSERT INTO `relation` VALUES ('xzr', '1', '1');
INSERT INTO `relation` VALUES ('xzr', '2', '1');
INSERT INTO `relation` VALUES ('xzr', '3', '1');
INSERT INTO `relation` VALUES ('xzr', 'ljw', '1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) CHARACTER SET utf8mb4 DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `username` (`username`),
  KEY `username_2` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '1', '123456');
INSERT INTO `user` VALUES ('2', '2', '123456');
INSERT INTO `user` VALUES ('3', '3', '123456');
INSERT INTO `user` VALUES ('4', '顾天浩', '123456');
INSERT INTO `user` VALUES ('5', 'xzr', '123456');
INSERT INTO `user` VALUES ('6', 'wjm', '123456');
INSERT INTO `user` VALUES ('7', 'yk', '123456');
INSERT INTO `user` VALUES ('8', 'ljw', '123456');
INSERT INTO `user` VALUES ('9', 'yq', '123456');
