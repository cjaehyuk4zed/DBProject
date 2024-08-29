package DBTeam3.Abandonne.domain;

import lombok.*;
import jakarta.persistence.*;

@Entity(name = "user_portfolio")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class User_Portfolio{

    @Id
    @Column(name = "Uid")
    long uid;

    @Column(name = "Level")
    int level;

    @Column(name = "About_me")
    String aboutMe;

    public void update(User_Portfolio userPortfolio) {

        this.uid = userPortfolio.getUid();
        this.level = userPortfolio.getLevel();

        String updatedAboutMe = userPortfolio.getAboutMe();
        if(updatedAboutMe.isEmpty())
            this.aboutMe = "(내용 없음)";
        else
            this.aboutMe = userPortfolio.getAboutMe();
    }


}
