package com.CGIsuvepraktika.lennundus.controller;

import com.CGIsuvepraktika.lennundus.model.Flight;
import com.CGIsuvepraktika.lennundus.service.FlightService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class FlightController {
    final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/flights/{id}")
    public Flight getFlightById(@PathVariable long id) {
        return flightService.getFlightById(id).orElse(null);
    }
    @GetMapping("/flights")
    public List<Flight> getFlights() {
        return flightService.getFlights();
    }

    @PostMapping("/flights")
    public void addFlight(@RequestBody Flight flight, @RequestParam int rowCount, @RequestParam int seatPerRow) {
        flightService.createFlight(flight, rowCount, seatPerRow);
    }


}
