package projekt.dziennik_ocen.auth.service;

import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import projekt.dziennik_ocen.auth.model.AppUserDetails;
import projekt.dziennik_ocen.model.User;
import projekt.dziennik_ocen.Repository.UserRepository;

@Service
@Transactional
public class AppUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public AppUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public AppUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
            .orElseThrow(
                () -> new UsernameNotFoundException("User Not Found with username: " + username)
            );
        return AppUserDetails.build(user);
    }

}
