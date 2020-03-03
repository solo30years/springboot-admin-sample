package com.solo.admin.sample.client.config;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Security配置类，用于配置admin client的安全控制
 * @author Ivan
 */
@Configuration
public class SecuritySecureConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //拦截所有endpoint，拥有ACTUATOR_ADMIN角色可访问，否则需登录
                .requestMatchers(EndpointRequest.toAnyEndpoint()).hasRole("ACTUATOR_ADMIN")
                //静态文件允许访问
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                //根路径允许访问
                .antMatchers("/").permitAll()
                //所有请求路径可以访问
                .antMatchers("/**").permitAll()
                .and().httpBasic();
    }
}