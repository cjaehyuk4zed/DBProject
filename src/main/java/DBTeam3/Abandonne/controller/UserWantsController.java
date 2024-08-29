package DBTeam3.Abandonne.controller;

import DBTeam3.Abandonne.domain.*;
import DBTeam3.Abandonne.dto.*;
import DBTeam3.Abandonne.service.UserWantsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserWantsController {

    private final UserWantsService userWantsService;

    //새로운 User Wants 생성
    @PostMapping("/api/user/{uid}/fav/new")
    public ResponseEntity<User_Wants> save(@RequestBody AddFavPieceRequest userRequest,
                                      @PathVariable Long uid) {
        try {
            User_Wants savedPiece = userWantsService.save(uid, userRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPiece);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    //특정 uid 유저의 모든 User Wants 조회
    @GetMapping("/api/user/{uid}/favs")
    public ResponseEntity<List<User_Wants>> findByUid(@PathVariable Long uid){
        List<User_Wants> favs = userWantsService.findByUid(uid);
        if (favs == null)
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        else
            return ResponseEntity.status(HttpStatus.OK).body(favs);
    }

    //{uid, pid}의 User_Wants 수정
    @PutMapping("/api/user/{uid}/fav/update/{pid}")
    public ResponseEntity<User_Wants> update(@PathVariable Long uid,
                                             @PathVariable Long pid,
                                             @RequestBody UpdateUserWantRequest uuwr){
        userWantsService.update(new User_Wants_Id(uid, pid), uuwr);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    //특정 uid의 특정 Fav 삭제
    @DeleteMapping("/api/user/{uid}/fav/del/{pid}")
    public ResponseEntity<String> deleteById(@PathVariable Long uid, @PathVariable Long pid) {
        try {
            userWantsService.deleteById(new User_Wants_Id(uid, pid));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Fav piece not found : " + uid + "/"+pid);
        }
        return ResponseEntity.status(HttpStatus.OK).body("delete "+pid);
    }
}
