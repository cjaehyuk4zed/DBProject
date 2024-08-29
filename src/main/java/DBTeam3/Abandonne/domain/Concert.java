package DBTeam3.Abandonne.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity(name = "concert")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Concert{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Concert_id")
    long cid;

    @Column(name = "Hall_id")
    long hid;

    @Column(name = "Concert_name")
    String concertName;

    @Column(name = "Time")
    java.sql.Timestamp time;

    @Column(name = "Date")
    LocalDate date = null;

    @Column(name = "Price")
    int price;

    public Concert(String concertName, java.sql.Timestamp time, LocalDate date, int price) {
        this.concertName = concertName;
        this.time = time;
        this.date = date;
        this.price = price;
    }

    public Concert(long cid, String concertName, java.sql.Timestamp time, LocalDate date, int price) {
        this.cid = cid;
        this.concertName = concertName;
        this.time = time;
        this.date = date;
        this.price = price;
    }

    public Concert(String concertName, int price){
        this.concertName = concertName;
        this.price = price;
    }

}
