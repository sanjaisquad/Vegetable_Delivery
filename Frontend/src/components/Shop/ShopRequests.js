import React, { useState } from 'react';
import api from '../../services/api';

const ShopRequest = () => {
  const [shopName, setShopName] = useState('');
  const [shopAddress, setShopAddress] = useState('');
  const [message, setMessage] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await api.post('/shops/request', {
        name: shopName,
        address: shopAddress,
      });
      setMessage('Shop request submitted successfully. Awaiting approval.');
      setShopName('');
      setShopAddress('');
    } catch (error) {
      setMessage('Failed to submit shop request.');
      console.error('Error submitting shop request', error);
    }
  };

  return (
    <div>
      <h1>Request Shop Creation</h1>
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="shopName">Shop Name:</label>
          <input
            type="text"
            id="shopName"
            value={shopName}
            onChange={(e) => setShopName(e.target.value)}
            required
          />
        </div>
        <div>
          <label htmlFor="shopAddress">Shop Address:</label>
          <input
            type="text"
            id="shopAddress"
            value={shopAddress}
            onChange={(e) => setShopAddress(e.target.value)}
            required
          />
        </div>
        <button type="submit">Submit Request</button>
      </form>
      {message && <p>{message}</p>}
    </div>
  );
};

export default ShopRequest;
