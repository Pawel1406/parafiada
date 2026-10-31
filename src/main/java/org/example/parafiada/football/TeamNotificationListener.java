package org.example.parafiada.football;

import lombok.RequiredArgsConstructor;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TeamNotificationListener {
    private final SimpMessagingTemplate simpMessagingTemplate;

    @EventListener
    public void handleTeamCreated(TeamCreatedRecord teamCreatedRecord) {
        FootballEntity football=teamCreatedRecord.footballEntity();
        FootbalTeamDTO footbalTeamDTO=new FootbalTeamDTO(football.getId(), football.getName(),football.getFootballCategory() , football.getCaptain());
        simpMessagingTemplate.convertAndSend("/topic/teams",footbalTeamDTO);
    }
}
