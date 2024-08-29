package DBTeam3.Abandonne.service;

import DBTeam3.Abandonne.domain.*;
import DBTeam3.Abandonne.dto.*;
import DBTeam3.Abandonne.repository.PieceRepository;
import DBTeam3.Abandonne.repository.UserWantsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserWantsService {

    private final UserWantsRepository userWantsRepository;
    private final PieceRepository pieceRepository;

    //Create new User wants
    public User_Wants save(Long uid, AddFavPieceRequest request){

        Piece piece = pieceRepository
                .findByPieceNameAndComposerName(request.getPieceName(), request.getComposerName());
        return userWantsRepository.save( new User_Wants(uid, piece.getPid()) );
    }

    //Retrieve all user wants by uid
    public List<User_Wants> findByUid(Long uid){
        return userWantsRepository.findByUid(uid);
    }
    public List<User_Wants> findByPid(Long pid) { return userWantsRepository.findByPid(pid); }

    //Update user wants
    @Transactional
    public void update(User_Wants_Id keys, UpdateUserWantRequest updateRequest){
        User_Wants targetWants = userWantsRepository.findById(keys)
                .orElseThrow();
        targetWants.update(updateRequest.toEntity());
    }



    //Delete user_wants by ids{uid, pid}
    public void deleteById(User_Wants_Id ids){
        userWantsRepository.deleteById(ids);
    }


    public List<Piece> getPieceInformation(String pieceName, String composerName){

        List<Piece> targetPieces = new ArrayList<>();

        if(pieceName != null && composerName != null){
                targetPieces.add(pieceRepository
                        .findByPieceNameAndComposerName(pieceName, composerName));
        }
        if(pieceName == null)
            targetPieces = pieceRepository.findByComposerName(composerName);
        if(composerName == null)
            targetPieces = pieceRepository.findByPieceName(pieceName);

       return targetPieces;
    }
}
