package org.example;

import java.sql.*;
import java.nio.file.*;
import java.io.IOException;
import java.util.List;

public class SQLFileExecutor {

      public static void main(String[] args) {
        String filePath = "C:\\rahul-projects\\jdbc\\src\\main\\resources\\insert.sql"; // Path to your SQL file
        try  {
            Connection conn = DbConnection.getConnection();
            conn.setAutoCommit(false); // Optional: transaction control
            String sqlContent = readFileAsString(filePath);
            String[] sqlStatements = sqlContent.split(":");


            try (Statement stmt = conn.createStatement()) {
                for (String sql : sqlStatements) {
                    sql = sql.trim();
                    if (!sql.isEmpty()) {

                        stmt.executeUpdate(sql);
                        System.out.println("Executed: " + sql);
                    }
                }
                conn.commit(); // Commit if all successful
                System.out.println("All SQL statements executed successfully.");
            } catch (SQLException ex) {
                conn.rollback(); // Roll back on error
                System.err.println("SQL execution failed. Transaction rolled back.");
                ex.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String readFileAsString(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }
}
