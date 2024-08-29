package DBTeam3.Abandonne.dto.response;

import DBTeam3.Abandonne.domain.User_Portfolio;
import lombok.Getter;

@Getter
public class UserPortfolioResponse {

    long uid;
    String about_me;
    int level;

    public User_Portfolio toEntity(){
        return new User_Portfolio(uid, level, about_me);
    }
}
