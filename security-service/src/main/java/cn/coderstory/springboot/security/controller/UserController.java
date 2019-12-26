package cn.coderstory.springboot.security.controller;

import cn.coderstory.springboot.security.model.UserModel;
import cn.coderstory.springboot.security.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public UserModel findById(@PathVariable int id) {
        return service.findById(id);
    }
}
