package org.example.parafiada.game;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class GameService {
    private final GameRepository gameRepository;
    private final ApplicationEventPublisher applicationEventPublisher ;

    @Transactional
    public void addNewPlayer(PlayerDTO playerDTO){
        PlayerEntity playerEntity=new PlayerEntity();
        playerEntity.setFirstName(playerDTO.firstName());
        playerEntity.setLastName(playerDTO.lastName());
        playerEntity.setCategory(playerDTO.category());
        gameRepository.save(playerEntity);

        applicationEventPublisher.publishEvent(new PlayerCreatedEvent(playerEntity));

    }

    @Transactional
    public List<GamerDTO> getPlayers(){

        return  gameRepository.findAllOrderByNames().stream()
                .map(playerEntity -> new GamerDTO(
                        playerEntity.getId(),
                        playerEntity.getFirstName(),
                        playerEntity.getLastName(),
                        playerEntity.getCategory()
                ))
                .toList();
    }

    @Transactional
    public void addPoints(Long id){
        gameRepository.addPoints(id,5);
    }

    @Transactional
    public List<WInnerDTO> getWinners(){
        return gameRepository.findALLOrderByPoints().stream()
                .map(playerEntity -> new WInnerDTO(
                        playerEntity.getFirstName(),
                        playerEntity.getLastName(),
                        playerEntity.getPoints(),
                        playerEntity.getCategory()
                ))
                .toList();
    }

}
