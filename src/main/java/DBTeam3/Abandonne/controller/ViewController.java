package DBTeam3.Abandonne.controller;

import DBTeam3.Abandonne.domain.*;
import DBTeam3.Abandonne.dto.*;
import DBTeam3.Abandonne.dto.response.CareerWithPieceResponse;
import DBTeam3.Abandonne.dto.response.ConcertResponse;
import DBTeam3.Abandonne.dto.response.UserResponse;
import DBTeam3.Abandonne.repository.PieceRepository;
import DBTeam3.Abandonne.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@RequiredArgsConstructor
public class ViewController {

    private final UserService userService;
    private final UserWantsService userWantsService;
    private final CareerService careerService;
    private final PieceRepository pieceRepository;
    private final ConcertService concertService;

    //redirect when user exists (로그인 이후 상황 가정)

    @GetMapping("/dborch/main/user/{uid}")
    public String getMainPage(@PathVariable long uid, Model model){
        User_Info userInfo = userService.findById(uid);
        model.addAttribute("userInfo", userInfo);


        /* 무작위 사용자 추천*/
        Random rnd = new Random();
        List<User_Wants> userFavPieces = userWantsService.findByUid(uid); //해당 사용자의 선호곡 목록 가져오기
        List<UserResponse> userResponses = new ArrayList<>(); //사용자의 선호곡과 같은 곡을 좋아하는 모든 사람들
        
        List<Long> tmpUids = new ArrayList<>();
        List<Long> numbers = new ArrayList<>();

        boolean isNullFavList = (userFavPieces.isEmpty());

        List<UserResponse> userResponseResult = new ArrayList<>();

        //관심곡 있다면
        if(!isNullFavList) {

            //모든 선호 내역을 조회
            for (User_Wants elem : userFavPieces) {
                //elem.piece를 좋아하는 다른 유저들의 요구 사항
                //UserResponse = 그 유저들의 uid를 토대로 조회한 값들
                List<User_Wants> otherUsersWants = userWantsService.findByPid(elem.getPid());
                for (User_Wants userWants : otherUsersWants) {
                    long targetUserId = userWants.getUid();
                    System.out.println("\n\tDebugLog :: iteration_2, uid ="+targetUserId);

                    if (!tmpUids.contains(targetUserId) && targetUserId != uid) {
                        tmpUids.add(targetUserId);
                        userResponses.add(new
                                UserResponse(userService.findById(targetUserId)));
                    }
                }
            }

            long sizeOfTmpUsers  = userResponses.size();

            //조건에 맞는 사용자 (userResponses) 중에서 무작위로 가져오기
            while (numbers.size() < 4) {
                long candidate = rnd.nextLong(1, sizeOfTmpUsers - 1);
                if (candidate < 1) continue;
                if (!numbers.contains(candidate) && candidate != uid) {
                    numbers.add(candidate);
                    userResponseResult.add(userResponses.get((int) candidate));
                }
            }
        }

        //관심곡 없다면 : 무작위 4명 추천
        if(isNullFavList) {
            long bound = userService.findAll().size();

            while (numbers.size() < 4) {
                long candidate = rnd.nextLong(1, bound);
                if (candidate < 1 && candidate > 500) continue;

                numbers.add(candidate);
                userResponseResult.add(new UserResponse(userService.findById(candidate)));
            }
        }

        model.addAttribute("userSuggestions", userResponseResult);
        
        /*선호 곡에 따른 콘서트 정보*/
        List<ConcertResponse> concertResponses
                = concertService.recommendByPieces(uid);

        if(concertResponses == null)
            model.addAttribute("suggestedConcert", concertService.getRandomConcerts());
        else{
            model.addAttribute("suggestedConcert", concertResponses);
        }
        return "index";
    }

//    회원가입 페이지
    @RequestMapping(value = "/dborch/user/userform", method= RequestMethod.GET)
    public String getUserForm(Model model){
        model.addAttribute("userForm", new AddUserRequest());

        return "userForm";
    }

    // 로그인 페이지
    @GetMapping("/dborch/user/login")
    public String getLoginPage(Model model){
        model.addAttribute("userForm", new LoginRequest());

        return "userLogin";
    }

    // Uid로 유저 프로필 조회
    @GetMapping("/dborch/user/{uid}/profile")
    public String getUserProfilePage(@PathVariable long uid, Model model){
        User_Info userInfo = userService.findById(uid);
        model.addAttribute("userInfo", userInfo);

        return "userProfile";
    }


    // Uid로 유저 커리어 정보 조회
    @GetMapping("/dborch/user/{uid}/career")
    public String getCareerPage(@PathVariable long uid, Model model){

        List<User_Career> career = careerService.findByUid(uid);
        List<CareerWithPieceResponse> response = new ArrayList<>();

        User_Info userInfo = userService.findById(uid);
        model.addAttribute("userInfo", userInfo);

        if(!career.isEmpty()){
            for (User_Career uc : career) {
                Piece tmpPiece = pieceRepository.findById(uc.getPid())
                        .orElse(null);

                if (tmpPiece == null){
                    tmpPiece = new Piece();
                }

                response.add(new CareerWithPieceResponse(uc, tmpPiece));
            }

            model.addAttribute("careerList", response);
        }
        return "userCareer";
    }


//  "곡 이름" + "작곡가 이름"으로 piece_id 조회
    @GetMapping("/dborch/pieceinfo/{pieceName}/{composerName}")
    public String getPieceInfoPage(@PathVariable(required = false) String pieceName,
                                @PathVariable(required = false) String composerName,
                                Model model){

        List<Piece> pieceList =userWantsService.
                getPieceInformation(pieceName, composerName);
        model.addAttribute("pieceList", pieceList);

        return "api/piece/piece";
    }


    // Concert_id 로 콘스터 정보 조회
    @GetMapping("/dborch/concert/{cid}")
    public String getConcertPage(@PathVariable long cid, Model model){
        ConcertResponse concertResponse = concertService.makeConcertResponse(cid);

        model.addAttribute("concertInfo", concertResponse);
        return "concertInfo";
    }


    // 추천 받은 사용자 프로필 & 커리어 정보 조회
    @GetMapping("/dborch/user/{uid}/info")
    public String getUserInfo(@PathVariable long uid, Model model){

        List<User_Career> career = careerService.findByUid(uid);
        List<CareerWithPieceResponse> response = new ArrayList<>();

        User_Info userInfo = userService.findById(uid);
        model.addAttribute("userInfo", userInfo);

        if(!career.isEmpty()){
            for (User_Career uc : career) {
                Piece tmpPiece = pieceRepository.findById(uc.getPid())
                        .orElse(null);

                if (tmpPiece == null){
                    tmpPiece = new Piece();
                }

                response.add(new CareerWithPieceResponse(uc, tmpPiece));
            }

            model.addAttribute("careerList", response);
        }
        return "userInfo";
    }
}
