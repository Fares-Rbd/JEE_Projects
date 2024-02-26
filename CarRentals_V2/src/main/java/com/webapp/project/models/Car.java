package com.webapp.project.models;

import java.io.Serializable;

public class Car implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int carId;
    private String make;
    private String model;
    private int year;
    private String color;
    private double price;
    private boolean available;

    public Car() { //empty constructor needed in order to make use of jsp:useBean
    	
    }

    public Car(int carId, String make, String model, int year, String color, double price, boolean available) {
        this.carId = carId;
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.price = price;
        this.available = available;
    }


	public int getCarId() {
		return carId;
	}


	public void setCarId(int carId) {
		this.carId = carId;
	}


	public String getMake() {
		return make;
	}


	public void setMake(String make) {
		this.make = make;
	}


	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	}


	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public boolean getAvailable() {
		return available;
	}


	public void setAvailable(boolean available) {
		this.available = available;
	}




   
}
