package DAO;

import java.sql.Connection;
import java.sql.SQLException;

import DBAccess.ConnexionDB;

import POJO.*;
/**
 * <b>Classe fournissant des objets DAO (fabrique de DAO)</b><br>
 * Ne contient que des methodes statiques<br>
 * Il faut impérativement appeller <b>initFactory</b> avant d'utiliser la Fabrique<br>
 * Il faut impérativement appeller <b>closeFactory</b> pour fermer proprement la Fabrique<br>
 * La fabrique propage les <b>exceptions SQL</b> lors de la création des objets<br>
 * @author Fidele et Jeremy 
 * @version 2.0
 **/
public class DAOMySqlFactory {
    private static  Connection connexion = null;
   
	public static void initFactory() throws SQLException { // Pseudo-constructeur
		connexion = ConnexionDB.getInstance();	
    }
    
	public static void closeFactory(){ // Pseudo Finalize
		ConnexionDB.kill();	
    }
	
    public static DAOMother<Client> getDAOClient() throws SQLException {return new DbClientDAO(connexion, "clients");};
    
    public static DAOMother<Commercial> getDAOCommercial() throws SQLException {
    	return new DbCommercialDAO(connexion, "commerciaux");
    }
    
    public static DAOMother<Voiture> getDAOVoiture() throws SQLException {
    	return new DbVoitureDAO(connexion, "voitures");
    }
    
    public static DAOMother<Voiturette> getDAOVoiturette() throws SQLException {
    	return new DbVoituretteDAO(connexion, "voiturettes");
    }
    
    public static DAOMother<Utilitaire> getDAOUtilitaire() throws SQLException {
    	return new DbUtilitaireDAO(connexion, "utilitaires");
    }
    
    public static DAOMother<Location> getDAOLocationVoiture() throws SQLException {
    	return new DbLocationDAO (connexion, "locationvoiture");
    }
    
    public static DAOMother<Location> getDAOLocationVoiturette() throws SQLException {
    	return new DbLocationDAO (connexion, "voiturettes");
    }
    
    public static DAOMother<Location> getDAOLocationUtilitaire() throws SQLException {
    	return new DbLocationDAO (connexion, "utilitaires");
    }
}
