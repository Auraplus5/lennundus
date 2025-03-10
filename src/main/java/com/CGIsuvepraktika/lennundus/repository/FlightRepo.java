package com.CGIsuvepraktika.lennundus.repository;

import com.CGIsuvepraktika.lennundus.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepo extends JpaRepository<Flight, Long> {
    @Query("SELECT f FROM Flight f WHERE f.destination =?1")
    List<Flight> findFlightsByDestination(String destination);
}
