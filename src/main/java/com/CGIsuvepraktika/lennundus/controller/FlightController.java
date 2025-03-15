package com.CGIsuvepraktika.lennundus.controller;

import com.CGIsuvepraktika.lennundus.model.Flight;
import com.CGIsuvepraktika.lennundus.service.FlightService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
public class FlightController {
    final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/flights")
    public List<Flight> getFlights(
            @RequestParam(required = false) String destination,
            @RequestParam(required = false) LocalDateTime departureTime,
            @RequestParam(required = false) LocalTime duration) {
        if (destination != null && departureTime != null) {
            return flightService.getFlightByDestinationAndDepartureTime(destination, departureTime);
        } else if (destination != null) {
            return flightService.getFlightByDestination(destination);
        } else if (departureTime != null) {
            return flightService.getFlightByDepartureTime(departureTime);
        } else if (duration != null) {
            return flightService.getFlightByDuration(duration);
        } else {
            return flightService.getFlights();
        }
    }

    @GetMapping("/flights/{id}")
    public Flight getFlightById(@PathVariable long id) {
        return flightService.getFlightById(id).orElse(null);
    }

    /*
    @GetMapping("/flights/byDestination/{destination}")
    public List<Flight> getFlightsByDestination(@PathVariable String destination){
        return flightService.getFlightByDestination(destination);
    }
    @GetMapping("/flights/byDepartureTime")
    public List<Flight> getFlightsByDepartureTime(@RequestParam("departureTime") LocalDateTime departureTime){
        return flightService.getFlightByDepartureTime(departureTime);
    }
     */
    @PostMapping("/flights")
    public void addFlight(@RequestBody Flight flight, @RequestParam int rowCount, @RequestParam int seatPerRow) {
        //flightService.addFlight(flight);
        flightService.createFlight(flight, rowCount, seatPerRow);
    }


}
