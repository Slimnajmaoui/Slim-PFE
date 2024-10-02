package com.example.demo.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.model.role;
import com.example.demo.repository.RoleRopository;
import com.example.demo.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (email.trim().isEmpty()) {
            throw new UsernameNotFoundException("username is empty");
        }

        User user = userService.findByEmail(email);
    	System.out.println(user+"//////////");

        if (user == null) {
            throw new UsernameNotFoundException("User with email = " + email + " not found");
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getMdp(), getGrantedAuthorities(user));
    }
@Autowired
RoleRopository authrepos ;
    private List<GrantedAuthority> getGrantedAuthorities(User user) {
    	 List<GrantedAuthority> authorities = new ArrayList<>(); 
    	 role auth = this.authrepos.findByProfil(user.getRole().getProfil());
    	     authorities.add(new SimpleGrantedAuthority(auth.getProfil())); 
    	     return authorities; }
}
