package com.project.HotelManagement.repository;

import com.project.HotelManagement.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationsRepository extends JpaRepository<Reservation, Integer> {
    List<Reservation> findByUserId(int userId);
    List<Reservation> findByRoomId(int id);
}
