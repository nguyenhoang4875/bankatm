package com.ltm.services;



import com.ltm.entities.Employee;

import java.util.List;

public interface EmployeeService {
    public List<Employee> getAllEmployee();
    public Employee getEmployeeId(Integer id);
    public boolean addEmployee(Employee employee);
    public boolean updateEmployee(Employee employee);
    public boolean deleteEmployee(Integer id);
}
