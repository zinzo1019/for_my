package com.example.HelloSpringboot.config;

import com.example.HelloSpringboot.config.login.handler.OAuth2AuthenticationFailureHandler;
import com.example.HelloSpringboot.config.login.handler.OAuth2AuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터 체인에 등록됨
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/public/**", "/api/**").permitAll()
                                .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .anyRequest().permitAll())

                .formLogin((form) -> form
                        .defaultSuccessUrl("/public", true)
                        .loginPage("/login")
                        .loginProcessingUrl("/login") // login 주소가 호출되면 시큐리티가 낚아채서 대신 로그인 진행
                        .successHandler(successHandler())
                        .failureHandler(failureHandler())
                        .defaultSuccessUrl("/user")
                        .permitAll())

                .logout((logout) -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true))

                /** oauth2 login */
                .oauth2Login((form) -> form
                        .loginPage("/login")
                        .successHandler(successHandler())
                        .failureHandler(failureHandler())
                        .defaultSuccessUrl("/user")
                        .permitAll())

                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/**"));

        return http.build();
    }

    /**
     * 로그인 성공 핸들러 주입
     */
    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new OAuth2AuthenticationSuccessHandler();
    }

    /**
     * 로그인 실패 핸들러 주입
     */
    @Bean
    public OAuth2AuthenticationFailureHandler failureHandler() {
        return new OAuth2AuthenticationFailureHandler();
    }

    /**
     * 비밀번호 암호화
     */
    @Bean // 해당 메서드의 리턴되는 오브젝트를 IoC로 등록 -> 어디서든 사용 가능
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/images/**", "/js/**", "/webjars/**");
    }
}
