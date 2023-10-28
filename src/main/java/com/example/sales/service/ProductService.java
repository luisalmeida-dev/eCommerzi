package com.example.sales.service;

import com.example.sales.Enum.CategoryEnum;
import com.example.sales.Enum.DiscountStatusEnum;
import com.example.sales.auth.service.TokenService;
import com.example.sales.dto.request.ProductRequestDTO;
import com.example.sales.dto.request.ProductUpdateRequestDTO;
import com.example.sales.dto.response.ProductResponseDTO;
import com.example.sales.mapper.ProductMapper;
import com.example.sales.model.ProductEntity;
import com.example.sales.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private TokenService tokenService;

    private static final String USERID = "userid";

    public List<ProductResponseDTO> getAllProductsByUser(String authorization) throws Exception {
        List<ProductEntity> productList = productRepository.findAllByUserId(tokenService.decodeToken(authorization).getClaim(USERID).asLong());
        if (!productList.isEmpty()) {
            return productList.stream()
                    .map(productMapper::toProductResponseDTO)
                    .collect(Collectors.toList());
        } else {
            throw new Exception("There's no product registered to this user!");
        }
    }

    public ProductResponseDTO getProductByUserAndSku(String authorization, String sku) throws Exception {
        ProductEntity product = productRepository.findByUserIdAndSku(tokenService.decodeToken(authorization).getClaim(USERID).asLong(), sku);
        if (product != null) {
            return productMapper.toProductResponseDTO(product);
        } else {
            throw new Exception("Produto nao encontrado!");
        }
    }

    public void createProduct(String authorization, ProductRequestDTO request) throws Exception {
        Long userId = tokenService.decodeToken(authorization).getClaim(USERID).asLong();
        String sku = generateSku(request.getCategory());
        if (productRepository.findByUserIdAndNameAndCategory(userId, request.getName(), request.getCategory()) == null) {
            ProductEntity product = productMapper.toProductEntity(request);
            product.setUserId(userId);
            product.setSku(sku);
            product.setTotal(calculatePrice(request.getPrice(), request.getDiscountPercentage(), request.getDiscountStatus()));
            if (product.getDiscountStatus() != DiscountStatusEnum.ACTIVE) {
                product.setDiscountStatus(DiscountStatusEnum.INACTIVE);
            }
            productRepository.save(product);
        } else {
            throw new Exception("This product is already registered!");
        }
    }

    public void updateProduct(String authorization, Long productId, ProductUpdateRequestDTO request) throws Exception {
        ProductEntity product = productRepository.findByIdAndUserId(productId, tokenService.decodeToken(authorization).getClaim(USERID).asLong());
        if (product != null) {
            product = productMapper.productUpdateDTOtoEntity(request);
            productRepository.save(product);
        } else {
            throw new Exception("This product isn't registered!");
        }
    }

    public void deleteAllProducts(Long userId) {
        productRepository.deleteAllByUserId(userId);
    }

    public void deleteProductById(String authorization, Long id) {
        Long userId = tokenService.decodeToken(authorization).getClaim("USERID").asLong();
        ProductEntity product = productRepository.findByIdAndUserId(id, userId);
        productRepository.delete(product);
    }

    private String generateSku(CategoryEnum category) {
        long seed = System.currentTimeMillis();
        Random rng = new Random(seed);
        long number = (rng.nextLong() % 90000000000L) + 10000000000L;
        return category.getAbbreviation() + number;
    }

    private BigDecimal calculatePrice(BigDecimal price, BigDecimal discountPercentage, DiscountStatusEnum discountStatus) {
        if (discountStatus.equals(DiscountStatusEnum.ACTIVE)) {
            return price.multiply(BigDecimal.valueOf(100.00).subtract(discountPercentage)).divide(BigDecimal.valueOf(100.00));
        } else {
            return price;
        }
    }
}
