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
import mx.itson.roblox.entities.Room;
import mx.itson.roblox.persistence.MySQLConnection;

/**
 *
 * @author alexi
 */
public class RoomModel {
    
    /**
     * Get the Room object with the id enter
     * @param id of the room to search
     * @return the requested room
     */
    public static Room getRoom(int id){
        Room room = new Room();
        try {
            Connection connection = MySQLConnection.get();
            PreparedStatement statement = connection.prepareStatement("Select * FROM rooms where id=?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                room.setId(resultSet.getInt(1));
                room.setRoomType(resultSet.getString(2));
                room.setCapacity(resultSet.getInt(3));
            }
        } catch (SQLException ex) {
            System.err.println("Error: "+ex.getMessage());
        }
        
        
        return room;
    }
    
    
    /**
     * Gets a list of all Room where their name matches the filter
     * @param filter with which matches will be searched in the database
     * @return returns a list of Room that match the entered filter
     */
    public static List<Room> getAll(String filter){
        List<Room> rooms = new ArrayList();
        try {
            Connection connection = MySQLConnection.get();
            PreparedStatement statement = connection.prepareStatement("Select * FROM rooms where room_type like ?");
            statement.setString(1, "%"+filter+"%");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Room r = new Room();
                r.setId(resultSet.getInt(1));
                r.setRoomType(resultSet.getString(2));
                r.setCapacity(resultSet.getInt(3));
                rooms.add(r);
                
            }
            
        } catch (SQLException ex) {
            System.err.println("Error: "+ex.getMessage());
        }
        return rooms;
    }
    
    /**
     * Add a new room to the database
     * @param roomType of the new room
     * @param capacity of the new room
     * @return boolean where if true, a new room has been successfully added to the database and if false, a failure has occurred
     */
    public static boolean save(String roomType, int capacity){
        boolean result = false;
        try {
            Connection connection = MySQLConnection.get();
            String query ="INSERT INTO rooms (room_type, capacity) VALUES (?, ?)";
            PreparedStatement statament = connection.prepareStatement(query);
            statament.setString(1, roomType);
            statament.setInt(2, capacity);
            statament.execute();            
            result = statament.getUpdateCount() == 1;
        } catch (SQLException ex) {
            System.err.print("Error: "+ex.getMessage());
        }

        return result;
    }
    
    /**
     * Update a room to the database
     * @param id of the room to edit
     * @param roomType of the room to edit
     * @param capacity of the room to edit
     * @return boolean where if true, the edit was successful, if false, an error occurred
     */
    public static boolean Update(int id, String roomType, int capacity){
        boolean result = false;
        try {
            Connection connection = MySQLConnection.get();
            String query ="UPDATE rooms SET room_type=?, capacity=? WHERE id=?";
            PreparedStatement statament = connection.prepareStatement(query);
            statament.setString(1, roomType);
            statament.setInt(2, capacity);
            statament.setInt(3, id);
            statament.execute();
            
            result = statament.getUpdateCount() == 1;
        } catch (SQLException ex) {
            System.err.print("Error: "+ex.getMessage());
        }

        return result;
    }
    
    /**
     * Delete a room to the database
     * @param id of the room to delete
     * @return boolean where if true the room was deleted and if false an error occurred
     */
    public static boolean delete(int id){
        boolean result = false;
        
        try {
            Connection connection = MySQLConnection.get();
            String query = "DELETE FROM rooms WHERE id=?";
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
