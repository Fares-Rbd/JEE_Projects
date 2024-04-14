package com.project.HotelManagement.service;

import com.project.HotelManagement.model.Reservation;
import com.project.HotelManagement.model.Room;
import com.project.HotelManagement.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.HotelManagement.repository.ReservationsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    private final ReservationsRepository reservationsRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public ReservationService(ReservationsRepository reservationsRepository, RoomRepository roomRepository) {
        this.reservationsRepository = reservationsRepository;
        this.roomRepository = roomRepository;}
    public List<Reservation> getAllReservations() {
        return reservationsRepository.findAll();
    }
    public Optional<Reservation> getReservationById(int id) {
        return reservationsRepository.findById(id);
    }
    public Reservation createReservation(Reservation reservation) {

        Optional<Room> room = roomRepository.findById(reservation.getRoom());
        room.get().setAvailability(reservation.getDepartureDate().plusDays(1)); //room will be available
                                                                                        // the day after the departure date
        return reservationsRepository.save(reservation);
    }
    public void deleteReservation(int id) {
        reservationsRepository.deleteById(id);
    }
    public Reservation updateReservation(int id, Reservation newReservation) {
        return reservationsRepository.findById(id).map(reservation -> {
            reservation.setUserId(newReservation.getUserId());
            reservation.setRoom(newReservation.getRoom());
            reservation.setArrivalDate(newReservation.getArrivalDate());
            reservation.setDepartureDate(newReservation.getDepartureDate());
            reservation.setStatus(newReservation.getStatus());
            return reservationsRepository.save(reservation);
        }).orElse(null);
    }
    public List<Reservation> getReservationsByUserId(int userId) {
        return reservationsRepository.findByUserId(userId);
    }
    public void deleteReservationsByRoomId(int id) {
        List<Reservation> reservations = reservationsRepository.findByRoomId(id);
        for (Reservation reservation : reservations) {
            reservationsRepository.delete(reservation);
        }
    }
    public void deleteReservationsByUserId(int userId) {
        List<Reservation> reservations = reservationsRepository.findByUserId(userId);

        for (Reservation reservation : reservations) {
            reservationsRepository.delete(reservation);
        }
    }
}
