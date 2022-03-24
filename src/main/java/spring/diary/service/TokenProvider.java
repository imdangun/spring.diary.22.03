package spring.diary.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class TokenProvider{	
	private final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
	
	public String createToken(String subject) {
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
				.signWith(key)
				.setSubject(subject)
				.setIssuer("diary")
				.setIssuedAt(new Date())
				.setExpiration(expiryDate)
				.compact();
	}
	
	public String validateToken(String token) {
		Claims claims = Jwts.parser()
				.setSigningKey(key)
				.parseClaimsJws(token)
				.getBody();
		
		return claims.getSubject();
	}
}
