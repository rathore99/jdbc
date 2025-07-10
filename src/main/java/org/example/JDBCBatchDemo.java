package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class JDBCBatchDemo {

    public static void main(String[] args) throws IOException {

        String filePath = "C:\\rahul-projects\\jdbc\\src\\main\\resources\\employee.csv"; // Path to your SQL file
        String record = readFileAsString(filePath);
        String[] rows = record.split("\n");
        List<Employee> employees = getEmployee(rows);
//        List<Employee> employees = Arrays.asList(
//                new Employee(111, "Alice", "alice@example.com", "40", 50000),
//                new Employee(112, "Bob", "bob@example.com", "30", 60000),
//                new Employee(113, "Charlie", "charlie@example.com", "20", 70000),
//                new Employee(114, "Diana", "diana@example.com", "10", 55000)
//        );
//        123, 'John', 'Smith', 'john.smith@email.com', '1234567890', '2020-01-10', 'IT_PROG', 6000.00, 20);

        String INSERT_SQL = "INSERT INTO employees  VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try{

            Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_SQL);


            conn.setAutoCommit(false);  // Required for batch

            for (Employee emp : employees) {
                ps.setInt(1, emp.getId());
                ps.setString(2, emp.getFisrtName());
                ps.setString(3, emp.getLastName());
                ps.setString(4, emp.getEmail());
                ps.setString(5,emp.getMobileNumber());
                ps.setDate(6,emp.getJoiningDate());
                ps.setString(7,emp.getJobId());
                ps.setDouble(8, emp.getSalary());
                ps.setInt(9,Integer.valueOf(emp.getDepartment()));
                ps.addBatch(); // Add to batch
            }

            int[] result = ps.executeBatch();  // Execute all in one go
            conn.commit(); // Commit only once

            System.out.println("Inserted " + result.length + " records successfully.");

        } catch (BatchUpdateException bue) {
            bue.printStackTrace();
            System.err.println("Batch failed: " + Arrays.toString(bue.getUpdateCounts()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static List<Employee> getEmployee(String[] rows) {
        List<Employee> employees = new ArrayList<>();
        for(String row:rows ){
            String[] values = row.split(",");
            employees.add(getEmployeeObj(values));
        }
        return employees;
    }

    private static Employee getEmployeeObj(String[] values) {
       for(int i=0;i < values.length;++i){
           System.out.println(values[i]);
           values[i] = values[i].trim();
       }
        Employee employee = new Employee(Integer.valueOf(values[0]),values[1],values[2],values[3],values[4], Date.valueOf(values[5]),values[6],Double.valueOf(values[7]),
                values[8]);

        return employee;
    }

    private static String readFileAsString(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }
}
