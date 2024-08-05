import React, { useState } from 'react';
import axios from 'axios';
import { useStateValue } from '../context/StateProvider';
import { actionType } from '../context/reducer';
import './AuthPage.css'; // Include your CSS file

const LoginForm = () => {
  const [formData, setFormData] = useState({ email: '', password: '' });
  const [{ user }, dispatch] = useStateValue();
  const [error, setError] = useState('');
  const [message, setMessage] = useState('');

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/api/v1/users/login', formData);
      dispatch({ type: actionType.SET_USER, user: response.data });
      localStorage.setItem('user', JSON.stringify(response.data));
      setMessage('Login successful');
      setError('');
    } catch (error) {
      console.error('Error logging in:', error);
      if (error.response) {
        setMessage(`Error ${error.response.status}: ${error.response.data.message}`);
      } else {
        setMessage('An unknown error occurred');
      }
      setError('Login failed, please check your email and password and try again.');
    }
  };

  return (
    <div>
      <form onSubmit={handleSubmit}>
        <input
          type="email"
          name="email"
          value={formData.email}
          onChange={handleChange}
          placeholder="Email"
          required
        />
        <br />
        <input
          type="password"
          name="password"
          value={formData.password}
          onChange={handleChange}
          placeholder="Password"
          required
        />
        <br />
        <button type="submit" className="submit-button">submit</button>
        {message && <p className="message">{message}</p>}
        {error && <p className="error-message">{error}</p>}
      </form>
    </div>
  );
};

export default LoginForm;
