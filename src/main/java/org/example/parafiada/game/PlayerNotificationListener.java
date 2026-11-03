package org.example.parafiada.game;

import jakarta.persistence.Column;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlayerNotificationListener {
    private final SimpMessagingTemplate messagingTemplate;

    @EventListener
    public void handlePlayerCreated(PlayerCreatedEvent event) {
       PlayerEntity playerEntity= event.playerEntity();
       GamerDTO gamerDTO =new GamerDTO(playerEntity.getId(), playerEntity.getFirstName(),playerEntity.getLastName(),playerEntity.getCategory());

        messagingTemplate.convertAndSend("/topic/players",gamerDTO);
    }

}
