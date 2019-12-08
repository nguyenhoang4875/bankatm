package com.ltm.dao.daoIplm;


import com.ltm.dao.EmployeeDao;
import com.ltm.entities.Employee;
import com.ltm.jdbc.ConnectionProvider;
import com.ltm.jdbc.ConnectionProviderImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoIplm implements EmployeeDao {
    private ConnectionProvider connectionProvider;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    public EmployeeDaoIplm() {
        connectionProvider = new ConnectionProviderImpl();
    }

    @Override
    public List<Employee> getAllEmployee() {
        List<Employee> employees = new ArrayList<>();
        Employee employee;
        try {

            connection = connectionProvider.getConnection();
            String sql = "SELECT * FROM employee";

            connection = connectionProvider.getConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                employee = new Employee();
                employee.setId(resultSet.getInt("id"));
                employee.setName(resultSet.getString("name"));
                employee.setDob(resultSet.getString("dob"));
                employee.setDepartment(resultSet.getString("department"));
                employees.add(employee);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return employees;
    }

    @Override
    public Employee getEmployeeId(Integer id) {
        Employee employee = null;
        try {
            employee = new Employee();
            String query = "SELECT *FROM employee WHERE id= ?";
            connection = connectionProvider.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employee.setId(resultSet.getInt("id"));
                employee.setName(resultSet.getString("name"));
                employee.setDob(resultSet.getString("dob"));
                employee.setDepartment(resultSet.getString("department"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public boolean addEmployee(Employee employee) {
        boolean result = false;
        try {

            String addEmployee = "INSERT INTO employee (name,dob,department) VALUES(?,?,?)";
            connection = connectionProvider.getConnection();
            preparedStatement = connection.prepareStatement(addEmployee);
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getDob());
            preparedStatement.setString(3, employee.getDepartment());
            preparedStatement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return result;
    }


    @Override
    public boolean updateEmployee(Employee employee) {
        boolean result = false;
        try {

            String addEmployee = "UPDATE employee Set name =?, dob =?, department = ? where id = ?";
            connection = connectionProvider.getConnection();
            preparedStatement = connection.prepareStatement(addEmployee);
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getDob());
            preparedStatement.setString(3, employee.getDepartment());
            preparedStatement.setInt(4, employee.getId());
            preparedStatement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return result;
    }

    @Override
    public boolean deleteEmployee(Integer id) {
        boolean result = false;
        try {

            String addEmployee = "DELETE FROM employee WHERE id=?";
            connection = connectionProvider.getConnection();
            preparedStatement = connection.prepareStatement(addEmployee);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return result;
    }
}
