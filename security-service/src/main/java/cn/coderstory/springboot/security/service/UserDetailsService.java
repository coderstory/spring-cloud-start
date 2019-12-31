package cn.coderstory.springboot.security.service;

import cn.coderstory.springboot.security.mapper.AuthorityMapper;
import cn.coderstory.springboot.security.mapper.UserMapper;
import cn.coderstory.springboot.security.model.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private UserMapper mapper;
    private AuthorityMapper authorityMapper;

    public UserDetailsService(UserMapper mapper, AuthorityMapper authorityMapper) {
        this.mapper = mapper;
        this.authorityMapper = authorityMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = mapper.findByUserName(username);
        if (user == null) {
            // AbstractUserDetailsAuthenticationProvider.java Line 151
            // 默认会隐藏用户名错误异常 而改成用户名或密码错误异常 可定制一个bean覆盖下配置
            throw new UsernameNotFoundException("指定账户不存在");
        }
        user.setAuthorities(authorityMapper.getAuthoritiesByUserId(user.getId()));
        return user;
    }
}
