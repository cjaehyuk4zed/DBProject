package DBTeam3.Abandonne.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class User_Wants_Id implements Serializable {

    private long uid;
    private long pid;

    // default constructor
    public User_Wants_Id (long uid, long pid) {
        this.uid = uid;
        this.pid = pid;
    }

    public boolean equals(User_Wants_Id obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User_Wants_Id uw = obj;
        return uid == uw.uid && pid == uw.pid;
    }

    @Override
    public int hashCode() {
        return
                super.hashCode();
    }
}
