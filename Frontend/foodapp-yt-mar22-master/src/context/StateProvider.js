import React, { createContext, useContext, useReducer } from 'react';

// Create the context
export const StateContext = createContext();

// Higher-order component to provide the state context
export const StateProvider = ({ reducer, initialState, children }) => (
  <StateContext.Provider value={useReducer(reducer, initialState)}>
    {children}
  </StateContext.Provider>
);

// Custom hook to use the state context
export const useStateValue = () => useContext(StateContext);
