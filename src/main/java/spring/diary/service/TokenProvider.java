package spring.diary.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import spring.diary.domain.User;

@Service
public class TokenProvider {
	private static final String SECRET_KEY = "NMA8JPctFuna59f5";
	
	public String createToken(User user) {
		// 만료기한 = 현재시각 + 1일
		Date expiryDate = Date.from(Instant.now().plus(1, ChronoUnit.DAYS));
		
		/*
			{ // header
			  "alg":"HS512"
			}.
			{ // payload
			  "sub":"40288093784915d201784916a40c0001",
			  "iss": "diary",
			  "iat":1595733657,
			  "exp":1596597657
			}.
			// SECRET_KEY를 이용해 서명한 부분
			Nn4d1MOVLZg79sfFACTIpCPKqWmpZMZQsbNrXdJJNWkRv50_l7bPLQPwhMobT4vBOG6Q3JYjhDrKFlBSaUxZOg
		 */		
		return Jwts.builder()
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY)
				.setSubject(user.getUserId())
				.setIssuer("diary")
				.setIssuedAt(new Date())
				.setExpiration(expiryDate)
				.compact();
	}
	
	public int validate(String token) {
		Claims claims = Jwts.parser()
				.setSigningKey(SECRET_KEY)
				.parseClaimsJws(token)
				.getBody();
		
		return Integer.parseInt(claims.getSubject());
	}
}
