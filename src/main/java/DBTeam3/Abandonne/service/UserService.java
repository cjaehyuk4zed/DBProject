package DBTeam3.Abandonne.service;

import DBTeam3.Abandonne.domain.*;
import DBTeam3.Abandonne.dto.LoginRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import DBTeam3.Abandonne.dto.AddUserRequest;
import DBTeam3.Abandonne.dto.UpdateUserInfoRequest;
import DBTeam3.Abandonne.dto.response.UserAllResponse;
import DBTeam3.Abandonne.repository.UserCareerRepository;
import DBTeam3.Abandonne.repository.UserPortfolioRepository;
import DBTeam3.Abandonne.repository.UserRepository;
import DBTeam3.Abandonne.repository.UserWantsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService{

    private final UserRepository userRepository;
    private final UserPortfolioRepository pfRepository;
    private final UserCareerRepository crRepository;
    private final UserWantsRepository uwRepository;

    //Create new user
    public User_Info save(AddUserRequest request){
        User_Info saveUser = request.toEntity();
        return userRepository.save(saveUser);
    }

    //Retrieve user information by uid
    public User_Info findById(Long id){

        return userRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("not found : id = "+id));

    }

    public List<User_Info> findAll(){
        return userRepository.findAll();
    }
    //Retrieve user information by uid
    public List<User_Info> findByFavPiece(Long pid){

        List<User_Wants> wants = uwRepository.findByPid(pid);
        List<Long> uids = new ArrayList<>();

        for (User_Wants elem : wants){
            if(!uids.contains(elem.getUid()))
                uids.add(elem.getUid());
        }

        List<User_Info> result = new ArrayList<>();

        for(long ids : uids){
            User_Info tmpUser = userRepository.findById(ids).orElse(null);
            if(tmpUser == null)
            {
                System.out.println("user id "+ids+" not found");
                continue;
            }
            result.add(tmpUser);
        }
        return result;
    }
    //Retrieve user's total information{profile, portfolio, interests}
    public UserAllResponse showUserAllById(long id){

        User_Info user = userRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("not found : id = "+id));

        Optional<User_Portfolio> pf = pfRepository.findById(id);
        User_Portfolio userPortfolio = pf.get();

        List<User_Career> careerList = crRepository.findByUid(id);
        if(careerList.isEmpty()) careerList = null;

        UserAllResponse response = new UserAllResponse(user, userPortfolio , careerList );

        return response;
    }

    //Update user information
    @Transactional
    public void update(Long uid, UpdateUserInfoRequest uir) {
        User_Info targetUserInfo = userRepository.findById(uid)
                .orElseThrow(() -> new IllegalArgumentException("User not found : " + uid));
        uir.checkValidity();

        targetUserInfo.update(uir);
    }

    //Delete user
    public void deleteById(Long uid){
        try {
            userRepository.deleteById(uid);
        }
        catch(Exception e){
            throw e;
        }
    }

    /**
     * Return Long. code by :
     * - -1 : email not found
     * - -2 : mismatch password
     * - 0 : ok
     */
    public int checkIdAndPw(LoginRequest request){

        List<User_Info> users = userRepository.findByEmail(request.getEmail());
        //email 맞는 유저 엔티티 없는 경우
        if(users.isEmpty())
            return -1;
        User_Info user = users.get(0);

        //그 유저 엔티티의 pw와 맞지 않는 경우
        if(!user.getPw().equals(request.getPw()))
            return -2;
        //검증 성공
        return Long.valueOf(user.getUid()).intValue();
    }

}
