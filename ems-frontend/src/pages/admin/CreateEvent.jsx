import { useState } from "react";
import API from "../../api";

function CreateEvent() {
  const [form, setForm] = useState({
    eventName: "",
    description: "",
    dateTime: "",
    location: "",
    price: "",
    totalSeats: "",
    category: ""
  });

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await API.post("/events/create", form);
      alert("Event created successfully ✅");
    } catch (error) {
      alert("Creation failed ❌");
    }
  };

  return (
    <div>
      <h2>Create Event</h2>

      <form onSubmit={handleSubmit}>
        <input name="eventName" placeholder="Event Name" onChange={handleChange} required />
        <input name="description" placeholder="Description" onChange={handleChange} required />
        <input type="datetime-local" name="dateTime" onChange={handleChange} required />
        <input name="location" placeholder="Location" onChange={handleChange} required />
        <input type="number" name="price" placeholder="Price" onChange={handleChange} required />
        <input type="number" name="totalSeats" placeholder="Total Seats" onChange={handleChange} required />
        <input name="category" placeholder="Category" onChange={handleChange} required />

        <button type="submit">Create</button>
      </form>
    </div>
  );
}

export default CreateEvent;
