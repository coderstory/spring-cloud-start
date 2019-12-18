package cn.coderstory.springboot.userservice.service;

import cn.coderstory.springboot.userservice.mapper.UserMapper;
import cn.coderstory.springboot.userservice.model.UserModel;
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
