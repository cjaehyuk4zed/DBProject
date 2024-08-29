package DBTeam3.Abandonne.dto.response;

import DBTeam3.Abandonne.domain.User_Info;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UserResponse {

    Long id;
    String fname, lname, email;
    LocalDate bdate;
    char gender;
    String instrument, phone_no, pw;

    public UserResponse(User_Info user){
        this.id = user.getUid();
        this.fname = user.getFname();
        this.lname = user.getLname();
        this.email = user.getEmail();
        this.bdate = user.getBdate();
        this.gender = user.getGender();
        this.instrument = user.getInstrument();
        this.phone_no = user.getPhone_no();
        this.pw = user.getPw();
    }

    public User_Info toEntity(){
        return new User_Info(id, fname, lname, email, bdate, gender, instrument, phone_no, pw);
    }
}
