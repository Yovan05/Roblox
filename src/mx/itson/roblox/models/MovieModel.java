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
import java.util.List;
import mx.itson.roblox.entities.Movie;
import mx.itson.roblox.persistence.MySQLConnection;

/**
 *
 * @author alexi
 */
public class MovieModel {
    
    /**
     * Get the Movie object with the id enter
     * @param id of the movie to search
     * @return the requested movie
     */
    public static Movie getMovie(int id){
        Movie movie = new Movie();
        try {
            Connection connection = MySQLConnection.get();
            PreparedStatement statement = connection.prepareStatement("Select * FROM movies where id=?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                movie.setId(resultSet.getInt(1));
                movie.setName(resultSet.getString(2));
                movie.setDescription(resultSet.getString(3));
                movie.setDuration(resultSet.getInt(4));
                movie.setClassification(resultSet.getString(5));
                movie.setActors(resultSet.getString(6));
                movie.setLanguage(resultSet.getString(7));
            }
        } catch (SQLException ex) {
            System.err.println("Error: "+ex.getMessage());
        }
        
        
        return movie;
    }
    
    /**
     * Gets a list of all Movie where their name matches the filter
     * @param filter with which matches will be searched in the database
     * @return returns a list of Movie that match the entered filter
     */
    public static List<Movie> getAll(String filter){
        List<Movie> movies = new ArrayList();
        try {
            Connection connection = MySQLConnection.get();
            PreparedStatement statement = connection.prepareStatement("Select * FROM movies where name like ?");
            statement.setString(1, "%"+filter+"%");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Movie m = new Movie();
                m.setId(resultSet.getInt(1));
                m.setName(resultSet.getString(2));
                m.setDescription(resultSet.getString(3));
                m.setDuration(resultSet.getInt(4));
                m.setClassification(resultSet.getString(5));
                m.setActors(resultSet.getString(6));
                m.setLanguage(resultSet.getString(7));
                movies.add(m);
                
            }
            
        } catch (SQLException ex) {
            System.err.println("Error: "+ex.getMessage());
        }
        return movies;
    }
    
    /**
     * Add a new movie to the database
     * @param name of the new Movie
     * @param description of the new Movie
     * @param duration of the new Movie
     * @param classification of the new Movie
     * @param actors of the new Movie
     * @param language of the new Movie
     * @return boolean where if true, a new movie has been successfully added to the database and if false, a failure has occurred
     */
    public static boolean save(String name, String description, int duration, String classification, String actors, String language){
        boolean result = false;
        try {
            Connection connection = MySQLConnection.get();
            String query ="INSERT INTO movies (name, description, duration, classification, actors, language) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statament = connection.prepareStatement(query);
            statament.setString(1, name);
            statament.setString(2, description);
            statament.setInt(3, duration);
            statament.setString(4, classification);
            statament.setString(5, actors);
            statament.setString(6, language);
            statament.execute();
            
            result = statament.getUpdateCount() == 1;
        } catch (SQLException ex) {
            System.err.print("Error: "+ex.getMessage());
        }

        return result;
    }
    
    /**
     * Update a movie to the database
     * @param id of the Movie to edit
     * @param name of the Movie to edit
     * @param description of the Movie to edit
     * @param duration of the Movie to edit
     * @param classification of the Movie to edit
     * @param actors of the Movie to edit
     * @param language of the Movie to edit
     * @return boolean where if true, the edit was successful, if false, an error occurred
     */
    public static boolean Update(int id, String name, String description, int duration, String classification, String actors, String language){
        boolean result = false;
        try {
            Connection connection = MySQLConnection.get();
            String query ="UPDATE movies SET name=?, description=?, duration=?, classification=?, actors=?, language=? WHERE id=?";
            PreparedStatement statament = connection.prepareStatement(query);
            statament.setString(1, name);
            statament.setString(2, description);
            statament.setInt(3, duration);
            statament.setString(4, classification);
            statament.setString(5, actors);
            statament.setString(6, language);
            statament.setInt(7, id);
            statament.execute();
            
            result = statament.getUpdateCount() == 1;
        } catch (SQLException ex) {
            System.err.print("Error: "+ex.getMessage());
        }

        return result;
    }
    
    /**
     * Delete a movie to the database
     * @param id of the movie to delete
     * @return  boolean where if true the movie was deleted and if false an error occurred
     */
    public static boolean delete(int id){
        boolean result = false;
        
        try {
            Connection connection = MySQLConnection.get();
            String query = "DELETE FROM movies WHERE id=?";
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
