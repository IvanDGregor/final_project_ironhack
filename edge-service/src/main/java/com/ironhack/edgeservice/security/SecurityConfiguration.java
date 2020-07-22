package com.ironhack.edgeservice.security;

import com.ironhack.edgeservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.savedrequest.NullRequestCache;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        /*
        httpSecurity.httpBasic();
        httpSecurity.authorizeRequests().anyRequest().permitAll();
        httpSecurity.csrf().disable();
        httpSecurity.formLogin()
                .permitAll()
                .loginPage("/login");
        httpSecurity.logout()
                .permitAll()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/logout/success")
                .deleteCookies("auth_code", "JSESSIONID")
                .clearAuthentication(true)
                .invalidateHttpSession(true);
         */

        httpSecurity.httpBasic();

        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                // --- USER CONTOLLER SECURITY
                .mvcMatchers("/user/*").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.POST,"/user/*").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.PUT,"/user/*").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.PATCH,"/user/*").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.DELETE,"/user/*").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.GET,"/users").hasAuthority("ROLE_ADMIN")
                // --- ACCOUNT CONTROLLER SECURITY ---
                .mvcMatchers("/account/*").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.POST,"/account/*").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.PUT,"/account/*").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.PATCH,"/account/*").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.DELETE,"/account/*").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.GET,"/accounts").hasAuthority("ROLE_ADMIN")
                // endregion
                // --- CREDIT CARD CONTROLLER SECURITY ---
                // region
                .mvcMatchers("/credit-card/*").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.POST,"/credit-card/*").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.PUT,"/credit-card/*").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.PATCH,"/credit-card/*").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.DELETE,"/credit-card/*").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.GET,"/credit-cards").hasAuthority("ROLE_ADMIN")
                // endregion
                // --- TRANSACTION CONTROLLER SECURITY ---
                // region
                .mvcMatchers("/transaction/*").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.POST,"/transaction/*").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.PUT,"/transaction/*").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.PATCH,"/transaction/*").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.DELETE,"/transaction/*").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.GET,"/transactions").hasAuthority("ROLE_ADMIN")
                // endregion

                .and().requestCache().requestCache(new NullRequestCache()).and().httpBasic().and().cors().and().csrf().disable();


    }

}
