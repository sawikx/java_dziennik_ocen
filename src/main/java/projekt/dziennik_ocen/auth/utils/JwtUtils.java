package projekt.dziennik_ocen.auth.utils;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import projekt.dziennik_ocen.auth.model.AppUserDetails;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtils {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${auth.jwtSecret}")
    public String jwtSecret;

    @Value("${auth.jwtExpirationMs}")
    public int jwtExpirationMs;

    public SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(this.jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateJwtToken(Authentication authentication) {
        AppUserDetails userPrincipal = (AppUserDetails) authentication.getPrincipal();

        Date tokenCreateTime = new Date();
        Date tokenValidity = new Date(
            tokenCreateTime.getTime() + TimeUnit.MINUTES.toMillis(this.jwtExpirationMs)
        );

        return Jwts.builder()
            .issuedAt(new Date())
            .subject(userPrincipal.getUsername())
            .expiration(tokenValidity)
            .signWith(getSigningKey())
            .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts
            .parser()
            .verifyWith(getSigningKey())
            .build()
            .parseSignedClaims(token)
            .getPayload()
            .getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts
                .parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(authToken);
            return true;
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature", ex);
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token", ex);
        } catch (ExpiredJwtException ex) {
            logger.error("JWT token is expired", ex);
        } catch (UnsupportedJwtException ex) {
            logger.error("JWT token is unsupported", ex);
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty", ex);
        }
        return false;
    }
}
