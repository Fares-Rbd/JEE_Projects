import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "./ManageRooms.css"; // Import the CSS file for styling
import { toast } from 'react-toastify';

function ManageRooms() {
  // State variables
  const [rooms, setRooms] = useState([]);
  const [filteredRooms, setFilteredRooms] = useState([]);
  const [error, setError] = useState("");
  const [filters, setFilters] = useState({
    type: "",
    price: "",
  });
  const navigate = useNavigate();

  // useEffect to fetch rooms data
  useEffect(() => {
    const fetchRooms = async () => {
      try {
        const token = localStorage.getItem("token");
        const response = await fetch("http://localhost:8080/api/rooms", {
          method: "GET",
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });
        if (!response.ok) {
            toast.error('Failed to fetch rooms');
          throw new Error("Failed to fetch rooms");
        }
        const data = await response.json();
        setRooms(data);
        setFilteredRooms(data); // Initialize filtered rooms with all rooms
      } catch (error) {
        setError(error.message);
      }
    };
  
    fetchRooms();
  }, []);

  // Function to handle room update
  const handleUpdateRoom = (room) => {
    localStorage.setItem("room", JSON.stringify(room)); // Store room data in local storage
    navigate(`/update-room/`); // Redirect to update room page
  };

  // Function to handle room deletion
  const handleDeleteRoom = async (roomId) => {
    try {
      const confirmed = window.confirm("Are you sure you want to delete this room?");
      if (!confirmed) {
        return; // Do nothing if user cancels the confirmation
      }
  
      const response = await fetch(`http://localhost:8080/api/rooms/${roomId}`, {
        method: "DELETE",
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`,
        },
      });
  
      if (!response.ok) { 
        toast.error('Failed to delete room');
        console.error("HTTP error:", response.statusText);
        throw new Error("Failed to delete room");
      }
  
      toast.success('Room deleted successfully ! ');
  
      // Update rooms state to remove the deleted room
      setRooms(room => room.filter((room) => room.id !== roomId));
      applyFilters(); // Apply filters after deleting room
    } catch (error) {
      setError(error.message);
    }
  };

  // Function to handle filter change
  const handleFilterChange = (e) => {
    const { name, value } = e.target;
    setFilters({ ...filters, [name]: value });
  };

  // Function to apply filters
  const applyFilters = () => {
    let filtered = [...rooms];
    if (filters.type) {
      filtered = filtered.filter((room) => room.type.toLowerCase().includes(filters.type.toLowerCase()));
    }
    
    if (filters.price) {
      filtered = filtered.filter((room) => room.price <= parseInt(filters.price));
    }
    setFilteredRooms(filtered);
  };

  useEffect(() => {
    applyFilters();
  }, [filters]);

  const handleCancelDelete = () => {
    setFilteredRooms(rooms); // Reset filtered rooms to all rooms
    navigate("/manage-rooms"); // Navigate back to manage rooms without updating
  };

  return (
    <div className="rooms-container">
      <h2>Manage Rooms</h2>
      {error && <div className="error">{error}</div>}
      <div className="filters-container">
        <p>
            <b>
                Filter by:
            </b>
        </p>
        <div className="filter">
          <label>Type:</label>
          <select name="type" value={filters.type} onChange={handleFilterChange}>
            <option value="">All</option>
            <option value="Single">Single</option>
            <option value="Double">Double</option>
            <option value="Suite">Suite</option>
          </select>
        </div>
        <div className="filter">
          <label>Price:</label>
          <input placeholder="Filter By Price" type="text" name="price" value={filters.price} onChange={handleFilterChange} />
        </div>
      </div>
      <table className="rooms-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Room Number</th>
            <th>Type</th>
            <th>Price</th>
            <th>Availability</th> {/* New column for availability */}
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {filteredRooms.map((room) => (
            <tr key={room.id}>
              <td>{room.id}</td>
              <td>{room.number}</td>
              <td>{room.type}</td>
              <td>{room.price}</td>
              <td>{new Date(room.availability).toLocaleDateString('en-GB')}</td> {/* Display availability date */}
              <td>
                <button className="update-button" onClick={() => handleUpdateRoom(room)}>Update</button>
                <button className="delete-button" onClick={() => handleDeleteRoom(room.id)}>Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default ManageRooms;
