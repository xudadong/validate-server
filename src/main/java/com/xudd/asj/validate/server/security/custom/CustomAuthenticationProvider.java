package com.xudd.asj.validate.server.security.custom;

import com.xudd.asj.validate.server.mapper.RoleMapper;
import com.xudd.asj.validate.server.mapper.UserMapper;
import com.xudd.asj.validate.server.mapper.UserRoleMapper;
import com.xudd.asj.validate.server.pojo.entity.Role;
import com.xudd.asj.validate.server.pojo.entity.User;
import com.xudd.asj.validate.server.pojo.entity.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * @Description AUTH_TOKEN
 * @Author xudd
 * @Date 2018/8/13 0013 上午 10:23
 */
@Component
@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {

    /**
     * 用户平台登录 key 后台管理
     */
    private final String SYSTEM_TYPE_KEY = "system";

    /**
     * 用户平台登录 key 客户中心
     */
    private final String CUSTOM_TYPE_KEY = "custom";

    /**
     * 用户平台登录 key 手机
     */
    private final String APP_TYPE_KEY = "app";

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    /**
     * 校验平台登录
     *
     * @param auth
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        log.info("登录信息:{}", auth);
        //登录验证
        return vaildAuthenticate(auth);
    }

    /**
     * 返回true就用上面逻辑
     *
     * @param arg0
     * @return
     */
    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }

    /**
     * 校验登录平台类型是否合法
     *
     * @param platformType
     */
    private void validPlatfromType(String platformType) {
        if (StringUtils.isEmpty(platformType)) {
            throw new UsernameNotFoundException("登录平台类型参数为空");
        }
        if (!(StringUtils.equals(platformType, SYSTEM_TYPE_KEY)
                || StringUtils.equals(platformType, CUSTOM_TYPE_KEY)
                || StringUtils.equals(platformType, APP_TYPE_KEY))) {
            throw new UsernameNotFoundException("登录平台类型参数非法");
        }
    }

    /**
     * 登录验证
     *
     * @return
     */
    private AbstractAuthenticationToken vaildAuthenticate(Authentication auth) {
        //解析数据
        Map<String, String> obj = (Map<String, String>) auth.getDetails();
        //设置平台登录类型
        String platformType = obj.get("platform_type");
//        String platformType = APP_TYPE_KEY;
        //获取用户名和密码
        String loginName = auth.getPrincipal().toString();
        String inputPassword = auth.getCredentials().toString();
        log.info("用户登录,登录名={}; 密码:{};平台={};", auth.getPrincipal(), inputPassword, platformType);
        //校验登录平台类型是否合法
        // validPlatfromType(platformType);

        //匹配用户数据
        log.info("后台管理用户登录");
        User systemUser = userMapper.selectByLoginName(loginName);
        String ss = PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(inputPassword);
        return matchUserInfo(systemUser, inputPassword);
    }

    /**
     * 匹配用户信息
     *
     * @param user
     * @return
     */
    private AbstractAuthenticationToken matchUserInfo(User user, String inputPassword) {
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        if (!PasswordEncoderFactories.createDelegatingPasswordEncoder().matches(inputPassword, user.getPassword())) {
            throw new UsernameNotFoundException("密码不正确");
        }
        //封装角色
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        UserRole userRole = userRoleMapper.selectByUserId(user.getId());
        Role role = roleMapper.selectByPrimaryKey(userRole.getRoleId());
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleName());
        authorities.add(authority);
        //构造token对象返回
        return new UsernamePasswordAuthenticationToken(user.getLoginName(),
                user.getPassword(),
                authorities);
    }
}
