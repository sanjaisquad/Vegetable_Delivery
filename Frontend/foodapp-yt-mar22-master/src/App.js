import React, { useEffect } from "react";
import { Route, Routes } from "react-router-dom";
import { AnimatePresence } from "framer-motion";
import { CreateContainer, Header, MainContainer } from "./components";
import { useStateValue } from "./context/StateProvider";
import { getAllFoodItems, getAllshops } from "./utils/firebaseFunctions";
import { actionType } from "./context/reducer";
import AuthPage from './components/AuthPage'; // import your AuthPage component
import UserProfile from './components/UserProfile';
import DeliveryPartnerProfile from './components/DeliveryPartnerProfile';
import ShopAdminDashboard from './components/ShopAdminDashboard';
import AdminProfile from './components/adminprofile'; // Create AdminProfile component
import MyShop from './components/MyShop';

const App = () => {
  const [{ foodItems, Allshop }, dispatch] = useStateValue(); // Correct destructuring

  const fetchData = async () => {
    const data = await getAllFoodItems();
    console.log('Fetched food items:', data);
    dispatch({
      type: actionType.SET_FOOD_ITEMS,
      foodItems: data,
    });
  };

  const fetchshopData = async () => {
    const dataa = await getAllshops();
    console.log('Fetched shops:', dataa);
    dispatch({
      type: actionType.ALL_SHOPS,
      Allshop: dataa,
    });
  };

  useEffect(() => {
    fetchData();
    fetchshopData();
  }, []); // Correct dependency array

  return (
    <AnimatePresence exitBeforeEnter>
      <div className="w-screen h-auto flex flex-col bg-primary">
        <Header />
        <main className="mt-14 md:mt-20 px-4 md:px-16 py-4 w-full">
          <Routes>
            <Route path="/*" element={<MainContainer />} />
            <Route path="/createItem" element={<CreateContainer />} />
            <Route path="/auth" element={<AuthPage />} />
            <Route path="/userprofile" element={<UserProfile />} />
            <Route path="/deliverypartnerprofile" element={<DeliveryPartnerProfile />} />
            <Route path="/shopAdminDashboard" element={<ShopAdminDashboard />} />
            <Route path="/adminProfile" element={<AdminProfile />} />
            <Route path="/myShop" element={<MyShop />} />
          </Routes>
        </main>
      </div>
    </AnimatePresence>
  );
};

export default App;
