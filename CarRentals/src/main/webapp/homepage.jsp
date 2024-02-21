<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Car Rental Service</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="styles.css"> <!-- Assuming you have a separate CSS file for additional styling -->
</head>
<body>
    <header>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="#">Car Rental Service</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="Catalogue">Catalogue</a>
                    </li>
                </ul>
                <!-- Check if the user is logged in -->
                <% if (request.getSession().getAttribute("loggedInUser") != null) { %>
                    <span class="navbar-text ml-auto">
                        Welcome, <%= request.getSession().getAttribute("loggedInUser") %>
                    </span>
                <% } else { %>
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link" href="login.jsp">Login</a>
                        </li>
                    </ul>
                <% } %>
                <!-- Add more navigation links as needed -->
            </div>
        </nav>
    </header>
    <div class="container mt-4">
        <section id="description">
            <h1>Welcome to our Car Rental Service</h1>
            <p class="lead">Our car rental service offers a wide range of vehicles for your needs. Whether you're looking for a compact car for city driving or a spacious SUV for a family road trip, we've got you covered.</p>
            <p class="lead">Explore our catalog to find the perfect car for your next adventure!</p>
        </section>
    </div>
    <footer class="mt-4 bg-light text-center py-3">
        <p>&copy; 2024 Car Rental Service</p>
    </footer>

    <!-- Bootstrap JS (optional) -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
