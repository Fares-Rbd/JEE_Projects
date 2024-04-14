
import React,{useState} from "react";
import { Link, useNavigate } from "react-router-dom";
import "./Register.css";
import { FaUser } from "react-icons/fa";
import { RiLockPasswordFill } from "react-icons/ri";
import { MdEmail } from "react-icons/md";
import { toast } from 'react-toastify';


function Register() {
  const [name, setName] = useState("");  
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");

  const [error, setError] = useState("");

  const navigate = useNavigate();

  const handleRegister = async (e) => {
    e.preventDefault(); // Prevent page from refreshing on form submission
  
    // Check if passwords match
    if (password !== confirmPassword) {
      toast.error('Passwords do not match');
      return; // Exit early if passwords do not match
    }
  
    try {
      const newAccount = JSON.stringify({ name, email, password }); // Convert JS object to JSON string
      const response = await fetch("http://localhost:8080/register", {
        method: "POST",
        body: newAccount, // Send new account data in request body
        credentials: "include", // Include credentials in the request
        headers: {
          "Content-Type": "application/json" // Specify content type as JSON
        }
      });
      
      if (response.ok) {
        // Registration successful
        toast.success('Registration successful');
        navigate("/login"); // Redirect to login page

      } else {
        // Registration failed
        toast.error('Failed to register');
        const data = await response.json();
        setError(data.message); // Set error message based on server response
      }
    } catch (error) {
      toast.error('An error occurred while registering');
      setError("An error occurred while registering"); // Set generic error message
      console.error("Error:", error);
    }
  };


  return (
    <div className="wrapper">
      <h1>Register</h1>
      <form onSubmit={handleRegister}>
      {/* <form > */}
        <div className="input-box">
          <input type="text" placeholder="Username" value={name} onChange={(e) => setName(e.target.value)} required /><FaUser className="icon" />
        </div>
        <div className="input-box">
          <input type="email" placeholder="Email" value={email} onChange={(e) => setEmail(e.target.value)} required /><MdEmail className="icon" />
        </div>
        <div className="input-box">
          <input type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)} required minLength={8} /><RiLockPasswordFill className="icon" />
        </div>
        <div className="input-box">
          <input type="password" placeholder="Confirm Password" value={confirmPassword} onChange={(e) => setConfirmPassword(e.target.value)} required minLength={8} /><RiLockPasswordFill className="icon" />
        </div>
        <div>
          <button type="submit">Register</button>
        </div>
        <div className="login-link">
          <p>
            Already have an account? <Link to="/login">Login</Link>
          </p>
        </div>
      </form>
    </div>
  );
}

export default Register;
