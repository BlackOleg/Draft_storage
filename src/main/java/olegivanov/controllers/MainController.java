package olegivanov.controllers;

import lombok.RequiredArgsConstructor;
import olegivanov.dtos.JwtRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class MainController {
//    @GetMapping("/info")
//    public String info(Principal principal) {
//        return principal.getName();
//    }


}
