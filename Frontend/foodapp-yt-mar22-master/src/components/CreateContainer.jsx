import React, { useState, useEffect } from "react";
import { motion } from "framer-motion";
import { MdFastfood, MdAttachMoney, MdFoodBank, MdCheckCircle, MdCancel } from "react-icons/md";
import axios from "axios";
import { getAllFoodItems } from "../utils/firebaseFunctions";
import { actionType } from "../context/reducer";
import { useStateValue } from "../context/StateProvider";
import { fetchShop,fetchUser } from "../utils/fetchLocalStorageData";

const CreateContainer = () => {
  const [selectedProduct, setSelectedProduct] = useState(null);
  const [price, setPrice] = useState('');
  const [stock, setStock] = useState('');
  const [availability, setAvailability] = useState(true);
  const [fields, setFields] = useState(false);
  const [alertStatus, setAlertStatus] = useState("danger");
  const [msg, setMsg] = useState(null);
  const [isLoading, setIsLoading] = useState(false);
  const [{ foodItems = []}, dispatch] = useStateValue(); // Initialize foodItems as an empty array

  useEffect(() => {
    fetchData();
  }, []);
  const user = fetchUser();
  const shop = fetchShop();
  const fetchData = async () => {
    const data = await getAllFoodItems();
    dispatch({
      type: actionType.SET_FOOD_ITEMS,
      foodItems: data,
    });
  };

  const handleProductSelect = (product) => {
    setSelectedProduct(product);
  };

  const saveDetails = async () => {
    if (!user || !shop) {
      setFields(true);
      setMsg("Please login to continue.");
      setAlertStatus("danger");
      return;
    }

    if (!selectedProduct || !price || !stock) {
      setFields(true);
      setMsg("Please select a product and fill in all fields.");
      setAlertStatus("danger");
      setTimeout(() => setFields(false), 4000);
      return;
    }

    setIsLoading(true);

    const data = {
      
      availability: availability,
      stock: stock,
      price:price
    };

    try {
      await axios.post(`http://localhost:8080/api/v1/shops/products?shopId=${shop.id}&productId=${selectedProduct.id}`, data, {
        headers: {
          "Content-Type": "application/json",
        },
      });

      setIsLoading(false);
      setFields(true);
      setMsg("Product added successfully.");
      setAlertStatus("success");
      setTimeout(() => setFields(false), 4000);
    } catch (error) {
      console.error("Error adding product:", error);
      setFields(true);
      setMsg("Error adding product. Please try again.");
      setAlertStatus("danger");
      setIsLoading(false);
    }
  };

  // Ensure foodItems is an array before filtering
  const fruits = foodItems?.filter(item => item.catagori === 'fruit') || [];
  const vegetables = foodItems?.filter(item => item.catagori === 'vegetable') || [];

  return (
    <div className="w-full min-h-screen flex items-center justify-center">
      <div className="w-[90%] md:w-[50%] border border-gray-300 rounded-lg p-4 flex flex-col items-center justify-center gap-4">
        {fields && (
          <motion.p
            initial={{ opacity: 0 }}
            animate={{ opacity: 1 }}
            exit={{ opacity: 0 }}
            className={`w-full p-2 rounded-lg text-center text-lg font-semibold ${
              alertStatus === "danger"
                ? "bg-red-400 text-red-800"
                : "bg-emerald-400 text-emerald-800"
            }`}
          >
            {msg}
          </motion.p>
        )}

        <div className="w-full py-2 border-b border-gray-300 flex items-center gap-2">
          <MdFastfood className="text-xl text-gray-700" />
          <select
            onChange={(e) =>
              handleProductSelect(
                foodItems.find((item) => item.id === parseInt(e.target.value))
              )
            }
            className="outline-none w-full text-base border-b-2 border-gray-200 p-2 rounded-md cursor-pointer"
          >
            <option value="">Select Product</option>
            <optgroup label="Fruits">
              {fruits.map((item) => (
                <option key={item.id} value={item.id}>
                  {item.name}
                </option>
              ))}
            </optgroup>
            <optgroup label="Vegetables">
              {vegetables.map((item) => (
                <option key={item.id} value={item.id}>
                  {item.name}
                </option>
              ))}
            </optgroup>
          </select>
        </div>

        {selectedProduct && (
          <>
            <div className="w-full flex items-center gap-2">
              <div className="w-1/2 py-2 border-b border-gray-300 flex items-center gap-2">
                <MdFoodBank className="text-gray-700 text-2xl" />
                <input
                  type="text"
                  value={selectedProduct.category}
                  readOnly
                  className="w-full h-full text-lg bg-transparent outline-none border-none placeholder:text-gray-400 text-textColor"
                />
              </div>

              <div className="w-1/2 py-2 border-b border-gray-300 flex items-center gap-2">
                <MdAttachMoney className="text-gray-700 text-2xl" />
                <input
                  type="number"
                  value={price}
                  onChange={(e) => setPrice(e.target.value)}
                  placeholder="Price"
                  className="w-full h-full text-lg bg-transparent outline-none border-none placeholder:text-gray-400 text-textColor"
                />
              </div>
            </div>

            <div className="w-full py-2 border-b border-gray-300 flex items-center gap-2">
              <MdFastfood className="text-xl text-gray-700" />
              <input
                type="number"
                value={stock}
                onChange={(e) => setStock(e.target.value)}
                placeholder="Stock"
                className="w-full h-full text-lg bg-transparent outline-none border-none placeholder:text-gray-400 text-textColor"
              />
            </div>

            <div className="w-full py-2 border-b border-gray-300 flex items-center gap-2">
              <MdCheckCircle className="text-xl text-gray-700" />
              <button
                type="button"
                onClick={() => setAvailability(true)}
                className={`w-1/2 py-2 rounded-lg ${availability ? 'bg-green-500' : 'bg-gray-300'}`}
              >
                Available
              </button>
              <MdCancel className="text-xl text-gray-700" />
              <button
                type="button"
                onClick={() => setAvailability(false)}
                className={`w-1/2 py-2 rounded-lg ${!availability ? 'bg-red-500' : 'bg-gray-300'}`}
              >
                Unavailable
              </button>
            </div>
          </>
        )}

        <div className="w-full flex items-center justify-center">
          <button
            type="button"
            className="w-full md:w-auto border-none outline-none bg-emerald-500 px-12 py-2 rounded-lg text-lg text-white font-semibold"
            onClick={saveDetails}
          >
            Save
          </button>
        </div>
      </div>
    </div>
  );
};

export default CreateContainer;
