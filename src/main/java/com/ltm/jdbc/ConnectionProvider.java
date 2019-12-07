package com.ltm.jdbc;

import java.sql.Connection;

public interface ConnectionProvider {
    public Connection getConnection();
}
