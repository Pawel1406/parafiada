package org.example.parafiada.football;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/footballGame")
@RequiredArgsConstructor
public class FootballController {
    private final FootballService footballService;

    @PreAuthorize("hasAnyRole('ADMIN','FOOTBALL_USER')")
    @PostMapping("/register")
    public ResponseEntity<?> registerTeam(@Valid @RequestBody FootballRegisterDTO footballRegisterDTO) {
        footballService.registerTeam(footballRegisterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/matchResult")
    public ResponseEntity<?> matchResult(@Valid @RequestBody MatchResultDTO matchResultDTO) {
        footballService.matchResult(matchResultDTO);
        return ResponseEntity.ok(HttpStatus.OK);

    }

    @GetMapping("/getTeams")
    @PreAuthorize("hasAnyRole('ADMIN', 'FOOTBALL_USER')")
    public ResponseEntity<?> getTeams() {
        return ResponseEntity.ok(footballService.getTeams());
    }

    @GetMapping("/winner")
    public ResponseEntity<?> getWinner() {
        return ResponseEntity.ok(footballService.winners());
    }

}
