package org.example.parafiada.football;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FootballRepository extends JpaRepository<FootballEntity,Long> {

    @Query("SELECT f from  FootballEntity  f order by f.footballCategory,f.points desc,f.goalsScored desc, f.goalsConceded,f.redCard,f.yellowCard")
    public List<FootballEntity> findAllByOrderByGame();

    public List<FootballEntity> findAllByOrderByName();
}
