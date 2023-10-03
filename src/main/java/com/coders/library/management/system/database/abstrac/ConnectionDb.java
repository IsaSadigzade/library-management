package com.coders.library.management.system.database.abstrac;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface ConnectionDb {
    static Connection connectDb() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/postgres", "postgres", "postgres123");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("An error occurred: " + e.getMessage());
        }
        return connection;
    }

    static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
