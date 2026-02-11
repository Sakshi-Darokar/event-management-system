import { useEffect, useState } from "react";
import API from "../../api";

function UpdateProfile() {
  const [form, setForm] = useState({
    name: "",
    password: ""
  });

  useEffect(() => {
    loadProfile();
  }, []);

  const loadProfile = async () => {
    const res = await API.get("/users/me");
    setForm({
      name: res.data.name,
      password: ""
    });
  };

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await API.put("/users/update", form);
      alert("Profile updated successfully ✅");
    } catch (error) {
      alert("Update failed ❌");
    }
  };

  return (
    <div>
      <h2>Update Profile</h2>
      <form onSubmit={handleSubmit}>
        <input
          name="name"
          value={form.name}
          onChange={handleChange}
          placeholder="Name"
        />
        <input
          name="password"
          type="password"
          onChange={handleChange}
          placeholder="New Password"
        />
        <button type="submit">Update</button>
      </form>
    </div>
  );
}

export default UpdateProfile;
