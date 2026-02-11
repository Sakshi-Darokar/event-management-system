import { useEffect, useState } from "react";
import API from "../../api";

function Profile() {
  const [profile, setProfile] = useState(null);

  useEffect(() => {
    fetchProfile();
  }, []);

  const fetchProfile = async () => {
    try {
      const res = await API.get("/users/me");
      setProfile(res.data);
    } catch (error) {
      alert("Failed to load profile");
    }
  };

  if (!profile) return <h3>Loading...</h3>;

  return (
    <div>
      <h2>My Profile</h2>
      <p>Name: {profile.name}</p>
      <p>Email: {profile.email}</p>
      <p>Role: {profile.role}</p>
    </div>
  );
}

export default Profile;
