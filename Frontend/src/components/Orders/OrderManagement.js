import React, { useEffect, useState } from 'react';
import api from '../../services/api';

const OrderManagement = () => {
  const [orders, setOrders] = useState([]);

  useEffect(() => {
    const fetchOrders = async () => {
      try {
        const response = await api.get('/orders');
        setOrders(response.data);
      } catch (error) {
        console.error('Failed to fetch orders', error);
      }
    };

    fetchOrders();
  }, []);

  const handleApprove = async (orderId) => {
    try {
      await api.put(`/orders/${orderId}/approve`);
      setOrders(orders.map(order => order.id === orderId ? { ...order, status: 'APPROVED' } : order));
    } catch (error) {
      console.error('Failed to approve order', error);
    }
  };

  const handleDeliver = async (orderId) => {
    try {
      await api.put(`/orders/${orderId}/deliver`);
      setOrders(orders.map(order => order.id === orderId ? { ...order, status: 'DELIVERED' } : order));
    } catch (error) {
      console.error('Failed to deliver order', error);
    }
  };

  return (
    <div>
      <h1>Order Management</h1>
      {orders.length ? (
        <table>
          <thead>
            <tr>
              <th>Order ID</th>
              <th>Customer ID</th>
              <th>Shop ID</th>
              <th>Status</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {orders.map(order => (
              <tr key={order.id}>
                <td>{order.id}</td>
                <td>{order.customerId}</td>
                <td>{order.shopId}</td>
                <td>{order.status}</td>
                <td>
                  {order.status === 'PENDING' && <button onClick={() => handleApprove(order.id)}>Approve</button>}
                  {order.status === 'APPROVED' && <button onClick={() => handleDeliver(order.id)}>Deliver</button>}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <p>No orders available</p>
      )}
    </div>
  );
};

export default OrderManagement;
