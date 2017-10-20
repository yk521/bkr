
-- 清空记录
delete  from `role_permission`;
delete  from `permission`;
delete  from `user`;
delete  from `role`;


-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '管理员', '管理员');
INSERT INTO `role` VALUES ('2', '前台用户', '前台用户');
INSERT INTO `role` VALUES ('3', '后台用户', '后台用户');

-- ----------------------------
-- Records of user
-- ----------------------------

INSERT INTO `user` VALUES ('1', 'user1@xxx.com', 'user1', '96E79218965EB72C92A549DD5A330112', 'http://ouq085d8z.bkt.clouddn.com/b297f429f14708b0e60a0bce30337781.jpg', '1');
INSERT INTO `user` VALUES ('2', 'user2@xxx.com', 'user2', '202CB962AC59075B964B07152D234B70', 'http://ouq085d8z.bkt.clouddn.com/b297f429f14708b0e60a0bce30337782.jpg', '2');
INSERT INTO `user` VALUES ('3', 'user3@xxx.com', 'user3', '96E79218965EB72C92A549DD5A330112', 'http://ouq085d8z.bkt.clouddn.com/b297f429f14708b0e60a0bce30337783.jpg', '3');
INSERT INTO `user` VALUES ('4', 'test3@xxx.com', 'user4', '96E79218965EB72C92A549DD5A330112', 'http://ouq085d8z.bkt.clouddn.com/b297f429f14708b0e60a0bce30337783.jpg', '2');
INSERT INTO `user` VALUES ('5', 'test3@xxx.com', 'user5', '96E79218965EB72C92A549DD5A330112', 'http://ouq085d8z.bkt.clouddn.com/b297f429f14708b0e60a0bce30337783.jpg', '2');
INSERT INTO `user` VALUES ('6', 'test3@xxx.com', 'user6', '96E79218965EB72C92A549DD5A330112', 'http://ouq085d8z.bkt.clouddn.com/b297f429f14708b0e60a0bce30337783.jpg', '2');
INSERT INTO `user` VALUES ('7', 'test3@xxx.com', 'user7', '96E79218965EB72C92A549DD5A330112', 'http://ouq085d8z.bkt.clouddn.com/b297f429f14708b0e60a0bce30337783.jpg', '2');
INSERT INTO `user` VALUES ('8', 'test3@xxx.com', 'user8', '96E79218965EB72C92A549DD5A330112', 'http://ouq085d8z.bkt.clouddn.com/b297f429f14708b0e60a0bce30337783.jpg', '2');
INSERT INTO `user` VALUES ('9', 'test3@xxx.com', 'user9', '96E79218965EB72C92A549DD5A330112', 'http://ouq085d8z.bkt.clouddn.com/b297f429f14708b0e60a0bce30337783.jpg', '2');
INSERT INTO `user` VALUES ('10', 'test3@xxx.com', 'user10', '96E79218965EB72C92A549DD5A330112', 'http://ouq085d8z.bkt.clouddn.com/b297f429f14708b0e60a0bce30337783.jpg', '1');
INSERT INTO `user` VALUES ('11', 'test3@xxx.com', 'user11', '96E79218965EB72C92A549DD5A330112', 'http://ouq085d8z.bkt.clouddn.com/b297f429f14708b0e60a0bce30337783.jpg', '1');
INSERT INTO `user` VALUES ('12', 'test3@xxx.com', 'user12', '96E79218965EB72C92A549DD5A330112', 'http://ouq085d8z.bkt.clouddn.com/b297f429f14708b0e60a0bce30337783.jpg', '1');
INSERT INTO `user` VALUES ('13', 'test3@xxx.com', 'user13', '96E79218965EB72C92A549DD5A330112', 'http://ouq085d8z.bkt.clouddn.com/b297f429f14708b0e60a0bce30337783.jpg', '1');
INSERT INTO `user` VALUES ('14', 'test3@xxx.com', 'user14', '96E79218965EB72C92A549DD5A330112', 'http://ouq085d8z.bkt.clouddn.com/b297f429f14708b0e60a0bce30337783.jpg', '1');
INSERT INTO `user` VALUES ('15', 'test3@xxx.com', 'user15', '96E79218965EB72C92A549DD5A330112', 'http://ouq085d8z.bkt.clouddn.com/b297f429f14708b0e60a0bce30337783.jpg', '1');
INSERT INTO `user` VALUES ('16', 'test3@xxx.com', 'user16', '96E79218965EB72C92A549DD5A330112', 'http://ouq085d8z.bkt.clouddn.com/b297f429f14708b0e60a0bce30337783.jpg', '1');
-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', 'user.view', '用户查看');
INSERT INTO `permission` VALUES ('2', 'user.edit', '用户编辑');
INSERT INTO `permission` VALUES ('3', 'role.view', '角色查看');
INSERT INTO `permission` VALUES ('4', 'role.edit', '角色编辑');

-- ----------------------------
-- Records of role_permission
-- ----------------------------

INSERT INTO `role_permission` VALUES ('1', '1');
INSERT INTO `role_permission` VALUES ('1', '2');
INSERT INTO `role_permission` VALUES ('1', '3');
INSERT INTO `role_permission` VALUES ('1', '4');
INSERT INTO `role_permission` VALUES ('2', '1');
INSERT INTO `role_permission` VALUES ('2', '2');
INSERT INTO `role_permission` VALUES ('3', '1');
INSERT INTO `role_permission` VALUES ('3', '2');
