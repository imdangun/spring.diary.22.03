package spring.diary.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	@Autowired private TokenProvider tokenProvider;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			log.info("인증 시작.");
			String token = parseToken(request);			
			if(token != null) {
				String userId = tokenProvider.getSubject(token);				
				AbstractAuthenticationToken  authentication 
					= new UsernamePasswordAuthenticationToken(
							userId, null, AuthorityUtils.NO_AUTHORITIES);
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
				securityContext.setAuthentication(authentication);
				SecurityContextHolder.setContext(securityContext);				
			}
			log.info("인증 성공.");
		} catch(Exception e) {
			logger.error("인증 실패.", e);
		}
		filterChain.doFilter(request, response);
	}
	
	private String parseToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		String result = null;
		if(StringUtils.hasText(token) && token.startsWith("Bearer "))
			result = token.substring(7);
		
		return result;
	}
}
