import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useStateValue } from '../context/StateProvider';
import { actionType } from '../context/reducer';
import { fetchUser } from '../utils/fetchLocalStorageData';

const MyShop = () => {
    const [{ shop, shopProducts }, dispatch] = useStateValue();
    const user = fetchUser();
    const [shopName, setShopName] = useState('');
    const [shopAddress, setShopAddress] = useState('');
    const [editProducts, setEditProducts] = useState([]);
    console.log("shopProducts:", shopProducts)

    useEffect(() => {
        if (user && !shop) {
            const storedShop = JSON.parse(localStorage.getItem('shop'));
            const storedShopProducts = JSON.parse(localStorage.getItem('shopProducts'));

            if (storedShop) {
                dispatch({ type: actionType.SET_SHOP, shop: storedShop });
                dispatch({ type: actionType.SET_SHOP_PRODUCTS, shopProducts: storedShopProducts || [] });

                if (storedShop.approved) {
                    fetchShopProducts(storedShop.id);
                }
            }
        }
    }, [user, shop, dispatch]);

    const fetchShopDetailsByAdminID = async (adminId) => {
        try {
            const response = await axios.get(`http://localhost:8080/api/v1/shopAdmins/${adminId}/shop`);
            const shopData = response.data;

            dispatch({ type: actionType.SET_SHOP, shop: shopData });
            localStorage.setItem('shop', JSON.stringify(shopData));

            if (shopData.approved) {
                fetchShopProducts(shopData.id);
            }
        } catch (error) {
            console.error('Error fetching shop details:', error);
        }
    };

    const fetchShopProducts = async (shopId) => {
        try {
            const response = await axios.get(`http://localhost:8080/api/v1/shops/${shopId}/products`);
            const productsData = response.data;

            dispatch({ type: actionType.SET_SHOP_PRODUCTS, shopProducts: productsData || [] });
            localStorage.setItem('shopProducts', JSON.stringify(productsData));
        } catch (error) {
            console.error('Error fetching shop products:', error);
        }
    };

    const handleRegisterShop = async (e) => {
        e.preventDefault();

        try {
            const response = await axios.post(`http://localhost:8080/api/v1/shopAdmins/shops/request?shopAdminId=${user.id}`, {
                name: shopName,
                address: shopAddress
            });

            const shopData = response.data;
            dispatch({ type: actionType.SET_SHOP, shop: shopData });
            localStorage.setItem('shop', JSON.stringify(shopData));
            console.log(shopData)

            if (!shopData.approved) {
                fetchShopDetails(shopData.id);
            }
        } catch (error) {
            console.error('Error registering shop:', error);
        }
    };

    const handleEditProducts = () => {
        setEditProducts(shopProducts);
    };

    const handleChangeProductDetails = (e, index) => {
        const { name, value, checked, type } = e.target;
        const updatedProducts = [...editProducts];
        updatedProducts[index] = {
            ...updatedProducts[index],
            [name]: type === 'checkbox' ? checked : value
        };
        setEditProducts(updatedProducts);
    };

    const handleSaveProductDetails = async () => {
        try {
            await axios.put(`http://localhost:8080/api/v1/shops/${shop.id}/products`, editProducts);

            dispatch({ type: actionType.SET_SHOP_PRODUCTS, shopProducts: editProducts });
            localStorage.setItem('shopProducts', JSON.stringify(editProducts));
            setEditProducts([]);
            alert('Products updated successfully');
        } catch (error) {
            console.error('Error updating products:', error);
        }
    };

    if (!user) {
        return <p>Please login to continue.</p>;
    }

    if (!shop) {
        return (
            <form onSubmit={handleRegisterShop} className="h-screen flex flex-col justify-center items-center">
                <h1 className="text-2xl font-bold mb-4">Register My Shop</h1>
                <div className="mb-4 w-full max-w-md">
                    <label className="block text-gray-700">Shop Name</label>
                    <input
                        type="text"
                        value={shopName}
                        onChange={(e) => setShopName(e.target.value)}
                        className="mt-1 p-2 w-full border rounded"
                        required
                    />
                </div>
                <div className="mb-4 w-full max-w-md">
                    <label className="block text-gray-700">Shop Address</label>
                    <input
                        type="text"
                        value={shopAddress}
                        onChange={(e) => setShopAddress(e.target.value)}
                        className="mt-1 p-2 w-full border rounded"
                        required
                    />
                </div>
                <button
                    type="submit"
                    className="bg-blue-500 text-white py-2 px-4 rounded hover:bg-blue-700"
                >
                    Register Shop
                </button>or
                <button
                    className="bg-blue-500 text-white py-2 px-4 rounded hover:bg-blue-700"
                    onClick={() => fetchShopDetailsByAdminID(user.id)}
                >
                    shop exist
                </button>
            </form>
        );
    }

    if (!shop.approved) {
        return (
            <div className="p-6 bg-white shadow-md rounded-lg max-w-md mx-auto h-screen flex flex-col justify-center items-center">
                <h1 className="text-2xl font-bold mb-4">Shop Registration Pending</h1>
                <p>Your shop registration is pending approval.</p>
                <button
                    className="bg-yellow-500 text-white py-2 px-4 rounded"
                    onClick={() => fetchShopDetailsByAdminID(user.id)}
                >
                    Refresh
                </button>
            </div>
        );
    }

    return (
        <div className="p-6 bg-white shadow-md rounded-lg max-w-screen-lg mx-auto h-screen flex flex-col">
            <h1 className="text-2xl font-bold mb-4">My Shop</h1>
            <p><strong>Name:</strong> {shop.name}</p>
            <p><strong>Address:</strong> {shop.address}</p>
            <p><strong>Status:</strong> {shop.approved ? 'Approved' : 'Pending'}</p>
            <h2 className="text-xl font-bold mt-6">Products</h2>
            <div className="flex-grow">
                <button
                    className="bg-green-500 text-white py-2 px-4 rounded"
                    onClick={() => fetchShopProducts(shop.id)}
                >Refresh Products
                </button>
                <table className="table-auto w-full mt-4">
                    <thead>
                        <tr>
                            <th className="px-4 py-2">S.No</th>
                            <th className="px-4 py-2">Product Name</th>
                            <th className="px-4 py-2">Availability</th>
                            <th className="px-4 py-2">Stock</th>
                            <th className="px-4 py-2">Price</th>
                        </tr>
                    </thead>
                    <tbody>
                        {(editProducts.length > 0 ? editProducts : shopProducts)?.map((product, index) => (
                            <tr key={product.id}>
                                <td className="border px-4 py-2">{index + 1}</td>
                                <td className="border px-4 py-2">
                                    {editProducts.length > 0 ? (
                                        <input
                                            type="text"
                                            name="name"
                                            value={product.product.name}
                                            onChange={(e) => handleChangeProductDetails(e, index)}
                                            className="w-full"
                                        />
                                    ) : (
                                        product.product.name
                                    )}
                                </td>
                                <td className="border px-4 py-2">
                                    {editProducts.length > 0 ? (
                                        <input
                                            type="checkbox"
                                            name="avilablity"
                                            checked={product.avilablity}
                                            onChange={(e) => handleChangeProductDetails(e, index)}
                                            className="w-full"
                                        />
                                    ) : (
                                        product.avilablity ? 'Available' : 'Unavailable'
                                    )}
                                </td>
                                <td className="border px-4 py-2">
                                    {editProducts.length > 0 ? (
                                        <input
                                            type="number"
                                            name="stock"
                                            value={product.stock}
                                            onChange={(e) => handleChangeProductDetails(e, index)}
                                            className="w-full"
                                        />
                                    ) : (
                                        product.stock
                                    )}
                                </td>
                                <td className="border px-4 py-2">
                                    {editProducts.length > 0 ? (
                                        <input
                                            type="number"
                                            name="price"
                                            value={product.price}
                                            onChange={(e) => handleChangeProductDetails(e, index)}
                                            className="w-full"
                                        />
                                    ) : (
                                        product.price
                                    )}
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
            {editProducts.length > 0 ? (
                <div className="mt-4">
                    <button
                        className="bg-green-500 text-white py-2 px-4 rounded"
                        onClick={handleSaveProductDetails}
                    >
                        Save All Changes
                    </button>
                </div>
            ) : (
                <div className="mt-4">
                    <button
                        className="bg-yellow-500 text-white py-2 px-4 rounded"
                        onClick={handleEditProducts}
                    >
                        Edit Products
                    </button>
                </div>
            )}
        </div>
    );
};

export default MyShop;
