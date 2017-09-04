package main.DAO;

import main.POJO.Voiturette;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * <b>Classe definissant un DAO vers la table Voiturettes</b><br>
 * @author Fidele et Jeremy 
 * @version 1.0
 **/
public class DbVoituretteDAO extends DAOMother<Voiturette> {

    private final String QUERY_UPDATE_VOITURETTES= "UPDATE voiturettes SET couleur=?, modele = ?, immatricualtion = ? WHERE id=?";
    private final String QUERY_INSERT_VOITURETTES= "INSERT into voiturettes (couleur, modele, immatriculation) VALUES (?,?,?)";

    public DbVoituretteDAO(Connection cnx, String table) throws SQLException {
        super(cnx, table);
    }

    @Override
    public boolean creer(Voiturette v) {

        try {
            PreparedStatement pstate = cnx.prepareStatement(QUERY_INSERT_VOITURETTES);
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
    public boolean modifier(int id, Voiturette v) {

        try {
            PreparedStatement pstate = cnx.prepareStatement(QUERY_UPDATE_VOITURETTES);

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
    public List<Voiturette> find() {

        List<Voiturette> vehicules= new ArrayList<>();

        try {

            PreparedStatement pstate = cnx.prepareStatement(QUERY_FIND_HEADER + table);
            ResultSet rs = pstate.executeQuery();

            while (rs.next()){
                vehicules.add(new Voiturette(rs.getInt("id"),rs.getString("couleur"), rs.getString("modele"), rs.getString("immatriculation")));
            }
            rs.close();
            pstate.close();

        } catch (SQLException se){
            System.out.println("Erreur SQL: " + se.getMessage());
        }
        return vehicules;
    }

    public Voiturette find(int id){
        try {

            PreparedStatement pstate = cnx.prepareStatement(QUERY_FIND_HEADER + table + " where id=?");

            pstate.setInt(1, id);
            ResultSet rs = pstate.executeQuery();

            while (rs.next()){
                return (new Voiturette(rs.getInt("id"),rs.getString("couleur"), rs.getString("modele"), rs.getString("immatriculation")));
            }
            rs.close();
            pstate.close();

        } catch (SQLException se){
            System.out.println("Erreur SQL: " + se.getMessage());
        }
        return null;
    }
}
