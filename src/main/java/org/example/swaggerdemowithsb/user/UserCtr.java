package org.example.swaggerdemowithsb.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserCtr {

    // GET 예시 - 조회 - 파라미터 X
    @GetMapping("/get1")
    public String get1() {
        return "GET 요청 성공";
    }

    // GET 예시2 - 조회 - 파라미터 O
    @GetMapping("/get2")
    public String get2(
            @RequestParam int age,
            @RequestParam String name,
            @RequestParam(required = false) String job
    ) {
        return String.format("요청 접수!! - \n이름: %s, 나이: %d, 직업: %s", name, age, job != null ? job : "미입력");
    }

    // GET 예시3 - 조회 - Enum 타입 사용
    @GetMapping("/role")
    public UserRole getRole(@RequestParam UserRole role) {
        return role;
    }

    // POST 예시 (DTO 사용) - 생성
    @PostMapping("/post1")
    public UserDto post1(@RequestBody UserDto user) {
        return user;
    }

    // PUT 예시 (경로 변수 사용) - 수정
    @PutMapping("/put1/{id}")
    public String put1(@PathVariable Long id, @RequestParam String name) {
        return id + "번 사용자 이름 수정: " + name;
    }

    // DELETE 예시 - 삭제
    @DeleteMapping("/delete1/{id}")
    public String delete1(@PathVariable Long id) {
        return id + "번 사용자 삭제";
    }


}
