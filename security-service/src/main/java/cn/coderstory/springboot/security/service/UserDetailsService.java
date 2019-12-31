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
        user.setAuthorities(authorityMapper.getAuthoritiesByUserId(user.getId()));
        return user;
    }
}
