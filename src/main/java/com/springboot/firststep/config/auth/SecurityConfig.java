package com.springboot.firststep.config.auth;

import com.springboot.firststep.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity          // 스프링 시큐리티의 설정을 활성화 시켜줌
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().headers().frameOptions().disable()
                .and()
                    //URL별 권한 관리를 설정한다.
                    .authorizeRequests()
                    //아래 URL들은 모든 권한을 가진다
                    .antMatchers("/","/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                    //  /api/v1/ 의 URL은 USER 권한을 가진 사람만 가능하다
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                    // 설정된 값 외의 나머지 URL을 나타낸다. 마지막 메소드를 추가하여 나머지 URL들은 모두 로그인한 사용자들에게 허용된다.
                    .anyRequest().authenticated()
                .and()
                    // 로그아웃 성공시 / 주소로 이동
                    .logout().logoutSuccessUrl("/")
                .and()
                    // 로그인 성공 후 진행할 UserService 인터페이스의 구현체를 등록한다.
                    .oauth2Login().userInfoEndpoint().userService(customOAuth2UserService);
    }


}
