package main.ExceptionsMetiers;
/**
 * <b>Exception metier declenchee quand l'age < 18 ans</b><br>
 * @author Fidele et Jeremy 
 * @version 1.0
 **/
public class AgeException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private String message;

	public AgeException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return this.message;
	}
	
	
}
