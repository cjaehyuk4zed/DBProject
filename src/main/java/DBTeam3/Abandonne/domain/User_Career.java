package DBTeam3.Abandonne.domain;
import java.time.LocalDate;
import lombok.*;
import jakarta.persistence.*;
import DBTeam3.Abandonne.dto.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.MapKeyCompositeType;


@Entity(name = "user_career")
@Getter @Setter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User_Career {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Career_id")
    long cid;

    @Column(name = "Uid")
    long uid;

    @Column(name = "Piece_id")
    long pid;

    @Column(name = "Position")
    String position;

    @Column(name = "Organization")
    String organization;

    @Column(name = "When_start")
    LocalDate startDate;

    @Column(name = "When_end")
    LocalDate endDate = null;

    public User_Career(long uid, long pid, String position, String organization,
                      LocalDate startDate, LocalDate endDate)
    {
        this.uid = uid;
        this.pid = pid;
        this.position = position;
        this.organization = organization;
        this.startDate = startDate;
        this.endDate = endDate;

    }

    //Default value
    public User_Career()
    {
        this.position = null;
        this.organization = null;
        this.startDate = LocalDate.of(2000, 1, 1);
        this.endDate =  LocalDate.of(2000, 1, 1);
    }
    
    public void update(UpdateCareerRequest ucr){
        this.uid = ucr.getUid();
        this.cid = ucr.getCid();
        this.pid = ucr.getCid();
        this.position = ucr.getPosition();
        this.organization = ucr.getOrganization();
        this.startDate = ucr.getStartDate();
        this.endDate = ucr.getEndDate();
    }
}