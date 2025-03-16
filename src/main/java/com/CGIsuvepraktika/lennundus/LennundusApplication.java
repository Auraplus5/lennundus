package com.CGIsuvepraktika.lennundus;

import com.CGIsuvepraktika.lennundus.model.Flight;
import com.CGIsuvepraktika.lennundus.service.FlightService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.LocalTime;

@SpringBootApplication
public class LennundusApplication {

	public static void main(String[] args) {
		SpringApplication.run(LennundusApplication.class, args);
	}
	@Bean
	public CommandLineRunner run(FlightService flightService){
		return args -> {
			Flight flight1 = new Flight(1000, "New York", LocalDateTime.of(2025, 6, 1, 12, 0), LocalTime.of(3, 0));
			Flight flight2 = new Flight(2000, "London", LocalDateTime.of(2025, 6, 2, 12, 0), LocalTime.of(2, 0));
			Flight flight3 = new Flight(3000, "Paris", LocalDateTime.of(2025, 6, 3, 12, 0), LocalTime.of(5, 30));

			flightService.createFlight(flight1, 10, 6);
			flightService.createFlight(flight2, 8, 4);
			flightService.createFlight(flight3, 10, 6);
		};
	}

}
