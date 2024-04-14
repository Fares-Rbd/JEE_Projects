import React, { useState, useEffect } from "react";
import {  useNavigate } from "react-router-dom";
import "./Rooms.css";
import { toast } from "react-toastify";


function Rooms() {
  const navigate = useNavigate();
  const [rooms, setRooms] = useState([]);
  const [filteredRooms, setFilteredRooms] = useState([]);
  const [error, setError] = useState("");
  const [capacityFilter, setCapacityFilter] = useState("");
  const [availabilityDateFilter, setAvailabilityDateFilter] = useState("");
  const [priceFilter, setPriceFilter] = useState("");

  useEffect(() => {
    const fetchRooms = async () => {
      try {
        const jwtToken = localStorage.getItem("token");
        const response = await fetch("http://localhost:8080/api/rooms", {
          method: "GET",
          headers: {
            Authorization: `Bearer ${jwtToken}`,
          },
        });

        if (!response.ok) {
          throw new Error("Failed to fetch rooms");
        }

        const data = await response.json();
        setRooms(data);
        setFilteredRooms(data);
      } catch (error) {
        toast.error('Failed to fetch rooms');
        setError(error.message);
      }
    };

    fetchRooms();
  }, [location.state]);

  useEffect(() => {
    // Apply filters
    let filtered = rooms;

    if (capacityFilter !== "") {
      filtered = filtered.filter(room => room.capacity >= capacityFilter);
    }

    if (availabilityDateFilter !== "") {
      filtered = filtered.filter(room => room.availability >= availabilityDateFilter);
    }

    if (priceFilter !== "") {
      filtered = filtered.filter(room => room.price <= priceFilter);
    }

    setFilteredRooms(filtered);
  }, [rooms, capacityFilter, availabilityDateFilter, priceFilter]);

  const handleReservation = (room) => {
    localStorage.setItem("room", JSON.stringify(room))
    // Navigate to the reservation page passing the room
    navigate("/reservation" );
  };

  return (
    <div className="room-list">
      <h2>Available Rooms</h2>
      {error && <div className="error">{error}</div>}
      <div className="filter-container">
        <p><b>Filter by:</b></p>
        <input
          type="text"
          placeholder="Minimum Capacity"
          value={capacityFilter}
          onChange={(e) => setCapacityFilter(e.target.value)}
        />

        <input
          type="text"
          placeholder="Maximum Price/Day"
          value={priceFilter}
          onChange={(e) => setPriceFilter(e.target.value)}
        /><input
          type="date"
          value={availabilityDateFilter}
          onChange={(e) => setAvailabilityDateFilter(e.target.value)}
        />
      </div>
      <div className="cards-container">
        
        {filteredRooms.map((room) => (
          <div key={room.id} className="card">
            <h3>Room {room.number}</h3>
            <p><b>Type: </b>{room.type}</p>
            <p><b>Price:</b> ${room.price}/day</p>
            <p><b>Capacity:</b> {room.capacity} occupent(s)</p>
            <p> <b>Available from:</b>
              <br /> {new Date(room.availability).toLocaleDateString('en-GB')}</p>
            <button className="reservation-button" onClick={() => handleReservation(room)}>
              Make <br />
              Reservation
            </button>
          </div>
        ))}
      </div>
    </div>
  );
}

export default Rooms;
