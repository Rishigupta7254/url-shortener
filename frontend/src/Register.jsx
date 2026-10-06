import { useState } from "react";

function Register() {

  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const registerUser = async () => {

    if (!username || !email || !password) {
      alert("Please fill all fields");
      return;
    }

    try {

      const response = await fetch(
        "http://localhost:8080/api/auth/register",
        {
          method: "POST",

          headers: {
            "Content-Type": "application/json"
          },

          body: JSON.stringify({
            username: username,
            email: email,
            password: password
          })
        }
      );

      const message = await response.text();

      if (!response.ok) {
        alert(message);
        return;
      }

      alert("Registration successful!");

      setUsername("");
      setEmail("");
      setPassword("");

    } catch (error) {

      console.error(error);
      alert("Something went wrong");

    }
  };

  return (
    <div className="container">

      <div className="card">

        <div className="logo">
          👤
        </div>

        <h1>Create Account</h1>

        <p>
          Register to use URL Shortener
        </p>

        <input
          className="url-input"
          type="text"
          placeholder="Enter username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />

        <br />
        <br />

        <input
          className="url-input"
          type="email"
          placeholder="Enter email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />

        <br />
        <br />

        <input
          className="url-input"
          type="password"
          placeholder="Enter password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />

        <button
          className="shorten-btn"
          onClick={registerUser}
        >
          📝 Register
        </button>

      </div>

    </div>
  );
}

export default Register;