package olegivanov.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import olegivanov.dtos.JwtRequest;
import olegivanov.dtos.JwtResponse;
import olegivanov.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import ru.netology.cloudstorage.exception.AppError;


@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
//    private final AuthenticationRepository authenticationRepository;
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;


    //здесь мы регистрируем нового пользователя и сохраняем его в базу, если такая функция будет необходима
    public ResponseEntity<?> createAuthToken(JwtRequest jwtRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Incorrect login or password"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(jwtRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }


//    public AuthResponse login(AuthRequest request) {
//        final String username = request.getLogin();
//        final String password = request.getPassword();
//        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//        final UserDetails userDetails = userService.loadUserByUsername(username);
//        String token = jwtTokenUtil.generateToken(userDetails);
//        authenticationRepository.putTokenAndUsername(token, username);
//        log.info("User {} is authorized", username);
//        return AuthResponse.builder()
//                .authToken(token)
//                .build();
//    }
//
//
//    public void logout(String authToken) {
//        if (authToken.startsWith("Bearer ")) {
//            authToken = authToken.substring(7);
//        }
//        final String username = authenticationRepository.getUserNameByToken(authToken);
//        log.info("User {} logout", username);
//        authenticationRepository.removeTokenAndUsernameByToken(authToken);
//    }
}
