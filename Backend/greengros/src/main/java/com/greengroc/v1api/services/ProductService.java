package com.greengroc.v1api.services;
 import com.greengroc.v1api.models.Product;
import com.greengroc.v1api.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    public List<Product> getProductData(){
        return productRepository.findAll();
    }
    public void addNewProduct(Product product){
        if(productRepository.findByName(product.getName()).isPresent()){
            throw new IllegalStateException("Name taken");
        }
       productRepository.save(product);
    }
    public void deleteProduct(int productId){
    boolean exists = productRepository.existsById(productId);
    if(!exists){
        throw  new IllegalStateException("Product whit id "+ productId+"does not exists");
    }
    productRepository.deleteById(productId);
    }

    @Transactional
    public void updateProduct(int productId, String name, Double price, String catagori, String image){
        Product product = productRepository.findById(productId).orElseThrow(()-> new IllegalStateException("Product with id "+ productId + "does not exist"));

        if(price!=null &&price >0 &&  price!=product.getPrice()){
            product.setName(name);
            System.out.println("product price updated successfully");
        }
        if(name!=null && name.length() > 0 && !Objects.equals(product.getName(),name)){
            Optional<Product> studentOptional = productRepository.findByName(name);
            if(!studentOptional.isPresent()){
            product.setName(name);
            System.out.println("Product name updated successfully");}
        }
        if(catagori != null && catagori.length() > 0 && !Objects.equals(product.getCatagori(),catagori)){
            product.setCatagori(catagori);
            System.out.println("product catagori updated successfully");
        }
        if(image != null && image.length() > 0 && !Objects.equals(product.getImage(),image)){
            product.setImage(image);
            System.out.println("product image updated successfully");
        }
        System.out.println("**** updated successfully ****");

    }
}

