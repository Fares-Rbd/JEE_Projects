import React, { useState } from "react";
import "./Reservation.css";
import { jwtDecode } from "jwt-decode";
import { toast } from 'react-toastify';
import { useNavigate } from "react-router";

function Reservation() {
    const navigate = useNavigate();
    const [arrivalDate, setArrivalDate] = useState("");
    const [departureDate, setDepartureDate] = useState("");
    const room = localStorage.getItem("room") ? JSON.parse(localStorage.getItem("room")) : null;

    // Function to handle change in arrival date input
    const handleArrivalDateChange = (e) => {
        const { value } = e.target;
        if (new Date(value) < new Date(room?.availability)) {
            setArrivalDate(room?.availability);
        } else {
            setArrivalDate(value);
        }
    };

    // Function to handle change in departure date input
    const handleDepartureDateChange = (e) => {
        const { value } = e.target;
        if (new Date(value) < new Date(addDays(room?.availability, 1))) {
            setDepartureDate(addDays(room?.availability, 1));
        } else {
            setDepartureDate(value);
        }
    };

    const handleReservation = async () => {
        try {
            // Extract user ID from token

            const userIdString = localStorage.getItem("user") ? jwtDecode(localStorage.getItem("token")).sub : null;
            const userId = userIdString ? parseInt(userIdString) : null;

            // Prepare request body
            const requestBody = {
                arrivalDate,
                departureDate,
                status: "ON_HOLD",
                userId: userId,
                room: room.id
            };
            // Send POST request
            const response = await fetch("http://localhost:8080/api/reservations", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${localStorage.getItem("token")}`,
                },
                body: JSON.stringify(requestBody)
            });

            // Check if request was successful
            if (!response.ok) {
                
                throw new Error("Failed to make reservation");
            }

            // Reservation successful
            toast.success('Reservation successful !\nYou will now be redirected to your reservations page to CONFIRM your reservation !');
            navigate("/check-reservations")
            // Optionally, you can navigate to a success page or perform any other action
        } catch (error) {
            // Handle errors
            toast.error('Failed to make reservation');
            console.error("Error making reservation:", error.message);
            // Optionally, you can display an error message to the user
        }
    };


    // Function to add one day to a date string
    const addDays = (dateString, days) => {
        const date = new Date(dateString);
        date.setDate(date.getDate() + days);
        return date.toISOString().split('T')[0];
    };

    return (
        <div className="reservation-container">
            <h2>Make Reservation</h2>
            {room && (
                <div className="reservation-card">
                    <div className="room-info">
                        <h3>Room {room.number}</h3>
                        <p><b>Type:</b> {room.type}</p>
                        <p><b>Price:</b> ${room.price}/day</p>
                        <p><b>Capacity:</b> {room.capacity} occupant(s)</p>
                        <p><b>Available from:</b> {new Date(room.availability).toLocaleDateString('en-GB')}</p>
                    </div>
                    <div className="date-inputs">
                        <br />
                        <br />
                        <br />
                        <label htmlFor="arrivalDate"><b>Arrival <br /> Date:</b></label>
                        <input
                            type="date"
                            id="arrivalDate"
                            min={room?.availability}
                            value={arrivalDate}
                            onChange={(e) => setArrivalDate(e.target.value)}
                        />
                        <label htmlFor="departureDate"><b>Departure <br /> Date:</b></label>
                        <input
                            type="date"
                            id="departureDate"
                            min={room?.availability ? addDays(room?.availability, 1) : ""}
                            value={departureDate}
                            onChange={(e) => setDepartureDate(e.target.value)}
                        />
                    </div>
                    <div className="button-container">
                        <button onClick={handleReservation}>Confirm Reservation</button>
                    </div>
                </div>
            )}

        </div>
    );
}

export default Reservation;
