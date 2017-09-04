package DAO;

import POJO.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * <b>Classe definissant un DAO vers la table clients</b><br>
 * @author Fidele et Jeremy 
 * @version 1.0
 **/
public class DbClientDAO extends DAOMother <Client>{
    private final String QUERY_UPDATE_CLIENT= "UPDATE Clients SET nom=?, prenom = ?, permis = ?, age=? WHERE id=?";
    private final String QUERY_INSERT_CLIENT= "INSERT into Clients (nom, prenom, permis, age) VALUES (?,?,?,?)"; ;
     
    public DbClientDAO(Connection cnx, String table) throws SQLException {
        super(cnx, table);
    }

    public boolean creer(Client p) {

        try {
            PreparedStatement pstate = cnx.prepareStatement(QUERY_INSERT_CLIENT);
            pstate.setString(1, p.getNom());
            pstate.setString(2, p.getPrenom());
            pstate.setString(3, p.getPermis());
            pstate.setInt(4,    p.getAge());

            pstate.executeUpdate();

            return true;

        }catch (SQLException se){
            System.out.println("Erreur SQL: " + se.getMessage());
            return false;
        }
    }

    /**
     *
     * @param id identifiant du client dans la base de donnees SQL
     * @param p objet Personne contennant les nouvelles informations.
     * @return Vrai, si la modification a fonctionne. Sinon, FAUX
     */
    public boolean modifier(int id, Client p) {

        try {
            PreparedStatement pstate =cnx.prepareStatement(QUERY_UPDATE_CLIENT);

            pstate.setString(1, p.getNom());
            pstate.setString(2, p.getPrenom());
            pstate.setString(3, p.getPermis());
            pstate.setInt(4,    p.getAge());
            pstate.setInt(5, id);

            pstate.executeUpdate();

            return true;

        } catch (SQLException se){
            System.out.println("Erreur SQL: " + se.getMessage());
            return false;
        }
    }

    public List<Client> find(){

        List<Client> clients= new ArrayList<Client>();

        try {

            PreparedStatement pstate = cnx.prepareStatement(QUERY_FIND_HEADER + table);
            ResultSet rs = pstate.executeQuery();

            while (rs.next()){
                clients.add(new Client(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"),  rs.getString("permis"),  rs.getInt("age")));
            }
            rs.close();
            pstate.close();

        } catch (SQLException se){
            System.out.println("Erreur SQL: " + se.getMessage());
        } catch (Exception se){
            System.out.println("Erreur non SQL : " + se.getMessage());
        }
        return clients;
    }

    public Client find(int id){
        try {

            PreparedStatement pstate = cnx.prepareStatement(QUERY_FIND_HEADER + table + " where id=?");

            pstate.setInt(1, id);
            ResultSet rs = pstate.executeQuery();

            while (rs.next()){
                return (new Client(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"),  rs.getString("permis"),  rs.getInt("age")));
            }
            rs.close();
            pstate.close();

        } catch (SQLException se){
            System.out.println("Erreur SQL: " + se.getMessage());
        } catch (Exception se){
            System.out.println("Erreur non SQL : " + se.getMessage());
        }
        return null;
    }
}
