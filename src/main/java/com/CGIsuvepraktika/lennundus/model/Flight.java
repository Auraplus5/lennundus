package com.CGIsuvepraktika.lennundus.model;

import jakarta.persistence.*;

@Entity
@Table
public class Flight {
    @Id
    @SequenceGenerator(
            name = "flight_sequence",
            sequenceName = "flight_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "flight_sequence"
    )
    private long flight_id;
    private int price;
    private String destination;

    public Flight(){};

    public Flight(long flight_id, int price, String destination) {
        this.flight_id = flight_id;
        this.price = price;
        this.destination = destination;
    }

    public Flight(int price, String destination) {
        this.price = price;
        this.destination = destination;
    }

    public long getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(long flight_id) {
        this.flight_id = flight_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

}
