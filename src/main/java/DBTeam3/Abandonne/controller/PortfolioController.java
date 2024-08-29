package DBTeam3.Abandonne.controller;

import DBTeam3.Abandonne.domain.User_Portfolio;
import DBTeam3.Abandonne.dto.AddPortfolioRequest;
import DBTeam3.Abandonne.dto.UpdatePortfolioRequest;
import DBTeam3.Abandonne.service.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PortfolioController {

    private final PortfolioService portfolioService;


    //새로운 user가 등록될 시, 기본 값으로 초기화하는 메서드
    @PostMapping("/api/user/{uid}/portfolio/new")
    public ResponseEntity<User_Portfolio> save(@PathVariable long uid,
                                               @RequestBody AddPortfolioRequest userRequest){
        try {
            User_Portfolio savedPortfolio = portfolioService.save(userRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPortfolio);
        }
        catch(Exception e){
            System.out.println("이미 존재하는 사용자를 대상으로는 호출할 수 없음");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    //특정 uid의 유저 포트폴리오 조회
    @GetMapping("/api/user/{uid}/portfolio")
    public ResponseEntity<User_Portfolio> findById(@PathVariable Long uid){
        try{
            User_Portfolio portRes = portfolioService.findById(uid);
            return ResponseEntity.status(HttpStatus.OK).body(portRes);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    //특정 uid의 유저 포트폴리오 수정
    @PutMapping("/api/user/{uid}/portfolio/update")
    public ResponseEntity<User_Portfolio> update(@PathVariable Long uid,
                                                 @RequestBody UpdatePortfolioRequest updateRequest){
        portfolioService.update(uid, updateRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(updateRequest.toEntity(uid));
    }

}
