package com.ltm.controller;

import com.ltm.dto.TransferInfor;
import com.ltm.dto.User;
import com.ltm.services.UserService;
import com.ltm.services.serviceIplm.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CustomerController extends HttpServlet {
    private UserService userService;
    private RequestDispatcher dispatcher;

    public CustomerController() {
        userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String action = req.getParameter("action");
        switch (action) {
            case "balance":
                getBalance(req, resp, session);
                break;
            case "withdraw":
                withDraw(req, resp, session);
                break;
            case "transfer":
                transferMoney(req, resp, session);
                break;
            default:
                getBalance(req, resp, session);
                break;
        }

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        String action = req.getParameter("Action");
        System.out.println("------------- " + action);

        switch (action) {
            case "withdraw":
                withdrawPost(req, resp, session);
                break;
            case "transfer":
                transferMoneyPost(req,resp,session);
                break;

        }
        if (action.compareTo("withdraw") == 0) {
            System.out.println("hello doPost withdraw");

        }
    }

    private void transferMoneyPost(HttpServletRequest req, HttpServletResponse resp, HttpSession session) {
        User loginedUser = (User) session.getAttribute("loginedUser");
        String username = req.getParameter("username");
        int amount = Integer.parseInt(req.getParameter("amount"));
        TransferInfor transferInfor = new TransferInfor(username,amount);
        int balanceCurrent = userService.getBalance(loginedUser);

        if (amount <= balanceCurrent) {
            userService.transferMoney(loginedUser,transferInfor);
            req.setAttribute("message", "Transfer success with amount:" + amount);

            System.out.println("money after Transfer: " + userService.getBalance(loginedUser));

            dispatcher = req.getRequestDispatcher("/views/account-withdraw.jsp");
        } else {
            req.setAttribute("message", "Amount money  transfer must be less than balance current");

        }

        dispatcher = req.getRequestDispatcher("/views/account-transfer.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void transferMoney(HttpServletRequest req, HttpServletResponse resp, HttpSession session) {
        User loginedUser = (User) session.getAttribute("loginedUser");

        dispatcher = req.getRequestDispatcher("/views/account-transfer.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void getBalance(HttpServletRequest req, HttpServletResponse resp, HttpSession session) {

        User loginedUser = (User) session.getAttribute("loginedUser");
        int balanceCurrent = userService.getBalance(loginedUser);
        System.out.println("balance current in controller: " + balanceCurrent);
        req.setAttribute("balanceCurrent", balanceCurrent);
        dispatcher = req.getRequestDispatcher("/views/account-balance.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void withdrawPost(HttpServletRequest req, HttpServletResponse resp, HttpSession session) {
        User loginedUser = (User) session.getAttribute("loginedUser");

        int amount = Integer.parseInt(req.getParameter("amount"));
        System.out.println("in setWithdraw: " + amount);
        int balanceCurrent = userService.getBalance(loginedUser);

        if (amount <= balanceCurrent) {
            userService.withdraw(loginedUser, balanceCurrent - amount);
            req.setAttribute("message", "Withdraw success with amount:" + amount);

            System.out.println("money after withdraw: " + userService.getBalance(loginedUser));

            dispatcher = req.getRequestDispatcher("/views/account-withdraw.jsp");
        } else {
            req.setAttribute("message", "Amount money withdraw must be less than balance current");

        }
        dispatcher = req.getRequestDispatcher("/views/account-withdraw.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void withDraw(HttpServletRequest req, HttpServletResponse resp, HttpSession session) {
        User loginedUser = (User) session.getAttribute("loginedUser");
        dispatcher = req.getRequestDispatcher("/views/account-withdraw.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
