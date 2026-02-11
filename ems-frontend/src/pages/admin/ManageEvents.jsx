import { useEffect, useState } from "react";
import API from "../../api";
import { useNavigate } from "react-router-dom";

function ManageEvents() {
  const navigate = useNavigate();

  const [events, setEvents] = useState([]);

  useEffect(() => {
    fetchEvents();
  }, []);

  const fetchEvents = async () => {
    try {
      const res = await API.get("/events");
      setEvents(res.data);
    } catch (error) {
      alert("Failed to load events");
    }
  };

  const handleDelete = async (id) => {
    try {
      await API.delete(`/events/delete/${id}`);
      alert("Event deleted ✅");
      fetchEvents(); // refresh
    } catch (error) {
      alert("Delete failed ❌");
    }
  };

  return (
    <div>
      <h2>Manage Events</h2>

      {events.map((event) => (
  <div 
    key={event.id} 
    style={{ 
      border: "1px solid gray", 
      margin: "15px", 
      padding: "15px",
      backgroundColor: "#f9f9f9"
    }}
  >
    <h3>{event.eventName}</h3>

    <p><b>Description:</b> {event.description}</p>
    <p><b>Date:</b> {new Date(event.dateTime).toLocaleString()}</p>
    <p><b>Location:</b> {event.location}</p>
    <p><b>Category:</b> {event.category}</p>
    <p><b>Price:</b> ₹{event.price}</p>
    <p><b>Total Seats:</b> {event.totalSeats}</p>
    <p><b>Available Seats:</b> {event.availableSeats}</p>
    <button onClick={() => navigate(`/admin/update-event/${event.id}`)}>
  Edit
</button>

    <button onClick={() => handleDelete(event.id)}>
      Delete
    </button>
  </div>
))}

    </div>
  );
}

export default ManageEvents;
