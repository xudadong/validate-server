-- 初始化数据库，取消外键约束检查
SET FOREIGN_KEY_CHECKS = 0;

-- 用户表
DROP TABLE IF EXISTS user;
CREATE TABLE `user`
(
  `id`            INT NOT NULL AUTO_INCREMENT PRIMARY KEY, -- 主键自增
  `user_name`     VARCHAR(30), -- 用户名
  `login_name`    VARCHAR(30), -- 登录名
  `user_type`     INT, -- 用户类型
  `password`      VARCHAR(255), -- 密码
  `enabled`       INT, -- 是否可用
  `phone`         VARCHAR(20), -- 联系电话
  `email`         VARCHAR(30), -- 电子邮箱
  `enterprise_id` INT, -- 企业ID（外键 企业信息表主键）
  FOREIGN KEY (`enterprise_id`) REFERENCES `i_enterprise` (`id`)-- 企业表外键
);

-- 角色表
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`
(
  `id`          INT NOT NULL AUTO_INCREMENT PRIMARY KEY, -- 主键自增
  `role_name`   VARCHAR(20), -- 角色名称
  `role_type`   VARCHAR(20), -- 角色类型
  `role_detail` VARCHAR(100), -- 角色描述
  `enabled`     INT(1) -- 是否启用
);

-- 用户角色关系表
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id`      INT NOT NULL AUTO_INCREMENT PRIMARY KEY, -- 主键自增
  `user_id` INT, -- 用户ID(外键 用户表主键)
  `role_id` INT -- 角色ID(外键 角色表主键)
);


