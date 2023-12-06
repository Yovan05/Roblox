/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.roblox.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import mx.itson.roblox.entities.Show;

import mx.itson.roblox.persistence.MySQLConnection;

/**
 *
 * @author alexi
 */
public class ShowModel {
    
    /**
     * Get the Show object with the id enter
     * @param id of the show to search
     * @return the requested show
     */
    public static Show getShow(int id){
        Show show = new Show();
        try {
            Connection connection = MySQLConnection.get();
            PreparedStatement statement = connection.prepareStatement("Select * FROM shows where id=?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
            show.setId(resultSet.getInt(1));
            show.setEmployee(EmployeeModel.getEmployee(resultSet.getInt(2)));
            show.setMovie(MovieModel.getMovie(resultSet.getInt(3)));
            show.setRoom(RoomModel.getRoom(resultSet.getInt(4)));
            show.setSchedule(resultSet.getString(5));
            show.setDate(resultSet.getDate(6));
            
            }
        } catch (SQLException ex) {
            System.err.println("Error: "+ex.getMessage());
        }
        
        
        return show;
    }
    
    /**
     * Gets a list of all Show where their name matches the filter
     * @param filter with which matches will be searched in the database
     * @return returns a list of Show that match the entered filter
     */
    public static List<Show> getAll(String filter){
        List<Show> shows = new ArrayList();
        try {
            Connection connection = MySQLConnection.get();
            PreparedStatement statement = connection.prepareStatement("SELECT shows.id, employees.id, employees.`name`, (SELECT id FROM movies WHERE id =movie_id), (SELECT name FROM movies WHERE id =movie_id), (SELECT id FROM rooms WHERE id=room_id),  shows.`schedule`, shows.`date`  FROM shows JOIN employees ON shows.employee_id=employees.id WHERE employees.name LIKE ?");
            statement.setString(1, "%"+filter+"%");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Show s = new Show();
                s.setId(resultSet.getInt(1));
                s.setEmployee(EmployeeModel.getEmployee(resultSet.getInt(2)));
                s.setMovie(MovieModel.getMovie(resultSet.getInt(4)));
                s.setRoom(RoomModel.getRoom(resultSet.getInt(6)));
                s.setSchedule(resultSet.getString(7));
                s.setDate(resultSet.getDate(8));
                shows.add(s);
                
            }
            
        } catch (SQLException ex) {
            System.err.println("Error: "+ex.getMessage());
        }
        return shows;
    }
    
    /**
     * Add a new show to the database
     * @param employeeId of the new Show
     * @param movieId of the new Schow
     * @param roomId of the new Show
     * @param schedule of the new Show
     * @param date of the new show
     * @return boolean where if true, a new show has been successfully added to the database and if false, a failure has occurred
     */
    public static boolean save(int employeeId, int movieId, int roomId, String schedule, Date date){
        boolean result = false;
        try {
            Connection connection = MySQLConnection.get();
            String query ="INSERT INTO shows (employee_id, movie_id, room_id, schedule, date) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statament = connection.prepareStatement(query);
            statament.setInt(1, employeeId);
            statament.setInt(2, movieId);
            statament.setInt(3, roomId);
            statament.setString(4, schedule);
            statament.setDate(5, new java.sql.Date(date.getTime()));
            statament.execute();
            
            result = statament.getUpdateCount() == 1;
        } catch (SQLException ex) {
            System.err.print("Error: "+ex.getMessage());
        }

        return result;
    }
    
    public static boolean Update(int id, int employeeID, int movieId, int roomID, String schedule, Date date){
        boolean result = false;
        try {
            Connection connection = MySQLConnection.get();
            String query ="UPDATE shows SET employee_id=?, movie_id=?, room_id=?, schedule=?,date=? WHERE id=?";
            PreparedStatement statament = connection.prepareStatement(query);
            statament.setInt(1, employeeID);
            statament.setInt(2, movieId);
            statament.setInt(3, roomID);
            statament.setString(4, schedule);
            statament.setDate(5, new java.sql.Date(date.getTime()));
            statament.setInt(6, id);
            statament.execute();
            
            result = statament.getUpdateCount() == 1;
        } catch (SQLException ex) {
            System.err.print("Error: "+ex.getMessage());
        }

        return result;
    }
    
    /**
     * Delete a show to the database
     * @param id of the show to delete
     * @return  boolean where if true the show was deleted and if false an error occurred
     */
    public static boolean delete(int id){
        boolean result = false;
        
        try {
            Connection connection = MySQLConnection.get();
            String query = "DELETE FROM shows WHERE id=?";
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
