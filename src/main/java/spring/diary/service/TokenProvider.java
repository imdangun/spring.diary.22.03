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
		String token = Jwts.builder()
				.signWith(key)
				.setSubject(subject)
				.setIssuer("diary")
				.setIssuedAt(new Date())
				.setExpiration(expiryDate)
				.compact();
		/* 토큰은 header.payload.signature 로 구성된다. 아래는 token 을 decoding 한 예이다.
			{ // header
			  "typ": "JWT"              : type
			  "alg":"HS512"             : algorithm
			}.
			{ // payload
			  "sub":"1",                : subject
			  "iss": "diary",           : issuer
			  "iat":1595733657,         : issued at
			  "exp":1596597657          : expiration
			}.			
			Nn4d1MOVLZg79sfFACTIpCPKqWmpZMZQsbNrXdJJNWkRv50_l7bPLQPwhMobT4vBOG6Q3JYjhDrKFlBSaUxZOg : signature
		*/
		
		return token;
	}
	
	// user_id 를 subject 으로 사용한다.
	public String validateToken(String token) {
		Claims claims = Jwts.parser()
				.setSigningKey(key)
				.parseClaimsJws(token)
				.getBody();
		
		return claims.getSubject();
	}
}
