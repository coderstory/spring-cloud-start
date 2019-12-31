package cn.coderstory.springboot.security.model;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {
    private String username;
    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }
}
