package DBTeam3.Abandonne.dto;

import DBTeam3.Abandonne.domain.User_Info;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class AddUserRequest {

    String fname;
    String lname;
    String email;
    String instrument;
    String phone_no;
    String pw;
//    LocalDate bdate;
// 여기 bdate 없앴고 대신 내가 User_Info에 생성자를 2개 만들어놨어
//    bdate가 있는 생성자 하나랑, bdate없는 생성자
//    bdate가 있는 생성자는 UpdateUserInfoRequest에서 사용하고
//    bdate가 없는 생성자는 여기에 사용하고 // dzdzㅇㅋㅇㅋ 굿굿
    //Req to entity
    public User_Info toEntity(){
        return new User_Info(fname, lname, email, instrument, phone_no, pw);
    }
}
