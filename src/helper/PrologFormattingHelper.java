package helper;

public class PrologFormattingHelper {
	/**
	 * <p>extract the Double result out of a prolog result Map</p>
	 * <p>example "{Z=0.5}" -> "0.5" </p>
	 * @param prologResult
	 * @return
	 */
	public static double extractDouble(String prologResult) {
		int start = prologResult.indexOf("=");
		int end = prologResult.indexOf("}");
		
		return Double.parseDouble(prologResult.substring(start + 1 , end));
	}
}
