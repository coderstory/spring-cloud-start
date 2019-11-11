package cn.coderstory.springboot.start.controller;

import cn.coderstory.springboot.start.configure.TestConfigBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/home")
@EnableConfigurationProperties()
public class HomeController {
    TestConfigBean bean;
    RestTemplate template;
    LoadBalancerClient loadBalancerClient;

    public HomeController(TestConfigBean bean, RestTemplate template, LoadBalancerClient loadBalancerClient) {
        this.bean = bean;
        this.template = template;
        this.loadBalancerClient = loadBalancerClient;
    }

    @ApiOperation("hello")
    @GetMapping
    String index() {
        return bean.getName();
    }

    /**
     * 通过ribbon实现负载均衡 RestTemplate通过服务名调用服务
     */
    @GetMapping("/access")
    String accessService() {
        ServiceInstance instance = loadBalancerClient.choose("USER-SERVICE");
        return instance.getHost() + instance.getPort() + template.getForObject("http://USER-SERVICE/", String.class);
    }

}
