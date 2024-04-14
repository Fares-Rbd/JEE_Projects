import React  ,{useState}from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { MdOutlineBedroomChild ,MdOutlineRoomPreferences } from "react-icons/md";
import { RiLogoutBoxLine } from "react-icons/ri";
import { FaPersonShelter, FaUsers } from "react-icons/fa6";
import { FaUserEdit } from "react-icons/fa";
import { RiLoginBoxLine } from "react-icons/ri";
import "./Home.css";
import { jwtDecode } from "jwt-decode";

function Home() {
  const navigate = useNavigate();
  const jwtToken = localStorage.getItem("token"); // Extract token from localStorage


   


  const handleClickRooms = () => {
    navigate("/rooms");
  };
  const handleClickManageRooms = () => {

    navigate("/manage-rooms");
  };
  

  const handleClickLogin = () => {
    navigate("/login");
  };

  const handleClickReservations = () => {
    navigate("/check-reservations");
  };

  const handleClickUsers = () => {

    navigate("/users");
  };

  const handleEditProfile = () => {
    localStorage.setItem("userToEdit", localStorage.getItem("user"));
    navigate("/update-user", { state: { updateUser:true } });
  };

  const handleLogout = () => {
    // Perform logout logic, such as removing the token from local storage
    // Then navigate to the home page
    localStorage.removeItem("user");
    localStorage.removeItem("token");
    navigate("/");
  };

  return (
    <div className="home-container">
      <h2 className="welcome-text">Welcome{jwtToken ? `, ${JSON.parse(localStorage.getItem("user")).username} !` : ",Please log in !"}</h2>
      {jwtToken ? (
        // User is logged in
        <div className="container">
          <div className="card-button" onClick={handleClickRooms}>
            <button>
              <div className="button-icon"><MdOutlineBedroomChild /></div>
              <br />
              View Rooms
            </button>
          </div>
          <div className="card-button" onClick={handleClickReservations}>
            <button>
              <div className="button-icon"><FaPersonShelter /></div><br /> View Reservations
            </button>
          </div>
          {jwtDecode(jwtToken)?.role === "ADMIN" && (
            // User is admin
            <div className="card-button" onClick={handleClickUsers}>
              <button><div className="button-icon"><FaUsers /></div><br />View Users</button>
            </div>
          )}
            {jwtDecode(jwtToken)?.role === "ADMIN" && (
            <div className="card-button" onClick={handleClickManageRooms}>
              <button><div className="button-icon"><MdOutlineRoomPreferences /></div><br />Manage Rooms</button>
            </div>
          )}
          <div className="card-button" onClick={handleEditProfile}>
            <button>
              <div className="button-icon">
                <FaUserEdit />
              </div>
              <br />Edit Profile
            </button>
          </div>
          <div className="card-button" onClick={handleLogout}>
            <button>
              <div className="button-icon">
                <RiLogoutBoxLine />
              </div>
              <br />Logout
            </button>
          </div>
        </div>
      ) : (
        // User is not logged in
        <div className="container">
          <div className="card-button" onClick={handleClickLogin}>
            <button><div className="button-icon"><RiLoginBoxLine /></div><br />Login </button>
          </div>
        </div>
      )}
    </div>
  );
}

export default Home;
