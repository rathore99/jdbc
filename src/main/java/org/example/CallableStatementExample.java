package org.example;

import org.example.config.ConfigLoader;
import org.example.config.DatabaseConfig;

import java.sql.*;

public class CallableStatementExample {

    public static void main(String[] args) {

        try{
            Connection dbConnection = DbConnection.getConnection();
            CallableStatement callableStatement = dbConnection.prepareCall("call getEmployee(?)");
            callableStatement.setInt(1,101);
            ResultSet result = callableStatement.executeQuery();
            Employee employee = new Employee();
            while(result.next()){
                employee.setId(result.getInt("employee_id"));
                employee.setFisrtName(result.getString("first_name"));
                employee.setLastName(result.getString("last_name"));
                employee.setEmail(result.getString("email"));
                employee.setMobileNumber(result.getString("phone_number"));
                employee.setJoiningDate(result.getDate("hire_date"));
                employee.setSalary(result.getDouble("salary"));
                employee.setDepartment(Integer.valueOf(result.getInt("department_id")).toString());
            }

            System.out.println(employee);

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
