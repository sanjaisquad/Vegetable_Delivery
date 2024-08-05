import { fetchAllShops, fetchCart, fetchUser } from "../utils/fetchLocalStorageData";
import { fetchShop, fetchShopProducts } from "../utils/fetchLocalStorageData"; // Add fetch functions for shop and shop products

const userInfo = fetchUser();
const cartInfo = fetchCart();
const shopInfo = fetchShop(); // Add initial shop data fetch
const shopProductsInfo = fetchShopProducts(); // Add initial shop products data fetch
const AllshopInfo = fetchAllShops();

export const initialState = {
  user: userInfo,
  foodItems: null,
  cartShow: false,
  cartItems: cartInfo,
  shop: shopInfo, // Add shop to initial state
  shopProducts: shopProductsInfo, // Add shop products to initial state
  Allshop: AllshopInfo,
};
