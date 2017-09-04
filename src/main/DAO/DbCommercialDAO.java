package DAO;

import POJO.Commercial;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * <b>Classe definissant un DAO vers la table commerciaux</b><br>
 * @author Fidele et Jeremy 
 * @version 1.0
 **/
public class DbCommercialDAO extends DAOMother <Commercial> {
    private final String QUERY_UPDATE_COMMERCIAUX= "UPDATE Commerciaux SET nom=?, prenom = ?,matricule=? WHERE id=?";
    private final String QUERY_INSERT_COMMERCIAUX= "INSERT into Commerciaux (nom, prenom, matricule) VALUES (?,?,?)";

    public DbCommercialDAO(Connection cnx, String table) throws SQLException {
        super(cnx, table);
    }

    @Override
    public boolean creer(Commercial p) {

        try {
            PreparedStatement pstate = cnx.prepareStatement(QUERY_INSERT_COMMERCIAUX);
            pstate.setString(1, p.getNom());
            pstate.setString(2, p.getPrenom());
            pstate.setString(3, p.getMatricule());

            pstate.executeUpdate();

            return true;

        } catch (SQLException se){
            System.out.println("Erreur SQL: " + se.getMessage());
            return false;
        }
    }

    @Override
    public boolean modifier(int id, Commercial p) {

        try {
            PreparedStatement pstate = cnx.prepareStatement(QUERY_UPDATE_COMMERCIAUX);

            pstate.setString(1, p.getNom());
            pstate.setString(2, p.getPrenom());
            pstate.setString(3, p.getMatricule());
            pstate.setInt(4, id);

            pstate.executeUpdate();

            return true;

        } catch (SQLException se){
            System.out.println("Erreur SQL: " + se.getMessage());
            return false;
        }
    }

    @Override
    public List<Commercial> find() {

        List<Commercial> commerciaux= new ArrayList<>();

        try {

            PreparedStatement pstate = cnx.prepareStatement(QUERY_FIND_HEADER + table);
            ResultSet rs = pstate.executeQuery();

            while (rs.next()){
                commerciaux.add(new Commercial(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"),  rs.getString("matricule")));
            }
            rs.close();
            pstate.close();

        } catch (SQLException se){
            System.out.println("Erreur SQL: " + se.getMessage());
        }
        return commerciaux;
    }

    public Commercial find(int id){
        try {

            PreparedStatement pstate = cnx.prepareStatement(QUERY_FIND_HEADER + table + " where id=?");

            pstate.setInt(1, id);
            ResultSet rs = pstate.executeQuery();

            while (rs.next()){
                return (new Commercial(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"),  rs.getString("matricule")));
            }
            rs.close();
            pstate.close();

        } catch (SQLException se){
            System.out.println("Erreur SQL: " + se.getMessage());
        }
        return null;
    }
}

