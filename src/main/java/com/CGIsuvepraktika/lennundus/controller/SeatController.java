package com.CGIsuvepraktika.lennundus.controller;

import com.CGIsuvepraktika.lennundus.model.Seat;
import com.CGIsuvepraktika.lennundus.repository.SeatRepo;
import com.CGIsuvepraktika.lennundus.service.SeatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class SeatController {
    private final SeatRepo seatRepo;
    private final SeatService seatService;

    public SeatController(SeatRepo seatRepo, SeatService seatService) {
        this.seatRepo = seatRepo;
        this.seatService = seatService;
    }

    @GetMapping("/flights/{id}/seats")
    public List<Seat> getSeatsByFlightId(@PathVariable long id) {
        return seatRepo.findSeatsByFlightId(id);
    }

    @PutMapping("/seats/reserve")
    public ResponseEntity<?> reserveSeats(@RequestBody Map<String, List<Long>> request) {
        try {
            List<Long> seatIds = request.get("seatIds");
            List<Seat> reservedSeats = seatService.reserveSeats(seatIds);
            return ResponseEntity.ok(reservedSeats);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
