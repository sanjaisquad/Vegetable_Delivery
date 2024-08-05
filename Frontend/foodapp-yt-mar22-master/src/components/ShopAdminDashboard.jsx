import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import { useStateValue } from "../context/StateProvider";

const ShopAdminDashboard = () => {
  const [orders, setOrders] = useState([]);
  const [error, setError] = useState(null);
  const [{ shop }] = useStateValue();

  // Assuming the shopId is stored in the user's profile or obtained via a global state
  console.log("shop name", shop);

  useEffect(() => {
    const fetchOrders = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/api/v1/orders/shop/${shop.id}/notifications`);
        if (response.status === 200 && response.data) {
          setOrders(response.data); // Assuming response.data is an array of orders
        } else {
          setOrders([]);
        }
        console.log("order...", response);
      } catch (error) {
        setError('Error fetching orders.');
        console.error('Error fetching orders:', error);
      }
    };

    fetchOrders();
  }, [shop]);

  const handleUpdateStatus = async (orderId, newStatus) => {
    try {
      await axios.put(`http://localhost:8080/api/v1/orders/${orderId}/status`, { status: newStatus });
      setOrders(orders.map(order => order.id === orderId ? { ...order, status: newStatus } : order));
    } catch (error) {
      setError('Error updating order status.');
      console.error('Error updating order status:', error);
    }
  };
  console.log(orders)

  return (
    <div className="flex">
      <div className="w-1/4 bg-gray-200 h-screen p-4">
        <h2 className="text-xl font-bold mb-4">Admin Dashboard</h2>
        <ul className="space-y-4">
          <li>
            <Link to="/adminProfile">
              <button type="button"
                      className="w-full md:w-auto border-none outline-none bg-emerald-500 px-12 py-2 rounded-lg text-lg text-white font-semibold">
                Admin Profile
              </button>
            </Link>
          </li>
          <li>
            <Link to="/myShop">
              <button type="button"
                      className="w-full md:w-auto border-none outline-none bg-emerald-500 px-12 py-2 rounded-lg text-lg text-white font-semibold">
                My Shop
              </button>
            </Link>
          </li>
          <li>
            <Link to="/createItem">
              <button type="button"
                      className="w-full md:w-auto border-none outline-none bg-emerald-500 px-12 py-2 rounded-lg text-lg text-white font-semibold">
                Create Item
              </button>
            </Link>
          </li>
        </ul>
      </div>
      <div className="w-3/4 p-4">
        <h1 className="text-2xl font-bold mb-4">Your Shop Orders</h1>
        {error && <p className="text-red-500">{error}</p>}
        {orders.length > 0 ? (
          <div className="space-y-4">{orders}
            
            {orders.map(order => (  
              <div key={order.id} className="p-4 border rounded-lg bg-white shadow-md">
                <h2 className="text-lg font-bold">Order ID: {order.id}</h2>
                <p>Status: {order.status}</p>
                <p>Customer: {order.customer.name}</p>
                <ul>
                  {order.orderItems.map(item => (
                    <li key={item.id}>
                      {item.product.name} - {item.quantity} x ${item.price}
                    </li>
                  ))}
                </ul>
                <div className="mt-4">
                  <button
                    className="mr-2 bg-green-500 text-white px-4 py-2 rounded-lg"
                    onClick={() => handleUpdateStatus(order.id, 'Processing')}
                  >
                    Mark as Processing
                  </button>
                  <button
                    className="bg-yellow-500 text-white px-4 py-2 rounded-lg"
                    onClick={() => handleUpdateStatus(order.id, 'Delivery')}
                  >
                    Delivery
                  </button>

                  <button
                    className="bg-red-500 text-white px-4 py-2 rounded-lg"
                    onClick={() => handleUpdateStatus(order.id, 'Cancelled')}
                  >
                    Cancel Order
                  </button>
                </div>
              </div>
            ))}
          </div>
        ) : (
          <p>No orders available.</p>
        )}
      </div>
    </div>
  );
};

export default ShopAdminDashboard;
