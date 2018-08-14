-- 默认用户表数据
-- ----------------------------
-- Records of i_user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '超级管理员', 'admin', '1', '{bcrypt}$2a$10$4HKmn2xLILP1gbG83rlC6.IgSNtJMqeTjSLVLbc9OYu7u9rMGFYkm', '1', '18901541234', '111@qq.com', null);
INSERT INTO `user` VALUES ('2', '企业管理员', 'qiyeAdmin', '2', '{bcrypt}$2a$10$4HKmn2xLILP1gbG83rlC6.IgSNtJMqeTjSLVLbc9OYu7u9rMGFYkm', '1', '18901541234', '111@qq.com', 1);
INSERT INTO `user` VALUES ('3', '企业管理员1', 'qiyeAdmin1', '2', '{bcrypt}$2a$10$4HKmn2xLILP1gbG83rlC6.IgSNtJMqeTjSLVLbc9OYu7u9rMGFYkm', '1', '18901541234', '111@qq.com', 2);

-- ----------------------------
-- Records of i_role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '超级管理员', '1', '超级管理员', '1');
INSERT INTO `role` VALUES ('2', '系统管理员', '1', '系统级别管理员',  '1');
INSERT INTO `role` VALUES ('3', '企业管理员', '2', '企业级别的管理', '1');
INSERT INTO `role` VALUES ('4', '普通企业用户', '2', '企业级别的普通用户', '1');
INSERT INTO `role` VALUES ('5', '运维管理员', '1', '运维管理员',  '1');

-- ----------------------------
-- Records of i_user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1', '1');
INSERT INTO `user_role` VALUES ('2', '2', '3');
INSERT INTO `user_role` VALUES ('3', '3', '3');
-- 创建完表格后外键约束生效
SET FOREIGN_KEY_CHECKS = 1;
