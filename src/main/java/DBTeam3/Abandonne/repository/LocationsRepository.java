package DBTeam3.Abandonne.repository;

import DBTeam3.Abandonne.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LocationsRepository extends JpaRepository<Location, Long> {

}
