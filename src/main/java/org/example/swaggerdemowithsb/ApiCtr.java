package org.example.swaggerdemowithsb;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.swaggerdemowithsb.user.UserDto;
import org.example.swaggerdemowithsb.user.UserRole;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Tag(name = "메인 API", description = "사용자 관리 API")
public class ApiCtr {

    // GET 예시 - 조회 - 파라미터 X
    @Operation(summary = "GET 예시", description = "간단한 GET 요청 테스트")  // Swagger 어노테이션
    @GetMapping("/get1")
    public String get1() {
        return "GET 요청 성공";
    }

    // GET 예시2 - 조회 - 파라미터 O
    @Operation(summary = "GET 예시2 (파라미터 입력)", description = "여러 파라미터를 받는 GET 요청 테스트")
    @GetMapping("/get2")
    public String get2(
            @Parameter(description = "사용자 나이", example = "25")
            @RequestParam int age,

            @Parameter(description = "사용자 이름", example = "홍길동")
            @RequestParam String name,

            @Parameter(description = "사용자 직업", example = "개발자")
            @RequestParam(required = false) String job
    ) {
        return String.format("요청 접수!! - \n이름: %s, 나이: %d, 직업: %s", name, age, job != null ? job : "미입력");
    }

    // POST 예시 (DTO 사용) - 생성
    @Operation(summary = "사용자 생성", description = "사용자 정보를 입력받아 생성합니다.")
    @ApiResponse(responseCode = "201", description = "사용자 생성 성공")
    @PostMapping("/post1")
    public UserDto post1(@RequestBody UserDto user) {
        return user;
    }

    // PUT 예시 (경로 변수 사용) - 수정
    @Operation(summary = "사용자 수정", description = "ID로 사용자 이름을 수정합니다.")
    @Parameter(name = "id", description = "사용자 ID", example = "1")
    @PutMapping("/put1/{id}")
    public String put1(@PathVariable Long id, @RequestParam String name) {
        return id + "번 사용자 이름 수정: " + name;
    }

    // DELETE 예시 - 삭제
    @Operation(summary = "사용자 삭제", description = "ID로 사용자를 삭제합니다.")
    @ApiResponse(responseCode = "204", description = "사용자 삭제 성공")
    @DeleteMapping("/delete1/{id}")
    public String delete1(@PathVariable Long id) {
        return id + "번 사용자 삭제";
    }

    @Operation(summary = "검색 기능", description = "이름으로 사용자 검색")
    @GetMapping("/search")
    public String search(
        @Parameter(description = "검색할 이름", example = "John")
        @RequestParam String name
    ) {
        return name + " 검색 결과: ...";
    }

    @Operation(summary = "역할 조회", description = "사용자 역할을 조회합니다.")
    @GetMapping("/role")
    public UserRole getRole(
        @Parameter(description = "사용자 역할", example = "ADMIN")
        @RequestParam UserRole role
    ) {
        return role;
    }
}
