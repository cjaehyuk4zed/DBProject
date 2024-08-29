package DBTeam3.Abandonne.repository;

import DBTeam3.Abandonne.domain.User_Career;
import DBTeam3.Abandonne.domain.User_Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPortfolioRepository extends JpaRepository<User_Portfolio, Long> {

}