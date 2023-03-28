package ru.ohanyan.bpm.fw.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ohanyan.bpm.domain.User;
import ru.ohanyan.bpm.fw.auth.jwt.JwtTokenProvider;

/**
 * todo Document type AuthController
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/bpm")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    ResponseEntity<String> authenticate(@RequestBody User user) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                user.getLogin(), user.getPassword().trim().toLowerCase());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        String token = jwtTokenProvider.generateToken(authentication);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
