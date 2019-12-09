package com.ltm.dao;

import com.ltm.dto.TransferInfor;
import com.ltm.dto.User;

public interface UserDao {
    public boolean login(User user);

    public int getBalance(User user);

    public boolean withdraw(User user, int amount);

    boolean transferMoney(User user, TransferInfor transferInfor);

    public boolean deposit(User user, int amount);

}
