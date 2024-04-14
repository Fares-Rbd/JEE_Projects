package com.project.HotelManagement.controller;

import com.project.HotelManagement.model.User;
import com.project.HotelManagement.repository.UserRepository;
import com.project.HotelManagement.service.JwtService;
import com.project.HotelManagement.service.ReservationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.project.HotelManagement.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final JwtService jwtService;
    private final UserService userService;
    private final UserRepository userRepository;
    private final ReservationService reservationService;
    private final PasswordEncoder passwordEncoder; // set to be a BCryptPasswordEncoder in the
                                                   // WebSecurityConfiguration class

    @Autowired //auto-injection of dependency (in this case the userService class)
    public UserController(JwtService jwtService, UserService userService,
                          UserRepository userRepository,
                          ReservationService reservationService,
                          PasswordEncoder passwordEncoder) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.userRepository = userRepository;
        this.reservationService = reservationService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id, HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring("Bearer ".length());
            int userId = jwtService.extractUserId(token);
            String role = jwtService.extractClaim(token, claims -> claims.get("role", String.class));

            // Check if the user is an ADMIN or if the user ID matches the requested ID
            if ("ADMIN".equals(role) || userId == id) {
                Optional<User> user = userService.getUserById(id);
                return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
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
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
        if(user.getPassword().isEmpty())
            user.setPassword(userRepository.findById(id).get().getPassword());
        else
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        User updatedUser = userService.updateUser(id, user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        // Delete reservations associated with the user
        reservationService.deleteReservationsByUserId(id);

        // Delete the user
        userService.deleteUser(id);



        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/searchByEmail")
    public ResponseEntity<User> searchUserByEmail(@RequestParam String email) {
        Optional<User> user = userService.findByEmail(email);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/searchByName")
    public ResponseEntity<User> searchUserByName(@RequestParam String name) {
        Optional<User> user = userService.findByName(name);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


}
