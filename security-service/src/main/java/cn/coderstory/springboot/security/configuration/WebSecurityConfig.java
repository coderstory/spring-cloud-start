package cn.coderstory.springboot.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import javax.sql.DataSource;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private DataSource dataSource;

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
                .antMatchers("/app/api").permitAll()
                .antMatchers("/h2-console/**").permitAll()
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
                .permitAll()
                .loginProcessingUrl("/login")
                // 用户登入成功事件处理器 可修改返回内容
                .successHandler(
                        (httpServletRequest, httpServletResponse, authentication) ->
                                httpServletResponse.getWriter().write(" login success"))
                // 用户登入失败事件处理器 可修改返回内容
                .failureHandler((httpServletRequest, httpServletResponse, e) -> {
                    httpServletResponse.setContentType("application/json;charset=UTF-8");
                    httpServletResponse.getWriter().write(e.getClass() + "\r\n" + e.getMessage());
                })
                .and()
                .csrf().disable()
                .headers().frameOptions().disable();
    }

    /**
     * @Bean public UserDetailsService userDetailsService() {
     * // 自带的用户和权限表比较简陋 不适合使用 创建sql直接ctrl+n 搜索 user.ddl
     * JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
     * 基于内存的无法长久存储和也很难维护
     * InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
     * manager.createUser(
     * User.withUsername("admin")
     * .password(passwordEncoder().encode("admin"))
     * .roles("ADMIN", "USER")
     * .build());
     * manager.createUser(
     * User.withUsername("user")
     * .password(passwordEncoder().encode("user"))
     * .roles("USER")
     * .build());
     * return manager;
     * }
     **/

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
