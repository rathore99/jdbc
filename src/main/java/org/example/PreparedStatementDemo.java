package org.example;

import java.sql.*;
import java.util.Scanner;

public class PreparedStatementDemo {
    public static Connection connection;

    public static void main(String[] args) throws SQLException {
        Scanner scan = new Scanner(System.in);
        String exit = "Y";
        setDBConnection();
        while (exit.equals("Y")) {
            System.out.println("Enter Employee Id to get details");
            int id = scan.nextInt();
            Employee employee = getEmployeeInfo(id);
            //deleteEmployee(id);
            System.out.println(employee);
            System.out.println("do you wanna continue (Y\\N)");
            exit = scan.next();
        }
    }

        private static Connection setDBConnection() {
        if (connection != null) return connection;
        String url = "jdbc:mysql://localhost:3307/hr";
        String username = "root";
        String password = "root";
        try {
            connection = DriverManager.getConnection(url, username, password);
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void deleteEmployee(int id) {
        Connection connection = setDBConnection();
        try {
            PreparedStatement delete = connection.prepareStatement("DELETE FROM employees WHERE employee_id = ?");
            delete.setInt(1, id);
            int rowAffected = delete.executeUpdate();
            System.out.println("rows deleted " + rowAffected);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("failiure");
        }

    }

public static Employee getEmployeeInfo(int id) throws SQLException {

    Employee employee = new Employee();
 String query = "select e.*,d.department_name from employees e join departments d on e.department_id = d.department_id where e.employee_id = ?;";
    PreparedStatement preparedStatement = setDBConnection().prepareStatement(query);
    preparedStatement.setInt(1, id);
   // preparedStatement.setString(2, "john");
    ResultSet result = preparedStatement.executeQuery();

    while(result.next()){
        employee.setId(result.getInt("employee_id"));
        employee.setFisrtName(result.getString("first_name"));
        employee.setLastName(result.getString("last_name"));
        employee.setEmail(result.getString("email"));
        employee.setMobileNumber(result.getString("phone_number"));
        employee.setJoiningDate(result.getDate("hire_date"));
        employee.setSalary(result.getDouble("salary"));
        employee.setDepartment(result.getString("department_name"));
    }
    //System.out.println(employee);
    result.close();
    preparedStatement.close();
    // conn.close()
    return employee;
}
}


class Employee1 {
    int employeeId;
    String firstName;
    String lastName;
    String email;

    public Employee1() {
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Employee Details are:\n" + "employeeId: " + employeeId + "\nfirstName: " + firstName + "\nlastName: " + lastName + "\nemail: " + email;
    }
}
