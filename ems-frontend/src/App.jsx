import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import Login from "./pages/Login";
import Register from "./pages/Register";
import AdminDashboard from "./pages/AdminDashboard";
import UserDashboard from "./pages/UserDashboard";
import AllEvents from "./pages/user/AllEvents";
import MyEvents from "./pages/user/MyEvents";
import Profile from "./pages/user/Profile";
import UpdateProfile from "./pages/user/UpdateProfile";
import CreateEvent from "./pages/admin/CreateEvent";
import ManageEvents from "./pages/admin/ManageEvents";
import UpdateEvent from "./pages/admin/UpdateEvent";
import Users from "./pages/admin/Users";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/admin-dashboard" element={<AdminDashboard />} />
        <Route path="/user-dashboard" element={<UserDashboard />} />
        <Route path="/events" element={<AllEvents />} />
        <Route path="/my-events" element={<MyEvents />} />
        <Route path="/profile" element={<Profile />} />
        <Route path="/update-profile" element={<UpdateProfile />} />
        <Route path="/admin/create-event" element={<CreateEvent />} />
        <Route path="/admin/manage-events" element={<ManageEvents />} />
        <Route path="/admin/update-event/:id" element={<UpdateEvent />} />
        <Route path="/admin/users" element={<Users />} />
      </Routes>
    </Router>
  );
}

export default App;
