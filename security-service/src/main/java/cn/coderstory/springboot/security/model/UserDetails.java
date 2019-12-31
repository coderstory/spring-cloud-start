package cn.coderstory.springboot.security.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Data
@Service
public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {
    Collection<? extends GrantedAuthority> authorities;
    private String id;
    private String password;
    private String username;
    private boolean enable;
    private String name;
    private int age;
    private String email;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }
}
