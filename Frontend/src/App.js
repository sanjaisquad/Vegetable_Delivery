import React, { useEffect } from 'react';
import {Routes ,Route } from 'react-router-dom'
import './App.css';
import { Header, CreateContainer, MainContainer } from './components'
import { AnimatePresence } from 'framer-motion'
import { useStateValue } from './Context/StateProvider';
import { getAllFoodItems } from './utils/fireBaseFunctions';


function App() {
  const [{foodItems }, dispatch] = useStateValue();
  const fetchData = async () => { 
    await getAllFoodItems().then(data => {
      //console.log(data)
      dispatch({
                type: "SET_FOOD_ITEMS",
                foodItems: data
            });
    })
  }
  useEffect(() => {
    fetchData()
  }, []);
  
  return (
    <AnimatePresence exitBeforeEnter >
      <div className="w-screen h-auto flex flex-col bg-primary">
        <Header />
        <main className='mt-14 md:mt-20 px-4 md:px-16 py-4 w-full'>
          <Routes>
            <Route path="/" element={<MainContainer />} />
            <Route path="/createItem" element={<CreateContainer />} />
          </Routes>
        </main>
      
      </div>
    </AnimatePresence>
    
  );
}

export default App;