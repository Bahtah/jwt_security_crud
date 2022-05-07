package ru.surantaev.jwt_security.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.surantaev.jwt_security.config.jwt.JwtTokenUtil;
import ru.surantaev.jwt_security.dto.*;
import ru.surantaev.jwt_security.entity.User;
import ru.surantaev.jwt_security.repo.UserRepository;
import ru.surantaev.jwt_security.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/jwt")
public class AuthController {

    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;
    private final AuthMapper authMapper;

    @PostMapping("/login")
    public ResponseEntity<AuthResponce> login(@RequestBody AuthRequest authRequest) {
        try{
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest
                            .getPassword());
            User user = userRepository.findByEmail(authenticationToken.getName()).get();
            return ResponseEntity.ok().body(authMapper.view(jwtTokenUtil.generateToken(user), ValidationExceptionType.SUCCESS_FULLY, user));
        }catch(BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(authMapper.view("", ValidationExceptionType.LOGIN_FAILED, null));
        }
    }

    @PostMapping("/registration")
    public UserResponce create(@RequestBody UserRequest userRequest) {
        return userService.create(userRequest);
    }
}
