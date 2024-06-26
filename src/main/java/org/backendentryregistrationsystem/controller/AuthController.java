package org.backendentryregistrationsystem.controller;

import org.backendentryregistrationsystem.DTO.AuthResponseDTO;
import org.backendentryregistrationsystem.DTO.LoginDTO;
import org.backendentryregistrationsystem.DTO.RegisterDTO;
import org.backendentryregistrationsystem.model.Role;
import org.backendentryregistrationsystem.model.UserEntity;
import org.backendentryregistrationsystem.repository.RoleRepository;
import org.backendentryregistrationsystem.repository.UserRepository;
import org.backendentryregistrationsystem.security.JWTGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JWTGenerator tokenGenerator;
    private final RoleRepository roleRepository;

    @Autowired
    public AuthController(UserRepository userRepository, AuthenticationManager authenticationManager,
                          PasswordEncoder passwordEncoder, JWTGenerator tokenGenerator, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.tokenGenerator = tokenGenerator;
        this.roleRepository = roleRepository;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDto) {

        if (!userRepository.existsByEmail(loginDto.getEmail())) {
            return new ResponseEntity<>("Error: Email is not found!", HttpStatus.BAD_REQUEST);
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Get the username from the Authentication object
        String email = authentication.getName();

        // Load the UserEntity from the database
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        // Pass the UserEntity to the generateToken method
        String token = tokenGenerator.generateToken(user);

        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody RegisterDTO registerDTO) {
        if (userRepository.existsByEmail(registerDTO.getEmail())) {
            return new ResponseEntity<>("Error: Email is already in use!", HttpStatus.BAD_REQUEST);
        }

        UserEntity user = new UserEntity();
        user.setEmail(registerDTO.getEmail());
        user.setUsername(registerDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setPhoneNumber(registerDTO.getPhoneNumber());

        Role roles = roleRepository.findByName("USER");
        user.setRoles(Collections.singletonList(roles));

        userRepository.save(user);

        return new ResponseEntity<>("User registered successfully!", HttpStatus.OK);
    }
}
