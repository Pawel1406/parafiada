package org.example.parafiada.game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GameRepository extends JpaRepository<PlayerEntity, Long> {

    @Modifying
    @Query("UPDATE PlayerEntity p SET p.points = p.points + :points WHERE p.id = :id")
    public void addPoints(@Param("id") Long id, @Param("points") Integer points);

    @Query("SELECT p FROM PlayerEntity p ORDER BY p.lastName ,p.firstName " )
    public List<PlayerEntity> findAllOrderByNames();

    @Query("SELECT p from PlayerEntity p ORDER BY p.points DESC,p.lastName asc,p.firstName asc")
    public List<PlayerEntity> findALLOrderByPoints();


}
