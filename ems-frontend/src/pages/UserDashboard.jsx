import { useNavigate } from "react-router-dom";

function UserDashboard() {
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("role");
    navigate("/");
  };

  return (
    <div>
      <h2>Welcome User 👤</h2>
      <button onClick={handleLogout}>Logout</button>
        <button onClick={() => navigate("/events")}>
    View All Events
    </button>
    <button onClick={() => navigate("/my-events")}>
        My Events
    </button>
    <button onClick={() => navigate("/profile")}>
   My Profile
</button>
    <button onClick={() => navigate("/update-profile")}>
   Update Profile
</button>

    </div>
  );
}

export default UserDashboard;
