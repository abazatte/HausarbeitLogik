package exception;


/**
 * Benutzerdefinierte Exception
 * (Min Eingabewerte duerfen nicht groesser sein als die max Eingabewerte)
 *
 * @author Berkan Yildiz
 *
 */
public class MinMaxException extends Exception{

	public MinMaxException(String errorMessage) {
		super(errorMessage);
	}
	
	public MinMaxException() {
		System.err.println("VON Werte duerfen nicht groesser als BIS Werte sein");
	}
	
}
