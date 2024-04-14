import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./RoomUpdate.css"; 
import { toast } from 'react-toastify';

function UpdateRoom() {
    const navigate = useNavigate();

    // Retrieve room data from local storage and parse it into JSON
    const room = JSON.parse(localStorage.getItem("room"));

    // State variables for room data
    const [number, setNumber] = useState(String(room.number)); // Convert to string
    const [capacity, setCapacity] = useState(String(room.capacity)); // Convert to string
    const [type, setType] = useState(room.type);
    const [description, setDescription] = useState(room.description);
    const [availability, setAvailability] = useState(room.availability);
    const [price, setPrice] = useState(String(room.price)); // Convert to string
    const [error, setError] = useState("");

    const handleConfirm = async (e) => {
        e.preventDefault();
        try {
            const token = localStorage.getItem("token");
            const response = await fetch(`http://localhost:8080/api/rooms/${room.id}`, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${token}`,
                },
                body: JSON.stringify({ number, capacity, type, price, description, availability}),
            });

            if (!response.ok) {
                throw new Error("Failed to update room");
            }
            toast.success('Room updated successfully');
            navigate("/manage-rooms"); // Redirect to the manage rooms page after updating
        } catch (error) {
            toast.error('Failed to update room');
            setError(error.message);
        }
    };

    const handleCancel = () => {
        navigate("/manage-rooms"); // Navigate back to manage rooms without updating
    };

    return (
        <div className="room-update-container">
            <h2>Room Update</h2>
            {error && <div className="error">{error}</div>}
            <form onSubmit={handleConfirm} className="room-update-form">
                <label>Room Number:</label>
                <input type="text" value={number} onChange={(e) => setNumber(e.target.value)} />

                <label>Capacity:</label>
                <input type="text" value={capacity} onChange={(e) => setCapacity(e.target.value)} />

                <label>Type:</label>
                <select value={type} onChange={(e) => setType(e.target.value)}>
                    <option value="Single">Single</option>
                    <option value="Double">Double</option>
                    <option value="Suite">Suite</option>
                </select>
                <label>Description:</label>
                <input type="text" value={description} onChange={(e) => setDescription(e.target.value)} />

                <label>Availability:</label>
                <input type="date" value={availability} onChange={(e) => setAvailability(e.target.value)} />

                <label>Price:</label>
                <input type="text" value={price} onChange={(e) => setPrice(e.target.value)} />

                <div className="buttons">
                    <button className="confirm-button" type="submit">Confirm</button>
                    <button className="cancel-button" onClick={handleCancel}>Cancel</button>
                </div>
            </form>
        </div>
    );
}

export default UpdateRoom;
