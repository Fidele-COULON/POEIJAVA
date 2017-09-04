package main.POJO;
/**
 * <b>Classe abstraite Mere de tous les objets metiers stockes dans la BDD</b><br>
 * possede un seul attribut ID
 * @author Fidele et Jeremy 
 * @version 1.0
 **/
public abstract class ClasseAvecId {
    private int id =-1;

    public ClasseAvecId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "id=" + id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClasseAvecId that = (ClasseAvecId) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
