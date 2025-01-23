package org.example.swaggerdemowithsb.product;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    @Schema(description = "상품명", example = "갤럭시 노트북", required = true)
    @NotBlank
    private String name;

    @Schema(description = "상품 가격", example = "1000000", minimum = "0")
    @Min(0)
    private int price;

    public String getName() {
        return name;
    }
    public int getPrice() {
        return price;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(int price) {
        this.price = price;
    }
}
