package POJO;
import ExceptionsMetiers.AgeException;
/**
 * <b>Classe definissant un client</b><br>
 * definit les attributs age et permis (en plus de ceux de Personne)
 * @author Fidele et Jeremy 
 * @version 1.0
 **/
public class Client extends Personne {
    private String permis;
    private int age;

    public Client(int id, String nom, String prenom, String permis, int age) throws AgeException {
        super(id, nom, prenom);
        this.permis = permis;
        this.setAge(age);
    }

    public String getPermis() {
        return permis;
    }

    public void setPermis(String permis) {
        this.permis = permis;
    }

    public int getAge() {
        return age;
    }
/**
 * <b>Setter de age</b><br>
 * @param age 
 * @throws AgeException levee si pas majeur
 */
    public void setAge(int age) throws AgeException {
    	if (age < 18) throw new AgeException ("Il faut etre majeur pour louer une véhicule");
        this.age = age;
    }

    @Override
    public String toString() {
        return super.toString()+ " permis=" + permis  + "  age=" + age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Client client = (Client) o;

        if (age != client.age) return false;
        return permis != null ? permis.equals(client.permis) : client.permis == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (permis != null ? permis.hashCode() : 0);
        result = 31 * result + age;
        return result;
    }
}
