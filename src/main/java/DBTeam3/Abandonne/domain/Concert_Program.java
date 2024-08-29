package DBTeam3.Abandonne.domain;
import lombok.*;
import jakarta.persistence.*;

@Entity(name = "concert_program")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@IdClass(Concert_Program_Id.class)
public class Concert_Program{

    @Id
    @Column(name = "Concert_id")
    long cid;

    @Id
    @Column(name = "Piece_id")
    long pid;

}
