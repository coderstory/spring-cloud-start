package cn.coderstory.springboot.userservice.mapper;

import cn.coderstory.springboot.userservice.model.UserModel;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    UserModel findById(int id);
}
