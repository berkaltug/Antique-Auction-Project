package com.scopic.antiqueauction.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyRestAuthenticationEntryPoint authEntryPoint;
    @Autowired
    private UserDetailsService customUserDetailsService;
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().and()
                .authorizeRequests()
                .antMatchers("/antique/list", "/antique/see/**","/antique/login").hasAnyRole("REGULAR","ADMIN")
                .antMatchers("/antique/add","/antique/delete/**","/antique/admin/login").hasRole("ADMIN")
                .antMatchers("/h2-console/**","/user/**").permitAll()
                .antMatchers("/static/**","/static/images/**","/resources/static/images/**","/resources/**","/images/**","/media/**").permitAll()
                .anyRequest().authenticated()
                .and().headers().frameOptions().disable()//neccessary for h2-console
                .and()
                .httpBasic()
                .authenticationEntryPoint(authEntryPoint)
                ;
    }
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}
