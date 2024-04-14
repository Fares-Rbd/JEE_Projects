import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { toast } from 'react-toastify';
import "./Users.css";

function Users() {
  const [users, setUsers] = useState([]);
  const [filteredUsers, setFilteredUsers] = useState([]);
  const [error, setError] = useState("");
  const [nameFilter, setNameFilter] = useState("");
  const [emailFilter, setEmailFilter] = useState("");
  const [roleFilter, setRoleFilter] = useState("");
  const navigate = useNavigate(); // React Router's useNavigate hook

  useEffect(() => {
    const fetchUsers = async () => {
      try {
        const response = await fetch("http://localhost:8080/api/users", {
          method: "GET",
          headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`,
          },
        });

        if (!response.ok) {
          throw new Error("Failed to fetch users");
        }

        const data = await response.json();
        setUsers(data);
        setFilteredUsers(data);
      } catch (error) {
        setError(error.message);
      }
    };

    fetchUsers();
  }, []);

  useEffect(() => {
    // Apply filters
    let filtered = users;

    if (nameFilter) {
      filtered = filtered.filter(user => user.username.toLowerCase().includes(nameFilter.toLowerCase()));
    }

    if (emailFilter) {
      filtered = filtered.filter(user => user.email.toLowerCase().includes(emailFilter.toLowerCase()));
    }

    if (roleFilter) {
      filtered = filtered.filter(user => user.role.toLowerCase().includes(roleFilter.toLowerCase()));
    }

    setFilteredUsers(filtered);
  }, [users, nameFilter, emailFilter, roleFilter]);

  const handleDeleteUser = async (userId) => {
    try {
      const confirmed = window.confirm("Are you sure you want to delete this user?");
      if (!confirmed) {
        return; // Do nothing if user cancels the confirmation
      }
  
      const response = await fetch(`http://localhost:8080/api/users/${userId}`, {
        method: "DELETE",
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`,
        },
      });
  
      if (!response.ok) { 
        console.error("HTTP error:", response.statusText);
        throw new Error("Failed to delete user");
      }
      toast.success('User deleted successfully ! ');
      // Remove the deleted user from the users state
      setUsers(users.filter((user) => user.id !== userId));
    } catch (error) {
      
      toast.error('Failed to delete user !');
      setError(error.message);
    }
  };

  const handleUpdateUser = (user) => {
    localStorage.setItem("userToEdit", JSON.stringify(user));
    // Navigate to the user update page and pass the selected user
    navigate("/update-user");
  };
  

  return (
    <div className="users-container">
      <h2>Users</h2>
      <div className="filters">
  <input
    type="text"
    placeholder="Filter by Name"
    value={nameFilter}
    onChange={(e) => setNameFilter(e.target.value)}
  />
  <input
    type="text"
    placeholder="Filter by Email"
    value={emailFilter}
    onChange={(e) => setEmailFilter(e.target.value)}
  />
  <select
    value={roleFilter}
    onChange={(e) => setRoleFilter(e.target.value)}
  >
    <option value="">Filter by Role</option>
    <option value="CLIENT">CLIENT</option>
    <option value="ADMIN">ADMIN</option>
  </select>
</div>

      {error && <div className="error">{error}</div>}
      <table className="users-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Role</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {filteredUsers.map((user) => (
            <tr key={user.id}>
              <td>{user.id}</td>
              <td>{user.username}</td>
              <td>{user.email}</td>
              <td style={{ color: user.role === "ADMIN" ? "red" : "inherit" }}>{user.role}</td>
              <td>
                <button className="update-button" onClick={() => handleUpdateUser(user)}>Update</button>
                <button className="delete-button"onClick={() => handleDeleteUser(user.id)}>Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default Users;
