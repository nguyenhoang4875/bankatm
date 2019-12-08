package com.ltm.dto;

public class TransferInfor {
    private String username;
    private int amount;

    public TransferInfor() {
    }

    public TransferInfor(String username, int amount) {
        this.username = username;
        this.amount = amount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return String.join("-", username, amount + "");
    }
}
