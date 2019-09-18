package com.resc.springsecuritydemo101.config;

import com.resc.springsecuritydemo101.Security.AppUserDetailsManager;
import com.resc.springsecuritydemo101.Security.PlainTextPasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import java.util.Arrays;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {

//        return new PlainTextPasswordEncoder();

        return NoOpPasswordEncoder.getInstance();

    }

    @Bean
    public UserDetailsManager userDetailsManager() {

//        AppUserDetailsManager appUserDetailsManager = new AppUserDetailsManager();
//        appUserDetailsManager.createUser(new User("john","1234"));
//        return appUserDetailsManager;

        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        UserDetails userDetails =
                User.withUsername("john")
                    .password("1234")
                    .authorities(Arrays.asList(new GrantedAuthority[]{()->"ADMIN", ()->"USER"}))
                .build();
        inMemoryUserDetailsManager.createUser(userDetails);
        return inMemoryUserDetailsManager;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.authorizeRequests().anyRequest().authenticated();
    }
}
