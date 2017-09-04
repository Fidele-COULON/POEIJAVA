package main.POJO;

/**
 * <b>Classe implementant une location de vehicules</b><br>
 * possede 3 attributs: id_vehicule, id_commercial et id_client
 * @author Fidele et Jeremy 
 * @version 1.0
 **/
public class Location extends ClasseAvecId {

    private int id_vehicule;
    private int id_client;
    private int id_commercial;
    private String dateLocation ;

    public String getDateLocation() {
		return dateLocation;
	}

	public void setDateLocation(String dateLocation) {
		this.dateLocation = dateLocation;
	}

    public Location(int id, int id_vehicule, int id_client, int id_commercial, String dateLocation) {
		super(id);
		this.id_vehicule = id_vehicule;
		this.id_client = id_client;
		this.id_commercial = id_commercial;
		this.dateLocation = dateLocation;
	}

	public int getId_vehicule() {
        return id_vehicule;
    }

    public void setId_vehicule(int id_vehicule) {
        this.id_vehicule = id_vehicule;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getId_commercial() {
        return id_commercial;
    }

    public void setId_commercial(int id_commercial) {
        this.id_commercial = id_commercial;
    }

    @Override
    public String toString() {
        return super.getId() + " id_vehicule=" + id_vehicule +" id_client=" + id_client + " id_commercial=" + id_commercial + "date location=" + this.dateLocation;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dateLocation == null) ? 0 : dateLocation.hashCode());
		result = prime * result + id_client;
		result = prime * result + id_commercial;
		result = prime * result + id_vehicule;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (dateLocation == null) {
			if (other.dateLocation != null)
				return false;
		} else if (!dateLocation.equals(other.dateLocation))
			return false;
		if (id_client != other.id_client)
			return false;
		if (id_commercial != other.id_commercial)
			return false;
		if (id_vehicule != other.id_vehicule)
			return false;
		return true;
	}
    
}
