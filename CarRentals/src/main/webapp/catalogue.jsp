<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
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
			<%
			for (com.webapp.project.model.Car car : (List<com.webapp.project.model.Car>) request.getAttribute("cars")) {
			%>
			<div class="col-md-4 mb-4">
				<div class="card">

					<div class="card-body">
						<h5 class="card-title"><%=car.getMake()%>
							<%=car.getModel()%></h5>
						<p class="card-text">
							Year:
							<%=car.getYear()%></p>
						<p class="card-text">
							Color:
							<%=car.getColor()%></p>
						<p class="card-text">
							Price: $<%=car.getPrice()%></p>
						<p class="card-text">
							Availability:
							<%=car.isAvailable() ? "Available" : "Not Available"%></p>
						
						<a href="CarPage?carId=<%=car.getCarId()%>"
							class="btn btn-primary">View Details</a>
					</div>
				</div>
			</div>
			<%
			}
			%>
		</div>
	</div>
</body>
</html>
