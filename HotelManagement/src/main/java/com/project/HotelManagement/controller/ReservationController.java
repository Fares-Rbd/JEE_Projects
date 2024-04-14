package com.project.HotelManagement.controller;

import com.project.HotelManagement.model.Reservation;
import com.project.HotelManagement.model.ReservationStatus;
import com.project.HotelManagement.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.project.HotelManagement.service.ReservationService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;
    private final JwtService jwtService;

    @Autowired
    public ReservationController(ReservationService reservationService, JwtService jwtService) {
        this.reservationService = reservationService;
        this.jwtService = jwtService;
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservations = reservationService.getAllReservations();
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable int id) {
        Optional<Reservation> reservation = reservationService.getReservationById(id);
        return reservation.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Reservation>> getReservationsByUserId(@PathVariable int userId, HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring("Bearer ".length());
            int tokenUserId = jwtService.extractUserId(token);
            String role = jwtService.extractClaim(token, claims -> claims.get("role", String.class));

            // Check if the user is an ADMIN or if the user ID matches the requested user ID
            if ("ADMIN".equals(role) || tokenUserId == userId) {
                List<Reservation> reservations = reservationService.getReservationsByUserId(userId);
                System.out.println(reservations);
                return new ResponseEntity<>(reservations, HttpStatus.OK);
            } else {
                // Return 403 Forbidden if the user does not have sufficient privileges
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } else {
            // Handle cases where the Authorization header is missing or not in the expected format
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation, HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring("Bearer ".length());
            String role = jwtService.extractClaim(token, claims -> claims.get("role", String.class));

            // Check if the user is an ADMIN or if the reservation is for the same user
            if ("ADMIN".equals(role) || reservation.getUserId() == jwtService.extractUserId(token)) {
                Reservation newReservation = reservationService.createReservation(reservation);
                return new ResponseEntity<>(newReservation, HttpStatus.CREATED);
            } else {
                // Return 403 Forbidden if the user does not have sufficient privileges
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } else {
            // Handle cases where the Authorization header is missing or not in the expected format
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable int id, @RequestBody Reservation reservation) {
        Reservation updatedReservation = reservationService.updateReservation(id, reservation);
        return new ResponseEntity<>(updatedReservation, HttpStatus.OK);
    }
    @PutMapping("/confirm/{id}")
    public ResponseEntity<Reservation> confirmReservation(@PathVariable int id) {
        Reservation reservation = reservationService.getReservationById(id).get();
        reservation.setStatus(ReservationStatus.CONFIRMED);
        return new ResponseEntity<>(reservationService.updateReservation(id, reservation), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable int id) {
        reservationService.deleteReservation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
