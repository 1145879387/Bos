/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50022
Source Host           : localhost:3306
Source Database       : privilegedemo

Target Server Type    : MYSQL
Target Server Version : 50022
File Encoding         : 65001

Date: 2015-06-02 10:17:17
*/

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for `itcast_privilege`
-- ----------------------------
DROP TABLE IF EXISTS `itcast_privilege`;
CREATE TABLE `itcast_privilege` (
  `id`       BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name`     VARCHAR(64)         DEFAULT NULL,
  `url`      VARCHAR(255)        DEFAULT NULL,
  `parentId` BIGINT(20)          DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2A54CF9CE7D51427` (`parentId`),
  CONSTRAINT `FK2A54CF9CE7D51427` FOREIGN KEY (`parentId`) REFERENCES `itcast_privilege` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of itcast_privilege
-- ----------------------------
INSERT INTO `itcast_privilege` VALUES ('1', '系统管理', NULL, NULL);
INSERT INTO `itcast_privilege` VALUES ('2', '角色管理', '/roleAction_list', '1');
INSERT INTO `itcast_privilege` VALUES ('3', '部门管理', '/departmentAction_list', '1');
INSERT INTO `itcast_privilege` VALUES ('4', '用户管理', '/userAction_list', '1');
INSERT INTO `itcast_privilege` VALUES ('5', '角色列表', '/roleAction_list', '2');
INSERT INTO `itcast_privilege` VALUES ('6', '角色删除', '/roleAction_delete', '2');
INSERT INTO `itcast_privilege` VALUES ('7', '角色添加', '/roleAction_save', '2');
INSERT INTO `itcast_privilege` VALUES ('8', '角色修改', '/roleAction_update', '2');
INSERT INTO `itcast_privilege` VALUES ('9', '部门列表', '/departmentAction_list', '3');
INSERT INTO `itcast_privilege` VALUES ('10', '部门删除', '/departmentAction_delete', '3');
INSERT INTO `itcast_privilege` VALUES ('11', '部门添加', '/departmentAction_save', '3');
INSERT INTO `itcast_privilege` VALUES ('12', '部门修改', '/departmentAction_update', '3');
INSERT INTO `itcast_privilege` VALUES ('13', '用户列表', '/userAction_list', '4');
INSERT INTO `itcast_privilege` VALUES ('14', '用户删除', '/userAction_delete', '4');
INSERT INTO `itcast_privilege` VALUES ('15', '用户添加', '/userAction_save', '4');
INSERT INTO `itcast_privilege` VALUES ('16', '用户修改', '/userAction_update', '4');
INSERT INTO `itcast_privilege` VALUES ('17', '用户初始化密码', '/userAction_reSetPassword', '4');
INSERT INTO `itcast_privilege` VALUES ('21', '设置权限', '/roleAction_setPrivilege', '2');
