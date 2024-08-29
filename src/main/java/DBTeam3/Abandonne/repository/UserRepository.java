package DBTeam3.Abandonne.repository;
import DBTeam3.Abandonne.domain.User_Info;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User_Info, Long>{
    List<User_Info> findByEmail(String email);
    List<User_Info> findUser_InfoByUid(Long uid);//select u from User_Info u where u.email = ? 생성됨

}



