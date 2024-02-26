<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<jsp:useBean id="Car" class="com.webapp.project.model.Car" />
	<p class="card-text">
		<jsp:setProperty name="Car" property="year" value="2020" />
		<jsp:setProperty name="Car" property="make" value="mana3rch" />
		<jsp:setProperty name="Car" property="color" value="a7mer" />
		<jsp:setProperty name="Car" property="price" value="7050" />
		<jsp:setProperty name="Car" property="available" value="1" />
		Year:
		<jsp:getProperty name="Car" property="year" /></p>
	<p class="card-text">
		Color:
		<jsp:getProperty name="Car" property="color" /></p>
	<p class="card-text">
		Price: $<jsp:getProperty name="Car" property="price" /></p>
	<p class="card-text">
		Availability:
		<jsp:getProperty name="Car" property="available" /></p>
		
		<p class="card-text">
		Make:
		<jsp:getProperty name="Car" property="make" /></p>
	<a href="CarPage?carId=<jsp:getProperty name='Car' property='carId' />"
	
	
		class="btn btn-primary">View Details</a>²
</body>
</html>