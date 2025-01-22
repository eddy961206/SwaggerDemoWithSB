package org.example.swaggerdemowithsb.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    @Schema(description = "사용자 이름", example = "홍길동", required = true)
    @NotBlank   // 필수 필드 (유효성 검사)
    private String name;

    @Schema(description = "나이", example = "30", minimum = "0")
    @Min(0)     // 최소값 제한 (유효성 검사)
    private int age;
}
