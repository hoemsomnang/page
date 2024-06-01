package com.page.page.security;


import com.page.page.service.CustomUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/login","/user-access","/css/**","/dist/**","/js/**","/plugins/**").permitAll()
                        // View
                        .requestMatchers("/view/user/**").hasRole("ADMIN")
                        .requestMatchers("/view/main-owner/**").hasRole("ADMIN")
                        .requestMatchers("/view/office/**").hasRole("ADMIN")
                        .requestMatchers("/view/page/**").hasAnyRole("ADMIN","USER")
                        // API
                        .requestMatchers("/api/v1/page-list/**").hasAnyRole("ADMIN","USER")
                        .requestMatchers("/api/v1/main-owner/**").hasRole("ADMIN")
                        .requestMatchers("/api/v1/Office/**").hasRole("ADMIN")
                        .requestMatchers("/api/v1/page-owner/**").hasRole("ADMIN")
                        .requestMatchers("/api/v1/user/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
               ).formLogin(
                        form -> form
                                .loginPage("/login")
                                .defaultSuccessUrl("/view/page/page-list",true)
                                .permitAll()
                ).httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailService();
    }

   /* @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }*/

    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService());
        return provider;
    }
}
