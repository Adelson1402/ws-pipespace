package com.pipespace.pipespace.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.pipespace.pipespace.authentication.User;

@Service
public class TokenService {
	
	@Value("${api.security.token.secret}")
	private String secret;
	
	public String generateToken(User user) {
		
		try {
			Algorithm alg = Algorithm.HMAC256(secret);
			
			String token = JWT.create()
					.withIssuer("AUTH-API")
					.withSubject(user.getUsername())
					.withExpiresAt(getExpirationDate())
					.sign(alg);
			
			return token;
					
		} catch (JWTCreationException e) {
			throw new RuntimeException("Erro ao criar TOKEN", e);
		}
	}
	
	
	
	public String validateToken(String token) {
		
		try {
			
			Algorithm alg = Algorithm.HMAC256(secret);
			
			return JWT.require(alg)
					.withIssuer("AUTH-API")
					.build()
					.verify(token)
					.getSubject();
			
			
		} catch (JWTVerificationException e) {
			return "";
		}
	}
	
	private Instant getExpirationDate() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}
	
}
