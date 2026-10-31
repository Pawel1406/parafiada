package org.example.parafiada.football;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.parafiada.game.PlayerEntity;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FootballService {
    private final FootballRepository footballRepository;
    private final ApplicationEventPublisher applicationEventPublisher ;

    @Transactional
    public void registerTeam(@Valid @RequestBody FootballRegisterDTO FootballRegisterDTO) {
        FootballEntity footballEntity = new FootballEntity();
        footballEntity.setFootballCategory(FootballRegisterDTO.footballCategory());
        footballEntity.setName(FootballRegisterDTO.name());
        footballEntity.setCaptain(FootballRegisterDTO.captain());
        footballRepository.save(footballEntity);

        applicationEventPublisher.publishEvent(new TeamCreatedRecord(footballEntity));
    }


    private MatchDTO firstWinner(MatchResultDTO matchResultDTO) {
        return new MatchDTO(matchResultDTO.firstID(), matchResultDTO.secondID(),
                matchResultDTO.firstGoalsScored(),matchResultDTO.firstGoalsConceded(),
                matchResultDTO.firstYellowCards(), matchResultDTO.firstRedCards(),
                matchResultDTO.secondGoalsScored(), matchResultDTO.secondGoalsConceded(),
                matchResultDTO.secondYellowCards(), matchResultDTO.secondRedCards());
    }

    private MatchDTO secondWinner(MatchResultDTO matchResultDTO) {
        return new MatchDTO(matchResultDTO.secondID(), matchResultDTO.firstID(),
                matchResultDTO.secondGoalsScored(), matchResultDTO.secondGoalsConceded(),
                matchResultDTO.secondYellowCards(), matchResultDTO.secondRedCards(),
                matchResultDTO.firstGoalsScored(),matchResultDTO.firstGoalsConceded(),
                matchResultDTO.firstYellowCards(), matchResultDTO.firstRedCards());
    }

    @Transactional
    public void matchResult(MatchResultDTO matchResultDTO) {
        if(footballRepository.findById(matchResultDTO.firstID()).orElse(null) == null) {return;}
        if(footballRepository.findById(matchResultDTO.secondID()).orElse(null) == null) {return;}




        if(matchResultDTO.idWinnerPenaltyKicks()==null) {
            if(matchResultDTO.firstGoalsScored()>matchResultDTO.secondGoalsScored()) {
                 matchWinnerResult(firstWinner(matchResultDTO));
            }
            else{
                matchWinnerResult(secondWinner(matchResultDTO));
            }
        }
        else{
            if(matchResultDTO.idWinnerPenaltyKicks().equals(matchResultDTO.firstID())){
                matchWinnerResult(firstWinner(matchResultDTO));
            }
            else{
                matchWinnerResult(secondWinner(matchResultDTO));
            }


        }

        if(matchResultDTO.firstGoalsScored()==matchResultDTO.secondGoalsScored()){
            if(matchResultDTO.idWinnerPenaltyKicks()==null){
                return;
            }
        }

    }

    @Transactional
    public void matchWinnerResult(MatchDTO matchDTO) {
        FootballEntity footballEntityWinner =footballRepository.findById(matchDTO.winnerID()).orElseThrow(()->new EntityNotFoundException("Nie ma takiej drużyny"));
        FootballEntity footballEntityLoser =footballRepository.findById(matchDTO.loserID()).orElseThrow(()->new EntityNotFoundException("Nie ma takiej drużyny"));

        footballEntityWinner.setPoints(footballEntityWinner.getPoints()+3);

        footballEntityWinner.setGoalsScored(footballEntityWinner.getGoalsScored()+ matchDTO.winnerGoalsScored());
        footballEntityWinner.setGoalsConceded(footballEntityWinner.getGoalsConceded()+ matchDTO.winnerGoalsConceded());

        footballEntityLoser.setGoalsScored(footballEntityLoser.getGoalsScored()+ matchDTO.loserGoalsScored());
        footballEntityLoser.setGoalsConceded(footballEntityLoser.getGoalsConceded()+ matchDTO.loserGoalsConceded());

        footballEntityWinner.setRedCard(footballEntityWinner.getRedCard()+ matchDTO.winnerRedCards());
        footballEntityWinner.setYellowCard(footballEntityWinner.getYellowCard()+ matchDTO.winnerYellowCards());

        footballEntityLoser.setRedCard(footballEntityLoser.getRedCard()+ matchDTO.loserRedCards());
        footballEntityLoser.setYellowCard(footballEntityLoser.getYellowCard()+ matchDTO.loserYellowCards());

        //footballRepository.saveAll(List.of(footballEntityWinner,footballEntityLoser));

    }
    @Transactional
    public List<FootbalTeamDTO> getTeams(){
        return footballRepository.findAllByOrderByName().stream()
                .map(footballEntity -> new FootbalTeamDTO(
                        footballEntity.getId(),
                        footballEntity.getName(),
                        footballEntity.getFootballCategory(),
                        footballEntity.getCaptain()
                ))
                .toList();

    }

    @Transactional
    public List<WinnerFootballDTO> winners() {
         return footballRepository.findAllByOrderByGame().stream()
                 .map(footballEntity -> new WinnerFootballDTO(
                         footballEntity.getId(),
                         footballEntity.getFootballCategory(),
                         footballEntity.getName(),
                         footballEntity.getPoints(),
                         footballEntity.getGoalsScored(),
                         footballEntity.getGoalsConceded(),
                         footballEntity.getRedCard(),
                         footballEntity.getYellowCard(),
                         footballEntity.getCaptain()
                 ))
                 .toList();
    }
}
