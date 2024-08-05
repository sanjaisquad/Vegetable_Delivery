import React, { useEffect, useState } from 'react';
import api from '../../services/api';

const ShopDashboard = () => {
  const [shopDetails, setShopDetails] = useState(null);

  useEffect(() => {
    const fetchShopDetails = async () => {
      try {
        const response = await api.get('/shop/dashboard');
        setShopDetails(response.data);
      } catch (error) {
        console.error('Failed to fetch shop details', error);
      }
    };

    fetchShopDetails();
  }, []);

  return (
    <div>
      <h1>Shop Dashboard</h1>
      {shopDetails ? (
        <div>
          <p>Shop Name: {shopDetails.name}</p>
          <p>Address: {shopDetails.address}</p>
          <p>Approved: {shopDetails.isApproved ? 'Yes' : 'No'}</p>
          {/* Add more shop details as needed */}
        </div>
      ) : (
        <p>Loading...</p>
      )}
    </div>
  );
};

export default ShopDashboard;
