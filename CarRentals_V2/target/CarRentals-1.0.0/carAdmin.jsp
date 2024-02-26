<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.webapp.project.model.Car"%>
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
	Car currentCar = carDAO.getCarById(Integer.parseInt(request.getAttribute("carId")));
	%>
	<div class="container">
		<h1>Car Details [ADMIN VIEW]</h1>
		<jsp:useBean id="car" class="com.webapp.project.model.Car"
			scope="request" />
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
				<button type="submit" class="btn btn-primary">Edit</button>

			</div>
		</div>
	</div>
</body>
</html>
