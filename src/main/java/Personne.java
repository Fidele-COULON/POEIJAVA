
public class Personne {
 private String nom ="";
 private String prenom ="";
public Personne(String nom, String prenom) {
	super();
	this.nom = nom;
	this.prenom = prenom;
}
/**
 * @return the nom
 */
public String getNom() {
	return nom;
}
/**
 * @param nom the nom to set
 */
public void setNom(String nom) {
	this.nom = nom;
}
/**
 * @return the prenom
 */
public String getPrenom() {
	return prenom;
}
/**
 * @param prenom the prenom to set
 */
public void setPrenom(String prenom) {
	this.prenom = prenom;
}
/* (non-Javadoc)
 * @see java.lang.Object#hashCode()
 */
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((nom == null) ? 0 : nom.hashCode());
	result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
	return result;
}
/* (non-Javadoc)
 * @see java.lang.Object#equals(java.lang.Object)
 */
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Personne other = (Personne) obj;
	if (nom == null) {
		if (other.nom != null)
			return false;
	} else if (!nom.equals(other.nom))
		return false;
	if (prenom == null) {
		if (other.prenom != null)
			return false;
	} else if (!prenom.equals(other.prenom))
		return false;
	return true;
}
 
 
}
