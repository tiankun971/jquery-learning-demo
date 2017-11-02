-- s_user
insert into s_user (USER_ID, username, realname, email, password, DOB, LAST_PASSWORD_RESET_DATE) values(1, 'test', '测试帐号', 'test@163.com', '098f6bcd4621d373cade4e832627b4f6', current_DATE(), current_timestamp());
insert into s_user (USER_ID, username, realname, email, password, DOB, LAST_PASSWORD_RESET_DATE) values(2, 'admin', '管理员帐号', 'admin@163.com', '098f6bcd4621d373cade4e832627b4f6', current_DATE(), current_timestamp());
insert into s_user (USER_ID, username, realname, email, password, DOB, LAST_PASSWORD_RESET_DATE) values(3, 'operator', '操作员帐号', 'operator@163.com', '098f6bcd4621d373cade4e832627b4f6', current_DATE(), current_timestamp());
-- s_role
insert into s_role(ROLE_ID, name,display_name) values (1, 'ROLE_user','普通用户');
insert into s_role(ROLE_ID, name,display_name) values (2, 'ROLE_admin','系统管理员');
insert into s_role(ROLE_ID, name,display_name) values (3, 'ROLE_operator','操作员');
-- s_user_role_r
insert into s_user_role_r(id, S_USER_ID, S_ROLE_ID, CREATE_USER, UPDATE_USER) values (1, 1, 1, 'ADMIN', 'ADMIN');
insert into s_user_role_r(id, S_USER_ID, S_ROLE_ID, CREATE_USER, UPDATE_USER) values (2, 2, 1, 'ADMIN', 'ADMIN');
insert into s_user_role_r(id, S_USER_ID, S_ROLE_ID, CREATE_USER, UPDATE_USER) values (3, 2, 2, 'ADMIN', 'ADMIN');
insert into s_user_role_r(id, S_USER_ID, S_ROLE_ID, CREATE_USER, UPDATE_USER) values (4, 3, 3, 'ADMIN', 'ADMIN');


--s_resource--
INSERT INTO s_resource (ID, RESOURCE_CODE, RESOURCE_NAME, RESOURCE_TYPE) VALUES (1, 'home', '主页', 1);
INSERT INTO s_resource (ID, RESOURCE_CODE, RESOURCE_NAME, RESOURCE_TYPE) VALUES (2, 'shelvesGeneral', '货架一览', 1);
INSERT INTO s_resource (ID, RESOURCE_CODE, RESOURCE_NAME, RESOURCE_TYPE) VALUES (3, 'shelvesSearch', '搜索', 1);
INSERT INTO s_resource (ID, RESOURCE_CODE, RESOURCE_NAME, RESOURCE_TYPE) VALUES (4, 'putOnShelves', '运单上架', 1);
INSERT INTO s_resource (ID, RESOURCE_CODE, RESOURCE_NAME, RESOURCE_TYPE) VALUES (5, 'pullOffShelves', '运单下架', 1);
INSERT INTO s_resource (ID, RESOURCE_CODE, RESOURCE_NAME, RESOURCE_TYPE) VALUES (6, 'shelvesList', '货架维护', 1);
INSERT INTO s_resource (ID, RESOURCE_CODE, RESOURCE_NAME, RESOURCE_TYPE) VALUES (7, 'wayBillsList', '主单维护', 1);
INSERT INTO s_resource (ID, RESOURCE_CODE, RESOURCE_NAME, RESOURCE_TYPE) VALUES (8, 'roleList', '角色维护', 1);
INSERT INTO s_resource (ID, RESOURCE_CODE, RESOURCE_NAME, RESOURCE_TYPE) VALUES (9, 'userList', '用户维护', 1);
INSERT INTO s_resource (ID, RESOURCE_CODE, RESOURCE_NAME, RESOURCE_TYPE) VALUES (10, 'shelfDetail', '运单明细', 2);
INSERT INTO s_resource (ID, RESOURCE_CODE, RESOURCE_NAME, RESOURCE_TYPE) VALUES (11, 'userProfile', '个人信息', 2);
INSERT INTO s_resource (ID, RESOURCE_CODE, RESOURCE_NAME, RESOURCE_TYPE) VALUES (12, 'roleResourceRelations', '角色菜单', 2);
INSERT INTO s_resource (ID, RESOURCE_CODE, RESOURCE_NAME, RESOURCE_TYPE) VALUES (13, 'userRoleRelations', '用户角色', 2);
INSERT INTO s_resource (ID, RESOURCE_CODE, RESOURCE_NAME, RESOURCE_TYPE) VALUES (14, 'billsRelations', '主分单关系', 2);


INSERT INTO s_resource_role_r (ID, S_RESOURCE_CODE, S_ROLE_ID, CREATE_USER, UPDATE_USER) VALUES (1, 'home', 1, 'ADMIN', 'ADMIN');
INSERT INTO s_resource_role_r (ID, S_RESOURCE_CODE, S_ROLE_ID, CREATE_USER, UPDATE_USER) VALUES (2, 'shelvesGeneral', 1, 'ADMIN', 'ADMIN');
INSERT INTO s_resource_role_r (ID, S_RESOURCE_CODE, S_ROLE_ID, CREATE_USER, UPDATE_USER) VALUES (3, 'shelvesSearch', 1, 'ADMIN', 'ADMIN');
INSERT INTO s_resource_role_r (ID, S_RESOURCE_CODE, S_ROLE_ID, CREATE_USER, UPDATE_USER) VALUES (4, 'putOnShelves', 1, 'ADMIN', 'ADMIN');
INSERT INTO s_resource_role_r (ID, S_RESOURCE_CODE, S_ROLE_ID, CREATE_USER, UPDATE_USER) VALUES (5, 'pullOffShelves', 1, 'ADMIN', 'ADMIN');
INSERT INTO s_resource_role_r (ID, S_RESOURCE_CODE, S_ROLE_ID, CREATE_USER, UPDATE_USER) VALUES (6, 'shelvesList', 1, 'ADMIN', 'ADMIN');
INSERT INTO s_resource_role_r (ID, S_RESOURCE_CODE, S_ROLE_ID, CREATE_USER, UPDATE_USER) VALUES (7, 'wayBillsList', 1, 'ADMIN', 'ADMIN');
INSERT INTO s_resource_role_r (ID, S_RESOURCE_CODE, S_ROLE_ID, CREATE_USER, UPDATE_USER) VALUES (8, 'home', 2, 'ADMIN', 'ADMIN');
INSERT INTO s_resource_role_r (ID, S_RESOURCE_CODE, S_ROLE_ID, CREATE_USER, UPDATE_USER) VALUES (9, 'shelvesGeneral', 2, 'ADMIN', 'ADMIN');
INSERT INTO s_resource_role_r (ID, S_RESOURCE_CODE, S_ROLE_ID, CREATE_USER, UPDATE_USER) VALUES (10, 'shelvesSearch', 2, 'ADMIN', 'ADMIN');
INSERT INTO s_resource_role_r (ID, S_RESOURCE_CODE, S_ROLE_ID, CREATE_USER, UPDATE_USER) VALUES (11, 'putOnShelves', 2, 'ADMIN', 'ADMIN');
INSERT INTO s_resource_role_r (ID, S_RESOURCE_CODE, S_ROLE_ID, CREATE_USER, UPDATE_USER) VALUES (12, 'pullOffShelves', 2, 'ADMIN', 'ADMIN');
INSERT INTO s_resource_role_r (ID, S_RESOURCE_CODE, S_ROLE_ID, CREATE_USER, UPDATE_USER) VALUES (13, 'shelvesList', 2, 'ADMIN', 'ADMIN');
INSERT INTO s_resource_role_r (ID, S_RESOURCE_CODE, S_ROLE_ID, CREATE_USER, UPDATE_USER) VALUES (14, 'wayBillsList', 2, 'ADMIN', 'ADMIN');
INSERT INTO s_resource_role_r (ID, S_RESOURCE_CODE, S_ROLE_ID, CREATE_USER, UPDATE_USER) VALUES (15, 'roleList', 2, 'ADMIN', 'ADMIN');
INSERT INTO s_resource_role_r (ID, S_RESOURCE_CODE, S_ROLE_ID, CREATE_USER, UPDATE_USER) VALUES (16, 'userList', 2, 'ADMIN', 'ADMIN');
INSERT INTO s_resource_role_r (ID, S_RESOURCE_CODE, S_ROLE_ID, CREATE_USER, UPDATE_USER) VALUES (17, 'putOnShelves', 3, 'ADMIN', 'ADMIN');
INSERT INTO s_resource_role_r (ID, S_RESOURCE_CODE, S_ROLE_ID, CREATE_USER, UPDATE_USER) VALUES (18, 'pullOffShelves', 3, 'ADMIN', 'ADMIN');
INSERT INTO s_resource_role_r (ID, S_RESOURCE_CODE, S_ROLE_ID, CREATE_USER, UPDATE_USER) VALUES (19, 'shelvesSearch', 3, 'ADMIN', 'ADMIN');
INSERT INTO s_resource_role_r (ID, S_RESOURCE_CODE, S_ROLE_ID, CREATE_USER, UPDATE_USER) VALUES (20, 'shelvesGeneral', 3, 'ADMIN', 'ADMIN');
INSERT INTO s_resource_role_r (ID, S_RESOURCE_CODE, S_ROLE_ID, CREATE_USER, UPDATE_USER) VALUES (21, 'shelfDetail', 1, 'ADMIN', 'ADMIN');
INSERT INTO s_resource_role_r (ID, S_RESOURCE_CODE, S_ROLE_ID, CREATE_USER, UPDATE_USER) VALUES (22, 'shelfDetail', 2, 'ADMIN', 'ADMIN');
INSERT INTO s_resource_role_r (ID, S_RESOURCE_CODE, S_ROLE_ID, CREATE_USER, UPDATE_USER) VALUES (23, 'shelfDetail', 3, 'ADMIN', 'ADMIN');
INSERT INTO s_resource_role_r (ID, S_RESOURCE_CODE, S_ROLE_ID, CREATE_USER, UPDATE_USER) VALUES (24, 'userProfile', 1, 'ADMIN', 'ADMIN');
INSERT INTO s_resource_role_r (ID, S_RESOURCE_CODE, S_ROLE_ID, CREATE_USER, UPDATE_USER) VALUES (25, 'userProfile', 2, 'ADMIN', 'ADMIN');
INSERT INTO s_resource_role_r (ID, S_RESOURCE_CODE, S_ROLE_ID, CREATE_USER, UPDATE_USER) VALUES (26, 'userProfile', 3, 'ADMIN', 'ADMIN');
INSERT INTO s_resource_role_r (ID, S_RESOURCE_CODE, S_ROLE_ID, CREATE_USER, UPDATE_USER) VALUES (27, 'roleResourceRelations', 2, 'ADMIN', 'ADMIN');
INSERT INTO s_resource_role_r (ID, S_RESOURCE_CODE, S_ROLE_ID, CREATE_USER, UPDATE_USER) VALUES (28, 'userRoleRelations', 2, 'ADMIN', 'ADMIN');
INSERT INTO s_resource_role_r (ID, S_RESOURCE_CODE, S_ROLE_ID, CREATE_USER, UPDATE_USER) VALUES (29, 'billsRelations', 1, 'ADMIN', 'ADMIN');
INSERT INTO s_resource_role_r (ID, S_RESOURCE_CODE, S_ROLE_ID, CREATE_USER, UPDATE_USER) VALUES (30, 'billsRelations', 2, 'ADMIN', 'ADMIN');
INSERT INTO s_resource_role_r (ID, S_RESOURCE_CODE, S_ROLE_ID, CREATE_USER, UPDATE_USER) VALUES (31, 'billsRelations', 3, 'ADMIN', 'ADMIN');

