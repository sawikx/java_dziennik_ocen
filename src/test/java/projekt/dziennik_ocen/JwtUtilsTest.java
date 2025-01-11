package projekt.dziennik_ocen;

import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import projekt.dziennik_ocen.auth.model.AppUserDetails;
import projekt.dziennik_ocen.auth.utils.JwtUtils;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class JwtUtilsTest {

    @Mock
    private Authentication authentication;

    @Mock
    private AppUserDetails appUserDetails;

    private JwtUtils jwtUtils;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        jwtUtils = new JwtUtils();

        // Konfiguracja wartości w polach @Value
        jwtUtils.jwtSecret = "pH2rUOKY7tnKN+jsiFmc0X6sN/iv73LrxndH+6ySUsc=";  // Zastąp odpowiednią tajną wartością
        jwtUtils.jwtExpirationMs = 60; // Token wygasa po 60 minutach
    }

    @Test
    void testGenerateJwtToken() {
        // Przygotowanie
        String username = "testUser";
        Mockito.when(authentication.getPrincipal()).thenReturn(appUserDetails);
        Mockito.when(appUserDetails.getUsername()).thenReturn(username);

        // Wywołanie metody
        String token = jwtUtils.generateJwtToken(authentication);

        // Weryfikacja
        Assertions.assertNotNull(token);
        Assertions.assertTrue(token.split("\\.").length == 3, "Token powinien zawierać 3 części");
    }

    @Test
    void testValidateJwtTokenWithValidToken() {
        // Przygotowanie - generowanie tokena przy użyciu tego samego sekretu
        String validToken = generateValidToken();

        // Wywołanie metody
        boolean isValid = jwtUtils.validateJwtToken(validToken);

        // Weryfikacja
        Assertions.assertTrue(isValid);
    }

    @Test
    void testValidateJwtTokenWithInvalidToken() {
        // Przygotowanie
        String invalidToken = "invalid.token.value";

        // Wywołanie metody
        boolean isValid = jwtUtils.validateJwtToken(invalidToken);

        // Weryfikacja
        Assertions.assertFalse(isValid);
    }

    @Test
    void testGetUserNameFromJwtToken() {
        // Przygotowanie - generowanie tokena przy użyciu tego samego sekretu
        String validToken = generateValidToken();

        // Wywołanie metody
        String username = jwtUtils.getUserNameFromJwtToken(validToken);

        // Weryfikacja
        Assertions.assertEquals("test", username, "Nazwa użytkownika w tokenie powinna być 'test'");
    }

    private String generateValidToken() {
        Date tokenCreateTime = new Date();
        Date tokenValidity = new Date(
                tokenCreateTime.getTime() + TimeUnit.MINUTES.toMillis(this.jwtUtils.jwtExpirationMs)
        );

        return Jwts.builder()
                .issuedAt(new Date())
                .subject("test")
                .expiration(tokenValidity)
                .signWith(jwtUtils.getSigningKey())
                .compact();
    }
}
