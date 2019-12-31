package cn.coderstory.springboot.security.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@Mapper
public interface AuthorityMapper {
    Collection<? extends GrantedAuthority> getAuthoritiesByUserId(String username);
}
