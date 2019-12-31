package cn.coderstory.springboot.security.mapper;

import cn.coderstory.springboot.security.model.UserDetails;
import cn.coderstory.springboot.security.model.UserModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    UserModel findById(int id);

    UserDetails findByUserName(String username);
}
