package com.pipespace.pipespace.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.pipespace.pipespace.repositories.UserRepository;
import com.pipespace.pipespace.services.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UserRepository userRepo;
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = getToken(request);
		
		if(token != null) {
			
			String subject = tokenService.validateToken(token);
			
			UserDetails user = userRepo.findUserByUsuario(subject);
			
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
	
			SecurityContextHolder.getContext().setAuthentication(authToken);
			
			
		}
		
		filterChain.doFilter(request, response);
	}

	private String getToken(HttpServletRequest request) {
		
		String authHeader = request.getHeader("Authorization");
		
		if(authHeader == null) {
			return null;
		}
		
		return authHeader.replace("Bearer ", "");
		
	}
	
}
