package com.project.HotelManagement.repository;

import com.project.HotelManagement.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    List<Room> findByType(String type);
    List<Room>  findByPrice(float price);
    List<Room>  findByCapacity(int capacity);
    List<Room>  findByAvailability(LocalDate Availability);

}
