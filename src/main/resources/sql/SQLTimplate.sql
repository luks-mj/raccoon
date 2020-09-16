-- sql 表创建脚本 --

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
