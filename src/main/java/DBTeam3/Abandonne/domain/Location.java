package DBTeam3.Abandonne.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "locations")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Loc_id")
    long lid;

    @Column(name = "Province")
    String province;

    @Column(name = "City")
    String city;


}


