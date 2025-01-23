package org.example.swaggerdemowithsb.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Tag(name = "1-사용자 API", description = "사용자 관리 API")
public class UserCtr {

    /*
     * 어노테이션 정리
     *
     * **** Spring 어노테이션 ****
     *  @RestController : REST API 컨트롤러임을 명시.
     *                    - JSON/XML 형식의 응답을 반환
     *                    - @Controller + @ResponseBody(메서드의 반환 값을 HTTP 응답 본문으로 변환)를 합친 기능.
     *
     *  @RequestMapping : API의 기본 URL 경로 설정.
     *                   - 예: @RequestMapping("/api") → /api로 시작하는 모든 요청을 처리.
     *
     *  @RequestParam   : 클라이언트로부터 받은 URL 쿼리 파라미터를 자바 메서드 파라미터에 바인딩.
     *                   - 예: 클라이언트 요청 - /users?id=1 → id 파라미터를 자바 메서드에 전달.
     *                   - required 속성으로 필수 여부 설정 가능 (기본값: true).
     *
     *  @PathVariable   : 클라이언트로부터 받은 URL 경로의 변수 값을 자바 메서드 파라미터에 바인딩.
     *                   - 예: 클라이언트 요청 - /users/1 → 1 부분을 자바 메서드에 전달.
     *                   - RESTful API에서 리소스 식별에 주로 사용.
     *
     *  @RequestBody    : HTTP 요청 본문(JSON, XML 등)을 자바 객체로 변환.
     *                   - 예: JSON → UserDto 객체로 변환.
     *                   - 주로 POST, PUT 요청에서 사용.
     *
     *
     * **** Swagger 어노테이션 ****
     *  @Tag         : API 그룹화를 위한 태그 설정.
     *                - 예: @Tag(name = "사용자 API", description = "사용자 관리 API")
     *                - Swagger UI에서 API를 그룹별로 분류하여 표시.
     *
     *  @Operation   : API 엔드포인트의 상세 정보를 설정.
     *                - 예: @Operation(summary = "사용자 조회", description = "ID로 사용자 정보를 조회합니다.")
     *                - Swagger UI에 API의 요약, 설명, 태그 등을 표시.
     *
     *  @Parameter   : API 파라미터(쿼리, 경로, 헤더 등)의 설명을 추가.
     *                - 예: @Parameter(description = "사용자 ID", example = "1")
     *                - Swagger UI에 파라미터의 의미, 예시 값, 필수 여부 등을 표시.
     *                - 실제 파라미터를 받는 기능은 Spring 어노테이션(@RequestParam, @PathVariable 등)이 담당.
     *
     *  @ApiResponse : API 응답 코드와 설명을 설정.
     *                - 예: @ApiResponse(responseCode = "200", description = "요청 성공")
     *                - Swagger UI에 응답 코드와 설명을 표시.
     */

    // GET 예시 - 조회 - 파라미터 X
    @Operation(summary = "GET 예시", description = "간단한 GET 요청 테스트")  // Swagger 어노테이션
    @GetMapping("/get1")
    public String get1() {
        return "GET 요청 성공";
    }

    // GET 예시2 - 조회 - 파라미터 O
    @Operation(summary = "GET 예시2 (파라미터 입력)", description = "여러 파라미터를 받는 GET 요청 테스트")
    @Parameter(name = "age", description = "사용자 나이", example = "25")
    @Parameter(name = "name", description = "사용자 이름", example = "홍길동")
    @Parameter(name = "job", description = "사용자 직업", example = "개발자")
    @ApiResponse(responseCode = "200", description = "요청 성공")
    @GetMapping("/get2")
    public String get2(
            @RequestParam int age,
            @RequestParam String name,
            @RequestParam(required = false) String job
    ) {
        return String.format("요청 접수!! - \n이름: %s, 나이: %d, 직업: %s", name, age, job != null ? job : "미입력");
    }

    // GET 예시3 - 조회 - Enum 타입 사용
    @Operation(summary = "역할 조회", description = "사용자 역할을 조회합니다.")
    @Parameter(name="role", description = "사용자 역할", example = "ADMIN")
    @GetMapping("/role")
    public UserRole getRole(@RequestParam UserRole role) {
        return role;
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
    @Parameter(name = "id", description = "수정할 사용자 ID", example = "1")
    @Parameter(name = "name", description = "수정할 사용자 이름", example = "홍길둥")
    @ApiResponse(responseCode = "200", description = "사용자 이름 수정 성공")
    @PutMapping("/put1/{id}")
    public String put1(@PathVariable Long id, @RequestParam String name) {
        return id + "번 사용자 이름 수정: " + name;
    }

    // DELETE 예시 - 삭제
    @Operation(summary = "사용자 삭제", description = "ID로 사용자를 삭제합니다.")
    @Parameter(name = "id", description = "삭제할 사용자 ID", example = "1")
    @ApiResponse(responseCode = "204", description = "사용자 삭제 성공")
    @DeleteMapping("/delete1/{id}")
    public String delete1(@PathVariable Long id) {
        return id + "번 사용자 삭제";
    }


}
