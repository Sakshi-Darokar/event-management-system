import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import API from "../../api";

function UpdateEvent() {
  const { id } = useParams();
  const navigate = useNavigate();

  const [form, setForm] = useState({
    eventName: "",
    description: "",
    dateTime: "",
    location: "",
    price: "",
    totalSeats: "",
    category: ""
  });

  useEffect(() => {
    loadEvent();
  }, []);

  const loadEvent = async () => {
    const res = await API.get(`/events/${id}`);
    setForm(res.data);
  };

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await API.put(`/events/update/${id}`, form);
      alert("Event updated ✅");
      navigate("/admin/manage-events");
    } catch (error) {
      alert("Update failed ❌");
    }
  };

  return (
    <div>
      <h2>Update Event</h2>

      <form onSubmit={handleSubmit}>
        <input name="eventName" value={form.eventName} onChange={handleChange} />
        <input name="description" value={form.description} onChange={handleChange} />
        <input type="datetime-local" name="dateTime" onChange={handleChange} />
        <input name="location" value={form.location} onChange={handleChange} />
        <input type="number" name="price" value={form.price} onChange={handleChange} />
        <input type="number" name="totalSeats" value={form.totalSeats} onChange={handleChange} />
        <input name="category" value={form.category} onChange={handleChange} />

        <button type="submit">Update</button>
      </form>
    </div>
  );
}

export default UpdateEvent;
