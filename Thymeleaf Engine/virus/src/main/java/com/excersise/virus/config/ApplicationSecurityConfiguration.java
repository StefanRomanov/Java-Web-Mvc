package com.excersise.virus.config;

import com.excersise.virus.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final UserService userService;

    @Autowired
    public ApplicationSecurityConfiguration(UserService userService) {
        this.userService = userService;
    }

    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();

        repository.setSessionAttributeName("_csrf");

        return repository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers("/css/**", "/js/**").permitAll()
                .antMatchers("/users/all").hasAuthority("ADMIN")
                .antMatchers("/viruses/edit", "/viruses/delete", "/viruses/add").hasAnyAuthority("MODERATOR", "ADMIN")
                .antMatchers("/viruses", "/home", "/logout").hasAnyAuthority("USER", "MODERATOR", "ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/users/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
                .and()
                .rememberMe()
                .rememberMeParameter("rememberMe")
                .key("PLYOK")
                .userDetailsService(this.userService)
                .rememberMeCookieName("KLYOK")
                .tokenValiditySeconds(1200)
                .and()
                .exceptionHandling()
                .accessDeniedPage("/unauthorized")
        ;
    }
}
