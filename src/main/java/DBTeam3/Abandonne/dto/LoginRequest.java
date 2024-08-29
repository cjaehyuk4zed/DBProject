package DBTeam3.Abandonne.dto;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class LoginRequest {

    String email;
    String pw;
}
