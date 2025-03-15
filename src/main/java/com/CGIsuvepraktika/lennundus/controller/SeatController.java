package com.CGIsuvepraktika.lennundus.controller;

import com.CGIsuvepraktika.lennundus.model.Seat;
import com.CGIsuvepraktika.lennundus.repository.SeatRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class SeatController {
    private final SeatRepo seatRepo;

    public SeatController(SeatRepo seatRepo) {
        this.seatRepo = seatRepo;
    }

    @GetMapping("/flights/{id}/seats")
    public List<Seat> getSeatsByFlightId(@PathVariable long id) {
        return seatRepo.findSeatsByFlightId(id);
    }
    @PutMapping("/seats/{id}")
    public ResponseEntity<String> reserveSeat(@PathVariable long id) {
        Optional<Seat> seatOptional = seatRepo.findById(id);
        if (seatOptional.isPresent()) {
            Seat currentSeat = seatOptional.get();
            if(!currentSeat.isTaken()) {
                currentSeat.setTaken(true);
                seatRepo.save(currentSeat);
                return ResponseEntity.ok("Seat reserved");
            } else {
                return ResponseEntity.badRequest().body("Seat is already taken");
            }
        }
        return ResponseEntity.notFound().build();
    }
}
