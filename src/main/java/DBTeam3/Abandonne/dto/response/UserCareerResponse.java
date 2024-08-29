package DBTeam3.Abandonne.dto.response;

import DBTeam3.Abandonne.domain.User_Career;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class UserCareerResponse {

    long cid, uid, pid;
    String position, organization;
    LocalDate startDate, endDate = null;

    public User_Career toEntity(){
        return new User_Career(cid, uid, pid,
                position, organization, startDate, endDate);
    }

    public UserCareerResponse(User_Career userCareer){
        this.cid = userCareer.getCid();
        this.uid = userCareer.getUid();
        this.pid = userCareer.getPid();
        this.position = userCareer.getPosition();
        this.organization = userCareer.getOrganization();
        this.startDate = userCareer.getStartDate();
        this.endDate = userCareer.getEndDate();
    }

}
