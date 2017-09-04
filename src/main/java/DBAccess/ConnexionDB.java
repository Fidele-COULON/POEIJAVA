package DBAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * <b>Classe implementant un singleton d'acces a la base de donnees MySQL</b><br>
 * Appeler getInstance pour recuperer le singleton<br>
 * Appeler kill en fin de programme<br>
 * @author Fidele et Jeremy 
 * @version 2.0
 **/
public class ConnexionDB {
    private static Connection myConnexion = null;

    private ConnexionDB() throws SQLException {
    	
            String url = "jdbc:mysql://localhost:3306/gestionlocationvehicules";
            String user = "glv";
            String password = "Password";

            myConnexion = DriverManager.getConnection(url, user, password);

            System.out.println("Connexion effectuée à la base de données " + url); 
    }

    public static Connection getInstance() throws SQLException{
        if (myConnexion == null){
            System.out.println("Création de la connexion");
            new ConnexionDB();
        } else {
            System.out.println("Utilisation de la connexion");
        }
     return myConnexion;
    }

    public static void kill(){
       try {
          if (myConnexion!= null ) myConnexion.close();
       } catch (SQLException se){
           System.out.println("Erreur SQL de déconnexion à la base :  " + se.getMessage());
       }
    }
}
