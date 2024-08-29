package DBTeam3.Abandonne.dto.response;


import DBTeam3.Abandonne.domain.*;
import lombok.Getter;
import java.util.List;
import java.util.ArrayList;

import java.time.LocalDate;

@Getter
public class ConcertResponse {

    //Data from Concert
    long cid;
    String concertName;
    java.sql.Timestamp time;
    LocalDate date = null;
    int price;

    //Data from Piece
    List<Piece> pieceList;

    //Data from Concert_hall and location
    String hallName;
    String province;
    String city;
    String hallAddress;

    public ConcertResponse(Concert concert){
            this.cid = concert.getCid();
            this.concertName = concert.getConcertName();
            this.time = concert.getTime();
            this.date = concert.getDate();
            this.price = concert.getPrice();
    }

    public ConcertResponse(Concert concert, Concert_Hall hall){
        this.cid = concert.getCid();
        this.concertName = concert.getConcertName();
        this.time = concert.getTime();
        this.date = concert.getDate();
        this.price = concert.getPrice();
        this.hallName = hall.getHallName();
        this.hallAddress = hall.getHallAddress();
    }


    public ConcertResponse(Concert concert, List<Piece> pl, Concert_Hall hall, Location location){

        this.cid = concert.getCid();
        this.concertName = concert.getConcertName();
        this.time = concert.getTime();
        this.date = concert.getDate();
        this.price = concert.getPrice();

        if(pl == null) //공연곡이 하나도 없는 경우
        {
            Piece piece= new Piece();
            this.pieceList = new ArrayList<>();
            this.pieceList.add(piece);
        }
        else this.pieceList = pl;

        this.hallName = hall.getHallName();
        this.province = location.getProvince();
        this.city = location.getCity();
        this.hallAddress = hall.getHallAddress();

    }

}






