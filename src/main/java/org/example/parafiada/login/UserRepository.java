package org.example.parafiada.login;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);

    @Modifying
    @Query("DELETE FROM UserEntity u where u.id!=:id")
    void deleteUsersWithoutAdmin(@Param("id")Long id);

    @Modifying
    @Query("DELETE FROM UserEntity u WHERE u.role != 'ADMIN' ")
    void deleteUsersWithNoRoleAdmin();

}
