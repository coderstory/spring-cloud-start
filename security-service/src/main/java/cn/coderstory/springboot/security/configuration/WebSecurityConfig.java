package cn.coderstory.springboot.security.configuration;

import cn.coderstory.springboot.security.authentication.VerificationCodeFilter;
import cn.coderstory.springboot.security.service.UserDetailsService;
import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import javax.annotation.Resource;
import java.util.Properties;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Resource
    private AuthenticationFailureHandler authenticationFailureHandler;
    @Resource
    private VerificationCodeFilter verificationCodeFilter;
    @Resource
    private AuthenticationProvider authenticationProvider;
    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private SessionRegistry sessionRegistry;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    @Bean
    public SessionRegistry getSessionRegistry() {
        return new SessionRegistryImpl();
    }


    /**
     * based authentication
     * 基于Form提交用户信息进行认证的登入方式
     * 默认账户配置路径 -> spring.security.user.name & spring.security.user.password
     * 登入成功后会刷新一个JSESSIONID
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 获取一个url拦截器
                .authorizeRequests()
                .antMatchers("/admin/api/**").hasRole("ADMIN")
                .antMatchers("/user/api/**").hasRole("USER")
                .antMatchers("/app/api/**").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/js/**.js", "/css/**.css", "/favicon.ico").permitAll()
                // url 匹配 所有请求
                .anyRequest()
                // 需要授权才能访问
                .authenticated()
                // 返回当前builder
                .and()
                // 开启表单基础认证
                .formLogin()
                // 登入页面url 默认为“/login”
                .loginPage("/login.html")
                .defaultSuccessUrl("/")
                .loginProcessingUrl("/login")
                .permitAll()
                // 用户登入成功事件处理器 可修改返回内容
                .successHandler(authenticationSuccessHandler)
                // 用户登入失败事件处理器 可修改返回内容
                .failureHandler(authenticationFailureHandler)
                .and()
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .rememberMe().userDetailsService(userDetailsService)
                .key("wqd2qw!23513dd(*&^")
                .and()
                // 限制每个用户只能存在一个session
                .sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(true).sessionRegistry(sessionRegistry);

        // 将过滤器添加在UsernamePasswordAuthenticationFilter之前
        // http.addFilterBefore(verificationCodeFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }


    @Bean
    public Producer captcha() {
        Properties properties = new Properties();
        properties.setProperty("kaptcha.image.width", "150");
        properties.setProperty("kaptcha.image.height", "40");
        properties.setProperty("kaptcha.textproducer.char.string", "1234567890");
        properties.setProperty("kaptcha.textproducer.char.length", "6");
        properties.setProperty("kaptcha.textproducer.font.size", "40");
        properties.setProperty("kaptcha.session.key", "code");
        properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
        properties.setProperty("kaptcha.border", "yes");
        Config config = new Config(properties);
        DefaultKaptcha kaptcha = new DefaultKaptcha();
        kaptcha.setConfig(config);
        return kaptcha;
    }

}
