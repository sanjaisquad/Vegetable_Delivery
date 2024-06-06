package com.vegetable_API;

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
    public List<Product> getStudentData(){
        return productRepository.findAll();
    }
    public void addNewStudent(Product product){
        if(productRepository.findByName(product.getName()).isPresent()){
            throw new IllegalStateException("Name taken");
        }
       productRepository.save(product);
    }
    public void deleteStudent(int productId){
    boolean exists = productRepository.existsById(productId);
    if(!exists){
        throw  new IllegalStateException("Student whit id "+ productId+"does not exists");
    }
    productRepository.deleteById(productId);
    }

    @Transactional
    public void updateStudent(int productId, String name, double price, String catagori, String avilablity){
        Product product = productRepository.findById(productId).orElseThrow(()-> new IllegalStateException("Product with id "+ productId + "does not exist"));

        if(price >0 &&  price!=product.getPrice()){
            product.setName(name);
            System.out.println("product price updated successfully");
        }
        if(name!=null && name.length() > 0 && !Objects.equals(product.getName(),name)){
            Optional<Product> studentOptional = productRepository.findByName(name);
            if(studentOptional.isPresent()){
            product.setName(name);
            System.out.println("Product name updated successfully");}
        }
        if(catagori != null && catagori.length() > 0 && !Objects.equals(product.getCatagori(),catagori)){
            product.setCatagori(catagori);
            System.out.println("product catagori updated successfully");
        }
        if(avilablity != null && avilablity.length() > 0 && !Objects.equals(product.getAvilable(),avilablity)){
            product.setAvilable(avilablity);
            System.out.println("product Avilablity updated successfully");
        }
        System.out.println("**** updated successfully ****");

    }
}

