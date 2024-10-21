package com.example.oracleapp.repository;

import com.example.oracleapp.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EmployeeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Employee> employeeRowMapper = new RowMapper<Employee>() {
        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            Employee employee = new Employee();
            employee.setId(rs.getInt("ID"));
            employee.setName(rs.getString("NAME"));
            employee.setDepartment(rs.getString("DEPARTMENT"));
            employee.setSalary(rs.getDouble("SALARY"));
            return employee;
        }
    };

    public List<Employee> findAll() {
        String sql = "SELECT * FROM EMPLOYEES";
        return jdbcTemplate.query(sql, employeeRowMapper);
    }

    public Employee findById(int id) {
        String sql = "SELECT * FROM EMPLOYEES WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, employeeRowMapper);
    }
}
