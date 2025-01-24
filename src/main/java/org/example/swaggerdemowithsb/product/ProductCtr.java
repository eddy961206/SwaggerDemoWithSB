package org.example.swaggerdemowithsb.product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductCtr {

    @GetMapping("/list")
    public String getAllProducts() {
        return "전체 상품 목록";
    }

    @GetMapping("/search")
    public String searchProducts(
            @RequestParam int price,
            @RequestParam String name,
            @RequestParam(required = false) String brand
    ) {
        return String.format("검색 결과 - 상품명: %s, 가격: %d원, 브랜드: %s", name, price, brand != null ? brand : "미지정");
    }

    @GetMapping("/category")
    public ProductCategory getCategory(@RequestParam ProductCategory category) {
        return category;
    }

    @PostMapping("/new")
    public ProductDto createProduct(@RequestBody ProductDto product) {
        return product;
    }

    @PutMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id, @RequestParam String name) {
        return id + "번 상품명 수정: " + name;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        return id + "번 상품 삭제 완료";
    }
}
