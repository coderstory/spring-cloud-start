package cn.coderstory.springboot.start.feign;

import cn.coderstory.springboot.start.configure.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "user-service", configuration = FeignConfig.class)
public interface UserApi {
    @GetMapping("/")
    String test();
}
