package com.aeropelican.productservice.service;
import com.aeropelican.productservice.Exceptions.ProductNotFound;
import com.aeropelican.productservice.dto.request.CreateProduct_VariantsRequest;
import com.aeropelican.productservice.dto.request.UpdateProduct_Variants;
import com.aeropelican.productservice.dto.response.CategoryResponse;
import com.aeropelican.productservice.dto.response.PageResponse;
import com.aeropelican.productservice.dto.response.ProductResponse;
import com.aeropelican.productservice.dto.response.Product_variantsResponse;
import com.aeropelican.productservice.entity.Category;
import com.aeropelican.productservice.entity.Product;
import com.aeropelican.productservice.entity.Product_Variants;
import com.aeropelican.productservice.mapper.CategoryMapper;
import com.aeropelican.productservice.mapper.ProductMapper;
import com.aeropelican.productservice.mapper.Product_VariantsMapper;
import com.aeropelican.productservice.repository.ProductVariantsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductVariantsService {
        private final ProductVariantsRepository productVariantsRepository;
    public Product_Variants getProductVariant(Integer variantId) {
        return productVariantsRepository.findById(variantId)
                .orElseThrow(() -> new RuntimeException("Product Variant not found"));
    }

    public PageResponse<Product_variantsResponse> listProduct_Variants(int page,
                                                      int size,
                                                      String sortBy,
                                                      String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("DESC")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Product_Variants> results = productVariantsRepository.findAll(pageable);

        List<Product_variantsResponse> result = new ArrayList<>(results
                .map(Product_VariantsMapper::toResponse)
                .getContent());

        return PageResponse.<Product_variantsResponse>builder()
                .content(result)
                .page(results.getNumber())
                .size(results.getSize())
                .totalElement(results.getTotalElements())
                .totalPage(results.getTotalPages())
                .hasNext(results.hasNext())
                .hasPrevious(results.hasPrevious())
                .build();
    }

        //To create Product_variants
        public Product_Variants createProductVariant(CreateProduct_VariantsRequest request) {
            Product_Variants variant = new Product_Variants();
            variant.setProduct_id(request.getProduct_id());
            variant.setSku(request.getSku());
            variant.setColor(request.getColor());
            variant.setStorage_capacity(request.getStorage_capacity());
            variant.setPrice(request.getPrice());
            Timestamp now = new Timestamp(System.currentTimeMillis());
            variant.setCreated_at(now);
            variant.setUpdated_at(now);

            return productVariantsRepository.save(variant);
        }

        public Product_Variants updateProductVariant(
                Integer variantId,
                UpdateProduct_Variants request) {
            Product_Variants variant = productVariantsRepository.findById(variantId)
                    .orElseThrow(() -> new RuntimeException("Product Variant not found"));
            variant.setProduct_id(request.getProduct_id());
            variant.setSku(request.getSku());
            variant.setColor(request.getColor());
            variant.setStorage_capacity(request.getStorage_capacity());
            variant.setPrice(request.getPrice());

            return productVariantsRepository.save(variant);
        }

        public Product_Variants deleteProductVariant(Integer variantId) {

            Product_Variants variant = productVariantsRepository.findById(variantId)
                    .orElseThrow(() -> new RuntimeException("Product Variant not found"));

            productVariantsRepository.delete(variant);

            return variant;
        }

}
