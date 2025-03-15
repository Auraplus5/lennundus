package com.CGIsuvepraktika.lennundus.repository;

import com.CGIsuvepraktika.lennundus.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepo extends JpaRepository<Seat, Long> {
    @Query("SELECT s FROM Seat s WHERE s.flight.flight_id =?1")
    List<Seat> findSeatsByFlightId(long flight_id);
}
