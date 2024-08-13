package com.zosh.service;

import com.zosh.modal.User;
import com.zosh.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch the user from the database using the provided email (username)
        User user = userRepository.findByEmail(username);

        // If the user is not found, throw an exception
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + username);
        }

        // Check if the email or password is null or empty and throw an exception if true
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException("User email cannot be null or empty");
        }

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("User password cannot be null or empty");
        }

        // Print email and password for debugging (remove or replace with proper logging in production)
        System.out.println("User email: " + user.getEmail());
        System.out.println("User password: " + user.getPassword());

        // Create an empty list of authorities (roles)
        List<GrantedAuthority> authorityList = new ArrayList<>();

        // Return a UserDetails object constructed with the user's email, password, and authorities
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                authorityList
        );
    }
}
