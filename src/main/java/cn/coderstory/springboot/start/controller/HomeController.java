package cn.coderstory.springboot.start.controller;

import cn.coderstory.springboot.start.configure.TestConfigBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableConfigurationProperties()
public class HomeController {
    TestConfigBean bean;

    public HomeController(TestConfigBean bean) {
        this.bean = bean;
    }

    @GetMapping
    String index() {
        return bean.getName();
    }
}
