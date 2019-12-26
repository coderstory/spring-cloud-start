package cn.coderstory.springboot.security.service;

import cn.coderstory.springboot.security.mapper.UserMapper;
import cn.coderstory.springboot.security.model.UserModel;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserMapper mapper;

    public UserService(UserMapper mapper) {
        this.mapper = mapper;
    }

    public UserModel findById(int id) {
        return mapper.findById(id);
    }
}
