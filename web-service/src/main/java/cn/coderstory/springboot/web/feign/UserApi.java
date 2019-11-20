package cn.coderstory.springboot.web.feign;

import cn.coderstory.springboot.web.configure.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "user-service", configuration = FeignConfig.class)
public interface UserApi {
    @GetMapping("/test")
    String test();
}
