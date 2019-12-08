package com.ltm.services;

import com.ltm.dto.TransferInfor;
import com.ltm.dto.User;

public interface UserService {
    public boolean login(User user);

    public int getBalance(User user);

    public boolean withdraw(User user, int amount);

    boolean transferMoney(User user, TransferInfor transferInfor);
}
