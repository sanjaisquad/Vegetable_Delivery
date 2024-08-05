import React, { useEffect, useState } from 'react';
import api from '../../services/api';

const ProductList = () => {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const response = await api.get('/products');
        setProducts(response.data);
      } catch (error) {
        console.error('Failed to fetch products', error);
      }
    };

    fetchProducts();
  }, []);

  return (
    <div>
      <h1>Product List</h1>
      {products.length ? (
        <table>
          <thead>
            <tr>
              <th>Product ID</th>
              <th>Name</th>
              <th>Description</th>
              <th>Price</th>
              <th>Image</th>
            </tr>
          </thead>
          <tbody>
            {products.map(product => (
              <tr key={product.id}>
                <td>{product.id}</td>
                <td>{product.name}</td>
                <td>{product.description}</td>
                <td>{product.price}</td>
                <td>
                  <img src={product.image} alt={product.name} style={{ width: '50px', height: '50px' }} />
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <p>No products available</p>
      )}
    </div>
  );
};

export default ProductList;
