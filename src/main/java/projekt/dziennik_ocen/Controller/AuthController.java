package projekt.dziennik_ocen.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import projekt.dziennik_ocen.auth.model.AppUserDetails;
import projekt.dziennik_ocen.auth.utils.JwtUtils;
import projekt.dziennik_ocen.DTO.JwtResponse;
import projekt.dziennik_ocen.DTO.LoginRequest;
import projekt.dziennik_ocen.DTO.MessageResponse;
import projekt.dziennik_ocen.DTO.SignUpRequest;
import projekt.dziennik_ocen.enums.ERole;
import projekt.dziennik_ocen.model.Role;
import projekt.dziennik_ocen.model.User;
import projekt.dziennik_ocen.Repository.RoleRepository;
import projekt.dziennik_ocen.Repository.UserRepository;
import org.springframework.ui.Model;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpHeaders;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    private final JwtUtils jwtUtils;

    public AuthController(
            AuthenticationManager authenticationManager,
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder encoder,
            JwtUtils jwtUtils
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, RedirectAttributes redirectAttributes,HttpSession session) {
        try {
            // Tworzenie obiektu Authentication z przesłanych danych
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
            // Próbujemy autentykację
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Generowanie tokenu JWT
            String jwtToken = jwtUtils.generateJwtToken(authentication);
            session.setAttribute("token", jwtToken);
            // Przekierowanie na stronę dashboard.html
            return "redirect:/uczniowie/";

        } catch (Exception e) {
            // Błąd autentykacji
            redirectAttributes.addFlashAttribute("error", "Nieprawidłowy login lub hasło.");
            return "redirect:/login";
        }
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }

        AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(
                Map.of(
                        "email", userDetails.getEmail(),
                        "roles", userDetails.getAuthorities().stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList())
                )
        );
    }

    /*@PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Wprowadzony adres email jest już zajęty..."));
        }
        User user = new User(
            signUpRequest.getEmail(),
            encoder.encode(signUpRequest.getPassword())
        );
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_UCZEN.name())
                .orElseThrow(() -> new RuntimeException("Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN.name())
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_NAUCZYCIEL.name())
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_UCZEN.name())
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok(
            new MessageResponse("Rejestracja Użytkownika powiodła się.")
        );
    }
*/
}
