package DBTeam3.Abandonne.repository;

import DBTeam3.Abandonne.domain.Concert_Program;
import DBTeam3.Abandonne.domain.Concert_Program_Id;
import DBTeam3.Abandonne.domain.Piece;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ConcertProgramRepository extends JpaRepository<Concert_Program, Concert_Program_Id>{

    List<Concert_Program> findByPid(long pid);

    List<Concert_Program> findByCid(long cid);

}

