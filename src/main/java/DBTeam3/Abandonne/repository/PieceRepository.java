package DBTeam3.Abandonne.repository;
import DBTeam3.Abandonne.domain.Piece;
//import DBTeam3.Abandonne.domain.User_Info;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PieceRepository extends JpaRepository<Piece, Long>{

    Piece findByPieceNameAndComposerName(String pieceName, String composerName);

    List<Piece> findByPieceName(String pieceName);
    List<Piece> findByComposerName(String composerName);
}
