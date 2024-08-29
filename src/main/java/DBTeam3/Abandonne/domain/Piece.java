package DBTeam3.Abandonne.domain;

import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDate;


@Entity(name = "piece")
@Getter @Setter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Piece {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Piece_id")
    long pid;

    @Column(name = "Piece_name")
    String pieceName;

    @Column(name = "Composer_name")
    String composerName;

    public Piece(String pieceName, String composerName)
    {
        this.pieceName = pieceName;
        this.composerName = composerName;
    }

    public Piece()
    {
        this.pid = 0;
        this.pieceName = "Unknown";
        this.composerName = "Unknown";
    }

}
