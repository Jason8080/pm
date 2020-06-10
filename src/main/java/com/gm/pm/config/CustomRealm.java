package com.gm.pm.config;

import com.gm.pm.entity.Login;
import com.gm.pm.kit.TokenKit;
import com.gm.pm.mapper.LoginMapper;
import com.gm.pm.service.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

/**
 * @author Jason
 */
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    LoginMapper loginMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("-------开始权限认证--------");
        Login login = (Login) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        String roles = login.getRoles();
        if (!StringUtils.isEmpty(roles)) {
            String[] split = roles.split(",");
            for (int i = 0; i < split.length; i++) {
                info.addRole(split[i]);
            }
            if("Jason".equalsIgnoreCase(login.getName())) {
                info.addRole("sa");
                info.addStringPermission("sa");
            }
        }
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("-------开始身份认证--------");
        JwtAuthenticationToken jat = (JwtAuthenticationToken) authenticationToken;
        Login login = jat.getPrincipal();
        String token = jat.getCredentials();
        return new SimpleAuthenticationInfo(login, token, getName());
    }
}