import { useEffect, useState } from "react";
import API from "../../api";

function MyEvents() {
  const [events, setEvents] = useState([]);

  useEffect(() => {
    fetchMyEvents();
  }, []);

  const fetchMyEvents = async () => {
    try {
      const res = await API.get("/users/my-events");
      setEvents(res.data);
    } catch (error) {
      alert("Failed to fetch my events");
    }
  };
  const handleCancel = async (eventId) => {
  try {
    await API.delete(`/booking/cancel?eventId=${eventId}`);
    alert("Booking cancelled ✅");
    fetchMyEvents(); // refresh list
  } catch (error) {
    alert("Cancel failed ❌");
  }
};

  return (
    <div>
      <h2>My Events</h2>

      {events.map((event) => (
        <div key={event.id} style={{ border: "1px solid gray", margin: "10px", padding: "10px" }}>
          <h3>{event.eventName}</h3>
          <p>{event.location}</p>
          <p>Date: {new Date(event.dateTime).toLocaleString()}</p>
          <button onClick={() => handleCancel(event.id)}>
            Cancel Booking
          </button>

        </div>
      ))}
    </div>
  );
}

export default MyEvents;
