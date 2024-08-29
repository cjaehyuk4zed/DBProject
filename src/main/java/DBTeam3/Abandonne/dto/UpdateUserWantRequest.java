package DBTeam3.Abandonne.dto;

import DBTeam3.Abandonne.domain.*;
import lombok.Getter;

@Getter
public class UpdateUserWantRequest {

    long uid, pid;

    Piece piece;
    public User_Wants toEntity(){
        return new User_Wants(uid, pid);
    }
}
