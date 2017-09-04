package main.DAO;

import main.POJO.Voiture;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * <b>Classe definissant un DAO vers la table Voitures</b><br>
 * @author Fidele et Jeremy 
 * @version 1.0
 **/
public class DbVoitureDAO extends DAOMother<Voiture> {

    private final String QUERY_UPDATE_VOITURES= "UPDATE voitures SET couleur=?, modele = ?, immatricualtion = ? WHERE id=?";
    private final String QUERY_INSERT_VOITURES= "INSERT into voitures (couleur, modele, immatriculation) VALUES (?,?,?)";

    public DbVoitureDAO(Connection cnx, String table) throws SQLException {
        super(cnx, table);
    }

    @Override
    public boolean creer(Voiture v) {

        try {
            PreparedStatement pstate = cnx.prepareStatement(QUERY_INSERT_VOITURES);
            pstate.setString(1, v.getCouleur());
            pstate.setString(2,v.getModele());
            pstate.setString(3, v.getImmatriculation());

            pstate.executeUpdate();
            pstate.close();

            return true;

        } catch (SQLException se){
            System.out.println("Erreur SQL: " + se.getMessage());
            return false;
        }
    }

    @Override
    public boolean modifier(int id, Voiture v) {

        try {
            PreparedStatement pstate = cnx.prepareStatement(QUERY_UPDATE_VOITURES);

            pstate.setString(1, v.getCouleur());
            pstate.setString(2,v.getModele());
            pstate.setString(3, v.getImmatriculation());
            pstate.setInt(4, id);

            pstate.executeUpdate();
            pstate.close();

            return true;

        } catch (SQLException se){
            System.out.println("Erreur SQL: " + se.getMessage());
            return false;
        }
    }

    @Override
    public List<Voiture> find() {

        List<Voiture> vehicules= new ArrayList<>();

        try {

            PreparedStatement pstate = cnx.prepareStatement(QUERY_FIND_HEADER + table);
            ResultSet rs = pstate.executeQuery();

            while (rs.next()){
                vehicules.add(new Voiture(rs.getInt("id"),rs.getString("couleur"), rs.getString("modele"), rs.getString("immatriculation")));
            }

            rs.close();
            pstate.close();

        } catch (SQLException se){
            System.out.println("Erreur SQL: " + se.getMessage());
        }
        return vehicules;
    }

    public Voiture find(int id){
        try {

            PreparedStatement pstate = cnx.prepareStatement(QUERY_FIND_HEADER + table + " where id=?");

            pstate.setInt(1, id);
            ResultSet rs = pstate.executeQuery();

            while (rs.next()){
                return (new Voiture(rs.getInt("id"),rs.getString("couleur"), rs.getString("modele"), rs.getString("immatriculation")));
            }
            rs.close();
            pstate.close();

        } catch (SQLException se){
            System.out.println("Erreur SQL: " + se.getMessage());
        }
        return null;
    }
}
