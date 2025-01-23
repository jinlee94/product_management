package kr.co.hanbit.product_management.presentation;

import jakarta.validation.constraints.NotNull;
import kr.co.hanbit.product_management.domain.Product;
import lombok.AllArgsConstructor;

public class ProductDto {
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Integer price;

    @NotNull
    private Integer amount;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getAmount() {
        return amount;
    }

    public ProductDto(){}
    public ProductDto(String name, Integer price, Integer amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public ProductDto(Long id, String name, Integer price, Integer amount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public static Product toEntity(ProductDto dto){
        Product product = new Product();

        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setAmount(dto.getAmount());

        return product;
    }

    public static ProductDto toDto(Product product){
        ProductDto productDto = new ProductDto(product.getName(), product.getPrice(), product.getAmount());
        productDto.setId(product.getId());
        return productDto;
    }
}
