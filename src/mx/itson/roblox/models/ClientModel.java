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
import mx.itson.roblox.entities.Client;
import mx.itson.roblox.persistence.MySQLConnection;

/**
 *
 * @author alexi
 */
public class ClientModel {
    
    /**
     * Get the Client object with the id enter
     * @param id of the client to search
     * @return the requested client
     */
    public static Client getClient(int id){
        Client client = new Client();
        try {
            Connection connection = MySQLConnection.get();
            PreparedStatement statement = connection.prepareStatement("Select * FROM clients where id=?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
            client.setId(resultSet.getInt(1));
            client.setName(resultSet.getString(2));
            client.setAgo(resultSet.getInt(3));
            client.setPhone(resultSet.getString(4));
            client.setCity(resultSet.getString(5));
            }
        } catch (SQLException ex) {
            System.err.println("Error: "+ex.getMessage());
        }
        
        
        return client;
    }
    
    /**
     * Gets a list of all Client where their name matches the filter
     * @param filter with which matches will be searched in the database
     * @return returns a list of Client that match the entered filter
     */
    public static List<Client> getAll(String filter){
        List<Client> clients = new ArrayList();
        try {
            Connection connection = MySQLConnection.get();
            PreparedStatement statement = connection.prepareStatement("Select * FROM clients where name like ?");
            statement.setString(1, "%"+filter+"%");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Client c = new Client();
                c.setId(resultSet.getInt(1));
                c.setName(resultSet.getString(2));
                c.setAgo(resultSet.getInt(3));
                c.setPhone(resultSet.getString(4));
                c.setCity(resultSet.getString(5));
                clients.add(c);
                
            }
            
        } catch (SQLException ex) {
            System.err.println("Error: "+ex.getMessage());
        }
        return clients;
    }
    
    /**
     * Add a new client to the database
     * @param name of the new Client
     * @param ago of the new Client
     * @param phone of the new Client
     * @param city of the new Client
     * @return boolean where if true, a new client has been successfully added to the database and if false, a failure has occurred
     */
    public static boolean save(String name, int ago, String phone, String city){
        boolean result = false;
        try {
            Connection connection = MySQLConnection.get();
            String query ="INSERT INTO clients (name, ago, phone, city) VALUES (?, ?, ?, ?)";
            PreparedStatement statament = connection.prepareStatement(query);
            statament.setString(1, name);
            statament.setInt(2, ago);
            statament.setString(3, phone);
            statament.setString(4, city);
            statament.execute();
            
            result = statament.getUpdateCount() == 1;
        } catch (SQLException ex) {
            System.err.print("Error: "+ex.getMessage());
        }

        return result;
    }
    
    /**
     * Update a client to the database
     * @param id of the Client to edit
     * @param name of the Client to edit
     * @param ago of the Client to edit
     * @param phone of the Client to edit
     * @param city of the Client to edit
     * @return boolean where if true, the edit was successful, if false, an error occurred
     */
    public static boolean Update(int id, String name, int ago, String phone, String city){
        boolean result = false;
        try {
            Connection connection = MySQLConnection.get();
            String query ="UPDATE clients SET name=?, ago=?, phone=?, city=? WHERE id=?";
            PreparedStatement statament = connection.prepareStatement(query);
            statament.setString(1, name);
            statament.setInt(2, ago);
            statament.setString(3, phone);
            statament.setString(4, city);
            statament.setInt(5, id);
            statament.execute();
            
            result = statament.getUpdateCount() == 1;
        } catch (SQLException ex) {
            System.err.print("Error: "+ex.getMessage());
        }

        return result;
    }
    
    /**
     * Delete a client to the database
     * @param id of the client to delete
     * @return  boolean where if true the client was deleted and if false an error occurred
     */
    public static boolean delete(int id){
        boolean result = false;
        
        try {
            Connection connection = MySQLConnection.get();
            String query = "DELETE FROM clients WHERE id=?";
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
