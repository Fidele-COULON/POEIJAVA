package DAO;

import POJO.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * <b>Classe definissant un DAO vers la table Utilitaires</b><br>
 * @author Fidele et Jeremy 
 * @version 1.0
 **/
public class DbUtilitaireDAO extends DAOMother<Utilitaire> {

    private final String QUERY_UPDATE_UTILITAIRE= "UPDATE utilitaires SET couleur=?, modele = ?, immatricualtion = ?, hauteur =?, largeur =? WHERE id=?";
    private final String QUERY_INSERT_UTILITAIRE= "INSERT into utilitaires (couleur, modele, immatriculation, hauteur, largeur) VALUES (?,?,?,?,?)";

    public DbUtilitaireDAO(Connection cnx, String table) throws SQLException {
        super(cnx, table);
    }

    @Override
    public boolean creer(Utilitaire v) {

        try {
            PreparedStatement pstate = cnx.prepareStatement(QUERY_INSERT_UTILITAIRE);
            pstate.setString(1, v.getCouleur());
            pstate.setString(2,v.getModele());
            pstate.setString(3, v.getImmatriculation());
            pstate.setInt(4, ((Utilitaire)v).getHauteur());
            pstate.setInt(5, ((Utilitaire)v).getLargeur());

            pstate.executeUpdate();
            pstate.close();

            return true;

        }catch (SQLException s){
            System.out.println("Erreur SQL: " + s.getMessage());
            return false;
        }
    }

    @Override
    public boolean modifier(int id, Utilitaire v) {

        try {
            PreparedStatement pstate = cnx.prepareStatement(QUERY_UPDATE_UTILITAIRE);

            pstate.setString(1, v.getCouleur());
            pstate.setString(2,v.getModele());
            pstate.setString(3, v.getImmatriculation());
            pstate.setInt(4, ((Utilitaire)v).getHauteur());
            pstate.setInt(5, ((Utilitaire)v).getLargeur());
            pstate.setInt(6, id);

            pstate.executeUpdate();
            pstate.close();

            return true;

        }catch (SQLException s){
            System.out.println("Erreur SQL: " + s.getMessage());
            return false;
        }
    }


    @Override
    public List<Utilitaire> find() {

        List<Utilitaire> vehicules= new ArrayList<>();

        try ( PreparedStatement pstate = cnx.prepareStatement(QUERY_FIND_HEADER + table);
              ResultSet rs = pstate.executeQuery();) {

            while (rs.next()){
                vehicules.add(new Utilitaire(rs.getInt("id"),rs.getString("couleur"), rs.getString("modele"), rs.getString("immatriculation"), rs.getInt("hauteur"), rs.getInt("largeur")));
            }

        } catch (SQLException s){
            System.out.println("Erreur SQL: " + s.getMessage());
        } catch (Exception se){
                System.out.println("Erreur non SQL : " + se.getMessage());
        }
        return vehicules;
    }

    public Utilitaire find(int id){

        try {

            PreparedStatement pstate = cnx.prepareStatement(QUERY_FIND_HEADER + table + " where id=?");

            pstate.setInt(1, id);
            ResultSet rs = pstate.executeQuery();

            while (rs.next()){
                return (new Utilitaire(rs.getInt("id"),rs.getString("couleur"), rs.getString("modele"), rs.getString("immatriculation"), rs.getInt("hauteur"), rs.getInt("largeur")));
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

