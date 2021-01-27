package com.example.demoapi.demologin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DemoLogin {
    private Long id;
    private String userName;
    private String password;
    private Long countLogin;
    private String isDelete;
}
