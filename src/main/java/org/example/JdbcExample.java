package org.example;

import java.sql.*;

public class JdbcExample {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3307/hr";
        String username = "root";
        String password = "root";


        try (Connection conn = DriverManager.getConnection(url, username, password)) {

            Statement stmt = conn.createStatement();
            insertRecord(stmt);
            //updateRecord(stmt);
            //deleteRecord(stmt);
//
//            ResultSet rs = stmt.executeQuery();
//
//            while (rs.next()) {
//                System.out.println(rs.getInt("employee_id") + ": " + rs.getString("first_name"));
//            }
//
//            rs.close();
//            stmt.close();
//            conn.close();
//
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static int deleteRecord(Statement stmt) {
        int rowsAffected =0;
        String deleteQuery = "DELETE FROM EMPLOYEES WHERE employee_id = 105;";
        try {
            rowsAffected = stmt.executeUpdate(deleteQuery);
            if(rowsAffected> 0){
                System.out.println("Row deleted Successfully "+ rowsAffected);
            }
            else{
                System.out.println("Row deletion śfailed "+rowsAffected);
            }
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("exception occured "+ e.getSQLState()+" stacktrace "+ e.getStackTrace());
        }
        return rowsAffected;
    }

    private static int updateRecord(Statement stmt) {
        int rowsAffected =0;
        String updateQuery = "UPDATE EMPLOYEES SET first_name= 'ramesh' WHERE employee_id = 105;";
        try {
            rowsAffected = stmt.executeUpdate(updateQuery);
            if(rowsAffected> 0){
                System.out.println("Row Updated Successfully");
            }
            else{
                System.out.println("Row Update failed");
            }
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("exception occured "+ e.getSQLState()+" stacktrace "+ e.getStackTrace());
        }
        return rowsAffected;
    }

    public static int insertRecord(Statement statement){
        int rowsAffected =0;
        String query = "INSERT INTO employees VALUES (105, 'John2', 'Smith', 'john2.smith@email.com', '1234567890', '2020-01-10', 'IT_PROG', 6000.00, 20);";
       try {
            rowsAffected = statement.executeUpdate(query);
           if(rowsAffected> 0){
               System.out.println("Row inserted Successfully");
           }
           else{
               System.out.println("Insertion failed");
           }
       }catch (SQLException e){
           e.printStackTrace();
           System.out.println("exception occured "+ e.getSQLState()+" stacktrace "+ e.getStackTrace());
       }
        return rowsAffected;
    }
}