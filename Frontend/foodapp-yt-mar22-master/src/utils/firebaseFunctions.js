import axios from 'axios';

// Base URL for API
const API_URL = 'http://localhost:8080/api/v1/';

// Saving new Item
export const saveItem = async (data) => {
  try {
    await axios.post(`${API_URL}products`, data);
  } catch (error) {
    console.error('Error saving item:', error);
  }
};

// Get all food items
export const getAllFoodItems = async () => {
  const user = JSON.parse(localStorage.getItem('user'));
  try {
    let response;
    if (user && user.shop) {
      // If user is present and has a shop, fetch products from the user's shop
      response = await axios.get(`${API_URL}shops/${user.shop}/products`);
      console.log("im on main calling food ",response.data);
    } else {
      console.log("im in default call")
      // If no user is present, fetch all products
      response = await axios.get(`${API_URL}products`);
    }
    console.log("response from api ",response)
    return response.data;
  } catch (error) {
    console.error('Error from generating ... :)', error);
    return [];
  }
};

//Get all approved shops

export const getAllshops = async () => {
  try {
    const response = await axios.get(`${API_URL}shops`);
    return response.data;
  } catch (error) {
    console.error('Error from generating ... :)', error);
    return [];
  }
};