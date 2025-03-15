package com.CGIsuvepraktika.lennundus.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seat_id;
    private int rowNum;
    private Character seatChar;
    private boolean isTaken;
    private boolean isWindow;
    private boolean isCloseToExit;
    private boolean isMoreLegroom;


    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    @JsonBackReference
    private Flight flight;

    public Seat() {
    }

    public Seat(int rowNum, Character seatChar, boolean isTaken, boolean isWindow, boolean isCloseToExit, boolean isMoreLegroom, Flight flight) {
        this.rowNum = rowNum;
        this.seatChar = seatChar;
        this.isTaken = isTaken;
        this.isWindow = isWindow;
        this.isCloseToExit = isCloseToExit;
        this.isMoreLegroom = isMoreLegroom;
        this.flight = flight;
    }

    public long getSeat_id() {
        return seat_id;
    }

    public void setSeat_id(long seat_id) {
        this.seat_id = seat_id;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public Character getSeatChar() {
        return seatChar;
    }

    public void setSeatChar(Character seatChar) {
        this.seatChar = seatChar;
    }

    public boolean isTaken() {
        return isTaken;
    }

    public void setTaken(boolean taken) {
        isTaken = taken;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public boolean isWindow() {
        return isWindow;
    }

    public void setWindow(boolean window) {
        isWindow = window;
    }

    public boolean isCloseToExit() {
        return isCloseToExit;
    }

    public void setCloseToExit(boolean closeToExit) {
        isCloseToExit = closeToExit;
    }

    public boolean isMoreLegroom() {
        return isMoreLegroom;
    }

    public void setMoreLegroom(boolean moreLegroom) {
        isMoreLegroom = moreLegroom;
    }
}
