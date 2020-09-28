-- sql 表创建脚本 --

-- 系统用户
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
 `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'userID',
 `name` varchar(32) DEFAULT NULL COMMENT '姓名',
 `phone` char(11) DEFAULT NULL COMMENT '手机号码',
 `telephone` varchar(16) DEFAULT NULL COMMENT '住宅电话',
 `address` varchar(64) DEFAULT NULL COMMENT '联系地址',
 `enabled` tinyint(1) DEFAULT '1',
 `username` varchar(255) DEFAULT NULL COMMENT '用户名',
 `password` varchar(255) DEFAULT NULL COMMENT '密码',
 `userface` varchar(255) DEFAULT NULL,
 `remark` varchar(255) DEFAULT NULL,
 PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- 菜单
CREATE TABLE `menu` (
 `id` int(11) NOT NULL AUTO_INCREMENT,
 `url` varchar(64) DEFAULT NULL,
 `path` varchar(64) DEFAULT NULL,
 `component` varchar(64) DEFAULT NULL,
 `name` varchar(64) DEFAULT NULL,
 `iconCls` varchar(64) DEFAULT NULL,
 `keepAlive` tinyint(1) DEFAULT NULL,
 `requireAuth` tinyint(1) DEFAULT NULL,
 `parentId` int(11) DEFAULT NULL,
 `enabled` tinyint(1) DEFAULT '1',
 PRIMARY KEY (`id`),
 KEY `parentId` (`parentId`),
 CONSTRAINT `menu_ibfk_1` FOREIGN KEY (`parentId`) REFERENCES `menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- 角色配置，默认初始值
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
 `id` int(11) NOT NULL AUTO_INCREMENT,
 `name` varchar(64) DEFAULT NULL,
 `nameZh` varchar(64) DEFAULT NULL COMMENT '角色名称',
 PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- 菜单角色
CREATE TABLE `menu_role` (
 `id` int(11) NOT NULL AUTO_INCREMENT,
 `mid` int(11) DEFAULT NULL,
 `rid` int(11) DEFAULT NULL,
 PRIMARY KEY (`id`),
 KEY `mid` (`mid`),
 KEY `rid` (`rid`),
 CONSTRAINT `menu_role_ibfk_1` FOREIGN KEY (`mid`) REFERENCES `menu` (`id`),
 CONSTRAINT `menu_role_ibfk_2` FOREIGN KEY (`rid`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=283 DEFAULT CHARSET=utf8;

-- 用户角色
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
 `id` int(11) NOT NULL AUTO_INCREMENT,
 `urid` int(11) DEFAULT NULL,
 `rid` int(11) DEFAULT NULL,
 PRIMARY KEY (`id`),
 KEY `rid` (`rid`),
 KEY `user_role_ibfk_1` (`urid`),
 CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`urid`) REFERENCES `user` (`id`) ON DELETE CASCADE,
 CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`rid`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8;

-- 导入初始值
INSERT INTO `role` (`id`, `name`, `nameZh`)
VALUES
 (
   1,
   'ROLE_manager',
   '省管理员'
 ),
 (
   2,
   'ROLE_personnel',
   '地市管理员'
 ),
 (
   3,
   'ROLE_recruiter',
   '县普通用户'
 ),
 (
   4,
   'ROLE_train',
   '地市普通用户'
 ),
 (
   5,
   'ROLE_performance',
   '访客'
 ),
 (
   6,
   'ROLE_admin',
   '系统管理员'
 ),
 (
   13,
   'ROLE_test2',
   '测试角色2'
 ),
 (
   14,
   'ROLE_test1',
   '测试角色1'
 ),
 (
   17,
   'ROLE_test3',
   '测试角色3'
 );

-- 国土数据导入模型
DROP TABLE IF EXISTS db_country_land;

CREATE TABLE `db_country_land` (
  `ID` varchar(25)  NOT NULL COMMENT '国土数据主键ID',
  `YEAR`   decimal(18,0) DEFAULT NULL COMMENT '评价年份',
  `MONTH`   decimal(18,0) DEFAULT NULL COMMENT '评价月份',
  `ENTERPRISE_NAME` varchar(300) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '国土数据企业名称',
  `ENTERPRISE_CODE` varchar(50) DEFAULT NULL COMMENT '统一社会信用代码',
  `REGISTER_AREA` double DEFAULT NULL COMMENT '登记用地面积',
  `LESSEE_AREA` double NOT NULL COMMENT '承租用地面积',
  `LEASE_AREA` double NOT NULL COMMENT '出租用地面积',
  `USER_ID`   decimal(18,0) DEFAULT NULL COMMENT '记录导入用户',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '记录首次创建的时间。',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '记录每次修改的时间。',
  `REMARK` varchar(2000) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '记录备注。',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `idx_enterprise_code` (`ENTERPRISE_CODE`),
  KEY `idx_enterprise_name` (`ENTERPRISE_NAME`),
  KEY `idx_year_month` (`YEAR`,`MONTH`),
  KEY `idx_enterprise_name_createtime` (`CREATE_DATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='记录企业国土数据资源数据信息';

-- 国税数据导入模型
DROP TABLE IF EXISTS db_country_taxation;

CREATE TABLE `db_country_taxation` (
  `ID` varchar(25) NOT NULL COMMENT '国税数据主键ID',
  `YEAR`   decimal(18,0) DEFAULT NULL COMMENT '评价年份',
  `MONTH`   decimal(18,0) DEFAULT NULL COMMENT '评价月份',
  `ENTERPRISE_NAME` varchar(300) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '国税数据企业名称',
  `ENTERPRISE_CODE` varchar(50) DEFAULT NULL COMMENT '统一社会信用代码',
  `TAXATION_DATA` double DEFAULT NULL COMMENT '实际缴纳国税金额（万元）',
  `TAXATION_DETAIL` varchar(2000) COLLATE utf8mb4_bin DEFAULT NULL COMMENT'国税税费明细',
  `USER_ID`   decimal(18,0) DEFAULT NULL COMMENT '记录导入用户ID',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '记录首次创建的时间。',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '记录每次修改的时间。',
  `REMARK` varchar(2000) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '记录备注。',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `idx_enterprise_code` (`ENTERPRISE_CODE`),
  KEY `idx_year_month` (`YEAR`,`MONTH`),
  KEY `idx_enterprise_name` (`ENTERPRISE_NAME`),
  KEY `idx_enterprise_name_createtime` (`CREATE_DATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='记录企业国税数据资源数据信息';

-- 统计数据导入
DROP TABLE IF EXISTS db_count_enterprise;
CREATE TABLE `db_count_enterprise` (
  `ID` varchar(25) NOT NULL COMMENT '主键ID',
  `YEAR`   decimal(18,0) DEFAULT NULL COMMENT '评价年份',
  `MONTH`   decimal(18,0) DEFAULT NULL COMMENT '评价月份',
  `ENTERPRISE_NAME` varchar(300) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '企业名称',
  `ENTERPRISE_CODE` varchar(50) DEFAULT NULL COMMENT '统一社会信用代码',
  `INDUSTRY_PRO` double DEFAULT NULL COMMENT '工业产值（万元）',
  `INDUSTRY_PUT` double DEFAULT NULL COMMENT '工业增加值（万元）',
  `INDUSTRY_RATIO` double DEFAULT NULL COMMENT 'R&D经费占主营业收入比重',
  `INDUSTRY_AVG_PERSON` decimal(10,0)  DEFAULT NULL COMMENT '年平均职工人数',
  `ENERGY_CONSUMPTION` double DEFAULT NULL COMMENT '综合能耗（顿标煤）',
  `USER_ID`   decimal(18,0) DEFAULT NULL COMMENT '记录导入用户ID',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '记录首次创建的时间。',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '记录每次修改的时间。',
  `REMARK` varchar(2000) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '记录备注。',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `idx_enterprise_code` (`ENTERPRISE_CODE`),
  KEY `idx_year_month` (`YEAR`,`MONTH`),
  KEY `idx_enterprise_name` (`ENTERPRISE_NAME`),
  KEY `idx_enterprise_name_createtime` (`CREATE_DATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='记录企业统计源数据信息';


--  企业信息数据导入模型
CREATE TABLE `db_enterprise` (
  `ID` varchar(25) NOT NULL COMMENT '主键ID',
  `ENTERPRISE_NAME` varchar(300) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '企业名称',
  `ENTERPRISE_CODE` varchar(50) DEFAULT NULL COMMENT '统一社会信用代码',
  `TAXATION_DATA` double DEFAULT NULL COMMENT '实际缴纳国税金额（万元）',
  `TAXATION_DETAIL` varchar(2000) COLLATE utf8mb4_bin DEFAULT NULL COMMENT'国税税费明细',
  `USER_ID`   decimal(18,0) DEFAULT NULL COMMENT '记录导入用户ID',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '记录首次创建的时间。',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '记录每次修改的时间。',
  `REMARK` varchar(2000) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '记录备注。',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `idx_enterprise_code` (`ENTERPRISE_CODE`),
  KEY `idx_enterprise_name_createtime` (`CREATE_DATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='记录企业国税数据资源数据信息';
