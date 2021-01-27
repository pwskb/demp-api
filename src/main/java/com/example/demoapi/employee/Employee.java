package com.example.demoapi.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String isDelete;
}
