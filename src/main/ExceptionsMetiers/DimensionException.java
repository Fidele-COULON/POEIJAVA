package main.ExceptionsMetiers;

public class DimensionException extends Exception{	
	private static final long serialVersionUID = 1L;
	private String message;
	
	public DimensionException(String message) {
		super();
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return this.message;
	}
}
