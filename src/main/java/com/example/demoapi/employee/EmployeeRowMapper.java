package com.example.demoapi.employee;


import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet rs, int i) throws SQLException {
        return Employee.builder()
                .id(rs.getLong("ID"))
                .firstName(rs.getString("FIRST_NAME"))
                .firstName(rs.getString("LAST_NAME"))
                .firstName(rs.getString("EMAIL"))
                .build();
    }

}
