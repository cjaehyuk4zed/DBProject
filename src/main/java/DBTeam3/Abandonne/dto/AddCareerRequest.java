package DBTeam3.Abandonne.dto;

import DBTeam3.Abandonne.domain.User_Career;
import lombok.Getter;
import java.time.LocalDate;

@Getter
public class AddCareerRequest {

    String pieceName;
    String composerName;
    String position;
    String organization;
    LocalDate startdate;
    LocalDate enddate;

    //Req to entity
     public User_Career toEntity(long uid, long pid){

         return new User_Career(uid, pid, position, organization, startdate, enddate);

     }

}
