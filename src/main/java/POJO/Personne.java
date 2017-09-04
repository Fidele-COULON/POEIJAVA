package POJO;
/**
 * <b>Classe mere des objets de type clients et commerciaux</b><br>
 * definit les attributs nom et prenom
 * @author Fidele et Jeremy 
 * @version 1.0
 **/
public abstract class Personne extends ClasseAvecId {
    private String nom;
    private String prenom;
    
    /**
     * Le constructeur utilise <b> nom, prenom et un id unique</b><br>
     * @see Personne#setNom(String)
     * @see Personne#getNom()
     */
    public Personne( int id, String nom, String prenom) {
        super(id);
        this.nom = nom;
        this.prenom = prenom;
    }
    
    /**
     * Getter du nom<br>
     * @return Un string du nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setter du nom<br>
     * @param nom est un nom de personne
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public String toString() {
        return super.toString()+  " nom=" + nom + " prenom=" + prenom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Personne personne = (Personne) o;

        if (nom != null ? !nom.equals(personne.nom) : personne.nom != null) return false;
        return prenom != null ? prenom.equals(personne.prenom) : personne.prenom == null;
    }

    @Override
    public int hashCode() {
        int result = nom != null ? nom.hashCode() : 0;
        result = 31 * result + (prenom != null ? prenom.hashCode() : 0);
        return result;
    }
}
