package com.ltm.bankingService;

public class CustomerAccount {
    private String account;
    private int balance;

    public CustomerAccount() {
    }

    public CustomerAccount(String account, int balance) {
        this.account = account;
        this.balance = balance;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
