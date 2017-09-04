package main.POJO;
/**
 * <b>Classe abstraite Mere de tous les objets vehicule stockes dans la BDD</b><br>
 * possede les attributs couleur, modele et immatriculation<br>
 * les autres attributs ont ete volontairement ignores pour alleger le code
 * @author Fidele et Jeremy 
 * @version 1.0
 **/
public abstract class Vehicule extends ClasseAvecId {
    private String couleur;
    private String modele;
    private String immatriculation;

    public Vehicule(int id, String couleur, String modele, String immatriculation) {
        super(id);
        this.couleur = couleur;
        this.modele = modele;
        this.immatriculation = immatriculation;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    @Override
    public String toString() {
        return super.toString() + " couleur=" + couleur + " modele=" + modele + " immatriculation=" + immatriculation ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vehicule vehicule = (Vehicule) o;

        if (couleur != null ? !couleur.equals(vehicule.couleur) : vehicule.couleur != null) return false;
        if (modele != null ? !modele.equals(vehicule.modele) : vehicule.modele != null) return false;
        return immatriculation != null ? immatriculation.equals(vehicule.immatriculation) : vehicule.immatriculation == null;
    }

    @Override
    public int hashCode() {
        int result = couleur != null ? couleur.hashCode() : 0;
        result = 31 * result + (modele != null ? modele.hashCode() : 0);
        result = 31 * result + (immatriculation != null ? immatriculation.hashCode() : 0);
        return result;
    }
}
