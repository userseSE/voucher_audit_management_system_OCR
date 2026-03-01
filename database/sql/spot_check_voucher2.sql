/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : spot_check_voucher

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 02/01/2023 11:32:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for auditor
-- ----------------------------
DROP TABLE IF EXISTS `auditor`;
CREATE TABLE `auditor`  (
  `auditor` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '审计师姓名',
  `auditor_id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '审计师工号',
  `aud_pro` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '负责的审计程序',
  `a_phone` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '审计师电话',
  `a_key` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '审计师账号密码',
  PRIMARY KEY (`auditor_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '审计师（管理者）表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of auditor
-- ----------------------------
INSERT INTO `auditor` VALUES ('是他', '1001', '实质性程序', NULL, NULL);
INSERT INTO `auditor` VALUES ('嘻嘻嘻', '1236', '哈利', '15299857778', '4564');
INSERT INTO `auditor` VALUES ('嚕嚕嚕', '1245', '酷酷', NULL, NULL);

-- ----------------------------
-- Table structure for auditor_manage
-- ----------------------------
DROP TABLE IF EXISTS `auditor_manage`;
CREATE TABLE `auditor_manage`  (
  `comp` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '单位名称',
  `vou_id` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '凭证编号',
  `vou_date` date NOT NULL,
  `vou_acc` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '记账科目',
  `con_acc` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '对方科目',
  `amount` float NULL DEFAULT NULL COMMENT '凭证金额',
  `aud_pro` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '审计程序',
  `spot_choose` tinyint(1) NULL DEFAULT NULL COMMENT '是否抽查',
  `aud_id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '抽凭索引号',
  `intern_name` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '抽凭负责人',
  `auditor_id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '审计师工号',
  `auditor` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '审计师姓名',
  `intern_id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '实习生工号',
  `need_pdf` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否需要上传pdf文件',
  `need_vouch` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否需要抽凭',
  `i_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '实习生状态（是否availible）',
  PRIMARY KEY (`comp`, `vou_id`, `vou_date`) USING BTREE,
  INDEX `FK_Reference_6`(`intern_id`) USING BTREE,
  INDEX `FK_Reference_1`(`auditor_id`) USING BTREE,
  CONSTRAINT `FK_Reference_1` FOREIGN KEY (`auditor_id`) REFERENCES `auditor` (`auditor_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_Reference_6` FOREIGN KEY (`intern_id`) REFERENCES `intern` (`intern_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_Reference_8` FOREIGN KEY (`comp`, `vou_id`, `vou_date`) REFERENCES `vouchers` (`comp`, `vou_id`, `vou_date`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '审计师用表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of auditor_manage
-- ----------------------------
INSERT INTO `auditor_manage` VALUES ('中白菜', '0100', '2022-09-23', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `auditor_manage` VALUES ('大白菜', '0110', '2022-09-22', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `auditor_manage` VALUES ('小白菜', '0111', '2022-09-21', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `auditor_manage` VALUES ('小白菜', '2000', '2022-09-01', NULL, NULL, NULL, NULL, NULL, '2000', NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for check_vou_info
-- ----------------------------
DROP TABLE IF EXISTS `check_vou_info`;
CREATE TABLE `check_vou_info`  (
  `comp` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '单位名称',
  `vou_id` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '凭证编号',
  `vou_date` date NULL DEFAULT NULL,
  `vou_acc` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '记账科目',
  `con_acc` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '对方科目',
  `amount` float NULL DEFAULT NULL COMMENT '凭证金额',
  `aud_pro` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '抽凭程序',
  `aud_id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '抽凭索引号',
  `intern_id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '实习生工号',
  `intern_name` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '抽凭负责人',
  `attach` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '凭证附件',
  `inv_typ` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '所涉发票类型',
  `inv_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '所涉发票编号（如有）',
  `procedu` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '手续是否齐全',
  `verify` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '核对内容是否相符',
  `att_file` varchar(2083) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '附件文件',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `need_pdf` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否需要上传pdf文件',
  `need_vouch` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否需要抽凭',
  PRIMARY KEY (`comp`, `aud_id`) USING BTREE,
  INDEX `FK_Reference_2`(`intern_id`) USING BTREE,
  INDEX `FK_Reference_9`(`comp`, `vou_id`, `vou_date`) USING BTREE,
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`intern_id`) REFERENCES `intern` (`intern_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_Reference_9` FOREIGN KEY (`comp`, `vou_id`, `vou_date`) REFERENCES `auditor_manage` (`comp`, `vou_id`, `vou_date`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '实习生抽查凭证信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of check_vou_info
-- ----------------------------
INSERT INTO `check_vou_info` VALUES ('中白菜', '0100', '2022-09-23', '库存现金', NULL, NULL, NULL, '0003', NULL, NULL, '123', '普通发票,专业发票', NULL, '否', '否', NULL, '2022-09-23 21:33:05', NULL, NULL, NULL);
INSERT INTO `check_vou_info` VALUES ('大白菜', '0110', '2022-09-22', NULL, NULL, NULL, NULL, '0002', '0001', NULL, NULL, '普通发票', NULL, '是', '否', '', '2022-10-03 22:04:33', NULL, NULL, NULL);
INSERT INTO `check_vou_info` VALUES ('小白菜', '0111', '2022-09-21', '银行存款', '固定资产', 100, '实质性程序', '0001', '0001', '是我', '增专票、订货单', '增专票', '0000', '是', '是', 'C:\\Users\\ASUS\\Desktop\\商科资料\\实习资料\\抽凭2\\ZQQ截止测试\\22-6-172.pdf', '2022-09-26 00:52:57', NULL, NULL, NULL);
INSERT INTO `check_vou_info` VALUES ('小白菜', '2000', '2022-09-01', NULL, NULL, NULL, NULL, '2000', '0001', NULL, NULL, '', NULL, NULL, NULL, '', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for intern
-- ----------------------------
DROP TABLE IF EXISTS `intern`;
CREATE TABLE `intern`  (
  `intern_name` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '实习生姓名',
  `intern_id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '实习生工号',
  `formal` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否签合同',
  `auditor_id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '带教审计师编号',
  `auditor` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '带教审计师姓名',
  `i_phone` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '实习生电话',
  `i_key` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '实习生账户密码',
  `i_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '实习生状态（是否free）',
  PRIMARY KEY (`intern_id`) USING BTREE,
  INDEX `FK_Reference_4`(`auditor_id`) USING BTREE,
  CONSTRAINT `FK_Reference_4` FOREIGN KEY (`auditor_id`) REFERENCES `auditor` (`auditor_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '凭证抽查者（实习生）表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of intern
-- ----------------------------
INSERT INTO `intern` VALUES ('是我', '0001', '是', '1001', '是他', NULL, NULL, '否');
INSERT INTO `intern` VALUES ('是我鸭', '0003', '否', NULL, NULL, NULL, NULL, '是');
INSERT INTO `intern` VALUES ('是我呢', '2222', NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for vouchers
-- ----------------------------
DROP TABLE IF EXISTS `vouchers`;
CREATE TABLE `vouchers`  (
  `comp` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '单位名称',
  `vou_id` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '凭证编号',
  `vou_date` date NOT NULL,
  `vou_acc` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '记账科目',
  `con_acc` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '对方科目',
  `amount` float NULL DEFAULT NULL COMMENT '凭证金额',
  PRIMARY KEY (`comp`, `vou_id`, `vou_date`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '凭证信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vouchers
-- ----------------------------
INSERT INTO `vouchers` VALUES ('中白菜', '0100', '2022-09-23', '库存现金', NULL, NULL);
INSERT INTO `vouchers` VALUES ('大白菜', '0110', '2022-09-22', '费用', NULL, NULL);
INSERT INTO `vouchers` VALUES ('小白菜', '0111', '2022-09-21', '银行存款', '固定资产', 100);
INSERT INTO `vouchers` VALUES ('小白菜', '2000', '2022-09-01', '库存商品', NULL, NULL);
INSERT INTO `vouchers` VALUES ('甜白菜', '3000', '2022-10-01', 'hahah', '', NULL);

SET FOREIGN_KEY_CHECKS = 1;
