package com.personalprojects.gestorb2b.services;

import com.personalprojects.gestorb2b.domain.dtos.RegisterDTO;
import com.personalprojects.gestorb2b.domain.entities.User;
import com.personalprojects.gestorb2b.infra.exceptions.UserAlreadyExistsException;
import com.personalprojects.gestorb2b.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username);
    }

    public User registerUser(RegisterDTO data) {
        if(userRepository.findByEmail(data.email()) != null) throw new UserAlreadyExistsException();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User user = new User();
        user.setEmail(data.email());
        user.setPassword(encryptedPassword);
        user.setFullName(data.fullName());
        user.setRole(data.role());

        return userRepository.save(user);
    }
}
