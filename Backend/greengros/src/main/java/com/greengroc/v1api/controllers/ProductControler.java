package com.greengroc.v1api.controllers;

import com.greengroc.v1api.services.ProductService;
import com.greengroc.v1api.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/ v1/Products")
public class ProductControler {
    private final ProductService productService;
    @Autowired
    public ProductControler(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts(){
        return productService.getProductData();
    }
    @PostMapping
    public void registerNewProduct(@RequestBody Product product) {
        productService.addNewProduct(product);
    }

    @DeleteMapping(path="{productID}")
    public void deleteProduct(@PathVariable("productID") int id){
        productService.deleteProduct(id);
    }
    @PutMapping(path= "{productID}")
    public void updateProduct(
            @PathVariable("productID") int productID,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double price,
            @RequestParam(required = false) String catagori,
            @RequestParam(required = false) String image)
            {
        productService.updateProduct(productID, name, price,catagori,image);

    }

}
