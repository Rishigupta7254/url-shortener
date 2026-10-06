
import { useState } from "react";
import "./App.css";
import Register from "./Register";
import Login from "./Login";

function App() {

  const [originalUrl, setOriginalUrl] = useState("");
  const [shortUrl, setShortUrl] = useState("");
  const [loading, setLoading] = useState(false);

  const [showRegister, setShowRegister] = useState(false);
  const [showLogin, setShowLogin] = useState(false);

  // Copy Short URL
  const copyShortUrl = () => {

    navigator.clipboard.writeText(shortUrl);

    alert("Short URL copied!");
  };

  // Create Short URL
  const createShortUrl = async () => {

    if (!originalUrl) {

      alert("Please enter a URL");

      return;
    }

    try {

      setLoading(true);

      const response = await fetch(
        "http://localhost:8080/api/urls",
        {
          method: "POST",

          headers: {
            "Content-Type": "application/json"
          },

          body: JSON.stringify({
            originalUrl: originalUrl
          })
        }
      );

      if (!response.ok) {

        throw new Error("Failed to create short URL");
      }

      const data = await response.json();

      setShortUrl(data.shortUrl);

    } catch (error) {

      console.error(error);

      alert("Something went wrong");

    } finally {

      setLoading(false);
    }
  };

  // Login Page
  if (showLogin) {

    return (
      <Login
        onLoginSuccess={() => {
          setShowLogin(false);
        }}
      />
    );
  }

  // Register Page
  if (showRegister) {

    return (
      <div>

        <Register />

        <div
          style={{
            textAlign: "center",
            marginTop: "20px"
          }}
        >

          <button
            className="copy-btn"
            onClick={() => setShowRegister(false)}
          >
            ← Back to URL Shortener
          </button>

        </div>

      </div>
    );
  }

  // Main URL Shortener Page
  return (
    <div className="container">

      <div className="card">

        <div className="logo">
          🔗
        </div>

        <h1>
          URL Shortener
        </h1>

        <p>
          Make your long URLs short, simple and easy to share.
        </p>

        <input
          className="url-input"
          type="text"
          placeholder="Paste your long URL here..."
          value={originalUrl}
          onChange={(e) => setOriginalUrl(e.target.value)}
        />

        <button
          className="shorten-btn"
          onClick={createShortUrl}
          disabled={loading}
        >
          {loading
            ? "Creating..."
            : "🚀 Shorten URL"}
        </button>

        {shortUrl && (

          <div className="result">

            <h3>
              🎉 Your Short URL
            </h3>

            <a
              href={shortUrl}
              target="_blank"
              rel="noreferrer"
            >
              {shortUrl}
            </a>

            <button
              className="copy-btn"
              onClick={copyShortUrl}
            >
              📋 Copy URL
            </button>

          </div>

        )}

        <div className="features">

          <div className="feature">

            <div className="feature-icon">
              ⚡
            </div>

            <h4>
              Fast
            </h4>

            <span>
              Quick URL generation
            </span>

          </div>

          <div className="feature">

            <div className="feature-icon">
              🔒
            </div>

            <h4>
              Secure
            </h4>

            <span>
              Safe URL shortening
            </span>

          </div>

          <div className="feature">

            <div className="feature-icon">
              📱
            </div>

            <h4>
              Easy
            </h4>

            <span>
              Simple to share
            </span>

          </div>

        </div>

        <button
          className="copy-btn"
          onClick={() => setShowRegister(true)}
          style={{
            marginTop: "25px"
          }}
        >
          👤 Create Account
        </button>

        <button
          className="copy-btn"
          onClick={() => setShowLogin(true)}
          style={{
            marginTop: "10px"
          }}
        >
          🔐 Login
        </button>

      </div>

    </div>
  );
}

export default App;

