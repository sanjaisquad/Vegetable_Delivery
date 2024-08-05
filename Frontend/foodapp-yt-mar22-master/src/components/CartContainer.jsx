import React, { useEffect, useState } from "react";
import { MdOutlineKeyboardBackspace } from "react-icons/md";
import { RiRefreshFill } from "react-icons/ri";
import { motion } from "framer-motion";
import { useStateValue } from "../context/StateProvider";
import { actionType } from "../context/reducer";
import EmptyCart from "../img/emptyCart.svg";
import CartItem from "./CartItem";
import OrderStatus from "./OrderStatus"; // Import the OrderStatus component
import axios from 'axios';

const CartContainer = () => {
  const [{ cartShow, cartItems, user, shop }, dispatch] = useStateValue();
  const [tot, setTot] = useState(0);
  const [responseMessage, setResponseMessage] = useState('');
  const [orderId, setOrderId] = useState(null);
  const [showSuccessModal, setShowSuccessModal] = useState(false);
  

  const showCart = () => {
    dispatch({
      type: actionType.SET_CART_SHOW,
      cartShow: !cartShow,
    });
  };

  useEffect(() => {
    let totalPrice = cartItems.reduce((accumulator, item) => {
      return accumulator + item.qty * item.price;
    }, 0);
    setTot(totalPrice);
  }, [cartItems]);

  const clearCart = () => {
    dispatch({
      type: actionType.SET_CARTITEMS,
      cartItems: [],
    });

    localStorage.setItem("cartItems", JSON.stringify([]));
  };

  const handleCheckout = async () => {
    if (!user) {
      alert('Please log in to check out.');
      return;
    }

    const orderData = {
      status: "Pending",
      orderDate: new Date().toISOString().split('T')[0], // Add the current date
      customer: {
        id: user.id
      },
      orderItems: cartItems.map(item => ({
        product: {
          id: item.id
        },
        quantity: item.qty,
        price: item.price
      }))
    };

    try {
      console.log(user)
      const response = await axios.post(`http://localhost:8080/api/v1/orders/1`, orderData);
      if (response.status === 200) {
        setResponseMessage('Order created successfully!');
        setShowSuccessModal(true);
        setOrderId(response.data.id); // Set the orderId to display order status
        clearCart(); // Clear the cart after successful checkout
      }
    } catch (error) {
      setResponseMessage('Error creating order. Please try again.');
      console.error('Error creating order:', error);
    }
  };

  const handleCloseModal = () => {
    setShowSuccessModal(false);
  };

  return (
    <motion.div
      initial={{ opacity: 0, x: 200 }}
      animate={{ opacity: 1, x: 0 }}
      exit={{ opacity: 0, x: 200 }}
      className="fixed top-0 right-0 w-full md:w-375 h-screen bg-white drop-shadow-md flex flex-col z-[101]"
    >
      <div className="w-full flex items-center justify-between p-4 cursor-pointer">
        <motion.div whileTap={{ scale: 0.75 }} onClick={showCart}>
          <MdOutlineKeyboardBackspace className="text-textColor text-3xl" />
        </motion.div>
        <p className="text-textColor text-lg font-semibold">Cart</p>

        <motion.p
          whileTap={{ scale: 0.75 }}
          className="flex items-center gap-2 p-1 px-2 my-2 bg-gray-100 rounded-md hover:shadow-md cursor-pointer text-textColor text-base"
          onClick={clearCart}
        >
          Clear <RiRefreshFill />
        </motion.p>
      </div>

      {cartItems && cartItems.length > 0 ? (
        <div className="w-full h-full bg-cartBg rounded-t-[2rem] flex flex-col">
          <div className="w-full h-340 md:h-42 px-6 py-10 flex flex-col gap-3 overflow-y-scroll scrollbar-none">
            {cartItems.map((item) => (
              <CartItem
                key={item.id}
                item={item}
                setFlag={setTot} // Update the total whenever an item is modified
                flag={tot}
              />
            ))}
          </div>

          <div className="w-full flex-1 bg-cartTotal rounded-t-[2rem] flex flex-col items-center justify-evenly px-8 py-2">
            <div className="w-full flex items-center justify-between">
              <p className="text-gray-400 text-lg">Sub Total</p>
              <p className="text-gray-400 text-lg">$ {tot.toFixed(2)}</p>
            </div>
            <div className="w-full flex items-center justify-between">
              <p className="text-gray-400 text-lg">Delivery</p>
              <p className="text-gray-400 text-lg">$ 0.00</p>
            </div>

            <div className="w-full border-b border-gray-600 my-2"></div>

            <div className="w-full flex items-center justify-between">
              <p className="text-gray-200 text-xl font-semibold">Total</p>
              <p className="text-gray-200 text-xl font-semibold">
                ${tot.toFixed(2)}
              </p>
            </div>

            <motion.button
              whileTap={{ scale: 0.8 }}
              type="button"
              className="w-full p-2 rounded-full bg-gradient-to-tr from-orange-400 to-orange-600 text-gray-50 text-lg my-2 hover:shadow-lg"
              onClick={handleCheckout}
            >
              {user ? 'Check Out' : 'Login to check out'}
            </motion.button>
            {responseMessage && (
              <p className="text-red-500 text-lg mt-2">{responseMessage}</p>
            )}
            {orderId && <OrderStatus orderId={orderId} />} {/* Display order status */}
          </div>
        </div>
      ) : (
        <div className="w-full h-full flex flex-col items-center justify-center gap-6">
          <img src={EmptyCart} className="w-300" alt="Empty Cart" />
          <p className="text-xl text-textColor font-semibold">
            Add some items to your cart
          </p>
        </div>
      )}

      {showSuccessModal && (
        <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 z-[102]">
          <div className="bg-white p-8 rounded-lg shadow-lg">
            <h2 className="text-xl font-bold mb-4">Success</h2>
            <p>{responseMessage}</p>
            <button
              className="mt-4 px-4 py-2 bg-blue-500 text-white rounded-lg"
              onClick={handleCloseModal}
            >
              Close
            </button>
          </div>
        </div>
      )}
    </motion.div>
  );
};

export default CartContainer;
