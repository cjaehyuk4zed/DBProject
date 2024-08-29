package DBTeam3.Abandonne.domain;

import DBTeam3.Abandonne.dto.UpdateUserInfoRequest;
import lombok.*;
import java.time.LocalDate;

import jakarta.persistence.*;


@Entity(name = "user_info")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User_Info {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Uid")
    long uid;
    @Column(name = "Fname", nullable = false)
    String fname;
    @Column(name = "Lname", nullable = false)
    String lname;
    @Column(name = "Email", nullable = false)
    String email;
    @Column(name = "Bdate")
    LocalDate bdate = null;
    @Column(name = "Gender")
    char gender = 'N';
    @Column(name = "Instrument")
    String instrument;
    @Column(name = "Phone_no")
    String phone_no;
    @Column(name = "Pw")
    String pw;

    public User_Info(String fname, String lname, String email, String instrument, String phone_no, String pw) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.instrument = instrument;
        this.phone_no = phone_no;
        this.pw = pw;
    }


    public User_Info(String fname, String lname, String email, String instrument, String phone_no, String pw, LocalDate bdate) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.instrument = instrument;
        this.phone_no = phone_no;
        this.pw = pw;
        this.bdate = bdate;
    }

    public String getFullName(){
        return fname+" "+ lname;
    }

    public void update(UpdateUserInfoRequest uir) {
        this.fname = uir.getFname();
        this.lname= uir.getLname();
        this.email = uir.getEmail();
        this.instrument = uir.getInstrument();
        this.phone_no = uir.getPhone_no();
        this.bdate = uir.getBdate();
        this.pw = getPw();
    }

}