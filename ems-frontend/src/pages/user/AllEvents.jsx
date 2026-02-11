import { useEffect, useState } from "react";
import API from "../../api";

function AllEvents() {
  const [events, setEvents] = useState([]);

  useEffect(() => {
    fetchEvents();
  }, []);

  const fetchEvents = async () => {
    try {
      const res = await API.get("/events");   // adjust if path different
      setEvents(res.data);
    } catch (error) {
      console.log(error);
      alert("Failed to fetch events");
    }
  };
  const handleBook = async (eventId) => {
  try {
    const res = await API.post(`/booking/register?eventId=${eventId}`);
    alert(res.data);
  } catch (error) {
    if (error.response) {
      alert(error.response.data.message || error.response.data);
    } else {
      alert("Booking failed");
    }
  }
};




  return (
    <div>
      <h2>All Events</h2>

      {events.map((event) => (
  <div key={event.id} style={{ border: "1px solid gray", margin: "10px", padding: "10px" }}>
    
    <h3>{event.eventName}</h3>
    
    <p>{event.description}</p>
    
    <p>Date: {event.dateTime}</p>
    
    <p>Location: {event.location}</p>
    
    <p>Price: ₹{event.price}</p>
    
    <p>Total Seats: {event.totalSeats}</p>
    
    <p>Available Seats: {event.availableSeats}</p>
    
    <p>Category: {event.category}</p>
    <button onClick={() => handleBook(event.id)}>
        Book Event
    </button>

  </div>
))}

    </div>
  );
}

export default AllEvents;
