package DBTeam3.Abandonne.controller;

import DBTeam3.Abandonne.domain.User_Career;
import DBTeam3.Abandonne.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import DBTeam3.Abandonne.service.CareerService;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class CareerController {

    private final CareerService careerService;

    //새로운 커리어 생성
    @PostMapping("/api/user/{uid}/career/new")
    public ResponseEntity<User_Career> saveCareer(@PathVariable Long uid,
                                                  @RequestBody AddCareerRequest careerRequest){
        try {
            User_Career savedCareer = careerService.saveCareer(uid, careerRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCareer);
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


    //특정 cid의 커리어 정보 조회
    @GetMapping("/api/user/career/{cid}")
    public ResponseEntity<User_Career> findById(@PathVariable Long cid){
        try{
            User_Career userCareer = careerService.findById(cid);
            return ResponseEntity.status(HttpStatus.OK).body(userCareer);
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    //특정 uid 유저의 모든 커리어 조회
    @GetMapping("/api/user/{uid}/career")
    public ResponseEntity<List<User_Career>> findByUid(@PathVariable Long uid){
        List<User_Career> userCareers = careerService.findByUid(uid);
        if (userCareers.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        else
            return ResponseEntity.status(HttpStatus.OK).body(userCareers);
    }

    //특정 cid의 업데이트
    @PutMapping("/api/user/{uid}/career/update/{cid}")
    public ResponseEntity<User_Career> updateCareer(@PathVariable Long uid,
            @PathVariable Long cid, @RequestBody UpdateCareerRequest ucr){
        try {
            ucr.checkValidity();
            User_Career updatedCareer = careerService.update(cid, uid, ucr);
            return ResponseEntity.status(HttpStatus.OK).body(updatedCareer);
        }
        catch(Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


    //특정 cid의 커리어 삭제
    @DeleteMapping("/api/user/career/{cid}")
    public ResponseEntity<String> deleteByCid(@PathVariable Long cid){
        careerService.deleteByCid(cid);

        return ResponseEntity.status(HttpStatus.OK).body("delete"+cid);
    }
}
