import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "./CheckReservations.css";
import { jwtDecode } from "jwt-decode";
import { toast } from "react-toastify";

function CheckReservations() {
  const [reservations, setReservations] = useState([]);
  const [filteredReservations, setFilteredReservations] = useState([]);
  const [error, setError] = useState("");
  const [filters, setFilters] = useState({
    arrivalDate: "",
    departureDate: "",
    userId: "",
    roomId: "",
    status: "",
  });
  const navigate = useNavigate();

  useEffect(() => {
    const fetchReservations = async () => {
      try {
        const token = localStorage.getItem("token");
        const user = localStorage.getItem("user");
        const isAdmin = token && jwtDecode(token).role === "ADMIN";
        let endpoint;
        if (isAdmin) {
          endpoint = "http://localhost:8080/api/reservations";
        } else {
          const userId = JSON.parse(user).id;
          endpoint = `http://localhost:8080/api/reservations/user/${userId}`;
        }

        const response = await fetch(endpoint, {
          method: "GET",
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });

        if (!response.ok) {
          throw new Error("Failed to fetch reservations");
        }

        const data = await response.json();
        setReservations(data);
        setFilteredReservations(data); // Initialize filtered reservations with all reservations
      } catch (error) {
        setError(error.message);
      }
    };

    fetchReservations();
  }, []);

  useEffect(() => {
    // Apply filtering whenever there is a change in filters
    applyFilters();
  }, [filters]); // eslint-disable-line react-hooks/exhaustive-deps

  const applyFilters = () => {
    let filtered = [...reservations];
    // Apply filters
    if (filters.arrivalDate) {
      filtered = filtered.filter((reservation) => reservation.arrivalDate.includes(filters.arrivalDate));
    }
    if (filters.departureDate) {
      filtered = filtered.filter((reservation) => reservation.departureDate.includes(filters.departureDate));
    }
    if (filters.userId) {
      const userId = parseInt(filters.userId); // Convert filter value to integer
      filtered = filtered.filter((reservation) => reservation.userId == userId);
    }
    if (filters.roomId) {
      const roomId = parseInt(filters.roomId); // Convert filter value to integer
      filtered = filtered.filter((reservation) => reservation.room == roomId);
    }
    if (filters.status) {
      filtered = filtered.filter((reservation) => reservation.status === filters.status);
    }
    setFilteredReservations(filtered);
  };

  const handleCancelReservation = async (reservationId) => {
    try {
      const confirmed = window.confirm("Are you sure you want to cancel this reservation?");
      if (!confirmed) {
        return; // Do nothing if user cancels the confirmation
      }
  
      const token = localStorage.getItem("token");
      const response = await fetch(`http://localhost:8080/api/reservations/${reservationId}`, {
        method: "DELETE",
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
  
      if (!response.ok) { 
        console.error("HTTP error:", response.statusText);
        throw new Error("Failed to cancel reservation");
      }
        toast.info('Reservation canceled successfully ...\nWe hope to see you back soon ! ');
  
      // Update both reservations and filteredReservations after canceling the reservation
      const updatedReservations = reservations.filter((reservation) => reservation.id !== reservationId);
      setReservations(updatedReservations);
      setFilteredReservations(updatedReservations);
    } catch (error) {
      setError(error.message);
    }
  };
  
  const handleConfirmReservation = async (reservationId) => {
    try {
      const token = localStorage.getItem("token");
      const response = await fetch(`http://localhost:8080/api/reservations/confirm/${reservationId}`, {
        method: "PUT",
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
  
      if (!response.ok) { 
        console.error("HTTP error:", response.statusText);
        throw new Error("Failed to confirm reservation");
      }
      toast.success('Reservation confirmed successfully ! ');
  
      // Update both reservations and filteredReservations after confirming the reservation
      const updatedReservations = reservations.map((reservation) => {
        if (reservation.id === reservationId) {
          return { ...reservation, status: "CONFIRMED" };
        }
        return reservation;
      });
      setReservations(updatedReservations);
      setFilteredReservations(updatedReservations);
    } catch (error) {
        toast.error('Failed to confirm reservation');
      setError(error.message);
    }
  };
  

  const handleFilterChange = (e) => {
    const { name, value } = e.target;
    setFilters({ ...filters, [name]: value });
  };

  return (
    <div className="reservations-container">
      <h2>Check Reservations</h2>
      <button className="back-button" onClick={() => navigate("/home")}>Back</button>
      {error && <div className="error">{error}</div>}
      
      <div className="filters-container">
      <p><b>You can filter by :</b></p>
        <div className="filter">
          <label>Arrival Date:</label> <br />
          <input type="date" name="arrivalDate" value={filters.arrivalDate} onChange={handleFilterChange} />
        </div>
        <div className="filter">
          <label>Departure Date:</label><br />
          <input type="date" name="departureDate" value={filters.departureDate} onChange={handleFilterChange} />
        </div>
        <div className="filter">
          <label>User ID:</label><br />
          <input type="text" name="userId" value={filters.userId} onChange={handleFilterChange} />
        </div>
        <div className="filter">
          <label>Room ID:</label><br />
          <input type="text" name="roomId" value={filters.roomId} onChange={handleFilterChange} />
        </div>
        <div className="filter">
          <label>Status:</label><br />
          <select name="status" value={filters.status} onChange={handleFilterChange}>
            <option value="">All</option>
            <option value="ON_HOLD">On Hold</option>
            <option value="CONFIRMED">Confirmed</option>
          </select>
        </div>
      </div>
      <table className="reservations-table">
        <thead>
          <tr>
            <th>ID</th>
            {jwtDecode(localStorage.getItem("token")).role === "ADMIN" && <th>User ID</th>}
            {jwtDecode(localStorage.getItem("token")).role === "ADMIN" && <th>Room ID</th>}
            <th>Arrival Date</th>
            <th>Departure Date</th>
            <th>Status</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {filteredReservations.map((reservation) => (
            <tr key={reservation.id}>
              <td>{reservation.id}</td>
              {jwtDecode(localStorage.getItem("token")).role === "ADMIN" && <td>{reservation.userId}</td>}
              {jwtDecode(localStorage.getItem("token")).role === "ADMIN" && <td>{reservation.room}</td>}
              <td>{new Date(reservation.arrivalDate).toLocaleDateString('en-GB')}</td>
              <td>{new Date(reservation.departureDate).toLocaleDateString('en-GB')}</td>
              <td>
                <span style={{ fontWeight: "bold", color: reservation.status === "ON_HOLD" ? "red" : "green" }}>
                  {reservation.status.replace("_", " ")}
                </span>
              </td>
              <td>
                <button className="cancel-button" onClick={() => handleCancelReservation(reservation.id)}>Cancel</button>
                {reservation.status === "ON_HOLD" && (
                  <button className="confirm-button" onClick={() => handleConfirmReservation(reservation.id)}>Confirm</button>
                )}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default CheckReservations;
