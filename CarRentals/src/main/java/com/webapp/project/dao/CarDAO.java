package com.webapp.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.webapp.project.model.Car;

public class CarDAO {

	private Connection conn ;

	public CarDAO(Connection connection) {
		this.conn = connection;
	}

	public List<Car> getAllCars() throws SQLException {
		List<Car> cars = new ArrayList<>();

		try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM cars");
				ResultSet resultSet = preparedStatement.executeQuery()) {
			while (resultSet.next()) {
				int id = resultSet.getInt("car_id");
				String make = resultSet.getString("make");
				String model = resultSet.getString("model");
				int year = resultSet.getInt("year");
				String color = resultSet.getString("color");
				double price = resultSet.getDouble("price");
				boolean available = resultSet.getBoolean("available");

				Car car = new Car(id, make, model, year, color, price, available);
				cars.add(car);
			}
		}

		return cars;
	}
	public  Car getCarById(int carId) throws SQLException {
        Car car = null;

        String query = "SELECT * FROM cars WHERE car_id = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, carId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("car_id");
                    String make = resultSet.getString("make");
                    String model = resultSet.getString("model");
                    int year = resultSet.getInt("year");
                    String color = resultSet.getString("color");
                    double price = resultSet.getDouble("price");
                    boolean available = resultSet.getBoolean("available");

                    car = new Car(id, make, model, year, color, price, available);
                }
            }
        }

        return car;
    }
}
