package DAO;

import POJO.Location;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * <b>Classe definissant un DAO vers une table de locations (Voiture, Voiturette ou Utilitaire)</b><br>
 * Seul le constructeur et la methode find ont ete cables
 * @author Fidele et Jeremy 
 * @version 1.0
 **/
public class DbLocationDAO extends DAOMother<Location> {

    private String QUERY_INSERT_LOCATION= "" ; 

    public DbLocationDAO(Connection cnx, String table) throws SQLException {
        super(cnx, table);
        QUERY_INSERT_LOCATION = "INSERT into " + table + " (id_voiture, id_client, id_commercial, dateLocation) VALUES (?,?,?, ?)";       
    }

    @Override
    public boolean creer(Location l) {
        try {
            PreparedStatement pstate = cnx.prepareStatement(QUERY_INSERT_LOCATION);
            pstate.setInt(1, l.getId_vehicule());
            pstate.setInt(2,l.getId_client());
            pstate.setInt(3,  l.getId_commercial());
            pstate.setString(4,  l.getDateLocation());
            pstate.executeUpdate();

            return true;

        } catch (SQLException se){
            System.out.println("Erreur SQL: " + se.getMessage());
            return false;
        }
    }
    
    /**
     * <b>Methode non implementee par design</b>
     */
    @Override
    public boolean modifier(int id, Location l) {
        return false;
    }

    @Override
    public List<Location> find() {
        List<Location> listeLocations= new ArrayList<Location>();

        try {

            PreparedStatement pstate = cnx.prepareStatement(QUERY_FIND_HEADER + table);
            ResultSet rs = pstate.executeQuery();

            while (rs.next()){
                listeLocations.add(new Location(rs.getInt("id"), rs.getInt("id_voiture"), rs.getInt("id_client"),  rs.getInt("id_commercial"),rs.getString("dateLocation")));
            }
            rs.close();
            pstate.close();

        } catch (SQLException se){
            System.out.println("Erreur SQL: " + se.getMessage());
        }
        return listeLocations;
    }

    @Override
    public Location find(int id) {
    	 try {

             PreparedStatement pstate = cnx.prepareStatement(QUERY_FIND_HEADER + table + " WHERE id=?");
             pstate.setInt(1, id);
             ResultSet rs = pstate.executeQuery();

             while (rs.next()){
                 return (new Location(rs.getInt("id"),rs.getInt("id_voiture"), rs.getInt("id_client"), rs.getInt("id_commercial"),rs.getString("dateLocation")));
             }
             rs.close();
             pstate.close();

         }catch (SQLException s){
             System.out.println("Erreur SQL: " + s.getMessage());
         } catch (Exception se){
             System.out.println("Erreur non SQL : " + se.getMessage());
         }
    	 
         return null;
    }
}
