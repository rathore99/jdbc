package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionManagement {
    public static void main(String[] args) throws SQLException {

        Connection conn = DriverManager.getConnection(url, user, pass);


        try {
            conn.setAutoCommit(false);  // Start transaction

            PreparedStatement withdraw = conn.prepareStatement(
                    "UPDATE accounts SET balance = balance - ? WHERE id = ?");
            withdraw.setDouble(1, 500);
            withdraw.setInt(2, 101);
            withdraw.executeUpdate();

            PreparedStatement deposit = conn.prepareStatement(
                    "UPDATE accounts SET balance = balance + ? WHERE id = ?");
            deposit.setDouble(1, 500);
            deposit.setInt(2, 102);
            deposit.executeUpdate();

            conn.commit();
            System.out.println("Transaction successful.");
        } catch (SQLException e) {
            conn.rollback();
            System.out.println("Transaction failed. Rolled back.");
        }

    }
}
