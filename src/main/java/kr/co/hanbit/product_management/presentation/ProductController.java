package kr.co.hanbit.product_management.presentation;

import jakarta.validation.Valid;
import kr.co.hanbit.product_management.application.SimpleProductService;
import kr.co.hanbit.product_management.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private SimpleProductService simpleProductService;

    // 생성자 주입을 통한 의존성 주입
    @Autowired
    ProductController(SimpleProductService simpleProductService) {
        this.simpleProductService = simpleProductService;
    }

    // 상품 추가
    @PostMapping("/products")
    public ProductDto createProduct(@Valid @RequestBody ProductDto productDto) {
        return simpleProductService.add(productDto);
    }

    // id로 조회
    @GetMapping("/products/{id}")
    public ProductDto findProductById(@PathVariable Long id) {
        return simpleProductService.findById(id);
    }

    // 전체 조회 또는 상품명으로 검색 조회
    @GetMapping("/products")
    public List<ProductDto> findProducts(@RequestParam(required = false) String name){
        System.out.println("name : " + name);
        if(name == null){
            return simpleProductService.findAll();
        }
        return simpleProductService.findByNameContaining(name);
    }

    // 상품 수정
    @PutMapping("/products/{id}")
    public ProductDto updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        productDto.setId(id); // PathVariable로 id를 보냈기 때문에 요청 바디에는 id를 넣어주지 않을 수도 있어서 set.
        return simpleProductService.update(productDto);
    }

    // 상품 삭제
    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        simpleProductService.delete(id);
    }

}
