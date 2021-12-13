package com.make.planner.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.make.planner.member.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private MemberService memberService;
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
//	@Bean
//	public static PasswordEncoder passwordEncoder() {
//	return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//	}
	
	@Override
    public void configure(WebSecurity web) throws Exception
    {
        // static 디렉터리의 하위 파일 목록은 인증 무시 ( = 항상통과 )
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
    }
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 페이지 권한 설정
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/myinfo").hasRole("MEMBER")
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
            .and()
            	// csrf설정 (https://badstorage.tistory.com/32) post 에러 제거하기위해 / 이유는 스프링시큐리티가 적용되면 모든 POST방식에서는 csrf토큰이 필요했다.
    			.csrf()
    			.ignoringAntMatchers("/login")
            .and() // 로그인 설정
                .formLogin()
                .loginPage("/")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/hello")
                .failureUrl("/?error") 
                .permitAll()
            .and() // 로그아웃 설정
                .logout()
                .permitAll()
//                .logoutUrl("/logout")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JESSIONID", "remember-me")
//                .addLogoutHandler(new LogoutHandler() {
//					@Override
//					public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
//						HttpSession session = request.getSession();
//						session.invalidate();
//					}
//				})
//                .logoutSuccessHandler(new LogoutSuccessHandler() {
//					@Override
//					public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
//							throws IOException, ServletException {
//						response.sendRedirect("/");
//					}
//				})
            .and()
                // 403 예외처리 핸들링
                .exceptionHandling()
                .accessDeniedPage("/denied");
    }
	
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
//    }
    
	// https://gregor77.github.io/2021/05/18/spring-security-03/
	
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authenticationProvider());
//    }
//    
//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        return new CustomAuthenticationProvider(memberService, passwordEncoder);
//    }
//    
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
}
