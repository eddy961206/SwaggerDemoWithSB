package org.example.swaggerdemowithsb;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class Get1Ctr {
    @GetMapping("/get1")
    public String get1() {
        return "/api/get1 에 대한 응답입니다.";
    }
}
