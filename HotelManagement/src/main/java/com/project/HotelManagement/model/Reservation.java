package com.project.HotelManagement.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @JoinColumn(name = "user_id")
    private int userId;
    @JoinColumn(name = "room_id")
    private int roomId;
    @Column(name = "arrival_date")
    private LocalDate arrivalDate;
    @Column(name = "departure_date")
    private LocalDate departureDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ReservationStatus status;


    public int getId() {
        return id;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int user_id) {
        this.userId = user_id;
    }
    public int getRoom() {
        return roomId;
    }
    public void setRoom(int room_id) {
        this.roomId = room_id;
    }
    public LocalDate getArrivalDate() {
        return arrivalDate;
    }
    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }
    public LocalDate getDepartureDate() {
        return departureDate;
    }
    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }
    public ReservationStatus getStatus() {
        return status;
    }
    public void setStatus(ReservationStatus status) {
        this.status = status;
    }
    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", user=" + userId +
                ", room=" + roomId +
                ", arrivalDate=" + arrivalDate +
                ", departureDate=" + departureDate +
                ", status=" + status +
                '}';
    }
}
