package DBTeam3.Abandonne.repository;

import DBTeam3.Abandonne.domain.User_Career;
import DBTeam3.Abandonne.domain.User_Wants;
import DBTeam3.Abandonne.domain.User_Wants;
import DBTeam3.Abandonne.domain.User_Wants_Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserWantsRepository extends JpaRepository<User_Wants, User_Wants_Id>{

    List<User_Wants> findByUid(long uid);
    List<User_Wants> findByPid(long pid);

}