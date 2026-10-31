package org.example.parafiada.login;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;


    @Transactional
    public Map<String,Object> autenticateAndReturn(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.username(),
                        loginDTO.password()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserEntity user = userRepository.findByUsername(loginDTO.username()).orElseThrow(()->new UsernameNotFoundException("Nie znaleziono uzytkownika"));
        Map<String,Object> response = new HashMap<>();

        response.put("username",user.getUsername());
        response.put("role",user.getRole());

        return response;
    }


}
