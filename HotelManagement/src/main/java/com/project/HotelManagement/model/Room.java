package com.project.HotelManagement.model;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "num")
    private String number;
    @Column(name = "type")
    private String type;
    @Column(name = "price")
    private int price;
    @Column(name = "capacity")
    private int capacity;
    @Column(name = "description")
    private String description;
    @Column(name = "availability")
    private LocalDate availability;


    public int getId() {
        return id;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public LocalDate getAvailability() {
        return availability;
    }
    public void setAvailability(LocalDate availability) {
        this.availability = availability;
    }
}
