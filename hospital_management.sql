/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80032 (8.0.32)
 Source Host           : localhost:3306
 Source Schema         : hospital_management

 Target Server Type    : MySQL
 Target Server Version : 80032 (8.0.32)
 File Encoding         : 65001

 Date: 24/05/2023 16:42:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for charge
-- ----------------------------
DROP TABLE IF EXISTS `charge`;
CREATE TABLE `charge`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ward_id` bigint NOT NULL COMMENT '病房表id',
  `patient_id` bigint NOT NULL COMMENT '病人表id',
  `charge_item` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '收费项目',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '收费价格',
  `count` int NULL DEFAULT NULL COMMENT '数量',
  `total_money` decimal(10, 2) NULL DEFAULT NULL COMMENT '总金额',
  `charging_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '收费的时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of charge
-- ----------------------------
INSERT INTO `charge` VALUES (1, 1, 3, '住院费', 1000.00, 1, 1000.00, '2023-05-14 13:35:55');
INSERT INTO `charge` VALUES (2, 1, 7, '住院费', 1000.00, 1, 1000.00, '2023-05-14 13:35:55');
INSERT INTO `charge` VALUES (4, 1, 5, '注射费', 1000.00, 2, 2000.00, '2023-05-15 19:40:11');
INSERT INTO `charge` VALUES (5, 2, 8, '注射费+住院费', 1000.00, 2, 2000.00, '2023-05-15 19:41:15');

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '科室名称',
  `head` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '负责人电话',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES (1, '消化科', '李华', '17644444444');
INSERT INTO `department` VALUES (2, '骨科', '仇二', '15622222222');
INSERT INTO `department` VALUES (3, '儿科', '肖申克', '15633333333');
INSERT INTO `department` VALUES (4, '皮肤科', '景元', '15644444444');
INSERT INTO `department` VALUES (5, '耳鼻科', '洞虚', '15655555555');
INSERT INTO `department` VALUES (6, '眼科', '若无', '15666666666');
INSERT INTO `department` VALUES (7, '老年病', '陈晓伟', '15688888888');
INSERT INTO `department` VALUES (9, '泌尿科', 'cxk', '15633333333');
INSERT INTO `department` VALUES (10, '心胸', '新一', '16911111111');

-- ----------------------------
-- Table structure for drug
-- ----------------------------
DROP TABLE IF EXISTS `drug`;
CREATE TABLE `drug`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '药物名称',
  `role` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '药物作用',
  `remaining_capacity` int NULL DEFAULT NULL COMMENT '药物剩余容量',
  `status` int NULL DEFAULT 1 COMMENT '销售状态：0表示售完  1表示在售中',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '药物价格',
  `image` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '药品图片',
  `shelf_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '药品上架时间',
  `description` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of drug
-- ----------------------------
INSERT INTO `drug` VALUES (1, '阿莫西林', '适用于敏感菌所致的呼吸系统、泌尿系统、耳鼻喉科及皮肤、软组织感染等', 100, 1, 25.50, 'b30cd056-eaa3-44c4-9730-08b3f5b960b6.jpeg', NULL, '不适用于所有人');
INSERT INTO `drug` VALUES (2, '青霉素', '抗病毒', 50, 1, 66.00, '3be58fcf-058e-4ee3-a5fe-fa20b171124f.jpeg', '2023-05-02 22:54:29', '很重要');
INSERT INTO `drug` VALUES (3, 'test', 'test', 11, 1, 22.00, NULL, '2023-05-03 17:24:32', 'test');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '姓名',
  `image` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '医生头像',
  `username` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '密码',
  `phone` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '手机号',
  `sex` varchar(2) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '性别',
  `dept_id` bigint NOT NULL COMMENT '科室表id',
  `id_number` varchar(18) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '身份证号',
  `status` int NOT NULL DEFAULT 1 COMMENT '状态 0:禁用，1:正常',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user` bigint NOT NULL COMMENT '创建人',
  `update_user` bigint NOT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_username`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = '员工信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (1, '管理员', '4aa25704-f8d6-40ca-89d1-9be07a297bd7.jpeg', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '13812312312', '1', 1, '110101199001010047', 1, '2021-05-06 17:20:07', '2023-05-14 20:58:06', 1, 1);
INSERT INTO `employee` VALUES (2, '张三', 'cce5715f29382336640fea12c8b9b255.jpeg', 'test1', 'e10adc3949ba59abbe56e057f20f883e', '13888888888', '0', 2, '441200222222222222', 1, '2023-03-28 13:54:27', '2023-03-28 13:54:27', 1, 1);
INSERT INTO `employee` VALUES (3, '李四', '5f18948f581ba91256fd0e8dab0d559f.jpeg', 'test2', 'e10adc3949ba59abbe56e057f20f883e', '17688888866', '1', 3, '441200888888888888', 1, '2023-03-28 14:14:39', '2023-04-25 16:53:10', 1, 1);
INSERT INTO `employee` VALUES (4, '王五', '5f18948f581ba91256fd0e8dab0d559f.jpeg', 'test3', 'e10adc3949ba59abbe56e057f20f883e', '13788888888', '1', 4, '441200000000000000', 1, '2023-03-28 21:24:23', '2023-03-28 21:24:27', 1, 1);
INSERT INTO `employee` VALUES (5, '赵六', '023c14bcc148caeaee4d22351c56fe25.jpeg', 'test4', 'e10adc3949ba59abbe56e057f20f883e', '15952465875', '1', 5, '438279824773248922', 1, '2023-03-28 21:38:25', '2023-03-28 21:55:36', 1, 1);
INSERT INTO `employee` VALUES (6, 'jam', '5f18948f581ba91256fd0e8dab0d559f.jpeg', 'test5', 'e10adc3949ba59abbe56e057f20f883e', '13888888888', '1', 6, '111222333444555666', 1, '2023-03-28 22:32:20', '2023-03-28 22:32:20', 1, 1);
INSERT INTO `employee` VALUES (7, 'jeryy', 'cce5715f29382336640fea12c8b9b255.jpeg', 'test6', 'e10adc3949ba59abbe56e057f20f883e', '15688888888', '0', 7, '999888777666555444', 1, '2023-03-28 22:33:53', '2023-03-28 22:33:55', 1, 1);
INSERT INTO `employee` VALUES (8, 'cxk', '292450f9-9494-4869-a497-a204175a89bb.jpeg', 'test7', 'e10adc3949ba59abbe56e057f20f883e', '17888888888', '1', 5, '422221199812212222', 1, '2023-04-26 00:24:52', '2023-04-26 00:25:44', 1, 1);
INSERT INTO `employee` VALUES (9, 'jianglou', '38e1e13c-a5f8-4f23-a19e-42ca40c7319c.jpeg', 'test22', 'e10adc3949ba59abbe56e057f20f883e', '13833333333', '1', 1, '421020123456789999', 1, '2023-04-29 15:43:00', '2023-05-12 11:11:17', 1, 1);
INSERT INTO `employee` VALUES (10, '燕十三', '2c874505-d6b3-42d6-9aeb-893b0dd2cb31.jpeg', 'yss', '0f359740bd1cda994f8b55330c86d845', '13722222223', '1', 6, '131232200001013333', 1, '2023-05-13 15:58:36', '2023-05-13 16:01:44', 1, 1);
INSERT INTO `employee` VALUES (11, '李华', 'e07067e6-7755-48ff-985d-e6c197588c00.jpeg', 'lihua', '0f359740bd1cda994f8b55330c86d845', '17644444444', '1', 1, '421444200101013333', 1, '2023-05-13 16:36:29', '2023-05-13 16:36:29', 1, 1);
INSERT INTO `employee` VALUES (12, '仇二', '4b378b84-9300-4ded-9a5e-7efe18fd7770.jpeg', 'qiuer', '0f359740bd1cda994f8b55330c86d845', '15622222222', '1', 3, '421444200012212222', 1, '2023-05-13 16:41:46', '2023-05-15 20:39:24', 1, 1);
INSERT INTO `employee` VALUES (13, 'test20', '3b5c345c-0fab-4395-8b84-0dda62dbd73b.jpeg', 'test20', '0f359740bd1cda994f8b55330c86d845', '15611111111', '1', 6, '320222199802057392', 0, '2023-05-14 20:14:30', '2023-05-14 20:14:52', 1, 1);
INSERT INTO `employee` VALUES (14, '新一', 'dfcd9af6-4f57-4eca-9466-0a98862162ba.jpeg', 'test21', '0f359740bd1cda994f8b55330c86d845', '16911111112', '1', 10, '523025199902053921', 1, '2023-05-15 21:40:33', '2023-05-15 21:40:51', 1, 1);
INSERT INTO `employee` VALUES (15, 'test1000', '913b214e-c354-4fef-bf79-bfca6e2462ec.jpeg', 'test1000', '0f359740bd1cda994f8b55330c86d845', '15912345678', '1', 1, '421040199602020202', 1, '2023-05-16 10:38:23', '2023-05-16 10:38:41', 1, 1);

-- ----------------------------
-- Table structure for patient
-- ----------------------------
DROP TABLE IF EXISTS `patient`;
CREATE TABLE `patient`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '病人姓名',
  `employee_id` bigint NOT NULL COMMENT '职工表id',
  `ward_id` bigint NOT NULL COMMENT '病房号id',
  `id_number` varchar(18) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '身份证',
  `address` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '住址',
  `phone` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '电话号码',
  `sex` varchar(2) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '性别',
  `age` int NOT NULL COMMENT '年龄',
  `disease` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '病症',
  `transfer_records` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '无' COMMENT '转院记录',
  `admission_date` datetime NOT NULL COMMENT '入住日期',
  `discharge_date` datetime NOT NULL COMMENT '出院日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of patient
-- ----------------------------
INSERT INTO `patient` VALUES (3, 'hig', 3, 1, '234567890098765431', '湖南省岳阳市', '15955555555', '1', 10, '营养不良', '无', '2023-04-29 00:00:00', '2023-06-01 00:00:00');
INSERT INTO `patient` VALUES (5, 'def', 5, 1, '123456789987654321', '湖南省岳阳市', '13844444444', '1', 44, '鼻炎', '无', '2023-04-29 00:00:00', '2023-05-02 20:20:57');
INSERT INTO `patient` VALUES (7, 'hsq', 3, 1, '34252319980907732x', '湖南岳阳', '13923232323', '1', 33, '突发性耳聋', '耳鼻科=====>骨科=====>儿科', '2023-05-04 20:56:13', '2023-05-18 00:00:00');
INSERT INTO `patient` VALUES (8, '叶青竹', 4, 1, '430411200101019986', '湖南岳阳', '18811111111', '1', 11, '肌肉拉伤', '无', '2023-05-13 16:03:41', '2023-06-13 08:00:00');
INSERT INTO `patient` VALUES (10, ' test111', 6, 8, '456123199802028753', '湖南岳阳', '13619292935', '1', 44, '胸闷', '无', '2022-04-29 02:02:00', '2023-05-17 15:35:16');

-- ----------------------------
-- Table structure for ward
-- ----------------------------
DROP TABLE IF EXISTS `ward`;
CREATE TABLE `ward`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `bed_count` int NULL DEFAULT NULL COMMENT '床位数',
  `ward_number` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '病房号',
  `dept_id` bigint NOT NULL COMMENT '科室id',
  `status` int NULL DEFAULT 1 COMMENT '床位状态 1：使用中 2：空闲中',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ward
-- ----------------------------
INSERT INTO `ward` VALUES (1, 8, '501', 7, 0);
INSERT INTO `ward` VALUES (2, 4, '502', 10, 1);
INSERT INTO `ward` VALUES (3, 4, '503', 2, 1);
INSERT INTO `ward` VALUES (4, 4, '504', 3, 1);
INSERT INTO `ward` VALUES (5, 4, '505', 5, 1);
INSERT INTO `ward` VALUES (6, 4, '506', 1, 1);
INSERT INTO `ward` VALUES (7, 4, '507', 4, 1);
INSERT INTO `ward` VALUES (8, 4, '508', 6, 1);
INSERT INTO `ward` VALUES (9, 4, '509', 9, 1);

SET FOREIGN_KEY_CHECKS = 1;
