package spring.diary.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.filter.CorsFilter;

import spring.diary.security.JwtAuthenticationFilter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter  {
	@Autowired private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Override
	protected void configure(HttpSecurity security) throws Exception {
		security.cors().and()  // WebMvcConfig에서 설정했으므로, 기본값을 사용한다.
			.csrf().disable()
			.httpBasic().disable() // token을 사용하므로, basic 이 아니다.
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //세션 기반이 아니다.
			.and().authorizeRequests().antMatchers("/", "/user/**").permitAll() // /와 /user 는 인증 안한다.
			.anyRequest().authenticated(); // /와 /user 외에는 인증해야 한다.
		
		// request마다 CorsFilter, jwtAuthenticationFilter 순으로 실행한다.
		security.addFilterAfter(jwtAuthenticationFilter, CorsFilter.class);	
	}
}
