import React from 'react';

const ShopList = ({ shops, onShopClick }) => {
    return (
        <div>
            <h2 className="text-xl font-bold mb-4">Shop List</h2>
            {shops && shops?.length > 0 ? (
                <div className="max-h-96 overflow-y-scroll">
                    <ul>
                        {shops.map((shop) => (
                            <li key={shop.id} className="mb-2">
                                <div onClick={() => onShopClick(shop.id)} className="cursor-pointer">
                                    <p><strong>ID:</strong> {shop.id}</p>
                                    <p><strong>Name:</strong> {shop.name}</p>
                                    <p><strong>Address:</strong> {shop.address}</p>
                                </div>
                            </li>
                        ))}
                    </ul>
                </div>
            ) : (
                <p>No shops available.</p>
            )}
        </div>
    );
};

export default ShopList;
