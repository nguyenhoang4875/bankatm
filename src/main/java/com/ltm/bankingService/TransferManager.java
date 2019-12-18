package com.ltm.bankingService;

import java.util.Scanner;

public class TransferManager implements Runnable {
    private static CustomerAccount customerAccount1;
    private static CustomerAccount customerAccount2;


    private static Scanner scanner;
    private static int amount;

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
        System.out.println("Enter amount transfer:");
        amount = Integer.parseInt(scanner.nextLine());

        customerAccount2 = new CustomerAccount(ac, balance);

        TransferManager transferManager = new TransferManager();

        Thread ac1 = new Thread(transferManager);

        System.out.println("----------------------------------------");
        System.out.println("Ac1 balance before transfer:" + customerAccount1.getBalance());
        System.out.println("Ac2 balance before transfer:" + customerAccount2.getBalance());

        ac1.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                transferMoney(customerAccount1, customerAccount2, amount);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private synchronized void transferMoney(CustomerAccount customerAccount1, CustomerAccount customerAccount2, int amount) {
        int banlanceBeforTransfer = customerAccount1.getBalance();
        if (banlanceBeforTransfer >= amount) {
            customerAccount1.setBalance(banlanceBeforTransfer - amount);
            customerAccount2.setBalance(customerAccount2.getBalance() + amount);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Transferring " + amount + " usd from ac1 to ac2");
            System.out.println("Ac1 balance: " + customerAccount1.getBalance());
            System.out.println("AC2 balance: " + customerAccount2.getBalance());
        }
    }
}
