package anam.pkg.duniyaar.security;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.*;
import org.springframework.security.web.SecurityFilterChain;

import anam.pkg.duniyaar.security.services.CUserDetailsService;
 
@Configuration
@Order(2)
public class UserSecurityConfig {
 
    @Bean
    public UserDetailsService customerUserDetailsService() {
        return new CUserDetailsService();
    }
 
    @Primary
	@Bean
	@Qualifier(value = "user")
    public PasswordEncoder passwordEncoder2() {
        return new BCryptPasswordEncoder();
    }
    
    @Primary
    @Qualifier(value = "user")
    @Bean
    public DaoAuthenticationProvider authenticationProvider2() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customerUserDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder2());
 
        return authProvider;
    }
 
    @Bean
    public SecurityFilterChain filterChain2(HttpSecurity http) throws Exception {
        http.authenticationProvider(authenticationProvider2());
 
        http.antMatcher("/user/**")
            .authorizeRequests().anyRequest().authenticated()
            .and()
            .formLogin()
                .loginPage("/user/login")
                .usernameParameter("email")
                .loginProcessingUrl("/user/login")
                .defaultSuccessUrl("/user/home")
                .permitAll()
            .and()
                .logout()
                    .logoutUrl("/user/logout")
                    .logoutSuccessUrl("/");
 
        return http.build();
    }
}