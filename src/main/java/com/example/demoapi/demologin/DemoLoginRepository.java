package com.example.demoapi.demologin;

import com.example.demoapi.employee.Employee;
import com.example.demoapi.exception.ErrorCode;
import com.example.demoapi.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class DemoLoginRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Optional<DemoLogin> checkUserName(DemoLoginDto demoLoginDto) {
        String sql = "SELECT * FROM DEMO_LOGIN WHERE user_name = :username";

        try {
            MapSqlParameterSource param = new MapSqlParameterSource();
            param.addValue("username", demoLoginDto.getUserName());
            return this.namedParameterJdbcTemplate.queryForObject(sql, param, new RowMapper<Optional <DemoLogin>>()  {
                @Override
                public Optional <DemoLogin> mapRow(ResultSet rs, int i) throws SQLException {
                    return Optional.of(DemoLogin.builder()
                            .id(rs.getLong("ID"))
                            .userName(rs.getString("USER_NAME"))
                            .password(rs.getString("PASSWORD"))
                            .countLogin(rs.getLong("COUNT_LOGIN"))
                            .isDelete(rs.getString("isDELETE"))
                            .build());
                }
            });
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public Optional<DemoLogin> checkPassword(DemoLoginDto demoLoginDto) {
        String sql = "SELECT * FROM DEMO_LOGIN WHERE user_name = :username and password = :password";

        try {
            MapSqlParameterSource param = new MapSqlParameterSource();
            param.addValue("username", demoLoginDto.getUserName());
            param.addValue("password", demoLoginDto.getPassword());
            return this.namedParameterJdbcTemplate.queryForObject(sql, param, new RowMapper<Optional <DemoLogin>>()  {
                @Override
                public Optional <DemoLogin> mapRow(ResultSet rs, int i) throws SQLException {
                    return Optional.of(DemoLogin.builder()
                            .id(rs.getLong("ID"))
                            .userName(rs.getString("USER_NAME"))
                            .password(rs.getString("PASSWORD"))
                            .countLogin(rs.getLong("COUNT_LOGIN"))
                            .isDelete(rs.getString("isDELETE"))
                            .build());
                }
            });
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public int updateCountLogin(DemoLoginDto demoLoginDto) {
        String sql = "UPDATE DEMO_LOGIN SET count_login = count_login + 1" +
                " WHERE user_name = :username";

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("username", demoLoginDto.getUserName());

        return this.namedParameterJdbcTemplate.update(sql, param);
    }

    public int loginSuccess(DemoLoginDto demoLoginDto) {
        String sql = "UPDATE DEMO_LOGIN SET count_login = 0" +
                " WHERE user_name = :username";

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("username", demoLoginDto.getUserName());

        return this.namedParameterJdbcTemplate.update(sql, param);
    }

}
