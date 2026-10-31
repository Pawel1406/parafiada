package org.example.parafiada.register;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.parafiada.login.UserEntity;
import org.example.parafiada.login.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public void addNewUser(RegisterDTO registerDTO){
        UserEntity  userEntity = new UserEntity();
        userEntity.setUsername(registerDTO.username());
        userEntity.setPassword(passwordEncoder.encode(registerDTO.password()));
        userEntity.setRole(registerDTO.role());
        userRepository.save(userEntity);

    }

}
