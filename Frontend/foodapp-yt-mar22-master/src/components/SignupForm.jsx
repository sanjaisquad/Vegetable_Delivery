import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useStateValue } from '../context/StateProvider';
import { actionType } from '../context/reducer';
import './AuthPage.css'; // Include your CSS file

const SignupForm = () => {
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    password: '',
    mobileNumber: '',
    address: '',
    userType: 'CUSTOMER',  // Assuming 'CUSTOMER' as default value
    shop: ''  // Initialize shop as an empty string
  });
  const [isRegistered, setIsRegistered] = useState(false);
  const [registeredUser, setRegisteredUser] = useState(null);
  const [{ user }, dispatch] = useStateValue();
  const [error, setError] = useState('');
  const [message, setMessage] = useState('');
  const [shops, setShops] = useState([]); // State to hold shop data

  // Fetch shop data
  useEffect(() => {
    const fetchShops = async () => {
      try {
        const response = await axios.get('http://localhost:8080/api/v1/shops');
        setShops(response.data);
      } catch (error) {
        console.error('Error fetching shops:', error);
      }
    };

    fetchShops();
  }, []);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/api/v1/users/register', formData);
      dispatch({ type: actionType.SET_USER, user: response.data });
      localStorage.setItem('user', JSON.stringify(response.data));
      setMessage('Registration successful');
      setError('');
      setIsRegistered(true);
      setRegisteredUser(response.data);
    } catch (error) {
      console.error('Error registering user:', error);
      if (error.response) {
        setMessage(`Error ${error.response.status}: ${error.response.data.message}`);
      } else {
        setMessage('An unknown error occurred');
      }
      setError('Registration failed, please try again.');
    }
  };

  return (
    <div>
      {!isRegistered ? (
        <form onSubmit={handleSubmit}>
          <input
            type="text"
            name="name"
            value={formData.name}
            onChange={handleChange}
            placeholder="Name"
            required
          />
          <br />
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
          <input
            type="text"
            name="mobileNumber"
            value={formData.mobileNumber}
            onChange={handleChange}
            placeholder="Mobile Number"
            required
          />
          <br />
          <input
            type="text"
            name="address"
            value={formData.address}
            onChange={handleChange}
            placeholder="Address"
            required
          />
          <br />
          <select
            name="userType"
            value={formData.userType}
            onChange={handleChange}
            required
          >
            <option value="CUSTOMER">Customer</option>
            <option value="SHOP_ADMIN">Admin</option>
            <option value="DELIVERY_PARTNER">Seller</option>
            {/* Add more user types as needed */}
          </select>
          <br />
          {formData.userType === 'CUSTOMER' && (
            <>
              <select
                name="shop"
                value={formData.shop}
                onChange={handleChange}
                required
              >
                <option value="">Select Shop</option>
                {shops.map((shop) => (
                  <option key={shop.id} value={shop.id}>
                    {shop.name}
                  </option>
                ))}
              </select>
              <br />
            </>
          )}
          <button type="submit" className="submit-button">Submit</button>
          {message && <p className="message">{message}</p>}
          {error && <p className="error-message">{error}</p>}
        </form>
      ) : (
        <div className="registration-success">
          <h2>Registration Successful!</h2>       
        </div>
      )}
    </div>
  );
};

export default SignupForm;
