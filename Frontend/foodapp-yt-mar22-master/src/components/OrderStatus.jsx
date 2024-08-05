// src/components/OrderStatus.js
import React, { useState, useEffect } from "react";
import axios from 'axios';

const OrderStatus = ({ orderId }) => {
  const [orderDetails, setOrderDetails] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchOrderDetails = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/api/v1/orders/${orderId}`);
        setOrderDetails(response.data);
      } catch (error) {
        setError("Failed to fetch order details.");
      } finally {
        setLoading(false);
      }
    };

    fetchOrderDetails();
  }, [orderId]);

  if (loading) return <p>Loading...</p>;
  if (error) return <p>{error}</p>;

  return (
    <div className="order-status">
      <h2>Order ID: {orderDetails.id}</h2>
      <p>Status: {orderDetails.status}</p>
      <p>Customer: {orderDetails.customer.name}</p>
      <p>Shop: {orderDetails.shop.name}</p>
      <h3>Items:</h3>
      <ul>
        {orderDetails.orderItems.map((item) => (
          <li key={item.productId}>
            {item.productName} - {item.quantity} x ${item.price}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default OrderStatus;
