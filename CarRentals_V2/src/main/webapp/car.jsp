<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.webapp.project.models.Car"%>
<%@ page import="com.webapp.project.dao.CarDAO"%>
<%@ page import="java.sql.Connection"%>

<%-- <jsp:useBean id="cars" class="java.util.List<Car>" scope="request" /> --%>
<!-- Import JSTL core library -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Car Details</title>
<!-- Bootstrap CSS -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>


	<%
	Connection dataSource = (Connection) application.getAttribute("db_connection");
		CarDAO carDAO = new CarDAO(dataSource);
		Car currentCar = carDAO.getCarById(Integer.parseInt(session.getAttribute("carId").toString()));
	%>
	<div class="container">
		<h1>Car Details :</h1>
		<jsp:useBean id="car" class="com.webapp.project.models.Car"
			scope="request" />
		<jsp:setProperty name="car" property="carId"
			value="<%=currentCar.getCarId()%>" />
		<jsp:setProperty name="car" property="make"
			value="<%=currentCar.getMake()%>" />
		<jsp:setProperty name="car" property="model"
			value="<%=currentCar.getModel()%>" />
		<jsp:setProperty name="car" property="year"
			value="<%=currentCar.getYear()%>" />
		<jsp:setProperty name="car" property="color"
			value="<%=currentCar.getColor()%>" />
		<jsp:setProperty name="car" property="price"
			value="<%=currentCar.getPrice()%>" />
		<jsp:setProperty name="car" property="available"
			value="<%=currentCar.getAvailable()%>" />
		<jsp:setProperty name="car" property="model" 
		value="<%=currentCar.getModel()%>"/>
		<div class="card">
			<div class="card-body">
				<h5 class="card-title"><jsp:getProperty name="car"
						property="make" /></h5>
				<p class="card-text">
					Model :
					<jsp:getProperty name="car" property="model" />
				<p class="card-text">
					Year:
					<jsp:getProperty name="car" property="year" /></p>
				<p class="card-text">
					Color:
					<jsp:getProperty name="car" property="color" /></p>
				<p class="card-text">
					Price: $<jsp:getProperty name="car" property="price" />/Day
				</p>
				<p class="card-text">
					Availability:
					<jsp:getProperty name="car" property="available" /></p>
				<button type="submit" class="btn btn-primary">Rent Now</button>

			</div>
		</div>
	</div>
</body>
</html>
