package com.CGIsuvepraktika.lennundus.service;

import com.CGIsuvepraktika.lennundus.model.Flight;
import com.CGIsuvepraktika.lennundus.model.Seat;
import com.CGIsuvepraktika.lennundus.repository.FlightRepo;
import com.CGIsuvepraktika.lennundus.repository.SeatRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    private final FlightRepo flightRepo;
    private final SeatRepo seatRepo;

    public FlightService(FlightRepo flightRepo, SeatRepo seatRepo) {
        this.flightRepo = flightRepo;
        this.seatRepo = seatRepo;
    }

    public Flight createFlight(Flight flight, int rowCount, int seatPerRow) {
        flight = flightRepo.save(flight);
        List<Seat> seats = new ArrayList<>();

        for (int i = 1; i <= rowCount; i++) {
            for (char j = 'A'; j < 'A' + seatPerRow; j++) {
                Seat newSeat = new Seat();
                newSeat.setRowNum(i);
                newSeat.setSeatChar(j);
                newSeat.setTaken(Math.random() < 0.3);
                newSeat.setWindow(j == 'A' || j == 'A' + seatPerRow - 1);
                newSeat.setCloseToExit(i == 1 || i == rowCount);
                newSeat.setMoreLegroom(i == 1 || i == rowCount || i == rowCount / 2);
                newSeat.setFlight(flight);
                seats.add(newSeat);
            }
        }
        seatRepo.saveAll(seats);
        return flight;
    }

    public List<Flight> getFlights() {
        return flightRepo.findAll();
    }
    public Optional<Flight> getFlightById(long id) {
        return flightRepo.findById(id);
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

    public List<Flight> getFlightByDuration(LocalTime duration) {
        return flightRepo.findFlightsByDuration(duration);
    }

    public List<Flight> getAllFlights() {
        return flightRepo.findAll();
    }
}
