package com.victor.raphael.estudaebackend.controllers;

import com.victor.raphael.estudaebackend.models.user.AuthenticationDTO;
import com.victor.raphael.estudaebackend.models.user.RegisterDTO;
import com.victor.raphael.estudaebackend.models.user.User;
import com.victor.raphael.estudaebackend.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO dto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.username(), dto.password());

        var auth = this.authenticationManager.authenticate(usernamePassword);

        return  ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO dto) {
        if(this.userRepository.findByUsername(dto.username()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());

        User newUser = new User(dto.username(), encryptedPassword, dto.email(), dto.role());

        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
