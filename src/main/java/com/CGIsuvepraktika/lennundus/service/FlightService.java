package com.CGIsuvepraktika.lennundus.service;

import com.CGIsuvepraktika.lennundus.model.Flight;
import com.CGIsuvepraktika.lennundus.repository.FlightRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FlightService {

    private final FlightRepo flightRepo;

    public FlightService(FlightRepo flightRepo) {
        this.flightRepo = flightRepo;
    }

    public List<Flight> getFlights() {
        return flightRepo.findAll();
    }
    public List<Flight> getFlightByDestination(String destination) {
        return flightRepo.findFlightsByDestination(destination);
    }

    public void addFlight(Flight flight) {
        flightRepo.save(flight);
    }
    public List<Flight> getFlightByDepartureTime(LocalDateTime departureTime) {
        return flightRepo.findFlightsByDepartureTime(departureTime);
    }
    public List<Flight> getFlightByDestinationAndDepartureTime(String destination, LocalDateTime departureTime) {
        return flightRepo.findFlightsByDestinationAndDepartureTime(destination, departureTime);
    }
}
