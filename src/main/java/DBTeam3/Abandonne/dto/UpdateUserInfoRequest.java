package DBTeam3.Abandonne.dto;

import DBTeam3.Abandonne.domain.User_Info;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UpdateUserInfoRequest {
    long uid;
    String fname, lname, email;
    LocalDate bdate;
    char gender;
    String instrument, phone_no, pw;

    public User_Info toEntity(Long uid) {
        checkValidity();
        return new User_Info(uid, fname, lname, email, bdate, gender, instrument, phone_no, pw);
    }

    public void checkValidity(){
        switch(gender){
            case 'f':
                gender = 'F'; break;
            case 'm':
                gender = 'M'; break;
            case 'F':
            case 'M':
                break;
            default:
                throw new IllegalArgumentException("UpdateUserInfoRequest : Invalid gender : "+gender);
        }
        if(!(bdate instanceof LocalDate))
            throw new IllegalArgumentException("UpdateUserInfoRequest : Invalid bdate");

    }
}
