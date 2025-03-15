package com.CGIsuvepraktika.lennundus.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long flight_id;
    private double price;
    private String destination;
    private LocalDateTime departureTime;

    public Flight(){};

    public Flight(long flight_id, double price, String destination) {
        this.flight_id = flight_id;
        this.price = price;
        this.destination = destination;
    }

    public Flight(double price, String destination, LocalDateTime departureTime) {
        this.price = price;
        this.destination = destination;
        this.departureTime = departureTime;
    }

    public long getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(long flight_id) {
        this.flight_id = flight_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }
}
