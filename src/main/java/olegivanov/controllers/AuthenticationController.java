package olegivanov.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import olegivanov.dtos.JwtRequest;
import olegivanov.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthenticationController {

   private final AuthenticationService authenticationService;



    @GetMapping("/info")
    public String info(Principal principal) {
        return principal.getName();
    }

    @PostMapping("/auth")
    public ResponseEntity<?> createToken(@RequestBody JwtRequest jwtRequest) {
        return authenticationService.createAuthToken(jwtRequest);
    }
    @PostMapping("/registerium")
    public ResponseEntity<?> register(@RequestBody JwtRequest jwtRequest) {
        return authenticationService.createAuthToken(jwtRequest);
    }


//    @PostMapping("/login")
//    public AuthResponse login(@RequestBody AuthRequest request) {
//        log.info("Authentication is successfully");
//        return authenticationService.login(request);
//
//    }
//
//    @PostMapping("/logout")
//    public ResponseEntity<?> logout(@RequestHeader("auth-token") String authToken) {
//        authenticationService.logout(authToken);
//        return ResponseEntity.ok(HttpStatus.OK);
//    }

}
