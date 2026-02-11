import { useState } from "react";
import { useNavigate } from "react-router-dom";
import API from "../api";

function Register() {
  const [form, setForm] = useState({
    name: "",
    email: "",
    password: "",
    role: "USER"
  });

  const navigate = useNavigate();

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await API.post("/auth/register", form);
      alert(res.data.message);
      navigate("/login");
    } catch (err) {
      alert("Registration failed");
    }
  };

  return (
    <div>
      <h2>Register</h2>
      <form onSubmit={handleSubmit}>
        <input name="name" placeholder="Name" onChange={handleChange} required />
        <input name="email" placeholder="Email" onChange={handleChange} required />
        <input name="password" type="password" placeholder="Password" onChange={handleChange} required />

        <select name="role" onChange={handleChange}>
          <option value="USER">USER</option>
          <option value="ADMIN">ADMIN</option>
        </select>

        <button type="submit">Register</button>
      </form>
    </div>
  );
}

export default Register;
