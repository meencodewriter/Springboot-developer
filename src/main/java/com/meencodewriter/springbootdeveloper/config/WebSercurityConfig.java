package com.meencodewriter.springbootdeveloper.config;

import com.meencodewriter.springbootdeveloper.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@RequiredArgsConstructor
@Configuration
public class WebSercurityConfig {
    private final UserDetailService userDetailService;


    // 스프링 시큐리티의 인증절차를 미적용 하는 설정
    // 정적리소스 등을 조회할 때는 굳이 인증, 인가를 거칠 필요가 없음
    // requestMatchers : 매개변수와 일치하는 url 에 대한 요청을 처리
    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring()
                .requestMatchers(toH2Console())
                .requestMatchers("/static/**");
    }

    // 특정 Http Request 에 대한 웹 기반 보안 설정
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
//                .authorizeRequests() // 인증, 인가 설정
//                    .requestMatchers("login", "signup", "signin").permitAll() // 로그인, 회원가입, 로그아웃 페이지는 인증 없이 접근 가능
//                    .anyRequest().authenticated() // 그 외의 페이지는 인증 필요
//                    .and()
//                .formLogin() // 로그인 페이지 설정
//                    .loginPage("/login") // 로그인 페이지 경로
//                    .defaultSuccessUrl("/articles") // 로그인 성공 후 이동할 페이지
//                    .and()
//                .logout() // 로그아웃 설정
//                    .logoutSuccessUrl("/login") // 로그아웃 성공 후 이동할 페이지
//                    .invalidateHttpSession(true) // 세션 무효화
//                    .and()
//                .csrf().disable() // csrf 보안 설정 미적용
//                .build();
//        이게 예제인데 nonLambda 식으로 빌드하는걸 deprecated 해버림 ( Spring security 6.1 부터인가 )
//        그래서 이후로는 아래처럼 쓰면 됨
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry
                                .requestMatchers("/login", "/signup", "/user").permitAll()
                                .anyRequest().authenticated()

                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/login")
                                .defaultSuccessUrl("/articles")
                )
                .logout(logout ->
                        logout
                                .logoutSuccessUrl("/login")
                                .invalidateHttpSession(true)
                )
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() throws Exception {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());

        return daoAuthenticationProvider;
    }

    // 패스워드 인코더로 사용할 Bean 등록
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
