package org.example;

import java.sql.*;

public class TransactionManagement {
    public static void main(String[] args) throws SQLException {
        String url = null,user = null,pass = null;
        Connection conn = DbConnection.getConnection();

        /*
        A -- balance 1000 --> 500
        B -- balance 500  --> 1000
        A-- withdraw 500
        B-- deposit 500


         101 0
         102 1500
         */

        try {
            conn.setAutoCommit(false);  // Start transaction
            PreparedStatement stmt = conn.prepareStatement("Select * from accounts where id = ?");
            stmt.setInt(1, 102);

            ResultSet rs1 = stmt.executeQuery();
            while(rs1.next()){
                System.out.println("Current balance is "+rs1.getDouble("balance"));
            }

            PreparedStatement withdraw = conn.prepareStatement(
                    "UPDATE accounts SET balance = balance - ? WHERE id = ?");
            withdraw.setDouble(1, 500);
            withdraw.setInt(2, 102);
            withdraw.executeUpdate();

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                System.out.println("Current balance is "+rs.getDouble("balance"));
            }

            if(!conn.getAutoCommit()){
                throw  new SQLException("Transaction failed");
            }
            PreparedStatement deposit = conn.prepareStatement(
                    "UPDATE accounts SET balance = balance + ? WHERE id = ?");
            deposit.setDouble(1, 500);
            deposit.setInt(2, 101);
            deposit.executeUpdate();

            conn.commit();
            System.out.println("Transaction successful.");
        } catch (SQLException e) {
            conn.rollback();
            System.out.println("Transaction failed. Rolled back.");
        }

    }
}
