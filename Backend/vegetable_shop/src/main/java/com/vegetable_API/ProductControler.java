package com.vegetable_API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/product")
public class ProductControler {
    private final ProductService productService;
    @Autowired
    public ProductControler(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getStudent(){
        System.out.println("333333333333333333333333333333333333333333333333333333333");

        return productService.getStudentData();
    }
    @PostMapping
    public void registerNewStudent(@RequestBody Product product) {
        System.out.println("im in post..");
        productService.addNewStudent(product);
    }

    @DeleteMapping(path="{productID}")
    public void deleteStudent(@PathVariable("productID") int id){
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
        productService.deleteStudent(id);
    }
    @PutMapping(path= "{productID}")
    public void updateStudent(
            @PathVariable("productID") int productID,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) double price,
            @RequestParam(required = false) String catagori,
            @RequestParam(required = false) String avilablity){
        productService.updateStudent(productID, name, price,catagori,avilablity);

    }

}
