package DBTeam3.Abandonne.dto.response;

import DBTeam3.Abandonne.domain.Piece;
import DBTeam3.Abandonne.domain.User_Career;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class CareerWithPieceResponse {

    String position, organization;
    LocalDate startDate, endDate = null;

    String pieceName, composerName;

    public CareerWithPieceResponse(User_Career userCareer, Piece piece){
        this.position = userCareer.getPosition();
        this.organization = userCareer.getOrganization();
        this.startDate = userCareer.getStartDate();
        this.endDate = userCareer.getEndDate();
        this.pieceName = piece.getPieceName();
        this.composerName = piece.getComposerName();
    }
}
