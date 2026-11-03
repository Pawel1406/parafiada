package org.example.parafiada.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor

public class AdminController {
    private final AdminService adminService;

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping ("/deleteUsers")
    public ResponseEntity<?> deleteUsersWithoutMe(Principal principal) {
        adminService.deleteUsers(principal.getName());
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping ("/deleteUsersWithoutAdmin")
    public ResponseEntity<?> deleteUsersWithoutAdmin() {
        adminService.deleteUsersWithNoRoleAdmin();
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deletePlayers")
    public ResponseEntity<?> deletePlayers() {
        adminService.deletePlayers();
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteFootballs")
    public ResponseEntity<?> deleteFootballs() {
        adminService.deleteFootballs();
        return ResponseEntity.ok().build();
    }
}
