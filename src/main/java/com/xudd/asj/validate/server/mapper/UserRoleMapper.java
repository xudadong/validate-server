package com.xudd.asj.validate.server.mapper;

import com.xudd.asj.validate.server.pojo.entity.UserRole;

public interface UserRoleMapper {
    /**
     * 根据用户ID取得角色ID
     * @param userId
     * @return
     */
    UserRole selectByUserId(long userId);
}