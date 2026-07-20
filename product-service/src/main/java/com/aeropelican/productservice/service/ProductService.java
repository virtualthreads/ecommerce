package com.aeropelican.productservice.service;

import com.aeropelican.productservice.dto.CreateProductRequest;
import com.aeropelican.productservice.entity.Product;
import com.aeropelican.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.management.RuntimeMBeanException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> listProducts() {
        List<Product> results = productRepository.findAll();
        return results;
    }

    public Product getProduct(Integer productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            return product.get();
        } else {
            return null;
        }
    }

    // Original quantity update method
    public Product updateProduct(Integer productId, Integer quantity) {
        Product product = productRepository.findById(productId).get();
        product.setQuantity(quantity);
        productRepository.save(product);
        return product;
    }

    // UPDATE product by ID (PUT)
    public Product updateProduct(Integer id, Product updatedProduct) {
        return productRepository.findById(id).map(existingProduct -> {
            existingProduct.setProductName(updatedProduct.getProductName());
            existingProduct.setCategory(updatedProduct.getCategory());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setQuantity(updatedProduct.getQuantity());
            return productRepository.save(existingProduct);
        }).orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    // DELETE product by ID (DELETE)
    public String deleteProduct(Integer id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return "Product deleted successfully with id: " + id;
        } else {
            throw new RuntimeException("Product not found with id: " + id);
        }
    }

    public Product createProduct(CreateProductRequest request) {
        System.out.println("Attempting to create a record in the product table");

        Product product = new Product();
        product.setProductName(request.getProductName());
        product.setCategory(request.getCategory());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());

        Product createdProduct = productRepository.save(product);
        System.out.println("Created a product with product ID: " + createdProduct.getProductId());
        return createdProduct;
    }
}