package main.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * <b>Classe abstraite generique implementant les objets DAO</b><br>
 * Est une methode abstraite plutot qu'une interface pour imposer un constructeur<br>
 * Le constructeur propage une <b>exception SQL</b> si la connexion � la table ne peut se faire.<br>
 * Impose la redefinition des methodes creer, modifier et find.<br>
 * Implemente la methode supprimer pour un objet dans la table.<br>
 * find() sans parametre renvoie la liste de tous les objets<br>
 * find(int id) ne renvoie que l'objet dont l'id est id ou null si non trouve
 * @author Fidele et Jeremy 
 * @version 1.0
 **/
public abstract class DAOMother<T> {
	
    protected Connection cnx= null;
    protected String table= "";
    protected final static String QUERY_FIND_HEADER= "SELECT * FROM ";

    private String QUERY_DELETE = "";
    
    /**
     * <b>Constructeur</b><br>
     * @param cnx Connection a la base de donnees
     * @param table Table utilisee dans la base de donnees
     * @throws <b>SQLException</b> si un probleme de cnx � la table existe (d�clench� par un select * from table)
     */
    public DAOMother(Connection cnx, String table) throws SQLException {
        this.cnx = cnx;
        this.table = table;
        
        QUERY_DELETE = "DELETE FROM "+ table + " WHERE id =?"; // Initialisation de la pseudo-constante avec la table
        
        // Test de connexion � la BD
        PreparedStatement pstate = cnx.prepareStatement(QUERY_FIND_HEADER + table);
        pstate.executeQuery();        
    }
    
    public abstract boolean creer(T p);
    public abstract boolean modifier(int id, T p);
    public abstract List<T> find();
    public abstract T find(int id);
    
/**
 * 
 * @param id L'id de l'objet a supprimer
 * @return vrai si la suppression est effectuee
 */
    public final boolean supprimer(int id) {
        try {
            PreparedStatement pstate = cnx.prepareStatement(QUERY_DELETE );
            pstate.setInt(1, id);
            pstate.executeUpdate();
            pstate.close();
            return true;

        } catch (SQLException se) {
            System.out.println("Erreur SQL :" + se.getMessage());
            return false;
        }
    }
}
