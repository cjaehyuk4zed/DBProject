package DBTeam3.Abandonne.repository;

import DBTeam3.Abandonne.domain.User_Career;
import DBTeam3.Abandonne.domain.User_Info;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCareerRepository extends JpaRepository<User_Career, Long> {

    List<User_Career> findByUid(long uid);
    //select u_career from User_Career where uid = ? 생성됨


}
