package com.ltm.bankingService;

import com.ltm.dao.UserDao;
import com.ltm.dao.daoIplm.UserDaoImpl;
import com.ltm.dto.TransferInfor;
import com.ltm.dto.User;

import java.io.IOException;
import java.io.PrintWriter;

public class TransferManager implements Runnable {
    private UserDao userDao;
    private static User user;
    private static TransferInfor transferInfor;
    private static PrintWriter printWriter;

    public TransferManager() {
        userDao = new UserDaoImpl();
    }

    public void transferMoneyUseThread(PrintWriter printWriter, User user, TransferInfor transferInfor) {

        this.user = user;
        this.transferInfor = transferInfor;
        this.printWriter = printWriter;
        Thread thread = new Thread(new TransferManager());
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                transferMoney(user, transferInfor);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private synchronized void transferMoney(User user, TransferInfor transferInfor) throws IOException {
        User userDes = new User(transferInfor.getUsername(), "");
        int banlanceBeforTransfer = userDao.getBalance(user);
        if (banlanceBeforTransfer >= transferInfor.getAmount()) {

            userDao.withdraw(user, userDao.getBalance(user) - transferInfor.getAmount());
            printWriter.println("<p>----------------------------------------------------</p>");
            printWriter.println("<p>Money after withdraw of user "+user.getUser()+ ": " + userDao.getBalance(user)+"</p");

            System.out.println("---------------------------------------");
            System.out.println("Money after withdraw of user " +user.getUser()+ ": " + userDao.getBalance(user));

            userDao.deposit(userDes, userDao.getBalance(userDes) + transferInfor.getAmount());
            printWriter.println("<p>Money after deposit of user "+userDes.getUser()+ ": " + userDao.getBalance(userDes)+"</p>");
            System.out.println("Money after deposit of user "+userDes.getUser() + ": "+ userDao.getBalance(userDes));
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
