package DBTeam3.Abandonne.service;

import DBTeam3.Abandonne.domain.*;
import DBTeam3.Abandonne.dto.response.ConcertResponse;
import DBTeam3.Abandonne.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConcertService {

    private final UserWantsRepository userWantsRepository;
    private final ConcertRepository concertRepository;
    private final ConcertProgramRepository concertProgramRepository;
    private final PieceRepository pieceRepository;
    private final ConcertHallRepository concertHallRepository;
    private final LocationsRepository locationsRepository;

    public Concert findById(long cid) {
        return concertRepository.findById(cid)
                .orElseThrow(() -> new IllegalArgumentException("not found : cid = " + cid));
    }

    public List<ConcertResponse> recommendByPieces(long id) {

        //이 id를 가진 사용자의 선호곡 리스트 뽑기
        List<User_Wants> userwantslist = userWantsRepository.findByUid(id);
        if (userwantslist.isEmpty()) return null;

        Iterator<User_Wants> iterator = userwantslist.iterator();

        //사용자의 각 선호곡 마다 그 선호곡을 공연하는 콘서트 리스트를 뽑아서 하나의 콘서트 리스트로 만들기
        List<Concert_Program> concertProgramList = new ArrayList<>();

        while (iterator.hasNext()) {
            long userwantpid = iterator.next().getPid();
            List<Concert_Program> concertListForThisPId =
                    concertProgramRepository.findByPid(userwantpid);

            concertProgramList.addAll(concertListForThisPId);

        }

        if (concertProgramList.isEmpty()) return null;

        //콘서트 리스트를 cid별로 그룹핑
        Map<Long, List<Concert_Program>> result
                = concertProgramList.stream().collect(Collectors.groupingBy(Concert_Program::getCid));

        List<concertwithcount> recommendCidList = new ArrayList();
        //추천되는 콘서트의 cid와 겹치는 선호곡 갯수 겍체 만 담기는 리스트

        Iterator<Long> iter = result.keySet().iterator();

        while (iter.hasNext()) {
            long key = iter.next();
            concertwithcount ct = new concertwithcount(key, result.get(key).size());
            recommendCidList.add(ct);
        }

        if (recommendCidList.isEmpty()) return null;

        Collections.sort(recommendCidList); //겹치는 선호곡 많은 순으로 정렬

        List<ConcertResponse> concertResponseList = new ArrayList<>();

        //정렬된 순서대로 concertResponse만들어서 concertResponseList에 다 담기
        int num = 0;
        for (concertwithcount key : recommendCidList) {
            if (num > 2) break;
            concertResponseList.add(makeConcertResponse(key.cid));
            num++;
        }
        if (concertResponseList.isEmpty()) return null;

        return concertResponseList;

    }


    //해당하는 cid에 관련된 정보 모아서 concertResponse 만들어주는 메서드
    public ConcertResponse makeConcertResponse(long cid) {

        Concert ct = concertRepository.findById(cid)
                .orElseThrow(() -> new IllegalArgumentException("not found : cid = " + cid));

        List<Concert_Program> programList = concertProgramRepository.findByCid(ct.getCid());

        List<Piece> pieceList = new ArrayList<>();

        for (Concert_Program program : programList) {
            Piece piece = pieceRepository.findById(program.getPid())
                    .orElseThrow(() -> new IllegalArgumentException("not found : pid = " + program.getPid()));
            pieceList.add(piece);
        }

        Concert_Hall hall = concertHallRepository.findById(ct.getHid())
                .orElseThrow(() -> new IllegalArgumentException("not found : id = " + ct.getHid()));
        Location location = locationsRepository.findById(hall.getHallLocationId())
                .orElseThrow(() -> new IllegalArgumentException("not found : lid = " + hall.getHallLocationId()));
        ConcertResponse concertResponse = new ConcertResponse(ct, pieceList, hall, location);
        return concertResponse;

    }

    // 등록된 선호곡이 없는 경우, 랜덤으로 공연 추천
    public List<ConcertResponse> getRandomConcerts() {

        List<ConcertResponse> responses = new ArrayList<>();

        Random rnd = new Random();
        long boundary = concertRepository.count();
        List<Long> avoidDuplicated = new ArrayList<>();

        for (int i = 0; i < 3; ) {
            long rndValue = rnd.nextLong(boundary) + 1;
            if (!avoidDuplicated.contains(rndValue)) {
                avoidDuplicated.add(rndValue);
                responses.add(makeConcertResponse(rndValue));
                i++;
            }
        }
        return responses;
    }
}


class concertwithcount implements Comparable<concertwithcount> {

    long cid;
    int num;

    concertwithcount(long cid, int num) {
        this.cid = cid;

        this.num = num;

    }

    public int compareTo(concertwithcount c) {
        if (this.num > c.num)
            return -1;
        else if (this.num == c.num)
            return 0;
        else
            return 1;
    }

}

