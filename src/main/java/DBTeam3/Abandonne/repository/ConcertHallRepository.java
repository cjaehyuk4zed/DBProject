package DBTeam3.Abandonne.repository;

import DBTeam3.Abandonne.domain.Concert_Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ConcertHallRepository extends JpaRepository<Concert_Hall, Long> {

}

