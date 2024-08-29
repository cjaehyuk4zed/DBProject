package DBTeam3.Abandonne.service;

import DBTeam3.Abandonne.domain.Piece;
import DBTeam3.Abandonne.domain.User_Career;
import DBTeam3.Abandonne.dto.AddCareerRequest;
import DBTeam3.Abandonne.dto.UpdateCareerRequest;
import DBTeam3.Abandonne.repository.PieceRepository;
import DBTeam3.Abandonne.repository.UserCareerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CareerService {

    private final UserCareerRepository userCareerRepository;
    private final PieceRepository pieceRepository;

    //Create Career
    public User_Career saveCareer(long uid, AddCareerRequest careerRequest) {

        String pieceName = careerRequest.getPieceName();
        String composerName = careerRequest.getComposerName();

        Piece piece = pieceRepository.findByPieceNameAndComposerName(pieceName, composerName);

        if (piece == null) //곡 명과 작곡가 명이 일치하는 곡이 db에 있지 않은 곡일 경우
            throw new IllegalArgumentException("Piece not found");

        //곡명과 작곡가 명이 일치하는 곡이 db에 있으면 pid가져오기
        long pid = piece.getPid();
        User_Career saveCareer = careerRequest.toEntity(uid, pid);
        return userCareerRepository.save(saveCareer);

    }


    //Retrieve all Career by uid
    public List<User_Career> findByUid(Long uid) {

        return userCareerRepository.findByUid(uid);
    }

    //Retrieve career by cid
    public User_Career findById(Long cid) {
        return userCareerRepository.findById(cid)
                .orElseThrow(() -> new IllegalArgumentException("Target career not found : " + cid));
    }

    //UPDATE career with {cid}
    @Transactional
    public User_Career update(long cid, long uid, UpdateCareerRequest ucr) {
        User_Career userCareer = userCareerRepository.findById(cid)
                .orElseThrow(() -> new IllegalArgumentException("Target career not found : " + cid));
        userCareer.update(ucr);
        return ucr.toEntity(cid, uid);
    }


    //Delete career by cid
    public void deleteByCid(Long cid) {
        try {
            userCareerRepository.deleteById(cid);
        } catch (Exception e) {
            throw e;
        }
    }
}
