package Exception;

public class MinMaxException extends Exception{

	public MinMaxException(String errorMessage) {
		super(errorMessage);
	}
	
	public MinMaxException() {
		System.err.println("VON Werte duerfen nicht groesser als BIS Werte");
	}
	
}
