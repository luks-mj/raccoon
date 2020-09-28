## raccoon
springBoot 工程

#### mysql 数据类型对应java数据类型
```java
JDBC Type           Java Type  
CHAR                String  
VARCHAR             String  
LONGVARCHAR         String  
NUMERIC             java.math.BigDecimal  
DECIMAL             java.math.BigDecimal  
BIT                 boolean  
BOOLEAN             boolean  
TINYINT             byte  
SMALLINT            short  
INTEGER             int  
BIGINT              long  
REAL                float  
FLOAT               double  
DOUBLE              double  
BINARY              byte[]  
VARBINARY           byte[]  
LONGVARBINARY       byte[]  
DATE                java.sql.Date  
TIME                java.sql.Time  
TIMESTAMP           java.sql.Timestamp  
CLOB                Clob  
BLOB                Blob  
ARRAY               Array  
DISTINCT            mapping of underlying type  
STRUCT              Struct  
REF                         Ref  
DATALINK            java.net.URL[color=red][/color]

-- 菜单查询服务

SELECT DISTINCT
	m1.*, m2.`id` AS id2,
	m2.`component` AS component2,
	m2.`enabled` AS enabled2,
	m2.`iconCls` AS iconCls2,
	m2.`keepAlive` AS keepAlive2,
	m2.`name` AS name2,
	m2.`parentId` AS parentId2,
	m2.`requireAuth` AS requireAuth2,
	m2.`path` AS path2
FROM
	menu m1,
	menu m2,
	user_role urr,
	menu_role mr
WHERE
	m1.`id` = m2.`parentId`
AND urr.`urid` = 3 and urr.`rid`=mr.`rid` and mr.`mid`=m2.`id` and m2.`enabled`=true order by m1.`id`,m2.`id`

```
