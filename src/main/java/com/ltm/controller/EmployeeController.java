package com.ltm.controller;


import com.ltm.entities.Employee;
import com.ltm.services.EmployeeService;
import com.ltm.services.serviceIplm.EmployeeServiceIplm;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EmployeeController extends HttpServlet {
    private EmployeeService employeeService;
    private RequestDispatcher dispatcher;

    public EmployeeController() {
        employeeService = new EmployeeServiceIplm();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "list";
        }
        switch (action) {
            case "list":
                listEmployees(req, resp);
                break;
            case "edit":
                getSingleEmployee(req, resp);
                break;
            case "delete":
                deleteEmployee(req, resp);
                break;
            default:
                listEmployees(req, resp);
                break;
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Employee employee = new Employee();

        String id = req.getParameter("id");


        employee.setName(req.getParameter("name"));
        employee.setDob(req.getParameter("dob"));
        employee.setDepartment(req.getParameter("department"));

        if (id == null || id.isEmpty()) {
            if (employeeService.addEmployee(employee)) {

                req.setAttribute("message", "Saved successfully");
            }
        } else {
            employee.setId(Integer.parseInt(id));
            if (employeeService.updateEmployee(employee)) {
                req.setAttribute("message", "Update successfully");
            }
        }
        listEmployees(req, resp);
    }

    public void listEmployees(HttpServletRequest req, HttpServletResponse resp) {
        List<Employee> employees = employeeService.getAllEmployee();
        req.setAttribute("employees", employees);
        dispatcher = req.getRequestDispatcher("/views/employee-list.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getSingleEmployee(HttpServletRequest req, HttpServletResponse resp) {

        Integer id = Integer.parseInt(req.getParameter("id"));
        Employee employee = employeeService.getEmployeeId(id);
        req.setAttribute("employee", employee);
        dispatcher = req.getRequestDispatcher("/views/employee-add.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void deleteEmployee(HttpServletRequest req, HttpServletResponse resp) {


        Integer id = Integer.parseInt(req.getParameter("id"));
        if (employeeService.deleteEmployee(id)) {
            req.setAttribute("message", "Deleted successfully");
        }
        listEmployees(req, resp);
    }
}
