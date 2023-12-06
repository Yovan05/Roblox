/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.roblox.models;

import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import mx.itson.roblox.persistence.MySQLConnection;
import mx.itson.roblox.entities.Employee;


/**
 *
 * @author alexi
 */
public class EmployeeModel {
    
    /**
     * Get the Employee object with the id enter
     * @param id of the employee to search
     * @return the requested employee
     */
    public static Employee getEmployee(int id){
        Employee employee = new Employee();
        try {
            Connection connection = MySQLConnection.get();
            PreparedStatement statement = connection.prepareStatement("Select * FROM employees where id=?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
            employee.setId(resultSet.getInt(1));
            employee.setName(resultSet.getString(2));
            employee.setAgo(resultSet.getInt(3));
            employee.setPhone(resultSet.getString(4));
            employee.setPosition(resultSet.getString(5));
            employee.setSalary(resultSet.getDouble(6));
            }
        } catch (SQLException ex) {
            System.err.println("Error: "+ex.getMessage());
        }
        
        
        return employee;
    }
    
    /**
     * Gets a list of all Employee where their name matches the filter
     * @param filter with which matches will be searched in the database
     * @return returns a list of Client that match the entered filter
     */
    public static List<Employee> getAll(String filter){
        List<Employee> employees = new ArrayList();
        try {
            Connection connection = MySQLConnection.get();
            PreparedStatement statement = connection.prepareStatement("Select * FROM employees where name like ?");
            statement.setString(1, "%"+filter+"%");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Employee e = new Employee();
                e.setId(resultSet.getInt(1));
                e.setName(resultSet.getString(2));
                e.setAgo(resultSet.getInt(3));
                e.setPhone(resultSet.getString(4));
                e.setPosition(resultSet.getString(5));
                e.setSalary(resultSet.getDouble(6));
                employees.add(e);
                
            }
            
        } catch (SQLException ex) {
            System.err.println("Error: "+ex.getMessage());
        }
        return employees;
    }
    
    /**
     * Add a new employee to the database
     * @param name of the new employee
     * @param ago of the new employee
     * @param phone of the new employee
     * @param position of the new employee
     * @param salary of the new employee
     * @return boolean where if true, a new employee has been successfully added to the database and if false, a failure has occurred
     */
    public static boolean save(String name, int ago, String phone, String position, double salary){
        boolean result = false;
        try {
            Connection connection = MySQLConnection.get();
            String query ="INSERT INTO employees (name, ago, phone, position, salary) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statament = connection.prepareStatement(query);
            statament.setString(1, name);
            statament.setInt(2, ago);
            statament.setString(3, phone);
            statament.setString(4, position);
            statament.setDouble(5, salary);
            statament.execute();
            
            result = statament.getUpdateCount() == 1;
        } catch (SQLException ex) {
            System.err.print("Error: "+ex.getMessage());
        }

        return result;
    }
    
    /**
     * Update a employee to the database
     * @param id of the Employee to edit
     * @param name of the Employee to edit
     * @param ago of the Employee to edit
     * @param phone of the Employee to edit
     * @param position of the Employee to edit
     * @param salary of the Employee to edit
     * @return boolean where if true, the edit was successful, if false, an error occurred
     */
    public static boolean Update(int id, String name, int ago, String phone, String position, double salary){
        boolean result = false;
        try {
            Connection connection = MySQLConnection.get();
            String query ="UPDATE employees SET name=?, ago=?, phone=?, position=?, salary=? WHERE id=?";
            PreparedStatement statament = connection.prepareStatement(query);
            statament.setString(1, name);
            statament.setInt(2, ago);
            statament.setString(3, phone);
            statament.setString(4, position);
            statament.setDouble(5, salary);
            statament.setInt(6, id);
            statament.execute();
            
            result = statament.getUpdateCount() == 1;
        } catch (SQLException ex) {
            System.err.print("Error: "+ex.getMessage());
        }

        return result;
    }
    
    /**
     * Delete a employee to the database
     * @param id of the employee to delete
     * @return  boolean where if true the employee was deleted and if false an error occurred
     */
    public static boolean delete(int id){
        boolean result = false;
        
        try {
            Connection connection = MySQLConnection.get();
            String query = "DELETE FROM employees WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
            result = statement.getUpdateCount()==1;
            connection.close();
   
        } catch (SQLException ex) {
            System.err.print("Error: "+ ex.getMessage());
        }
        
        
        return result;
    }
    
}
