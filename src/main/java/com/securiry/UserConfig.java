package com.securiry;

import com.entity.Consumer;
import com.service.ConsumerService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class UserConfig implements UserDetailsService {

    @Resource
    private ConsumerService consumerService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        // 模拟角色和用户权限
        List<GrantedAuthority> permissions = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN");

        Consumer consumer = consumerService.findByUsername(s);

        if (null == consumer) {
            throw new UsernameNotFoundException("此用户不存在");
        }

        consumer.setPermissions(permissions);

        System.out.println(consumer);
        System.out.println(consumer.getAuthorities());

        return consumer;
    }
}
