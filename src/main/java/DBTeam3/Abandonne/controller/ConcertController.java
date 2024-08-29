package DBTeam3.Abandonne.controller;

import DBTeam3.Abandonne.domain.Concert;
import DBTeam3.Abandonne.service.ConcertService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import DBTeam3.Abandonne.dto.response.ConcertResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController
@RequiredArgsConstructor
public class ConcertController {

    private final ConcertService concertService;

   @GetMapping("/api/user/{id}/recommended")
    public ResponseEntity<List<ConcertResponse>> recommendByPieces(@PathVariable Long id){
        try{
            List<ConcertResponse> concertResponseList = concertService.recommendByPieces(id);
            return (concertResponseList == null)?
                    ResponseEntity.status(HttpStatus.NO_CONTENT).body(null):
                    ResponseEntity.status(HttpStatus.OK).body(concertResponseList);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }

    @GetMapping("/api/concert/{cid}")
    public ResponseEntity<ConcertResponse> findById(@PathVariable Long cid){
        try{
            Concert concertResponse = concertService.findById(cid);
            return ResponseEntity.ok().body(new ConcertResponse(concertResponse));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


}