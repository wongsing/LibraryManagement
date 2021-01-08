/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : librarymanagement

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 07/01/2021 21:53:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'AA0000001' COMMENT '图书编号',
  `bookname` char(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图书名称',
  `booktype` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '科技' COMMENT '图书类别',
  `author` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图书作者',
  `translator` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '译者',
  `publisher` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '出版社',
  `publish_time` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '出版时间',
  `price` float NULL DEFAULT NULL,
  `stock` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('001', '计算机应用', '科技', '甘登岱', NULL, '航空工业出版社', '2008-07', 35, 2);
INSERT INTO `book` VALUES ('002', '现代西方文学', '文学', 'Albert', '文华章', '长江文艺出版社', '2008-08', 28, 3);
INSERT INTO `book` VALUES ('003', '职场关系学', '社科', '李三金', NULL, '中信出版社', '2009-10', 32, 3);
INSERT INTO `book` VALUES ('004', '新健康时代', '其他', '何明光', NULL, '中信出版社', '2010-06', 29, 5);
INSERT INTO `book` VALUES ('005', '新同居时代', '文学', '阿毛', NULL, '北大出版社', '2010-04', 29.8, 2);
INSERT INTO `book` VALUES ('006', '物流英语', '其他', '钟家玲', NULL, '物资出版社', '2009-08', 28, 5);
INSERT INTO `book` VALUES ('007', '数据结构', '科技', '包云中', NULL, '航空工业出版社', '2010-04', 29.8, 5);
INSERT INTO `book` VALUES ('009', '静静的冰河', '文学', '马巨', NULL, '人民文学出版社', '2007-09', 25, 6);

-- ----------------------------
-- Table structure for borrow
-- ----------------------------
DROP TABLE IF EXISTS `borrow`;
CREATE TABLE `borrow`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '借阅流水号',
  `book_id` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图书编号',
  `reader_id` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '读者编号',
  `borrow_date` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '借阅时间',
  `back_date` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '还书时间',
  `if_back` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否归还',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of borrow
-- ----------------------------
INSERT INTO `borrow` VALUES (2, '003', '001', '2020-12-06 ', NULL, '否');
INSERT INTO `borrow` VALUES (6, '001', '001', '2021-01-07 11:40:31', '2021-01-07', '否');
INSERT INTO `borrow` VALUES (8, '001', '003', '2021-01-07 21:50:34', '2021-01-07', '否');

-- ----------------------------
-- Table structure for reader
-- ----------------------------
DROP TABLE IF EXISTS `reader`;
CREATE TABLE `reader`  (
  `id` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'AA000001' COMMENT '读者编号',
  `readername` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '读者姓名',
  `readtype` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '读者类型',
  `sex` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '读者性别',
  `max_num` int(0) NULL DEFAULT NULL COMMENT '最大可借数',
  `days_num` int(0) NULL DEFAULT NULL COMMENT '可借天数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of reader
-- ----------------------------
INSERT INTO `reader` VALUES ('001', '邱展鹏', '学生', '女', 10, 90);
INSERT INTO `reader` VALUES ('002', '莫奈彬', '教师', '女', 10, 90);
INSERT INTO `reader` VALUES ('003', '王心艺', '学生', '男', 10, 90);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(0) NOT NULL COMMENT '用户流水号',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户姓名',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户密码',
  `is_admin` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否为管理员',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'wxy', '123456', '1');
INSERT INTO `user` VALUES (2, 'lbj', '123', '0');

SET FOREIGN_KEY_CHECKS = 1;
