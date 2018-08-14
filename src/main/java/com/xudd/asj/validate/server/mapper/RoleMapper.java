package com.xudd.asj.validate.server.mapper;

import com.xudd.asj.validate.server.pojo.entity.Role;

public interface RoleMapper {
    /**
     * 取的角色信息
     * @param id
     * @return
     */
    Role selectByPrimaryKey(Long id);

}