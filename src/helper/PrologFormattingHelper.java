package helper;

public class PrologFormattingHelper {
	/**
	 * <p>extract the Double result out of a prolog result Map</p>
	 * <p>example "{Z=0.5}" -> "0.5" </p>
	 * @param prologResult
	 * @return
	 * @author Maximilian Jaesch
	 */
	public static double extractDouble(String prologResult) {
		int start = prologResult.indexOf("=");
		int end = prologResult.indexOf("}");
		
		return Double.parseDouble(prologResult.substring(start + 1 , end));
	}
	
	/**
	 * Author: Abdu
	 * @param prologResult
	 * @return
	 */
	public static String extractString(String prologResult) {
		int start = prologResult.indexOf("=");
		int end = prologResult.indexOf("}");
		
		return prologResult.substring(start + 1 , end);
	}
}
