import React, { useState } from 'react';
import LoginForm from './LoginForm';
import SignupForm from './SignupForm';
import './AuthPage.css'; // Import CSS file for styling

const AuthPage = () => {
  const [isLogin, setIsLogin] = useState(true);

  const toggleForm = () => {
    setIsLogin(!isLogin);
  };

  return (
    <div className="auth-container">
      {/* Container for buttons */}
      <div className="auth-buttons">
        {/* Heading */}
        {/* <h1>{isLogin ? 'LOGIN' : 'SIGNUP'}</h1> */}
        {/* Button to toggle between login and signup */}
        <button  className={isLogin ? 'active' : ''} onClick={() => setIsLogin(true)}>Login</button>
        <h5>or</h5>
        <button className={!isLogin ? 'active' : ''} onClick={() => setIsLogin(false)}>Signup</button>
      </div>
      {/* Container for form */}
      <div className="auth-form">
        {/* Render either login or signup form based on isLogin state */}
        {isLogin ? <LoginForm /> : <SignupForm />}
        {/* Big green submit button */}
        {/* <button className="submit-button">Submit</button> */}
      </div>
    </div>
  );
};

export default AuthPage;
