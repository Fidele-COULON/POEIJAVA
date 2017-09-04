package main;

import main.DAO.*;
import main.POJO.*;

import java.util.Scanner;

import main.DAO.DAOMySqlFactory;

import java.util.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

/**
 * <b>Classe lancant l'application</b><br>
 * Saisie en mode ligne de commande tr�s "Old School"<br>
 * La gestion des clients a ete fortement securisee par les controles idoines<br>
 * La gestion des autres objets est peu controlee<br>
 * La gestion de locations de voitures est en cours de finalisation<br>
 * La gestion de locations de voiturettes et utilitaires n'est pas envisagee pour l'instant (les tables existent) <br>
 * @author Fidele et Jeremy 
 * @version 7.0
 **/

public class Launcher {
    /**
     * <b>Variables DAO liant les objets aux tables de MySQL via DAOMySqlFactory</b><br>
     */
    private static DAOMother<Client> linkDBclient = null;
    private static DAOMother<Commercial> linkDBcommercial = null;
    private static DAOMother<Voiture> linkDBvoiture = null;
    private static DAOMother<Voiturette> linkDBvoiturette = null;
    private static DAOMother<Utilitaire> linkDButilitaire = null;
    private static DAOMother<Location> linkDBlocationVoitures = null;
    
    static Scanner clavier=  new Scanner(System.in); // Pour les saisies clavier

    // !!!!!!!!!!!!!!!!!!!!!!!!!!! MAIN !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public static void main(String arg[]){
    	
    	try {
    		DAOMySqlFactory.initFactory();
    		
    		linkDBclient = DAOMySqlFactory.getDAOClient();
            linkDBcommercial = DAOMySqlFactory.getDAOCommercial();
            linkDBvoiture = DAOMySqlFactory.getDAOVoiture();
            linkDBvoiturette =DAOMySqlFactory.getDAOVoiturette();
            linkDButilitaire = DAOMySqlFactory.getDAOUtilitaire();
            linkDBlocationVoitures = DAOMySqlFactory.getDAOLocationVoiture();
    	
    		    	System.out.println("=============================================");
	        System.out.println("= Gestion de parc locatif de vehicules v7.1 =");
	        System.out.println("=============================================");
            int choixMenuPrincipal, choixMenuSecondaire = -1;
           
            do {
            	afficherMenuPrincipal();

                choixMenuPrincipal = saisirChoix();

                if (choixMenuPrincipal!= 99){ // 99 est la sortie par convention, donc pas de sous-menu
                    
                	afficherSousMenu(choixMenuPrincipal);
                    choixMenuSecondaire = saisirChoix();
                    
                    executerChoix(choixMenuPrincipal*10 + choixMenuSecondaire); 
                    
                }
            } while(choixMenuPrincipal !=99); // 99 est la sortie par convention
            
            DAOMySqlFactory.closeFactory(); // lib�re aussi la cnx � la BD
     
    	} catch (SQLException se) {
    		System.out.println("Erreur de connexion � la base de donn�es  : " + se.getMessage());  	
    	}
    	
        System.out.println("Le programme s'est arr�t� ...");
    }

    private static final String[] LISTEMENUPRINCIPAL  = {
    		"Gestion des clients (complet et s�curis�)",
    		"Gestion des commerciaux (complet)",
    		"Gestion des voitures (complet)",
    		"Gestion des voiturettes (vide)",
    		"Gestion des utilitaires (vide)",
    		"Gestion des locations de voitures (complet et s�curis�)"
    };
    		
    private static void afficherMenuPrincipal() {
    	for(int i=0; i < LISTEMENUPRINCIPAL.length; i++) {
    		System.out.println((i+1) +". "+ LISTEMENUPRINCIPAL[i]);
    	}
        System.out.println();
        System.out.println("99. Sortie du programme");
        System.out.println();
    }

    private static void afficherSousMenu(int choixMenuPrincipal){
    	System.out.println("********** " + LISTEMENUPRINCIPAL[choixMenuPrincipal-1]);
        System.out.println("1. Creer");
        System.out.println("2. Supprimer");
        System.out.println("3. Modifier");
        System.out.println("4. Tout afficher");
        System.out.println("5. Afficher un seul objet par ID");
    }

    /**
     *  <b>Methode de saisie d'un choix d'option (int)</b><br> 
     * @return forcement un entier
     */
    private static int saisirChoix(){
        int choix = -1;
        do {
            try {
                System.out.print("Saisissez votre choix:");
                choix = clavier.nextInt(); clavier.nextLine();
            } catch (Exception e) {
                System.out.println("Saisissez un chiffre valide");
            }
        } while(choix == -1);

        return choix;
    }

/**
 * <b>Methode principale realisant toutes les operations</b><br> 
 * @param choix
 */
     private static void executerChoix(int choix){

        switch (choix) {
            case 11: {
            	try{
	            	System.out.println("=================================");
	           	 	System.out.println("Cr�ation non s�curis� d'un client");
	           	 	System.out.println("=================================");
	           	
	                System.out.println("nom :");
	                String nom = clavier.nextLine();
	                System.out.println("prenom :");
	                String prenom = clavier.nextLine();
	                System.out.println("permis :");
	                String permis = clavier.nextLine();
	                System.out.println("age :");
	                int age = clavier.nextInt(); clavier.nextLine();
               
                	 linkDBclient.creer(new Client(-1,nom, prenom, permis, age));
                	 
                } catch (Exception se){
                     System.out.println("Erreur non SQL : " + se.getMessage());
                }	       
                break;
            }

            case 12: {
            	System.out.println("=================================");
           	 	System.out.println("Suppression s�curis�e d'un client");
           	 	System.out.println("=================================");   
          
           	 	int id = getClientIDValide("Quel id client voulez vous supprimer ?", true);
           	 	
           	 	if (id != -1) {
           	 		linkDBclient.supprimer(id);
           	 		afficherListeClients();
           	 		}
           	 	else {
           	 		System.out.println("Trop de tentatives !!!!");
           	 	}         
                break;
            }

            case 13: {
            	System.out.println("==================================");
           	 	System.out.println("Modification s�curis�e d'un client");
           	 	System.out.println("==================================");
           	
           	 	try{
           		 
	           	 	int id = getClientIDValide("Quel id client voulez vous modifier ?", true);
	
	           	 	if (id != -1) {
	           	 		
	           	 	 System.out.println("nom :");
	                 String nom = clavier.nextLine();
	                 System.out.println("prenom :");
	                 String prenom = clavier.nextLine();
	                 System.out.println("permis :");
	                 String permis = clavier.nextLine();
	                 System.out.println("age :");
	                 int age = clavier.nextInt(); clavier.nextLine();
	                
	                 linkDBclient.modifier(id, new Client(-1,nom, prenom, permis, age));           
	               
	           	 	}
	           	 	else {
	           	 		System.out.println("Trop de tentatives !!!!");
	           	 	}
           	 	
	             } catch (Exception se){
	                 System.out.println("Erreur non SQL : " + se.getMessage());
	             }	
           	 	break;
             
            }

            case 14: {afficherListeClients() ; break;}
      
            case 15: {
            	System.out.println("==============================");
            	System.out.println("Affichage s�curis� d'un client");
           	 	System.out.println("==============================");   
          
           	 	int id = getClientIDValide("Quel id client voulez vous afficher ?", false);
           	 	
           	 	if (id != -1) {
       	 			System.out.println(linkDBclient.find(id));
           	 		}
           	 	else {
           	 		System.out.println("Trop de tentatives !!!!");
           	 	}         
                break;
            }

            case 21:{
                System.out.println("nom :");
                String nom = clavier.nextLine();
                System.out.println("prenom :");
                String prenom = clavier.nextLine();
                System.out.println("matricule :");
                String matricule= clavier.nextLine();

                linkDBcommercial.creer(new Commercial(-1,nom, prenom, matricule));
                break;
            }

            case 22:{
                System.out.println("Quel id commercial voulez vous supprimer ?");
                int id = clavier.nextInt();clavier.nextLine();
                linkDBcommercial.supprimer(id);
                break;
            }

            case 23:{
                System.out.println("Quel id commercial voulez vous modifier?");
                int id = clavier.nextInt();clavier.nextLine();

                System.out.println("nom :");
                String nom = clavier.nextLine();
                System.out.println("prenom :");
                String prenom = clavier.nextLine();
                System.out.println("matricule :");
                String matricule = clavier.nextLine();

                linkDBcommercial.modifier(id, new Commercial(-1,nom, prenom, matricule));
                break;
            }

            case 24:{
                System.out.println(linkDBcommercial.find());
                break;
            }

            case 25: {
                System.out.println("Quel id commercial voulez vous afficher ?");
                int id = clavier.nextInt();clavier.nextLine();

                System.out.println(linkDBcommercial.find(id));
                break;
            }

            case 31:{
                System.out.println("couleur :");
                String couleur = clavier.nextLine();
                System.out.println("modele :");
                String modele = clavier.nextLine();
                System.out.println("immatriculation :");
                String immatriculation= clavier.nextLine();

                linkDBvoiture.creer(new Voiture(-1,couleur, modele, immatriculation));
                break;
            }

            case 32:{
                System.out.println("Quel id voiture voulez vous supprimer ?");
                int id = clavier.nextInt();clavier.nextLine();
                linkDBvoiture.supprimer(id);
                break;
            }

            case 33:{
                System.out.println("Quel id voiture voulez vous modifier ?");
                int id = clavier.nextInt();clavier.nextLine();

                System.out.println("couleur :");
                String couleur = clavier.nextLine();
                System.out.println("modele :");
                String modele = clavier.nextLine();
                System.out.println("immatriculation :");
                String immatriculation= clavier.nextLine();

                linkDBvoiture.modifier(id, new Voiture(-1,couleur, modele, immatriculation));
                break;
            }

            case 34:{
                System.out.println(linkDBvoiture.find());
                break;
            }

            case 35: {
                System.out.println("Quel id Voiture voulez vous afficher ?");
                int id = clavier.nextInt();clavier.nextLine();

                System.out.println(linkDBvoiture.find(id));
                break;
            }

            case 41:{
                System.out.println("couleur :");
                String couleur = clavier.nextLine();
                System.out.println("modele :");
                String modele = clavier.nextLine();
                System.out.println("immatriculation :");
                String immatriculation= clavier.nextLine();

                linkDBvoiturette.creer(new Voiturette(-1,couleur, modele, immatriculation));
                break;
            }

            case 42:{
                System.out.println("Quel id voiturette voulez vous supprimer ?");
                int id = clavier.nextInt();clavier.nextLine();
                linkDBvoiturette.supprimer(id);
                break;
            }

            case 43:{
                System.out.println("Quel id voiturette voulez vous modifier ?");
                int id = clavier.nextInt();clavier.nextLine();

                System.out.println("couleur :");
                String couleur = clavier.nextLine();
                System.out.println("modele :");
                String modele = clavier.nextLine();
                System.out.println("immatriculation :");
                String immatriculation= clavier.nextLine();

                linkDBvoiturette.modifier(id, new Voiturette(-1,couleur, modele, immatriculation));
                break;
            }

            case 44:{
                System.out.println(linkDBvoiturette.find());
                break;
            }

            case 45: {
                System.out.println("Quel id voiturette voulez vous afficher ?");
                int id = clavier.nextInt();clavier.nextLine();

                System.out.println(linkDBvoiturette.find(id));
                break;
            }

            case 51:{
                System.out.println("couleur :");
                String couleur = clavier.nextLine();
                System.out.println("modele :");
                String modele = clavier.nextLine();
                System.out.println("immatriculation :");
                String immatriculation= clavier.nextLine();
                System.out.println("hauteur :");
                int hauteur = clavier.nextInt(); clavier.nextLine();
                System.out.println("largeur :");
                int largeur = clavier.nextInt(); clavier.nextLine();

                try{
                	linkDButilitaire.creer(new Utilitaire(-1,couleur, modele, immatriculation, hauteur, largeur));
                } catch (Exception se){
                     System.out.println("Erreur non SQL : " + se.getMessage());
                }	         
                
                break;
            }

            case 52:{
                System.out.println("Quel id utilitaire voulez vous supprimer ?");
                int id = clavier.nextInt();clavier.nextLine();
                linkDButilitaire.supprimer(id);
                break;
            }

            case 53:{
                System.out.println("Quel id utilitaire voulez vous modifier ?");
                int id = clavier.nextInt();clavier.nextLine();

                System.out.println("couleur :");
                String couleur = clavier.nextLine();
                System.out.println("modele :");
                String modele = clavier.nextLine();
                System.out.println("immatriculation :");
                String immatriculation= clavier.nextLine();
                System.out.println("hauteur :");
                int hauteur = clavier.nextInt(); clavier.nextLine();
                System.out.println("largeur :");
                int largeur = clavier.nextInt(); clavier.nextLine();

                try{
                	linkDButilitaire.modifier(id, new Utilitaire(-1,couleur, modele, immatriculation, hauteur, largeur));
                } catch (Exception se){
                     System.out.println("Erreur non SQL : " + se.getMessage());
                }	
                
                break;
            }

            case 54:{
                System.out.println(linkDButilitaire.find());
                break;
            }

            case 55: {
                System.out.println("Quel id utilitaire voulez vous afficher ?");
                int id = clavier.nextInt();clavier.nextLine();

                System.out.println(linkDButilitaire.find(id));
                break;
            }

            case 61: {
            	System.out.println("===========================================");
            	System.out.println("Saisie s�curis�e d'une location de voitures");
            	System.out.println("===========================================");
            	
            	Voiture v= null;
            	int id_voiture = -1, id_client = -1, id_commercial = -1 ;
            	
            	do { // Saisie ID Voiture
            		try {
            		
	            		System.out.println("Quel id voiture voulez vous louer ?");
	                    System.out.println(linkDBvoiture.find());
	                    id_voiture = clavier.nextInt();clavier.nextLine();
	                    v = linkDBvoiture.find(id_voiture);
	                    if (v == null) System.out.println("La voiture " + id_voiture + " n'a pas �t� trouv�e !!!");
            		} catch (Exception e) {
            			clavier.nextLine();
            		}
            	} while (v== null);
            	
                Client c= null ;
                do { // Saisie ID Client
                	try {
	                	System.out.println("Quel id client avez vous ?");
	                	System.out.println(linkDBclient.find());
	                	id_client = clavier.nextInt();clavier.nextLine();
	                	c =  linkDBclient.find(id_client);
	                	if (c == null) System.out.println("Le client " + id_client + " n'a pas �t� trouv� !!!");
                	} catch (Exception e) {
                		clavier.nextLine();
            		}
                } while (c == null);
              
                Commercial co = null ;
                do { // Saisie ID Commercial
                	try {
                		System.out.println("Quel id Commercial vous g�re ?");
                		System.out.println(linkDBcommercial.find());
                        id_commercial = clavier.nextInt();clavier.nextLine();
                        co = linkDBcommercial.find(id_commercial);
                        if (co == null) System.out.println("Le commercial " + id_commercial + " n'a pas �t� trouv� !!!");
                	}catch (Exception e) {
                		clavier.nextLine();
            		}
                } while (co == null);    		
                
                Date d = null; String strDate = "";
                do{ // Saisie de la date de location
                    System.out.println("A quelle date a lieu la location ?");
                    strDate = clavier.nextLine();
                    SimpleDateFormat formatter= new SimpleDateFormat("dd/MM/yyyy");
                    try{
                        d= formatter.parse(strDate);
                    } catch(Exception e){
                        System.out.println("Veuillez rentrer une date valide." + e.getMessage());
                    }
                } while(d== null);                
               
                linkDBlocationVoitures.creer(new Location(-1, id_voiture, id_client, id_commercial, strDate));
                break;
            }

            case 62: {
            	System.out.println("====================================");
           	 	System.out.println("Suppression s�curis�e d'une location");
           	 	System.out.println("====================================");   
          
           	 	Location loc = getLocationVoituresIDValide("Quel id location voulez vous supprimer ?", true);
           	 	
           	 	if (loc != null) {
           	 		linkDBlocationVoitures.supprimer(loc.getId());
           	 		afficherListeLocationsVoitures();
           	 		}
           	 	else {
           	 		System.out.println("Trop de tentatives !!!!");
           	 	}         
                break;
            }

            case 63: {System.out.println("Une location ne peut pas �tre modifi�e."); break; }
            
            case 64: {afficherListeLocationsVoitures(); break; }
            
            case 65: {
            	System.out.println("============================================");
            	System.out.println("Affichage s�curis� d'une location de voiture");
           	 	System.out.println("============================================");   
          
           	 	Location loc = getLocationVoituresIDValide("Quel id de location voulez vous afficher ?", false);
           	 	
           	 	if (loc != null) {
           	 		afficherUneLocationVoiture(loc);
           		} else {
           	 		System.out.println("Trop de tentatives !!!!");
           	 	}         
                break;
            }
                        	
        }
     }
   
     private static void afficherListeClients(){
    	 System.out.println("=============================");
    	 System.out.println("Liste des clients enregistres");
    	 System.out.println("=============================");
    	 for (Client c:linkDBclient.find()) {
    		 System.out.println("Le client N�" + c.getId() + " s'appelle " + c.getPrenom() + " " + c.getNom()+ " et a le permis: " + c.getPermis() + " et est ag� de : " + c.getAge());
    	 }
     }
     
     private static void afficherUneLocationVoiture(Location l){
    	 Client c= linkDBclient.find(l.getId_client());
	     Commercial co= linkDBcommercial.find(l.getId_commercial());
	 	 Voiture v= linkDBvoiture.find(l.getId_vehicule());
	 	 System.out.println("Location n� " + l.getId() + " : " + c.getPrenom() + " " + c.getNom() + " a lou� une " + v.getModele() + " g�r� par " + co.getNom() + " en date de " + l.getDateLocation());
     }
     
     private static void afficherListeLocationsVoitures(){
    	 System.out.println("===============================");
    	 System.out.println("Liste des locations de voitures");
    	 System.out.println("===============================");
    	 for (Location l :linkDBlocationVoitures.find()) {
    		Client c= linkDBclient.find(l.getId_client());
    		if (c == null) {
    			System.out.println("Location n� " + l.getId() + " ---- Erreur : Le client " + l.getId_client() + " n'a pas �t� trouv� !!!");
    			continue;
    		}
    		
    		Commercial co= linkDBcommercial.find(l.getId_commercial());
		    if (co == null) {
		    	System.out.println("Location n� " + l.getId() + " ---- Erreur : Le commercial " + l.getId_commercial() + " n'a pas �t� trouv� !!!");
		    	continue;
		    }
		    
    		Voiture v= linkDBvoiture.find(l.getId_vehicule());
    		if (v == null) {
    			System.out.println("Location n� " + l.getId() + " ---- Erreur : La voiture " + l.getId_vehicule() + " n'a pas �t� trouv�e !!!");
    			continue;
    		}		
		    	
		    System.out.println("Location n� " + l.getId() + " : " + c.getPrenom() + " " + c.getNom() + " a lou� une " + v.getModele() + " g�r� par " + co.getNom()  + " en date de " + l.getDateLocation());
    	 }
     }
     /**
      * <b>Fonction de saisie d'un ID avec verification d'existence. Sort apres 5 tentatives</b>
      * @param message le message de la question pour demander un ID
      * @param avecAffichageListe booleen pour afficher ou non la liste de tous les objets
      * @return -1 si trop de tentatives, sinon un ID Valide
      */
     private static int getClientIDValide(String message, boolean avecAffichageListe)  {
    		Client c = null;
       	 	int id = - 1;
       	 	
       	 	int nbTentatives = 0 ;
       	 	do {
       	 		if (avecAffichageListe) afficherListeClients();
       	 		System.out.println(message); 
       	 		id = clavier.nextInt();clavier.nextLine();
                c = linkDBclient.find(id);
                if (c == null) {
                	System.out.println("Le client N�" + id + " n'a pas �t� trouv� !!!");
                	nbTentatives ++;
                }
       	 	} while (c== null && nbTentatives <= 4 );
       	 	
       	 	return id;
     }
     /**
      * 
      * @param message le message de la question pour demander un ID
      * @param avecAffichageListe  pour afficher ou non la liste de tous les objets
      * @return null si Location = null si trop de tentatives, sinon un objet Location valide
      */
     private static Location getLocationVoituresIDValide(String message, boolean avecAffichageListe)  {
 			Location loc = null;
    	 	int id = - 1;
    	 	
    	 	int nbTentatives = 0 ;
    	 	do {
    	 		if (avecAffichageListe) afficherListeLocationsVoitures();
    	 		System.out.println(message); 
    	 		id = clavier.nextInt();clavier.nextLine();
    	 		loc = linkDBlocationVoitures.find(id);
    	 		if (loc == null) {
    	 			System.out.println("La location de voitures N� " + id + " n'a pas �t� trouv�e !!!");
    	 			nbTentatives ++;
    	 		}
    	 		} while (loc == null && nbTentatives <= 4 );
    	 	
    	 	return loc;
  }
}

