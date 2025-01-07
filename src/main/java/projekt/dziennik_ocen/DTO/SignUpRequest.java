package projekt.dziennik_ocen.DTO;

import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SignUpRequest {

    private String email;

    private Set<String> role;

    private String password;

}
