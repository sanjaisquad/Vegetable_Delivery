import api from './api';

export const login = (email, password) => {
  return api.post('/auth/login', { email, password });
};

export const signup = (userData) => {
  return api.post('/auth/signup', userData);
};

// Add other auth related functions if needed
