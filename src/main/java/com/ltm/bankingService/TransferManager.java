package com.ltm.bankingService;

import java.util.Scanner;

public class TransferManager implements Runnable {
    private static CustomerAccount customerAccount1;
    private static CustomerAccount customerAccount2;

    private static Scanner scanner;

    public static void main(String[] args) {
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

        customerAccount2 = new CustomerAccount(ac, balance);

        TransferManager transferManager = new TransferManager();

        Thread ac1 = new Thread(transferManager);

        Thread ac2 = new Thread(transferManager);

        ac1.start();
        ac2.start();

        System.out.println("----------------------------------------");

        System.out.println("Ac1 balance after transfer:" + customerAccount1.getBalance());
        System.out.println("Ac2 balance after transfer:" + customerAccount2.getBalance());


    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            //transferMoney(customerAccount1, customerAccount2,100);
            try {
                makeWithdraw(customerAccount1, 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private synchronized void transferMoney(CustomerAccount customerAccount1, CustomerAccount customerAccount2, int amount) {
        int banlanceBeforTransfer = customerAccount1.getBalance();
        if (banlanceBeforTransfer >= amount) {
            customerAccount1.setBalance(banlanceBeforTransfer - amount);
            customerAccount2.setBalance(customerAccount2.getBalance() + amount);
            System.out.println("transfer AC1 to AC2 amount:" + amount);
           /* System.out.println("Ac1 balance:"+customerAccount1.getBalance());
            System.out.println("Ac2 balance:"+customerAccount2.getBalance());*/

        }

    }

    private synchronized void makeWithdraw(CustomerAccount customerAccount1, int amount) throws InterruptedException {
        int banlanceBeforTransfer = customerAccount1.getBalance();
        if (banlanceBeforTransfer >= amount) {
            customerAccount1.setBalance(banlanceBeforTransfer - amount);
            Thread.sleep(100);
            System.out.println("Ac1 balance:" + customerAccount1.getBalance());
         /*   customerAccount2.setBalance(customerAccount2.getBalance() + amount);
            System.out.println("transfer AC1 to AC2 amount:"+ amount);*/
           /* System.out.println("Ac1 balance:"+customerAccount1.getBalance());
            System.out.println("Ac2 balance:"+customerAccount2.getBalance());*/

        }

    }
}
