package cn.coderstory.springboot.security.mapper;

import cn.coderstory.springboot.security.model.UserModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    UserModel findById(int id);
}
