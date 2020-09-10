-- sql 表创建脚本 --

-- 国土数据导入模型
DROP TABLE IF EXISTS db_country_land;

CREATE TABLE `db_country_land` (
  `ID` decimal(25,0) NOT NULL COMMENT '国土数据主键ID',
  `ENTERPRISE_NAME` varchar(300) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '国土数据企业名称',
  `ENTERPRISE_CODE` varchar(50) DEFAULT NULL COMMENT '统一社会信用代码',
  `REGISTER_AREA` decimal(18,0) DEFAULT NULL COMMENT '登记用地面积',
  `LESSEE_AREA` decimal(18,0) NOT NULL COMMENT '承租用地面积',
  `LEASE_AREA` decimal(18,0) NOT NULL COMMENT '出租用地面积',
  `USER_ID`   decimal(18,0) DEFAULT NULL COMMENT '记录导入用户',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '记录首次创建的时间。',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '记录每次修改的时间。',
  `REMARK` varchar(2000) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '记录备注。',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `idx_enterprise_code` (`ENTERPRISE_CODE`),
  KEY `idx_enterprise_name_updatetime` (`UPDATE_DATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='记录企业国土数据资源数据信息';
