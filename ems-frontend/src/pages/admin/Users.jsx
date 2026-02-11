import { useEffect, useState } from "react";
import API from "../../api";

function Users() {
  const [users, setUsers] = useState([]);

  useEffect(() => {
    fetchUsers();
  }, []);

  const fetchUsers = async () => {
    try {
      const res = await API.get("/admin/users");
      setUsers(res.data);
    } catch (error) {
      alert("Failed to load users");
    }
  };

  return (
    <div>
      <h2>All Users</h2>

      {users.map((user) => (
        <div 
          key={user.id}
          style={{
            border: "1px solid gray",
            margin: "10px",
            padding: "10px"
          }}
        >
          <p><b>Name:</b> {user.name}</p>
          <p><b>Email:</b> {user.email}</p>
          <p><b>Role:</b> {user.role}</p>
        </div>
      ))}
    </div>
  );
}

export default Users;
