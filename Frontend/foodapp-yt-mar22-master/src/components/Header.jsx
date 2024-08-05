import React, { useState } from "react";
import { MdShoppingBasket, MdAdd, MdLogout } from "react-icons/md";
import { motion } from "framer-motion";
import Logo from "../img/logo.png";
import Avatar from "../img/avatar.png";
import { Link, useNavigate } from "react-router-dom";
import { useStateValue } from "../context/StateProvider";
import { actionType } from "../context/reducer";

const Header = () => {
    const [{ user, cartShow, cartItems,shop, shopProducts}, dispatch] = useStateValue();
    const [isMenu, setIsMenu] = useState(false);
    const navigate = useNavigate();

    const logout = () => {
        setIsMenu(false);
        localStorage.clear();
        dispatch({ type: actionType.RESET_USER, user:null});
        dispatch({ type: actionType.RESET_SHOP, shop:null});
        dispatch({type: actionType.CLEAR_SHOP_PRODUCTS, shopProducts:null});

        console.log("im in log out , and i delete it,",user,shop);
    };

    const showCart = () => {
        dispatch({ type: actionType.SET_CART_SHOW, cartShow: !cartShow });
    };

    const handleProfileClick = () => {
        if (!user) {
            navigate('/auth');
        } else {
            console.log("im in log out , and i delete it,",user,shop);

            setIsMenu(!isMenu);
        }
    };

    return (
        <header className="fixed z-50 w-screen p-3 px-4 md:p-6 md:px-16 bg-primary">
            {/* Desktop & Tablet */}
            <div className="hidden md:flex w-full h-full items-center justify-between">
                <Link to={"/"} className="flex items-center gap-2">
                    <img src={Logo} className="w-8 object-cover" alt="logo" />
                    <p className="text-headingColor text-xl font-bold"> City</p>
                </Link>

                <div className="flex items-center gap-8">
                    <motion.ul
                        initial={{ opacity: 0, x: 200 }}
                        animate={{ opacity: 1, x: 0 }}
                        exit={{ opacity: 0, x: 200 }}
                        className="flex items-center gap-24"
                    >
                        <li className="text-lg text-textColor hover:text-headingColor duration-100 transition-all ease-in-out cursor-pointer">
                            Home
                        </li>
                        <li className="text-lg text-textColor hover:text-headingColor duration-100 transition-all ease-in-out cursor-pointer">
                            Menu
                        </li>
                        <li className="text-lg text-textColor hover:text-headingColor duration-100 transition-all ease-in-out cursor-pointer">
                            About Us
                        </li>
                        <li className="text-lg text-textColor hover:text-headingColor duration-100 transition-all ease-in-out cursor-pointer">
                            Service
                        </li>
                    </motion.ul>

                    <div
                        className="relative flex items-center justify-center"
                        onClick={showCart}
                    >
                        <MdShoppingBasket className="text-textColor text-2xl cursor-pointer" />
                        {cartItems && cartItems.length > 0 && (
                            <div className="absolute -top-2 -right-2 w-5 h-5 rounded-full bg-cartNumBg flex items-center justify-center">
                                <p className="text-xs text-white font-semibold">
                                    {cartItems.length}
                                </p>
                            </div>
                        )}
                    </div>

                    <div className="relative">
                        <motion.img
                            whileTap={{ scale: 0.6 }}
                            src={ Avatar}
                            className="w-10 min-w-[40px] h-10 min-h-[40px] drop-shadow-xl cursor-pointer rounded-full"
                            alt="userprofile"
                            onClick={handleProfileClick}
                        />
                        {isMenu && (
                            <motion.div
                                initial={{ opacity: 0, scale: 0.6 }}
                                animate={{ opacity: 1, scale: 1 }}
                                exit={{ opacity: 0, scale: 0.6 }}
                                className="w-40 bg-gray-50 shadow-xl rounded-lg flex flex-col absolute top-12 right-0"
                            >
                                {user.userType === "SHOP_ADMIN" && (
                                    <Link to={"/shopAdminDashboard"}>
                                        <p
                                            className="px-4 py-2 flex items-center gap-3 cursor-pointer hover:bg-slate-100 transition-all duration-100 ease-in-out text-textColor text-base"
                                            onClick={() => setIsMenu(false)}
                                        >
                                            Dashboard
                                        </p>
                                    </Link>
                                )}
                                {user.userType === "CUSTOMER" && (
                                    <Link to={"/userprofile"}>
                                        <p
                                            className="px-4 py-2 flex items-center gap-3 cursor-pointer hover:bg-slate-100 transition-all duration-100 ease-in-out text-textColor text-base"
                                            onClick={() => setIsMenu(false)}
                                        >
                                            Profile
                                        </p>
                                    </Link>
                                )}
                                {user.userType === "DELIVERY_PARTNER" && (
                                    <Link to={"/deliverypartnerprofile"}>
                                        <p
                                            className="px-4 py-2 flex items-center gap-3 cursor-pointer hover:bg-slate-100 transition-all duration-100 ease-in-out text-textColor text-base"
                                            onClick={() => setIsMenu(false)}
                                        >
                                            Profile
                                        </p>
                                    </Link>
                                )}
                                <p
                                    className="px-4 py-2 flex items-center gap-3 cursor-pointer hover:bg-slate-100 transition-all duration-100 ease-in-out text-textColor text-base"
                                    onClick={logout}
                                >
                                    Logout <MdLogout />
                                </p>
                            </motion.div>
                        )}
                    </div>
                </div>
            </div>

            {/* Mobile */}
            <div className="flex items-center justify-between md:hidden w-full h-full">
                <div
                    className="relative flex items-center justify-center"
                    onClick={showCart}
                >
                    <MdShoppingBasket className="text-textColor text-2xl cursor-pointer" />
                    {cartItems && cartItems.length > 0 && (
                        <div className="absolute -top-2 -right-2 w-5 h-5 rounded-full bg-cartNumBg flex items-center justify-center">
                            <p className="text-xs text-white font-semibold">
                                {cartItems.length}
                            </p>
                        </div>
                    )}
                </div>

                <Link to={"/"} className="flex items-center gap-2">
                    <img src={Logo} className="w-8 object-cover" alt="logo" />
                    <p className="text-headingColor text-xl font-bold"> City</p>
                </Link>

                <div className="relative">
                    <motion.img
                        whileTap={{ scale: 0.6 }}
                        src={user ? user.photoURL : Avatar}
                        className="w-10 min-w-[40px] h-10 min-h-[40px] drop-shadow-xl cursor-pointer rounded-full"
                        alt="userprofile"
                        onClick={handleProfileClick}
                    />
                    {isMenu && (
                        <motion.div
                            initial={{ opacity: 0, scale: 0.6 }}
                            animate={{ opacity: 1, scale: 1 }}
                            exit={{ opacity: 0, scale: 0.6 }}
                            className="w-40 bg-gray-50 shadow-xl rounded-lg flex flex-col absolute top-12 right-0"
                        >
                            {user.userType === "SHOP_ADMIN" && (
                                <Link to={"/shopAdminDashboard"}>
                                    <p className="px-4 py-2 flex items-center gap-3 cursor-pointer hover:bg-slate-100 transition-all duration-100 ease-in-out text-textColor text-base">
                                        Dashboard
                                    </p>
                                </Link>
                            )}
                            {user.userType === "CUSTOMER" && (
                                <Link to={"/userprofile"}>
                                    <p className="px-4 py-2 flex items-center gap-3 cursor-pointer hover:bg-slate-100 transition-all duration-100 ease-in-out text-textColor text-base">
                                        Profile
                                    </p>
                                </Link>
                            )}
                            {user.userType === "DELIVERY_PARTNER" && (
                                <Link to={"/deliverypartnerprofile"}>
                                    <p className="px-4 py-2 flex items-center gap-3 cursor-pointer hover:bg-slate-100 transition-all duration-100 ease-in-out text-textColor text-base">
                                        Profile
                                    </p>
                                </Link>
                            )}
                            <p
                                className="px-4 py-2 flex items-center gap-3 cursor-pointer hover:bg-slate-100 transition-all duration-100 ease-in-out text-textColor text-base"
                                onClick={logout}
                            >
                                Logout <MdLogout />
                            </p>
                        </motion.div>
                    )}
                </div>
            </div>
        </header>
    );
};

export default Header;
