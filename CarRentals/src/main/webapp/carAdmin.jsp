<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.webapp.project.model.Car" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Car Details</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h1 >Car Details [ADMIN VIEW]</h1>
        <% Car car = (Car) request.getAttribute("car"); %>
        <div class="card">
            <div class="card-body">
                <h5 class="card-title"><%= car.getMake() %> <%= car.getModel() %></h5>
                <p class="card-text">Year: <%= car.getYear() %></p>
                <p class="card-text">Color: <%= car.getColor() %></p>
                <p class="card-text">Price: $<%= car.getPrice() %></p>
                <p class="card-text">Availability: <%= car.isAvailable() ? "Available" : "Not Available" %></p>
                <button type="submit" class="btn btn-primary">Edit</button>
                
            </div>
        </div>
    </div>
</body>
</html>
