package com.personalprojects.gestorb2b.controllers;

import com.personalprojects.gestorb2b.domain.dtos.AuthDTO;
import com.personalprojects.gestorb2b.domain.dtos.LoginResponseDTO;
import com.personalprojects.gestorb2b.domain.dtos.RegisterDTO;
import com.personalprojects.gestorb2b.domain.entities.User;
import com.personalprojects.gestorb2b.infra.security.TokenService;
import com.personalprojects.gestorb2b.repositories.UserRepository;
import com.personalprojects.gestorb2b.services.AuthorizationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AuthorizationService authorizationService;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody @Valid RegisterDTO data) {
        User user = authorizationService.registerUser(data);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
