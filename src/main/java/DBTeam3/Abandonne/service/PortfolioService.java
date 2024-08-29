package DBTeam3.Abandonne.service;


import DBTeam3.Abandonne.domain.User_Portfolio;
import DBTeam3.Abandonne.dto.AddPortfolioRequest;
import DBTeam3.Abandonne.dto.UpdatePortfolioRequest;
import DBTeam3.Abandonne.repository.UserPortfolioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PortfolioService {

    private final UserPortfolioRepository userPortfolioRepository;

    //create new portfolio only FIRST TIME
    public User_Portfolio save(AddPortfolioRequest req){

        return userPortfolioRepository.save(req.toEntity());
    }

    //retrieve User_Portfolio by ID
    public User_Portfolio findById(Long id){
        return userPortfolioRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("not found by id : "+id));
    }

    //Update Career with {cid}
    @Transactional
    public void update(Long uid, UpdatePortfolioRequest updateRequest) {
        User_Portfolio updatedPortfolio = userPortfolioRepository.findById(uid)
                .orElseThrow(() -> new IllegalArgumentException("not found : portfolio of "+ uid));

        updatedPortfolio.update(updateRequest.toEntity(uid));
    }


}
