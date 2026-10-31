package org.example.parafiada.config;


import org.example.parafiada.login.Role;
import org.example.parafiada.login.UserEntity;
import org.example.parafiada.login.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Initializator {

    @Bean
    CommandLineRunner initUser(UserRepository userRepository,
                               PasswordEncoder passwordEncoder,
                               @Value("${app.admin.password}") String adminPassword) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                UserEntity user = new UserEntity();
                user.setUsername("admin");
                user.setPassword(passwordEncoder.encode(adminPassword));
                user.setRole(Role.ADMIN);

                userRepository.save(user);
                System.out.println("Dodano testowego użytkownika do bazy!");
            }
        };
    }
}
