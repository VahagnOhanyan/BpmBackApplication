package ru.ohanyan.bpm.fw.auth.jwt;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import ru.ohanyan.bpm.fw.auth.UserAuthDetails;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

/**
 * todo Document type JwtTokenProvider
 */
@Component
@Slf4j
public class JwtTokenProvider {
    private static final String JWT_SECRET = "secret";
    private static final long SECONDS = 3600;
    public String generateToken(Authentication authentication) {
        UserAuthDetails userAuthDetails = (UserAuthDetails) authentication.getPrincipal();
        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setSubject(userAuthDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(Date.from(Instant.now().plusSeconds(SECONDS)))
                .claim("user", userAuthDetails)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }


    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            log.debug("Invalid JWT signature -> Message: {} ", e.getMessage());
        } catch (MalformedJwtException e) {
            log.debug("Invalid JWT token -> Message: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.debug("Expired JWT token -> Message: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.debug("Unsupported JWT token -> Message: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.debug("JWT claims string is empty -> Message: {}", e.getMessage());
        }

        return false;
    }

    public String getToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }
}
