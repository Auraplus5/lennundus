package com.CGIsuvepraktika.lennundus.controller;

import com.CGIsuvepraktika.lennundus.model.Flight;
import com.CGIsuvepraktika.lennundus.service.FlightService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class FlightController {
    final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }
    @GetMapping("/flights")
    public List<Flight> getFlights() {
        return flightService.getFlights();
    }
    @GetMapping("/flights/{destination}")
    public List<Flight> getFlightsByDestination(@PathVariable String destination){
        return flightService.getFlightByDestination(destination);
    }
    @PostMapping("/flights")
    public void addFlight(@RequestBody Flight flight){
        flightService.addFlight(flight);
    }


}
