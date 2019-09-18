package com.resc.springsecuritydem0102.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public UserDetailsManager userDetailsManager() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        UserDetails[] users = {
                User.withUsername("john")
                    .password("12345")
                    .authorities("ADMIN")
                .build(),
                User.withUsername("bill")
                        .password("12345")
                        .authorities("USER")
                        .build()

        };
        for(UserDetails u : users) {
            manager.createUser(u);
        }
        return manager;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin() // how do I authenticate ? - Using a form;
                .defaultSuccessUrl("/hello", true);
//        http.authorizeRequests().anyRequest().authenticated();
        http.authorizeRequests()
                .mvcMatchers("/admin").access("hasAnyAuthority('ADMIN')")
                .mvcMatchers("/main").permitAll()
                .anyRequest().authenticated();
    }
}
