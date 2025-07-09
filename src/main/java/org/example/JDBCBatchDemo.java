package org.example;

import java.sql.*;
import java.util.*;

public class JDBCBatchDemo {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/company_db";
    private static final String USER = "root";
    private static final String PASS = "yourpassword";

    public static void main(String[] args) {

        List<Employee> employees = Arrays.asList(
                new Employee(101, "Alice", "alice@example.com", "HR", 50000),
                new Employee(102, "Bob", "bob@example.com", "Finance", 60000),
                new Employee(103, "Charlie", "charlie@example.com", "IT", 70000),
                new Employee(104, "Diana", "diana@example.com", "Sales", 55000)
        );

        String INSERT_SQL = "INSERT INTO employee (id, name, email, department, salary) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(INSERT_SQL)) {

            conn.setAutoCommit(false);  // Required for batch

            for (Employee emp : employees) {
                ps.setInt(1, emp.getId());
                ps.setString(2, emp.getName());
                ps.setString(3, emp.getEmail());
                ps.setString(4, emp.getDepartment());
                ps.setDouble(5, emp.getSalary());
                ps.addBatch(); // Add to batch
            }

            int[] result = ps.executeBatch();  // Execute all in one go
            conn.commit(); // Commit only once

            System.out.println("Inserted " + result.length + " records successfully.");

        } catch (BatchUpdateException bue) {
            System.err.println("Batch failed: " + Arrays.toString(bue.getUpdateCounts()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static class Employee {
        private int id;
        private String name, email, department;
        private double salary;

        public Employee(int id, String name, String email, String department, double salary) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.department = department;
            this.salary = salary;
        }

        public int getId() { return id; }
        public String getName() { return name; }
        public String getEmail() { return email; }
        public String getDepartment() { return department; }
        public double getSalary() { return salary; }
    }
}
