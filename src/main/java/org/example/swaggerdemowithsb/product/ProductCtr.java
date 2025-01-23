package org.example.swaggerdemowithsb.product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@Tag(name = "상품 API", description = "상품 관리 API")
public class ProductCtr {

    @Operation(summary = "전체 상품 조회", description = "모든 상품 목록을 조회합니다")
    @GetMapping("/list")
    public String getAllProducts() {
        return "전체 상품 목록";
    }

    @Operation(summary = "상품 검색", description = "여러 조건으로 상품을 검색합니다")
    @Parameter(name = "price", description = "상품 가격", example = "10000")
    @Parameter(name = "name", description = "상품명", example = "노트북")
    @Parameter(name = "brand", description = "브랜드", example = "삼성")
    @ApiResponse(responseCode = "200", description = "검색 성공")
    @GetMapping("/search")
    public String searchProducts(
            @RequestParam int price,
            @RequestParam String name,
            @RequestParam(required = false) String brand
    ) {
        return String.format("검색 결과 - 상품명: %s, 가격: %d원, 브랜드: %s", name, price, brand != null ? brand : "미지정");
    }

    @Operation(summary = "카테고리 조회", description = "상품 카테고리를 조회합니다")
    @Parameter(name="category", description = "상품 카테고리", example = "ELECTRONICS")
    @ApiResponse(responseCode = "200", description = "상품 조회 성공!")
    @GetMapping("/category")
    public ProductCategory getCategory(@RequestParam ProductCategory category) {
        return category;
    }

    @Operation(summary = "상품 등록", description = "새로운 상품을 등록합니다")
    @ApiResponse(responseCode = "201", description = "상품 등록 성공")
    @PostMapping("/new")
    public ProductDto createProduct(@RequestBody ProductDto product) {
        return product;
    }

    @Operation(summary = "상품 수정", description = "상품 정보를 수정합니다")
    @Parameter(name = "id", description = "수정할 상품 ID", example = "1")
    @Parameter(name = "name", description = "수정할 상품명", example = "고급 노트북")
    @ApiResponse(responseCode = "200", description = "상품 수정 성공")
    @PutMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id, @RequestParam String name) {
        return id + "번 상품명 수정: " + name;
    }

    @Operation(summary = "상품 삭제", description = "상품을 삭제합니다")
    @Parameter(name = "id", description = "삭제할 상품 ID", example = "1")
    @ApiResponse(responseCode = "204", description = "상품 삭제 성공")
    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        return id + "번 상품 삭제 완료";
    }
}
