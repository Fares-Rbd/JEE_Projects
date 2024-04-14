package com.project.HotelManagement.service;

import com.project.HotelManagement.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.HotelManagement.repository.RoomRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }
    public Optional<Room> getRoomById(int id) {
        return roomRepository.findById(id);
    }

    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }
    public void deleteRoom(int id) {
        roomRepository.deleteById(id);
    }

    public Room updateRoom(int id, Room newRoom) {
        return roomRepository.findById(id).map(room -> {
            room.setNumber(newRoom.getNumber());
            room.setType(newRoom.getType());
            room.setPrice(newRoom.getPrice());
            room.setCapacity(newRoom.getCapacity());
            room.setDescription(newRoom.getDescription());
            room.setAvailability(newRoom.getAvailability());
            return roomRepository.save(room);
        }).orElse(null);
    }
    public List<Room>  findByType(String type){
        return roomRepository.findByType(type);
    }
    public List<Room>  findByPrice(float price){
        return roomRepository.findByPrice(price);
    }
    public List<Room>  findByCapacity(int capacity){
        return roomRepository.findByCapacity(capacity);
    }
    public List<Room>  findByAvailability(LocalDate availability){
        return roomRepository.findByAvailability(availability);
    }
}
