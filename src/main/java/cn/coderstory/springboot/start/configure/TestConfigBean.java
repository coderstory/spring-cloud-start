package cn.coderstory.springboot.start.configure;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "test")
@PropertySource("classpath:test.yml")
public class TestConfigBean {
    @Value("${name}")
    private String name;
}
