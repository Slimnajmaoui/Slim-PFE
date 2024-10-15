package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.LoginRequest;
import com.example.demo.model.LoginResponse;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRopository;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenUtils;

import javax.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    UserRepository userrepos ;

    @Autowired
    RoleRopository roleRepository;

    // pour crypter le password (NB: il faut ajouter le bean BCryptPasswordEncoder dans l'application)
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenUtils jwtTokenUtils;

    
    
    
    
    
    
    
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login2(@Valid @RequestBody LoginRequest loginRequest)
    {
		 
    	
    	
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
      
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = this.jwtTokenUtils.generateToken(userDetails);
    
		User contact = this.userrepos.findByEmail(loginRequest.getEmail());
		
		return ResponseEntity.ok(new LoginResponse(token, "Bearer", "Login successfully",contact.getRole().getProfil(),loginRequest.getEmail(),contact.getId()));
  }
    
}
