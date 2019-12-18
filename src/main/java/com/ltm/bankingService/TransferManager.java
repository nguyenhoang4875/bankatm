package com.ltm.bankingService;

import com.ltm.dao.UserDao;
import com.ltm.dao.daoIplm.UserDaoImpl;
import com.ltm.dto.TransferInfor;
import com.ltm.dto.User;

import java.util.Scanner;

public class TransferManager implements Runnable {
    private UserDao userDao;
    private static User user;
    private static TransferInfor transferInfor;

    public TransferManager(){
        userDao = new UserDaoImpl();
    }

    public void transferMoneyUseThread(User user, TransferInfor transferInfor){
        this.user = user;
        this.transferInfor = transferInfor;
        Thread thread = new Thread(new TransferManager());
        thread.start();

    }


    private static Scanner scanner;
    private static int amount;

   /* public static void main(String[] args) {
        scanner = new Scanner(System.in);
        System.out.print("Enter account:");
        String ac = scanner.nextLine();
        System.out.print("Enter balance:");
        int balance = Integer.parseInt(scanner.nextLine());

        customerAccount1 = new CustomerAccount(ac, balance);
        System.out.print("Enter account:");
        ac = scanner.nextLine();
        System.out.print("Enter balance:");
        balance = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter amount transfer:");
        amount = Integer.parseInt(scanner.nextLine());

        customerAccount2 = new CustomerAccount(ac, balance);

        TransferManager transferManager = new TransferManager();

        Thread ac1 = new Thread(transferManager);

        System.out.println("----------------------------------------");
        System.out.println("Ac1 balance before transfer:" + customerAccount1.getBalance());
        System.out.println("Ac2 balance before transfer:" + customerAccount2.getBalance());

        ac1.start();
    }*/

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

    private synchronized void transferMoney(User user, TransferInfor transferInfor) {
        User userDes = new User(transferInfor.getUsername(),"");
        int banlanceBeforTransfer = userDao.getBalance(user);
        if (banlanceBeforTransfer >= transferInfor.getAmount()) {
            userDao.withdraw(user,userDao.getBalance(user) - transferInfor.getAmount());
            System.out.println("---------------------------------------");
            System.out.println("Money after withdraw use src: "+userDao.getBalance(user));

            System.out.println("---------------------------------------");

            userDao.deposit(userDes,userDao.getBalance(userDes)+transferInfor.getAmount());

            System.out.println("Money after deposit use des: "+userDao.getBalance(userDes));
            System.out.println(userDao.getBalance(user));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
