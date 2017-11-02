-- ----------------------------
-- Table structure for tbl_wms_biz_ass_bill_rel_entity
-- ----------------------------
DROP TABLE IF EXISTS `s_user_role_r`;
CREATE TABLE `s_user_role_r` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `S_USER_ID` int(11) DEFAULT NULL COMMENT '用户ID',
  `S_ROLE_ID` int(11) DEFAULT NULL COMMENT '角色ID',
  `CREATE_USER` varchar(200) DEFAULT NULL COMMENT '创建用户',
  `UPDATE_USER` varchar(200) DEFAULT NULL COMMENT '更新用户',
  `CREATE_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',  
  PRIMARY KEY (`ID`),
  KEY `F_USER_ID` (`S_USER_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
commit;

DROP TABLE IF EXISTS `s_resource_role_r`;
CREATE TABLE `s_resource_role_r` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `S_RESOURCE_CODE` VARCHAR(30) NOT NULL COMMENT '资源编码',
  `S_ROLE_ID` int(11) NOT NULL COMMENT '角色ID',
  `CREATE_USER` varchar(200) DEFAULT NULL COMMENT '创建用户',
  `UPDATE_USER` varchar(200) DEFAULT NULL COMMENT '更新用户',
  `CREATE_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',  
  PRIMARY KEY (`ID`),
  KEY `F_ROLE_ID`(`S_ROLE_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
commit;

DROP TABLE IF EXISTS `s_resource`;
CREATE TABLE `s_resource` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `RESOURCE_CODE` VARCHAR(30) NOT NULL COMMENT '资源编码',
  `RESOURCE_NAME` VARCHAR(30) NOT NULL COMMENT '资源名称',
  `RESOURCE_TYPE` int(1) NOT NULL DEFAULT 1 COMMENT '资源类型',
  `CREATE_USER` varchar(200) DEFAULT NULL COMMENT '创建用户',
  `UPDATE_USER` varchar(200) DEFAULT NULL COMMENT '更新用户',
  `CREATE_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',  
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
commit;

drop table if exists s_role;
create table s_role(
ROLE_ID int(11) not null primary key auto_increment, 
NAME varchar(20),
DISPLAY_NAME VARCHAR(30),
`CREATE_USER` varchar(200) DEFAULT NULL COMMENT '创建用户',
`UPDATE_USER` varchar(200) DEFAULT NULL COMMENT '更新用户',
`CREATE_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`UPDATE_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
  );
commit;

drop table if exists s_user;
create table s_user(
USER_ID int(11) not null primary key auto_increment, 
USERNAME varchar(50) not null,
REALNAME varchar(50) not null, 
PASSWORD varchar(100), 
EMAIL varchar(50), 
IMG_URL varchar(255) default 'images/userface3.jpg',
DOB DATE DEFAULT NULL,
LAST_PASSWORD_RESET_DATE datetime DEFAULT NULL,
ENABLED boolean not null default true,
ACCOUNT_NON_LOCKED boolean not null default true, 
ACCOUNT_NON_EXPIRED boolean not null default true, 
CREDENTIALS_NON_EXPIRED boolean not null default true,
`CREATE_USER` varchar(200) DEFAULT NULL COMMENT '创建用户',
`UPDATE_USER` varchar(200) DEFAULT NULL COMMENT '更新用户',
`CREATE_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`UPDATE_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
);
commit;