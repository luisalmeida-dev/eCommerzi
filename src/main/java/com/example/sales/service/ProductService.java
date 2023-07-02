package com.example.sales.service;

import com.example.sales.dto.request.ProductRequestDTO;
import com.example.sales.dto.request.ProductUpdateRequestDTO;
import com.example.sales.dto.response.ProductResponseDTO;
import com.example.sales.mapper.ProductMapper;
import com.example.sales.model.ProductEntity;
import com.example.sales.model.UserEntity;
import com.example.sales.repository.ProductRepository;
import com.example.sales.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    public List<ProductResponseDTO> getAllProductsByUser(Long userId) throws Exception {
        validateUser(userId);
        List<ProductEntity> productList = productRepository.findAllByUserId(userId);
        if (!productList.isEmpty()) {
            return productList.stream()
                    .map(productMapper::toProductResponseDTO)
                    .collect(Collectors.toList());
        } else {
            throw new Exception("There's no product registered to this user!");
        }
    }

    public ProductResponseDTO getProductByUserAndSku(Long userId, String sku) throws Exception {
        validateUser(userId);
        ProductEntity product = productRepository.findBySku(sku);
        if (product != null) {
            return productMapper.toProductResponseDTO(product);
        } else {
            throw new Exception("Produto nao encontrado!");
        }
    }

    public void createProduct(ProductRequestDTO request) throws Exception {
        validateUser(request.getUserId());
        if (productRepository.findBySku(request.getSku()) == null) {
            ProductEntity product = productMapper.toProductEntity(request);
            productRepository.save(product);
        } else {
            throw new Exception("This product is already registered!");
        }
    }

    public void updateProduct(Long productId, ProductUpdateRequestDTO request, Long userId) throws Exception {
        validateUser(userId);
        ProductEntity product = productRepository.findByIdAndUserId(productId, userId);
        if (product != null) {
            product = productMapper.productUpdateDTOtoEntity(request);
            productRepository.save(product);
        } else {
            throw new Exception("This product isn't registered!");
        }
    }

    //TODO ver uma forma de fazer essa validacao em apenas em um lugar e poder ser utilizada em outros locais
    private void validateUser(Long userId) throws Exception {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new Exception("This user was not found!"));
        if (user == null || user.getUserStatus() == null || user.getRole() == null) {
            throw new Exception("This user is invalid, please check user situation for more details!");
        }
    }
}
