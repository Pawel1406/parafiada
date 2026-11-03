package org.example.parafiada.admin;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.parafiada.football.FootballRepository;
import org.example.parafiada.football.FootballService;
import org.example.parafiada.game.GameRepository;
import org.example.parafiada.login.UserEntity;
import org.example.parafiada.login.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private  final GameRepository  gameRepository;
    private final FootballRepository footballRepository;

    @Transactional
    public void deleteUsers(String username) {
        UserEntity user = userRepository.findByUsername(username).
                orElseThrow(()->new EntityNotFoundException("Brak uzytkownika w bazie"));
        userRepository.deleteUsersWithoutAdmin(user.getId());
    }

    @Transactional
    public void deleteUsersWithNoRoleAdmin() {
        userRepository.deleteUsersWithNoRoleAdmin();

    }

    @Transactional
    public void deletePlayers(){
        gameRepository.deleteAll();
    }

    @Transactional
    public void deleteFootballs(){
        footballRepository.deleteAll();
    }
}
