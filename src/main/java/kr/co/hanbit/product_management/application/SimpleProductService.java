package kr.co.hanbit.product_management.application;

import kr.co.hanbit.product_management.domain.Product;
import kr.co.hanbit.product_management.infrastructure.ListProductRepository;
import kr.co.hanbit.product_management.presentation.ProductDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SimpleProductService {

    private ListProductRepository listProductRepository;
    private ModelMapper modelMapper;
    private ValidationService validationService;

    @Autowired
    SimpleProductService(ListProductRepository listProductRepository, ModelMapper modelMapper, ValidationService validationService) {
        this.listProductRepository = listProductRepository;
        this.modelMapper = modelMapper;
        this.validationService = validationService;
    }

    // 상품 추가
    public ProductDto add(ProductDto productDto) {
        // dto는 표현계층~응용계층까지만 역할을 하고 이후는 getter 없이 두 클래스를 변환하는 ModelMapper 매핑
        Product product = modelMapper.map(productDto, Product.class);
        validationService.checkValid(product); // 유효성 검사 클래스
        Product savedProduct = listProductRepository.add(product);
        ProductDto savedProductDto = modelMapper.map(savedProduct, ProductDto.class);
        return savedProductDto;
    }

    // id로 조회
    public ProductDto findById(Long id) {
        Product product = listProductRepository.findById(id);
        ProductDto ProductDto = modelMapper.map(product, ProductDto.class);
        return ProductDto;
    }

    // 전체 조회
    public List<ProductDto> findAll() {
        List<Product> products = listProductRepository.findAll();
        List<ProductDto> ProductDtos = products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();
        return ProductDtos;
    }

    // 상품명으로 검색
    public List<ProductDto> findByNameContaining(String name) {
        List<Product> products = listProductRepository.findByNameContaining(name);
        List<ProductDto> productDtos = products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();
        return productDtos;
    }

    // 상품 수정
    public ProductDto update(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        Product updatedProduct = listProductRepository.update(product);
        ProductDto updatedProductDto = modelMapper.map(updatedProduct, ProductDto.class);
        return updatedProductDto;
    }

    // 상품 삭제
    public void delete(Long id) {
        listProductRepository.delete(id);
    }

}
