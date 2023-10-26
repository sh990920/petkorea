package com.project.petkorea.config;

import com.project.petkorea.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    LoginService loginService;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/", "/loginPage/**", "/signUpPage/**").permitAll()
                .anyRequest().hasRole("USER");

        http.exceptionHandling()
                .accessDeniedPage("/");

        http.formLogin()
                .loginPage("/loginPage/")
                .usernameParameter("id")
                .passwordParameter("password")
                .loginProcessingUrl("/loginPage/login/")
                .defaultSuccessUrl("/loginPage/login/loginSuccess/")
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                        System.out.println("exception : " + exception.getMessage());
                        String errorMessage = null;
                        if(exception instanceof BadCredentialsException){
                            errorMessage = "아이디 또는 비밀번호가 잘못 입력 되었습니다.";
                        }else{
                            errorMessage = "알 수 없는 이유로 로그인에 실패하였습니다.";
                        }
                        response.sendRedirect("/loginPage/?error=true&errorMessage=" + URLEncoder.encode(errorMessage, "UTF-8"));
                    }
                })
                .permitAll();

        http.logout()
                .logoutUrl("/logout/")
                .logoutSuccessUrl("/");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/favicon.ico", "/resources/**", "/error", "/css/**", "/js/**", "/image/**", "/fonts/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(loginService).passwordEncoder(passwordEncoder());
    }

}
