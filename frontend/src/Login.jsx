
import { useState } from "react";

function Login({ onLoginSuccess }) {

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const loginUser = async () => {

    if (!username || !password) {

      alert("Please enter username and password");

      return;
    }

    try {

      const response = await fetch(
        "http://localhost:8080/api/auth/login",
        {
          method: "POST",

          headers: {
            "Content-Type": "application/json"
          },

          body: JSON.stringify({
            username: username,
            password: password
          })
        }
      );

      const token = await response.text();

      if (!response.ok) {

        alert(token);

        return;
      }

      // JWT save
      localStorage.setItem("token", token);

      alert("Login successful!");

      console.log("JWT Token:", token);

      // Login page se main page par jao
      onLoginSuccess();

    } catch (error) {

      console.error(error);

      alert("Something went wrong");
    }
  };

  return (
    <div className="container">

      <div className="card">

        <div className="logo">
          🔐
        </div>

        <h1>
          Welcome Back
        </h1>

        <p>
          Login to your URL Shortener account.
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
          type="password"
          placeholder="Enter password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />

        <button
          className="shorten-btn"
          onClick={loginUser}
        >
          🔑 Login
        </button>

      </div>

    </div>
  );
}

export default Login;

