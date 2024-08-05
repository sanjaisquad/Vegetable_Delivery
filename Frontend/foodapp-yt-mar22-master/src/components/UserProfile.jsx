import React, { useState, useEffect } from 'react';
import ShopList from './ShopList';
import { useStateValue } from '../context/StateProvider';
import axios from 'axios';
import avatar from '../img/avatar.png';

const UserProfile = () => {
    const [user, setUser] = useState(JSON.parse(localStorage.getItem('user')));
    const [{ Allshop }, dispatch] = useStateValue();
    const [orders, setOrders] = useState([]);
    const [error, setError] = useState(null);

    console.log("list of all shops => ", Allshop);

    useEffect(() => {
        if (user) {
            const fetchOrders = async () => {
                try {
                    const response = await axios.get(`http://localhost:8080/api/v1/orders/customer/${user.id}`);
                    if (response.status === 200 && response.data) {
                        // Fetch detailed information for each order
                        const orderDetailsPromises = response.data.map(order =>
                            axios.get(`http://localhost:8080/api/v1/orders/${order.id}`).then(res => res.data)
                        );
                        const detailedOrders = await Promise.all(orderDetailsPromises);
                        setOrders(detailedOrders);
                    } else {
                        setOrders([]);
                    }
                } catch (error) {
                    setError('Error fetching orders.');
                    console.error('Error fetching orders:', error);
                }
            };

            fetchOrders();
        }
    }, [user]);

    const handleShopClick = (shopId) => {
        setUser({ ...user, shop: shopId });
        localStorage.setItem('user', JSON.stringify({ ...user, shop: shopId }));
    };

    return (
        <div className="p-6 bg-white shadow-md rounded-lg max-w-md mx-auto">
            <h1 className="text-2xl font-bold mb-4">User Profile</h1>
            {user ? (
                <div>
                    <img
                        src={avatar}
                        alt="User Avatar"
                        className="w-24 h-24 rounded-full mx-auto mb-4"
                    />
                    <p className="text-lg"><strong>Name:</strong> {user.name}</p>
                    <p className="text-lg"><strong>Email:</strong> {user.email}</p>
                    <p className="text-lg"><strong>User Type:</strong> {user.userType}</p>
                    {user.shop && <p className="text-lg"><strong>Shop ID:</strong> {user.shop}</p>}
                </div>
            ) : (
                <p className="text-lg">No user information available.</p>
            )}

            <ShopList shops={Allshop} onShopClick={handleShopClick} />

            <h2 className="text-xl font-bold mt-6 mb-4">Your Orders</h2>
            {error && <p className="text-red-500">{error}</p>}
            {orders.length > 0 ? (
                <div className="space-y-4">
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
                        </div>
                    ))}
                </div>
            ) : (
                <p>No orders available.</p>
            )}
        </div>
    );
};

export default UserProfile;
