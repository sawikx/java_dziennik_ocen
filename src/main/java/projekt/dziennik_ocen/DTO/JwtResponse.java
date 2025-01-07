package projekt.dziennik_ocen.DTO;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private Integer id;
    private String email;
    private List<String> roles;
}
