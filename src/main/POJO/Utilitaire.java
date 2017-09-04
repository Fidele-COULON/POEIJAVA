package main.POJO;

import main.ExceptionsMetiers.DimensionException;

/**
 * <b>Classe heritee de vehicules</b><br>
 * Ajoute les attributs hauteur et largeur (exprimees en cm et doivent �tre positives)
 * @author Fidele et Jeremy 
 * @version 1.0
 **/
public class Utilitaire extends Vehicule {
    private int hauteur;
    private int largeur;

    /**
     * <b>Constructeur</b><br>
     * @param id
     * @param couleur
     * @param modele
     * @param immatriculation
     * @param hauteur
     * @param largeur
     * @throws DimensionException si la largeur ou la hauteur sont negatives 
     */
    public Utilitaire(int id, String couleur, String modele, String immatriculation, int hauteur, int largeur) throws DimensionException {
        super(id, couleur, modele, immatriculation);
        this.setHauteur(hauteur);
        this.setLargeur(largeur);
    }

    public int getHauteur() {
        return hauteur;
    }
    /**
     * <b>Setter de hauteur</b><br>
     * @param hauteur 
     * @throws DimensionException levee si pas positive
     */
    public void setHauteur(int hauteur) throws DimensionException {
        if (hauteur <=0 ) throw new DimensionException("La hauteur doit �tre positive");
        this.hauteur = hauteur;
    }

    public int getLargeur() {
        return largeur;
    }
    /**
     * <b>Setter de largeur</b><br>
     * @param hauteur 
     * @throws DimensionException levee si pas positive
     */
    public void setLargeur(int largeur) throws DimensionException {
        if (largeur <=0 ) throw new DimensionException("La largeur doit �tre positive");
        this.largeur = largeur;
    }

    @Override
    public String toString() {
        return super.toString() + " hauteur=" + hauteur + " largeur=" + largeur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Utilitaire that = (Utilitaire) o;

        if (hauteur != that.hauteur) return false;
        return largeur == that.largeur;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + hauteur;
        result = 31 * result + largeur;
        return result;
    }
}
