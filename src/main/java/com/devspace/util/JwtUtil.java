/**
 * 
 */
package com.devspace.util;

import java.security.Key;
import java.util.Date;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

/**
 * 
 */
@Component
public class JwtUtil {

	private final String SECRET = "MyVeryLongSecureSecretKeyOfAtLeast32Bytes!!\"!@#$%^&*((*&^%$#TDRYFYKGJULHJY";

	private final long EXPIRATION = 1000 * 60 * 30;

	private Key getSignInKey() {
		return Keys.hmacShaKeyFor(SECRET.getBytes());
	}

	public String generateToken(String username) {
		return Jwts.builder().setSubject(username).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
				.signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
	}

	public String extractUsername(String token) {
		return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody().getSubject();
	}

	public boolean isTokenValid(String token, String username) {
		return extractUsername(token).equals(username) && !isTokenExpired(token);
	}

	public boolean isTokenExpired(String token) {
		final Date expiration = Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token)
				.getBody().getExpiration();
		return expiration.before(new Date());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
