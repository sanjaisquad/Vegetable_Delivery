// import { actionType } from './actionType';
export const actionType = {
  SET_USER: 'SET_USER',
  SET_SHOP: 'SET_SHOP',
  SET_SHOP_PRODUCTS: 'SET_SHOP_PRODUCTS',
  SET_CART_ITEMS: 'SET_CART_ITEMS',
  SET_CART_SHOW: 'SET_CART_SHOW',
  SET_FOOD_ITEMS: 'SET_FOOD_ITEMS',
  RESET_USER: "RESET_USER",
  CLEAR_CART_ITEMS: "CLEAR_CART_ITEMS",
  RESET_SHOP:"RESET_SHOP",
  CLEAR_SHOP_PRODUCTS:"CLEAR_SHOP_PRODUCTS",
  ALL_SHOPS:"ALL_SHOPS_ID"

};


const reducer = (state, action) => {
  switch (action.type) {
    case actionType.SET_USER:
      return {
        ...state,
        user: action.user,
      };

    case actionType.SET_FOOD_ITEMS:
      return {
        ...state,
        foodItems: action.foodItems,
      };

    case actionType.RESET_USER:
        localStorage.clear();
        return {
            ...state,
            user:null
        };

    case actionType.SET_CART_SHOW:
      return {
        ...state,
        cartShow: action.cartShow,
      };

    case actionType.SET_CARTITEMS:
      return {
        ...state,
        cartItems: action.cartItems,
      };

    case actionType.CLEAR_CART_ITEMS:
        localStorage.removeItem("cart")
        return {
            ...state,
            cartItems:action.cartItems
        };

    case actionType.SET_SHOP:
      return {
        ...state,
        shop: action.shop,
      };

    case actionType.RESET_SHOP:
        localStorage.clear();
        return{
          ...state,
          shop:null
        };

    case actionType.SET_SHOP_PRODUCTS:
      return {
        ...state,
        shopProducts: action.shopProducts,
      };
    
    case actionType.CLEAR_SHOP_PRODUCTS:
      localStorage.clear();
      return{
        ...state,
        shopProducts:null
      };
    
    case actionType.ALL_SHOPS:
      return{
        ...state,
        Allshop: action.Allshop,
      };

    default:
      return state;
  }
};

export default reducer;
