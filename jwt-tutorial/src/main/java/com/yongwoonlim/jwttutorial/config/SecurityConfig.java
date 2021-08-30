package com.yongwoonlim.jwttutorial.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/h2-console/**", "/favicon.ico");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests() // HttpServletRequest를 사용하는 요청들에 대한 접근제한 설정
                .antMatchers("/api/hello").permitAll() // /api/hello에 대한 요청은 인증없이 접근 허용
                .anyRequest().authenticated(); // 나머지는 인증을 통해 접근
    }
}
