import { useNavigate } from "react-router-dom";

function AdminDashboard() {
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("role");
    navigate("/");
  };

  return (
    <div>
      <h2>Admin Dashboard 👑</h2>

      <button onClick={() => navigate("/admin/create-event")}>
        Create Event
      </button>

      <button onClick={() => navigate("/admin/manage-events")}>
        Manage Events
      </button>

      <button onClick={() => navigate("/admin/users")}>
        View Users
      </button>

      <button onClick={() => navigate("/admin/bookings")}>
        View Bookings
      </button>

      <br /><br />

      <button onClick={handleLogout}>Logout</button>
    </div>
  );
}

export default AdminDashboard;
