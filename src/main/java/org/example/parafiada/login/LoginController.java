package org.example.parafiada.login;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDTO loginDTO,HttpSession session) {
        try{
            LoginDTO trimmedLoginDTO=new LoginDTO(loginDTO.username().trim(),loginDTO.password().trim() );
            Map<String,Object> response=loginService.autenticateAndReturn(trimmedLoginDTO);
            for (String s : response.keySet()) {
                session.setAttribute(s,response.get(s));
            }
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null && auth.isAuthenticated()) {
                session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
            }
            return ResponseEntity.ok(response);
        }
        catch(UsernameNotFoundException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        catch(Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        HttpSession httpSession=request.getSession();
        if(httpSession!=null){
            httpSession.invalidate();

        }
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok("Wylogowano");
    }

}
