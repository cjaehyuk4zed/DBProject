package DBTeam3.Abandonne.domain;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "concert_hall")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Concert_Hall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Hall_id")
    long hid;

    @Column(name = "Concert_name")
    String hallName;

    @Column(name = "Hall_location")
    long hallLocationId;

    @Column(name = "Hall_address")
    String hallAddress;

}

