package com.ltm.services.serviceIplm;

import com.ltm.dao.UserDao;
import com.ltm.dao.daoIplm.UserDaoImpl;
import com.ltm.dto.TransferInfor;
import com.ltm.dto.User;
import com.ltm.services.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public UserServiceImpl() {
        userDao = new UserDaoImpl();
    }

    @Override
    public boolean login(User user) {
        return userDao.login(user);
    }

    @Override
    public synchronized int getBalance(User user) {

        return userDao.getBalance(user);
    }

    @Override
    public synchronized boolean withdraw(User user, int amount) {
        return userDao.withdraw(user, amount);
    }

    @Override
    public synchronized boolean transferMoney(User user, TransferInfor transferInfor) {
        return userDao.transferMoney(user, transferInfor);
    }

    @Override
    public synchronized boolean deposit(User user, int amount) {
        return userDao.deposit(user, amount);
    }
}
