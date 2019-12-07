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
    public int getBalance(User user) {

        return userDao.getBalance(user);
    }

    @Override
    public boolean withdraw(User user, int amount) {
        return userDao.withdraw(user, amount);
    }

    @Override
    public boolean transferMoney(User user,TransferInfor transferInfor) {
        return userDao.transferMoney(user, transferInfor);
    }
}
