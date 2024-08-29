package DBTeam3.Abandonne.repository;

import DBTeam3.Abandonne.domain.Concert;
import DBTeam3.Abandonne.domain.User_Info;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ConcertRepository extends JpaRepository<Concert, Long>{

    //List<Concert> findByHall(String hid);

}
