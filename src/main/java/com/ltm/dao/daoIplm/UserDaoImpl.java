package com.ltm.dao.daoIplm;

import com.ltm.dao.UserDao;
import com.ltm.dto.TransferInfor;
import com.ltm.dto.User;
import com.ltm.jdbc.ConnectionProvider;
import com.ltm.jdbc.ConnectionProviderImpl;

import java.sql.*;
import java.util.Arrays;

public class UserDaoImpl implements UserDao {
    private ConnectionProvider connectionProvider;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    public UserDaoImpl() {
        connectionProvider = new ConnectionProviderImpl();

    }

    @Override
    public boolean login(User user) {
        boolean result = false;
        try {
            String sql = "SELECT *FROM account WHERE username=? AND password=?";
            connection = connectionProvider.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUser());
            preparedStatement.setString(2, user.getPassword());
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            if (resultSet.getRow() != 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;

    }

    @Override
    public int getBalance(User user) {
        int balance = 0;
        try {

            String sql = "SELECT balance FROM account WHERE username=?";
            connection = connectionProvider.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUser());
            //  preparedStatement.setString(2, user.getPassword());
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            balance = resultSet.getInt("balance");
            System.out.println("balance: " + balance);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return balance;
    }

    @Override
    public boolean withdraw(User user, int amount) {
        boolean result = false;
        try {

            String sql = "UPDATE account SET balance = ? WHERE username=? AND password=?";
            connection = connectionProvider.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, amount);
            preparedStatement.setString(2, user.getUser());
            preparedStatement.setString(3, user.getPassword());
            int check = preparedStatement.executeUpdate();
            if (check != 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public synchronized boolean transferMoney(User user, TransferInfor transferInfor) {
        boolean result = false;
        int balanceSrcBeforeTsf = getBalance(user);
        int balanceDesBeforeTsf = getBalance(new User(transferInfor.getUsername(), ""));

        System.out.println("balance Src before transfer:" + balanceSrcBeforeTsf);
        System.out.println("balance Des before transfer:" + balanceDesBeforeTsf);
        System.out.println(transferInfor);
        try {

            String sqlWithdraw = "UPDATE account SET balance = ? WHERE username=? AND password=?";
            String sqladdMoney = "UPDATE account SET balance = ? WHERE username=?";

            connection = connectionProvider.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement psWithdraw = connection.prepareStatement(sqlWithdraw);
            PreparedStatement psAddMoney = connection.prepareStatement(sqladdMoney);

            psWithdraw.setInt(1, balanceSrcBeforeTsf - transferInfor.getAmount());
            psWithdraw.setString(2, user.getUser());
            psWithdraw.setString(3, user.getPassword());

            psWithdraw.addBatch();
            int[] rows1 = psWithdraw.executeBatch();
            System.out.println("------------------ Start Transfer Money Dao---------------");
            System.out.println(Arrays.toString(rows1));

            psAddMoney.setInt(1, balanceDesBeforeTsf + transferInfor.getAmount());
            psAddMoney.setString(2, transferInfor.getUsername());
            psAddMoney.addBatch();
            int[] rows2 = psAddMoney.executeBatch();
            System.out.println(Arrays.toString(rows2));
            connection.commit();
            result = true;
            System.out.println("------------------ End Transfer Money Dao---------------");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;

    }

    @Override
    public boolean deposit(User user, int amount) {
        return withdraw(user, amount);
    }
}
