import React, { useState } from "react";
import { Link ,json,useNavigate} from "react-router-dom";
import "./Login.css";
import { FaUser } from "react-icons/fa";
import { RiLockPasswordFill } from "react-icons/ri";
import { toast } from 'react-toastify';
import {jwtDecode} from "jwt-decode";


function Login() {
  const [name, setName] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const [showPassword, setShowPassword] = useState(false);
  const navigate = useNavigate();

const togglePassword = () => {
    setShowPassword(!showPassword);
  };
  const handleLogin = async (e) => {
    e.preventDefault();
  
    try {
      const response = await fetch("http://localhost:8080/login", {
        method: "POST",
        body: JSON.stringify({ name, password }),
        headers: {
          "Content-Type": "application/json",
        },
        credentials: "include", // Include credentials in the request
      });
  
      if (response.ok) {
        const jwtToken = await response.json(); // Extract user data from response
        localStorage.setItem("token", jwtToken.token); // Store token in localStorage
        toast.success('Login successful');
  
        // Fetch user data based on the ID from the token
        const userResponse = await fetch(`http://localhost:8080/api/users/${jwtDecode(jwtToken.token)?.sub}`, {
          method: "GET",
          headers: {
            Authorization: `Bearer ${jwtToken.token}`,
          },
        });
  
        if (userResponse.ok) {
          const userData = await userResponse.json(); // Extract user data from response
          localStorage.setItem("user", JSON.stringify(userData)); // Store user data in localStorage
          console.log(localStorage.getItem("user"));
          } else {
          // Handle error while fetching user data
          toast.error('Failed to fetch user data');
          console.error("HTTP error:", userResponse.statusText);
        }
  
        // Clear input fields after successful login
        setName("");
        setPassword("");
        
        navigate("/home"); // Navigate to home page after successful login
      } else {
        const errorMessage = await response.text(); // Extract error message from response
        toast.error('Incorrect Username or Password');
        console.error("HTTP error:", response.statusText);
      }
    } catch (error) {
      setError("An error occurred while processing your request");
      console.error("Error:", error);
    }
  };
  

  return (
    <div className="wrapper">
      <h1>Login</h1>
      <form onSubmit={handleLogin}>
        <div className="input-box">
          <input
            type="text"
            placeholder="Username"
            required
            autoFocus
            value={name}
            onChange={(e) => setName(e.target.value)}
          />
          <FaUser className="icon" />
        </div>
        <div className="input-box">
          <input
            type="password"
            placeholder="********"
            required
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
          <RiLockPasswordFill className="icon" onClick={togglePassword} />
        </div>
        {error && <div className="error">{error}</div>}
        <div>
          <button className="login-button" type="submit">Login</button>
        </div>
        <div className="register-link">
          <p>
            Don't have an account yet ? <Link to="/register">Register</Link>
          </p>
        </div>
      </form>
    </div>
  );
}

export default Login;
