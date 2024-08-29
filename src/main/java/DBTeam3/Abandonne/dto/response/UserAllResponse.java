package DBTeam3.Abandonne.dto.response;

import DBTeam3.Abandonne.domain.User_Career;
import DBTeam3.Abandonne.domain.User_Info;
import DBTeam3.Abandonne.domain.User_Portfolio;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class UserAllResponse {

    //Data from User_info
    String fname, lname, email, instrument, phone_no;

    //Data from Portfolio
    User_Portfolio userPortfolio;

    //Data from Career
    List<User_Career> userCareers;


    public UserAllResponse(User_Info ui, User_Portfolio up, List<User_Career> uc){

        this.fname = ui.getFname();
        this.lname = ui.getLname();
        this.email = ui.getEmail();
        this.instrument = ui.getInstrument();
        this.phone_no = ui.getPhone_no();

        this.userPortfolio = (up != null) ? up : new User_Portfolio();


        if(uc == null) //커리어가 하나도 없는 경우
        {
            User_Career career = new User_Career(); //디폴트 커리어 객체 하나 생성
            this.userCareers = new ArrayList<>(); //커리어 리스트 생성
            this.userCareers.add(career); //이 커리어 리스트에 디폴트 커리어 하나만 넣어 줌
        }

        else this.userCareers = uc;
    }



}
