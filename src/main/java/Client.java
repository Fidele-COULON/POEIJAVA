
public class Client extends Personne	{
	private int age = -1;
	private String permis ="";
	public Client(String nom, String prenom, int age, String permis) {
		super(nom, prenom);
		this.age = age;
		this.permis = permis;
	}
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	/**
	 * @return the permis
	 */
	public String getPermis() {
		return permis;
	}
	/**
	 * @param permis the permis to set
	 */
	public void setPermis(String permis) {
		this.permis = permis;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + age;
		result = prime * result + ((permis == null) ? 0 : permis.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (age != other.age)
			return false;
		if (permis == null) {
			if (other.permis != null)
				return false;
		} else if (!permis.equals(other.permis))
			return false;
		return true;
	}

}
