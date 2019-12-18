package cn.coderstory.springboot.userservice.model;

import lombok.Data;

@Data
public class UserModel {
    private String id;
    private String name;
    private int age;
    private String email;
}
