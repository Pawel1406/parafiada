package org.example.parafiada.game;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game")
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;

    @PostMapping("/newPlayer")
    public ResponseEntity<?> addNewPlayer(@Valid @RequestBody PlayerDTO playerDTO){
        gameService.addNewPlayer(playerDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/getPlayers")
    public ResponseEntity<?> getPlayers(){
        return ResponseEntity.ok(gameService.getPlayers());
    }

    @PostMapping("/endOfGame")
    public ResponseEntity<?> endOfGame(@Valid @RequestBody Long idWinner){
        gameService.addPoints(idWinner);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/getWinner")
    public ResponseEntity<?> getWinner(){
        return ResponseEntity.ok(gameService.getWinners());

    }
}
