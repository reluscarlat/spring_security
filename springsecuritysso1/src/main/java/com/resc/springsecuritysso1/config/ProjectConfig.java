package com.resc.springsecuritysso1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        ClientRegistration c = getClientRegistration();
        return new InMemoryClientRegistrationRepository(c);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.oauth2Login();
        http.authorizeRequests()
                .anyRequest().authenticated();
    }

    private ClientRegistration getClientRegistration() {
        return CommonOAuth2Provider.GITHUB.getBuilder("github")
                .clientId("c39669e2f0c332f67adb")
                .clientSecret("c3d89e02dc703cfcda588708b17116aca02da7c3")
                .build();
    }
}
