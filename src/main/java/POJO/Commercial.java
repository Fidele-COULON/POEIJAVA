package POJO;
/**
 * <b>Classe definissant un commercial</b><br>
 * definit l'attribut matricule (en plus de ceux de Personne)
 * @author Fidele et Jeremy 
 * @version 1.0
 **/
public class Commercial extends Personne {

    private String matricule;

    public Commercial(int id, String nom, String prenom, String matricule) {
        super(id, nom, prenom);
        this.matricule = matricule;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    @Override
    public String toString() {
        return super.toString() + " matricule=" + matricule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Commercial that = (Commercial) o;

        return matricule != null ? matricule.equals(that.matricule) : that.matricule == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (matricule != null ? matricule.hashCode() : 0);
        return result;
    }
}
