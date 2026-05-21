package org.example.session05.service;

import org.example.session05.model.dto.ProductCreateDTO;
import org.example.session05.model.entity.Product;
import org.example.session05.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository ;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product createProduct(ProductCreateDTO productCreateDTO){
        Product newProduct = convertDTOToProduct(productCreateDTO);
        return productRepository.save(newProduct);
    }

    private static Product convertDTOToProduct(ProductCreateDTO productCreateDTO) {
        return Product
                .builder()
                .productName(productCreateDTO.getProductName())
                .description(productCreateDTO.getDescription())
                .price(productCreateDTO.getPrice())
                .stock(productCreateDTO.getStock())
                .build();
    }

    public Product findProductById(long id){
        return productRepository.findById(id).orElse(null);
    }

    public ResponseEntity<?> findById(long id) {
        Product product = findProductById(id);
        if(product == null){
            return new ResponseEntity<>("Không tìm thấy product có id = " + id , HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    public ResponseEntity<?> updateProduct(long id ,ProductCreateDTO productCreateDTO) {
        Product product = findProductById(id);
        if(product == null){
            return new ResponseEntity<>("Không tìm thấy product có id = " + id , HttpStatus.NOT_FOUND);
        }
        Product newProduct = convertDTOToProduct(productCreateDTO);
        newProduct.setId(id);
        return new ResponseEntity<>(productRepository.save(newProduct), HttpStatus.OK);
    }

    public ResponseEntity<?> editProduct(long id , ProductCreateDTO productCreateDTO){
        Product product = findProductById(id);
        if(product == null){
            return new ResponseEntity<>("Không tìm thấy product có id = " + id , HttpStatus.NOT_FOUND);
        }
        if (productCreateDTO.getProductName() != null && !productCreateDTO.getProductName().isEmpty()) {
            product.setProductName(productCreateDTO.getProductName());
        }
        if (productCreateDTO.getDescription() != null && !productCreateDTO.getDescription().isEmpty()) {
            product.setDescription(productCreateDTO.getDescription());
        }
        if (productCreateDTO.getPrice() != null){
            product.setPrice(productCreateDTO.getPrice());
        }
        if (productCreateDTO.getStock() != null){
            product.setStock(productCreateDTO.getStock());
        }
        return new ResponseEntity<>(productRepository.save(product), HttpStatus.OK);
    }

    public ResponseEntity<String> deleteProductById(long id){
        Product product = findProductById(id);
        if(product == null){
            return new ResponseEntity<>("Không tìm thấy product có id = " + id , HttpStatus.NOT_FOUND);
        }
        productRepository.delete(product);
        return new ResponseEntity<>("Xóa sản phẩm thành công",HttpStatus.OK);
    }
}
