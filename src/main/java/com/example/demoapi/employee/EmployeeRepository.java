package com.example.demoapi.employee;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class EmployeeRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Collection<Employee> getEmployee() {
        String sql = "SELECT * FROM TBL_EMPLOYEES WHERE ISDELETE = 'N'";
        return this.namedParameterJdbcTemplate.query(sql, new RowMapper<Employee>() {
            @Override
            public Employee mapRow(ResultSet rs, int i) throws SQLException {
                return Employee.builder()
                        .id(rs.getLong("ID"))
                        .firstName(rs.getString("FIRST_NAME"))
                        .lastName(rs.getString("LAST_NAME"))
                        .email(rs.getString("EMAIL"))
                        .isDelete(rs.getString("isDelete"))
                        .build();
            }
        });
    }

    public Optional <Employee> getEmployeeById(long id) {
        String sql = "SELECT * FROM TBL_EMPLOYEES WHERE ID = :id AND ISDELETE = 'N'";

        try {
            MapSqlParameterSource param = new MapSqlParameterSource();
            param.addValue("id", id);
            return this.namedParameterJdbcTemplate.queryForObject(sql, param, new RowMapper<Optional <Employee>>()  {
                @Override
                public Optional <Employee> mapRow(ResultSet rs, int i) throws SQLException {
                    return Optional.of(Employee.builder()
                            .id(rs.getLong("ID"))
                            .firstName(rs.getString("FIRST_NAME"))
                            .lastName(rs.getString("LAST_NAME"))
                            .email(rs.getString("EMAIL"))
                            .isDelete(rs.getString("isDELETE"))
                            .build());
                }
            });
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public int saveEmployee(Employee employee) {
        String sql = "INSERT INTO TBL_EMPLOYEES (first_name, last_name, email)\n " +
                " values (:firstName, :lastName, :email)";

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("firstName", employee.getFirstName());
        param.addValue("lastName", employee.getLastName());
        param.addValue("email", employee.getEmail());

        return this.namedParameterJdbcTemplate.update(sql, param);
    }

    public int updateEmployee(Employee employee) {
        String sql = "UPDATE TBL_EMPLOYEES SET first_name = :firstName, last_name = :lastName, email = :email" +
                " WHERE ID = :id";

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", employee.getId());
        param.addValue("firstName", employee.getFirstName());
        param.addValue("lastName", employee.getLastName());
        param.addValue("email", employee.getEmail());

        return this.namedParameterJdbcTemplate.update(sql, param);
    }

    public int updateSomeFieldEmployee(Employee employee) {
        log.info("info : {}", employee);
        StringBuilder sql = new StringBuilder();

        sql.append("UPDATE TBL_EMPLOYEES SET id = :id");

        if(StringUtils.hasText(employee.getFirstName())){
            sql.append(", first_name = :firstName");
//            if(StringUtils.hasText(employee.getLastName()) || StringUtils.hasText(employee.getEmail())){
//                sql.append(",");
//            }
        }
        if(StringUtils.hasText(employee.getLastName())){
            sql.append(", last_name = :lastName");
//            if(StringUtils.hasText(employee.getEmail())){
//                sql.append(",");
//            }
        }
        if(StringUtils.hasText(employee.getEmail())){
            sql.append(", email = :email");
        }

        sql.append(" WHERE ID = :id");

        log.info("sql : {}", sql);
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", employee.getId());
        param.addValue("firstName", employee.getFirstName());
        param.addValue("lastName", employee.getLastName());
        param.addValue("email", employee.getEmail());

        return this.namedParameterJdbcTemplate.update(sql.toString(), param);
    }

    public int deleteEmployee(long id) {
        log.info("ID : {}", id);
        StringBuilder sql = new StringBuilder();

        sql.append("UPDATE TBL_EMPLOYEES SET isDelete = 'Y'");
        sql.append(" WHERE ID = :id AND ISDELETE = 'N'");

        log.info("sql : {}", sql);
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", id);

        return this.namedParameterJdbcTemplate.update(sql.toString(), param);
    }

//    public EmployeeDto addEmployee(EmployeeDto request) {
//        String sql = "INSERT INTO TBL_EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL) values (:firstName, :lastName, :email)";
//        SqlParameterSource parameters = new MapSqlParameterSource()
//                .addValue("firstName", request.getFirstName())
//                .addValue("lastName", request.getLastName())
//                .addValue("email", request.getEmail());
//        namedParameterJdbcTemplate.update(sql, parameters);
//        return request;
//    }
//
//    public Employee updateEmployee(Employee request) {
//        String sql = "UPDATE TBL_EMPLOYEES SET FIRST_NAME = :firstName, LAST_NAME = :lastName, EMAIL = :email" +
//                " WHERE ID = :id";
//        SqlParameterSource parameters = new MapSqlParameterSource()
//                .addValue("id", request.getId())
//                .addValue("firstName", request.getFirstName())
//                .addValue("lastName", request.getLastName())
//                .addValue("email", request.getEmail());
//        namedParameterJdbcTemplate.update(sql, parameters);
//        return request;
//    }
//
//    public void deleteEmployee(long id) {
//        String sql = "DELETE FROM TBL_EMPLOYEES WHERE ID = :id";
//        SqlParameterSource parameters = new MapSqlParameterSource()
//                .addValue("id", id);
//        namedParameterJdbcTemplate.update(sql, parameters);
//    }


}
