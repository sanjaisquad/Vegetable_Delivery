import React from 'react';

const DeliveryPartnerProfile = () => {
    const user = JSON.parse(localStorage.getItem('user'));

    return (
        <div className="p-6 bg-white shadow-md rounded-lg max-w-md mx-auto">
            <h1 className="text-2xl font-bold mb-4">Delivery Partner Profile</h1>
            {user ? (
                <div>
                    <img
                        src={user.photoURL || Avatar}
                        alt="User Avatar"
                        className="w-24 h-24 rounded-full mx-auto mb-4"
                    />
                    <p className="text-lg"><strong>Name:</strong> {user.name}</p>
                    <p className="text-lg"><strong>Email:</strong> {user.email}</p>
                    <p className="text-lg"><strong>User Type:</strong> {user.userType}</p>
                </div>
            ) : (
                <p className="text-lg">No user information available.</p>
            )}
        </div>
    );
};

export default DeliveryPartnerProfile;
