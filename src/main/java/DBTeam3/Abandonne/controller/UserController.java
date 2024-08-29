package DBTeam3.Abandonne.controller;

import DBTeam3.Abandonne.domain.Piece;
import DBTeam3.Abandonne.domain.User_Info;
import DBTeam3.Abandonne.domain.User_Wants;
import DBTeam3.Abandonne.dto.*;
import DBTeam3.Abandonne.dto.response.*;
import DBTeam3.Abandonne.service.UserService;
import DBTeam3.Abandonne.service.UserWantsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;


@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserWantsService userWantsService;

    //새로운 유저 정보 생성
    @PostMapping("/api/user/new")
    public ResponseEntity<User_Info> save(@RequestBody AddUserRequest userRequest) {
        try {
            System.out.println("Requested data : " + userRequest);
            User_Info savedUser = userService.save(userRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    //특정 uid로 유저 정보 모두 조회
    @GetMapping("/api/user/{uid}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long uid) {
        try {
            User_Info retrievedUser = userService.findById(uid);
            return ResponseEntity.ok().body(new UserResponse(retrievedUser));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/api/user/{uid}/rnd/{num}")
    public ResponseEntity<List<UserResponse>> getUserByFavPiece(@PathVariable long uid, @PathVariable int num){

        //사용자의 관심 곡
        List<User_Wants> userFavPieces = userWantsService.findByUid(uid);

        List<UserResponse> userResponses = new ArrayList<>();
        List<Long> tmpUserIds = new ArrayList<>();

        for(User_Wants elem : userFavPieces){

            if(!tmpUserIds.contains(elem.getUid())){
                tmpUserIds.add(elem.getUid());
                userResponses.add(new UserResponse(userService.findById(elem.getUid())));
            }
        }

        List<UserResponse> result = new ArrayList<>();
        Random rnd = new Random();
        List<Long> numbers = new ArrayList<>();
        long sizeOfTmpUsers  = tmpUserIds.size();

        while(numbers.size() < num){
            long candidate = rnd.nextLong(sizeOfTmpUsers);

            if(!numbers.contains(candidate)){
                numbers.add(candidate);
                result.add(userResponses.get((int) candidate));
            }
        }

        return ResponseEntity.ok().body(result);
    }

    //유저 개인 정보 조회
    @GetMapping("/api/user/info/{uid}")
    public ResponseEntity<UserAllResponse> showUserAllById(@PathVariable Long uid) {
        try {
            UserAllResponse response = userService.showUserAllById(uid);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    //유저 개인 정보 수정
    @PutMapping("/api/user/info/{uid}/update")
    public ResponseEntity<User_Info> update(@PathVariable Long uid,
                                            @RequestBody UpdateUserInfoRequest updateUserInfoRequest){

        try {
            userService.update(uid, updateUserInfoRequest);
            return ResponseEntity.status(HttpStatus.OK).body(updateUserInfoRequest.toEntity(uid));
        }
        catch(Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    //특정 uid의 유저 삭제
    @DeleteMapping("/api/user/del/{uid}")
    public ResponseEntity<String> deleteById(@PathVariable Long uid) {
        try {
            userService.deleteById(uid);
            return ResponseEntity.status(HttpStatus.OK).body("delete "+uid);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("User not found : " + uid);
        }
    }

    //로그인 시도
    @PostMapping("/api/user/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest userRequest) {
        try {
            int uid = userService.checkIdAndPw(userRequest);
            switch (uid) {
                case -1:
                    System.out.println("invalid email : " + userRequest.getEmail());
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid email : " + userRequest.getEmail());
                case -2:
                    System.out.println("invalid pw");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid pw");
                default:
                    String uidString = String.valueOf(uid);
                    return ResponseEntity.status(HttpStatus.OK).body(uidString);
            }
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Undefined error");
        }
    }


}
