package DBTeam3.Abandonne.dto;

import DBTeam3.Abandonne.domain.User_Career;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCareerRequest {

    private long pid;
    private long uid;
    private long cid;
    String position, organization;
    LocalDate startDate, endDate = null;

    public User_Career toEntity(long cid, long uid){
        return new User_Career(cid, uid, pid,
                position, organization, startDate, endDate);
    }

    /**
     * check validity
     * - 1 : invalid LocalDate format
     * @return
     */
    public void checkValidity(){
        if(!(startDate instanceof LocalDate))
            throw new IllegalArgumentException("UpdateCareerRequest : Invalid start date\n"+this);
    }
}
