package DBTeam3.Abandonne.domain;
import lombok.*;
import jakarta.persistence.*;

@Entity(name = "user_wants")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@IdClass(User_Wants_Id.class)
public class User_Wants {
    @Id
    @Column(name = "Uid")
    long uid;

    @Id
    @Column(name = "Piece_id")
    long pid;

    public User_Wants(User_Wants_Id pks){
        this.uid = pks.getUid();
        this.pid = pks.getPid();
    }

    public void update(User_Wants entity) {
        this.uid = entity.getUid();
        this.pid = entity.getPid();
    }

    public boolean checkPid(Long target){
        return (target == pid);
    }
}