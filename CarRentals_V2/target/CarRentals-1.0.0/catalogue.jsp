<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.webapp.project.model.Car"%>
<%@ page import="com.webapp.project.dao.CarDAO"%>
<%@ page import="java.sql.Connection"%>
<%--<%@ page import="java.sql.SQLException"%>--%>
<%@ page import="java.util.ArrayList"%>
<%--<%@ page import="java.sql.Array" %>--%>

<%-- <jsp:useBean id="cars" class="java.util.List<Car>" scope="request" /> --%>
<!-- Import JSTL core library -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Car Catalogue</title>
<!-- Bootstrap CSS -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container">
		<h1>Car Catalogue</h1>
		<div class="row">

			<!-- Declare Car bean for each iteration -->
			<%
			CarDAO carDAO = new CarDAO((Connection) application.getAttribute("db_connection"));
			List<Car> cars = carDAO.getAllCars();
			%>

			<jsp:useBean id="car" class="com.webapp.project.model.Car"
				scope="page" />

			<% for (Car currentCar : cars) { %>
				<!-- Set properties of the Car bean -->
				<jsp:setProperty name="car" property="carId"
					value="<%= currentCar.getCarId() %>" />
				<jsp:setProperty name="car" property="make"
					value="<%= currentCar.getMake() %>" />
				<jsp:setProperty name="car" property="model"
					value="<%= currentCar.getModel() %>" />
				<jsp:setProperty name="car" property="year"
					value="<%= currentCar.getYear() %>" />
				<jsp:setProperty name="car" property="color"
					value="<%= currentCar.getColor() %>" />
				<jsp:setProperty name="car" property="price"
					value="<%= currentCar.getPrice() %>" />
				<jsp:setProperty name="car" property="available"
					value="<%= currentCar.getAvailable() %>" />

				<!-- Display the Car information -->
				<div class="card">
					<div class="card-body">
						<h5 class="card-title"><jsp:getProperty name="car"
								property="make" />
							<jsp:getProperty name="car" property="model" /></h5>
						<p class="card-text">
							Year:
							<jsp:getProperty name="car" property="year" /></p>
						<p class="card-text">
							Color:
							<jsp:getProperty name="car" property="color" /></p>
						<p class="card-text">
							Price: $<jsp:getProperty name="car" property="price" /></p>
						<p class="card-text">
							Availability:
							<jsp:getProperty name="car" property="available" /></p>
						<a
							href="CarPage?carId=<jsp:getProperty name='car' property='carId' />"
							class="btn btn-primary">View Details</a>
					</div>
				</div>
			<% } %>

		</div>
	</div>
</body>
</html>
