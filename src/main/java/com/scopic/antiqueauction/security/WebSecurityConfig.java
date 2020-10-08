package com.scopic.antiqueauction.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().and()
                .authorizeRequests()
                .antMatchers("/antique/list", "/antique/see/**","/antique/login").hasAnyRole("REGULAR","ADMIN")
                .antMatchers("/antique/add","/antique/delete/**","/antique/admin/login").hasRole("ADMIN")
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/webjars/**","/static/**").permitAll()
                .anyRequest().authenticated()
                .and().headers().frameOptions().disable()//neccessary for h2-console
                .and()
                .httpBasic()
                ;
    }
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("{noop}admin")
                .roles("ADMIN")
                .and()
                .withUser("user")
                .password("{noop}user")
                .roles("REGULAR");
    }
}
