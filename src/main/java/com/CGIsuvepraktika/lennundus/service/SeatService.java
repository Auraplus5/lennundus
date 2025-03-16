package com.CGIsuvepraktika.lennundus.service;

import com.CGIsuvepraktika.lennundus.model.Seat;
import com.CGIsuvepraktika.lennundus.repository.SeatRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {
    private final SeatRepo seatRepo;

    public SeatService(SeatRepo seatRepo) {
        this.seatRepo = seatRepo;
    }

    @Transactional
    public List<Seat> reserveSeats(List<Long> seatIds) {
        List<Seat> seats = seatRepo.findAllById(seatIds);
        for (Seat seat : seats) {
            if(seat.isTaken()) {
                throw new IllegalStateException("Seat is already taken");
            } else {
                seat.setTaken(true);
            }
        }
        return seatRepo.saveAll(seats);
    }
}
