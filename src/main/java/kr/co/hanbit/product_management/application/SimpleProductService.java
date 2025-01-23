package kr.co.hanbit.product_management.application;

import kr.co.hanbit.product_management.domain.Product;
import kr.co.hanbit.product_management.domain.ProductRepository;
import kr.co.hanbit.product_management.presentation.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SimpleProductService {

    private ProductRepository productRepository;
    private ValidationService validationService;

    @Autowired
    SimpleProductService(ProductRepository productRepository, ValidationService validationService) {
        this.productRepository = productRepository;
        this.validationService = validationService;
    }

    // 상품 추가
    public ProductDto add(ProductDto productDto) {
        // dto는 표현계층~응용계층까지만 역할을 하고 이후는 getter 없이 두 클래스를 변환하는 ModelMapper 매핑
        Product product = ProductDto.toEntity(productDto);
        validationService.checkValid(product); // 유효성 검사 클래스
        Product savedProduct = productRepository.add(product);
        ProductDto savedProductDto = ProductDto.toDto(savedProduct);
        return savedProductDto;
    }

    // id로 조회
    public ProductDto findById(Long id) {
        Product product = productRepository.findById(id);
        ProductDto productDto = ProductDto.toDto(product);
        return productDto;
    }

    // 전체 조회
    public List<ProductDto> findAll() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> ProductDtos = products.stream()
                .map(product -> ProductDto.toDto(product))
                .toList();
        return ProductDtos;
    }

    // 상품명으로 검색
    public List<ProductDto> findByNameContaining(String name) {
        List<Product> products = productRepository.findByNameContaining(name);
        List<ProductDto> productDtos = products.stream()
                .map(product -> ProductDto.toDto(product))
                .toList();
        return productDtos;
    }

    // 상품 수정
    public ProductDto update(ProductDto productDto) {
        Product product = ProductDto.toEntity(productDto);
        Product updatedProduct = productRepository.update(product);
        ProductDto updatedProductDto = ProductDto.toDto(updatedProduct);
        return updatedProductDto;
    }

    // 상품 삭제
    public void delete(Long id) {
        productRepository.delete(id);
    }

}
