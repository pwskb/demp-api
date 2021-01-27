package com.example.demoapi.demologin;

import com.example.demoapi.employee.EmployeeDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class DemoLoginController {

    private final DemoLoginService demoLoginService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody DemoLoginDto demoLoginDto) throws Exception {
        log.info("login request : {}", demoLoginDto);
        this.demoLoginService.login(demoLoginDto);
        return ResponseEntity.noContent().build();
    }
}
