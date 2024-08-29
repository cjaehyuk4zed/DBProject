package DBTeam3.Abandonne.domain;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
public class Concert_Program_Id implements Serializable{

    private long cid;
    private long pid;

    public boolean equals(Concert_Program_Id obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Concert_Program_Id uw = obj;
        return cid == uw.cid && pid == uw.pid;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }


}
