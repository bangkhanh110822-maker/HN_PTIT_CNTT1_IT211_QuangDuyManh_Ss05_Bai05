package org.example.session05.controller;

import jakarta.validation.Valid;
import org.example.session05.model.dto.ProductCreateDTO;
import org.example.session05.model.entity.Product;
import org.example.session05.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService ;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable long id) {
        return productService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductCreateDTO productCreateDTO , BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(productService.createProduct(productCreateDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable long id ,@RequestBody ProductCreateDTO productCreateDTO){
        return productService.updateProduct(id, productCreateDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> editProduct(@PathVariable long id,@RequestBody ProductCreateDTO productCreateDTO){
        return productService.editProduct(id, productCreateDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable long id){
        return productService.deleteProductById(id);
    }
}
