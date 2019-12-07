package com.ltm.controller;

import com.ltm.dto.User;
import com.ltm.services.UserService;
import com.ltm.services.serviceIplm.UserServiceImpl;
import com.ltm.utils.AppUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginController extends HttpServlet {
    private UserService userService;
    private RequestDispatcher dispatcher;

    public LoginController() {
        userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("loginedUser") != null) {
            System.out.println("----------in doGet------");
            dispatcher = req.getRequestDispatcher("/views/home.jsp");

        } else {

            dispatcher = req.getRequestDispatcher("/views/login.jsp");
        }
        dispatcher.forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      //  doGet(req,resp);
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = new User(username, password);

        if (userService.login(user)) {
            AppUtils.storeLoginedUser(req.getSession(), user);
            dispatcher = req.getRequestDispatcher("/views/home.jsp");
        } else {
            req.setAttribute("message", "username or password wrong!");
            dispatcher = req.getRequestDispatcher("/views/login.jsp");
        }
        dispatcher.forward(req, resp);

    }


}
