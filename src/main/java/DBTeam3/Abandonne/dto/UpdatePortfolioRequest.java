package DBTeam3.Abandonne.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import DBTeam3.Abandonne.domain.User_Portfolio;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePortfolioRequest {

    int level;
    String aboutMe;

    public User_Portfolio toEntity(long uid){
        return new User_Portfolio(uid, level, aboutMe);
    }
}
