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
import mx.itson.roblox.entities.Ticket;

import mx.itson.roblox.persistence.MySQLConnection;

/**
 *
 * @author alexi
 */
public class TicketModel {
    
    /**
     * Get the Ticket object with the id enter
     * @param id of the ticket to search
     * @return the requested ticket
     */
    public static Ticket getTicket(int id){
        Ticket ticket = new Ticket();
        try {
            Connection connection = MySQLConnection.get();
            PreparedStatement statement = connection.prepareStatement("Select * FROM tickets where id=?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
            ticket.setId(resultSet.getInt(1));
            ticket.setClient(ClientModel.getClient(resultSet.getInt(2)));
            ticket.setShow(ShowModel.getShow(resultSet.getInt(3)));
            ticket.setSeat(resultSet.getString(4));
            ticket.setPrice(resultSet.getDouble(5));
            }
        } catch (SQLException ex) {
            System.err.println("Error: "+ex.getMessage());
        }
        
        
        return ticket;
    }
    
    /**
     * Gets a list of all Ticket where their name matches the filter
     * @param filter with which matches will be searched in the database
     * @return returns a list of Ticket that match the entered filter
     */
    public static List<Ticket> getAll(String filter){
        List<Ticket> tickets = new ArrayList();
        try {
            Connection connection = MySQLConnection.get();
            PreparedStatement statement = connection.prepareStatement("SELECT *, (SELECT (SELECT name FROM movies WHERE id=movie_id ) FROM shows WHERE id=show_id) FROM tickets JOIN clients ON tickets.client_id=clients.id WHERE clients.name LIKE ?");
            statement.setString(1, "%"+filter+"%");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Ticket t = new Ticket();
                t.setId(resultSet.getInt(1));
                t.setClient(ClientModel.getClient(resultSet.getInt(2)));
                t.setShow(ShowModel.getShow(resultSet.getInt(3)));
                t.setSeat(resultSet.getString(4));
                t.setPrice(resultSet.getDouble(5));
                tickets.add(t);
                
            }
            
        } catch (SQLException ex) {
            System.err.println("Error: "+ex.getMessage());
        }
        return tickets;
    }
    
    /**
     * Add a new ticket to the database
     * @param clientId of the new Ticket
     * @param showId of the new Ticket
     * @param seat of the new Ticket
     * @param price of the new Ticket
     * @return boolean where if true, a new ticket has been successfully added to the database and if false, a failure has occurred
     */
    public static boolean save(int clientId, int showId, String seat, double price){
        boolean result = false;
        try {
            Connection connection = MySQLConnection.get();
            String query ="INSERT INTO tickets (client_id, show_id, seat, price) VALUES (?, ?, ?, ?)";
            PreparedStatement statament = connection.prepareStatement(query);
            statament.setInt(1, clientId);
            statament.setInt(2, showId);
            statament.setString(3, seat);
            statament.setDouble(4, price);
            statament.execute();
            
            result = statament.getUpdateCount() == 1;
        } catch (SQLException ex) {
            System.err.print("Error: "+ex.getMessage());
        }

        return result;
    }
    
    /**
     * Update a ticket to the database
     * @param id of the Ticket edit
     * @param clientId of the Ticket edit
     * @param showId of the Ticket edit
     * @param seat of the Ticket edit
     * @param price of the Ticket edit
     * @return boolean where if true, the edit was successful, if false, an error occurred
     */
    public static boolean Update(int id, int clientId, int showId, String seat, double price){
        boolean result = false;
        try {
            Connection connection = MySQLConnection.get();
            String query ="UPDATE tickets SET client_id=?, show_id=?, seat=?, price=? WHERE id=?";
            PreparedStatement statament = connection.prepareStatement(query);
            statament.setInt(1, clientId);
            statament.setInt(2, showId);
            statament.setString(3, seat);
            statament.setDouble(4, price);
            statament.setInt(5, id);
            statament.execute();
            
            result = statament.getUpdateCount() == 1;
        } catch (SQLException ex) {
            System.err.print("Error: "+ex.getMessage());
        }

        return result;
    }
    
    /**
     * Delete a ticket to the database
     * @param id of the ticket to delete
     * @return  boolean where if true the ticket was deleted and if false an error occurred
     */
    public static boolean delete(int id){
        boolean result = false;
        
        try {
            Connection connection = MySQLConnection.get();
            String query = "DELETE FROM tickets WHERE id=?";
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
