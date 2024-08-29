package DBTeam3.Abandonne.dto;

import DBTeam3.Abandonne.domain.User_Portfolio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class AddPortfolioRequest {

    long uid;
    String about_me;
    int level;

    public User_Portfolio toEntity(){
        User_Portfolio up = new User_Portfolio();
        up.setUid(this.uid);
        up.setLevel(level);
        up.setAboutMe(about_me);
        return up;
    }

}
