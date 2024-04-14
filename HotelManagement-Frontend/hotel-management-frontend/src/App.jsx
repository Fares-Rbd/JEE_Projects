import { useState } from 'react';
import Login from './components/Login/Login';
import Register from './components/Register/Register';
import Home from './components/Home/Home';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Rooms from './components/Rooms/Rooms';
import Reservation from './components/Reservation/Reservation';
import Users from './components/Users/Users';
import UserUpdate from './components/User-Update/UserUpdate';
import CheckReservations from './components/Check-Reservations/CheckReservations';
import ManageRooms from './components/Manage-Rooms/ManageRooms';
import UpdateRoom from './components/Room-Update/RoomUpdate';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

function App() {
  return (<>
  <ToastContainer />
  <Router>  
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/home" element={<Home />} />
        <Route path="/rooms" element={<Rooms />} />
        <Route path="/reservation" element={<Reservation />} />
        <Route path="/users" element={<Users />} />
        <Route path="/update-user" element={<UserUpdate />} />
        <Route path="/check-reservations" element={<CheckReservations />} />
        <Route path="/manage-rooms" element={<ManageRooms />} />
        <Route path="/update-room" element={<UpdateRoom />} />

      </Routes>
    </Router></>
    
  );
}

export default App;
