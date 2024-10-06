package com.pipespace.pipespace.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pipespace.pipespace.authentication.User;
import com.pipespace.pipespace.enums.UserRole;
import com.pipespace.pipespace.repositories.UserRepository;
import com.pipespace.pipespace.rocords.LoginResponseRecord;
import com.pipespace.pipespace.rocords.UserLoginRecord;
import com.pipespace.pipespace.rocords.UserRegisterRecord;
import com.pipespace.pipespace.services.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "auth")
public class AuthenticatorController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private TokenService tokenService;

	@PostMapping("login")
	public ResponseEntity<?> login(@RequestBody @Valid UserLoginRecord userLoginR){
		
		UsernamePasswordAuthenticationToken userNamePassword = new UsernamePasswordAuthenticationToken(userLoginR.usuario(), userLoginR.password());
		
		Authentication auth = authManager.authenticate(userNamePassword);
		
		String token = tokenService.generateToken((User) auth.getPrincipal());
		
		return ResponseEntity.ok(new LoginResponseRecord(token));
	}
	
	@PostMapping("register")
	public ResponseEntity<?> register(@RequestBody @Valid UserRegisterRecord userRegisterR) {
		
		UserDetails u = userRepo.findUserByUsuario(userRegisterR.usuario());
		
		if(u != null) {
			return ResponseEntity.badRequest().build();
		}
		
		String encriptPassword = new BCryptPasswordEncoder().encode(userRegisterR.password());
		
		
		User newUser = new User();
		
		newUser.setUsuario(userRegisterR.usuario());
		newUser.setPassword(encriptPassword);
		newUser.setRole(UserRole.valueOf(userRegisterR.role()));
		
		userRepo.save(newUser);
		
		return ResponseEntity.accepted().build();
	}
}
