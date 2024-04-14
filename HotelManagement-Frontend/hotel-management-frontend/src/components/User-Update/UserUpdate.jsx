import React, { useState } from "react";
import { useNavigate,useLocation } from "react-router-dom";
import "./UserUpdate.css";
import { toast } from "react-toastify";

function UserUpdate() {
  const navigate = useNavigate();
  const location = useLocation();
  const user = JSON.parse(localStorage.getItem("userToEdit"));
  const [name, setName] = useState(user.username);
  const [email, setEmail] = useState(user.email);
  const [password, setPassword] = useState("");
  const [role, setRole] = useState(user.role);
  const updateUser = location.state?.updateUser || false;

  const handleConfirm = async (e) => {
    e.preventDefault();
    try {
        
      const requestOptions = {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${localStorage.getItem("token")}`,
        },
        body: JSON.stringify({ name, email, password, role }),
      };

      const response = await fetch(`http://localhost:8080/api/users/${user.id}`, requestOptions);

      if (!response.ok) {
        throw new Error("Failed to update user");
      }
      toast.success('User updated successfully');
    if(updateUser){
        localStorage.setItem("user", JSON.stringify({ id: user.id, username: name, email, role }));
    }
      localStorage.removeItem("userToEdit");
      navigate(-1);
    } catch (error) {
        toast.error('Failed to update user');
      console.error(error);
    }
  };

  const handleCancel = (e) => {
    e.preventDefault();
    toast.info('User update cancelled');
    localStorage.removeItem("userToEdit");
    navigate(-1);
  };

  return (
    <div className="user-update-container">
      <h2>User Update</h2>
      <form  className="user-update-form">
        <label>Username:</label>
        <input type="text" value={name} onChange={(e) => setName(e.target.value)} />

        <label>Email:</label>
        <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} />

        <label>Password:</label>
        <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} />

        {JSON.parse(localStorage.getItem("user")).role === "ADMIN" && ( // Render Role dropdown only if user is an admin
          <><label>Role:</label>
          <select value={role} onChange={(e) => setRole(e.target.value)}>
            <option value="CLIENT">Client</option>
            <option value="ADMIN">Admin</option>
          </select></>
        )}

        <div className="buttons">
          <button className="confirm-button" onClick={handleConfirm}>Confirm</button>
          <button className="cancel-button" onClick={handleCancel}>Cancel</button>
        </div>
      </form>
    </div>
  );
}

export default UserUpdate;
