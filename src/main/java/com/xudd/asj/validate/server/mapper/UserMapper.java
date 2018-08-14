package com.xudd.asj.validate.server.mapper;

import com.xudd.asj.validate.server.pojo.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * @Author xudd
 */
public interface UserMapper {
    /**
     * 根据用户名取得用户信息
     * @param loginName
     * @return
     */
    User selectByLoginName(@Param("loginName")String loginName);
}