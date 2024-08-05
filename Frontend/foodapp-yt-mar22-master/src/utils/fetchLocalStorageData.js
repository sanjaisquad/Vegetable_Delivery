
export const fetchUser = () => {
  const userInfo = localStorage.getItem('user') !== 'undefined' ? JSON.parse(localStorage.getItem('user')) : null;
  return userInfo;
};

export const fetchCart = () => {
  const cartInfo = localStorage.getItem('cartItems') !== 'undefined' ? JSON.parse(localStorage.getItem('cartItems')) : [];
  return cartInfo;
};

export const fetchShop = () => {
  const shopInfo = localStorage.getItem('shop') !== 'undefined' ? JSON.parse(localStorage.getItem('shop')) : null;
  return shopInfo;
};

export const fetchShopProducts = () => {
  const shopProductsInfo = localStorage.getItem('shopProducts') !== 'undefined' ? JSON.parse(localStorage.getItem('shopProducts')) : [];
  return shopProductsInfo;
};

export const fetchAllShops = () => {
  const AllshopInfo = localStorage.getItem('Allshop') !== 'undefined' ? JSON.parse(localStorage.getItem('AllShop')) : [];
  return AllshopInfo;
}
