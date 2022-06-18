package com.yeol.ocle.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration  //메모리에 띄움
@EnableWebSecurity  //활성화 : Spring Security Filer Chain 에 등록
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)   //@Sercured 활성화, @
public class WebSecuriyConfig extends WebSecurityConfigurerAdapter {

    @Bean   //해당 메소드의 리턴되는 오브젝트를 IoC로 등록해줌
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/consumer/**").authenticated()
                .antMatchers("/supplier/**").access("hasRole('ROLE_SUPPLIER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                //TODO api 테스트 위해 임시 주석
                //.antMatchers("/api/admin/**").access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/main/login/login")
                //.usernameParameter("username")    //아이디를 username 외에 다른 파라미터명으로 사용할 경우 파라미터명을 설정
                //.passwordParameter("password")    //비밀번호 password 외에 다른 파라미터명으로 사용할 경우 파라미터명을 설정
                .loginProcessingUrl("/main/login/loginPrcs")    //해당 url이 호출되면 Spring Security가 낚아채서 대신 로그인을 해줌
                .defaultSuccessUrl("/");
    }
}
