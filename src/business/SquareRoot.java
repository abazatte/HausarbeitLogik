package business;

import helper.PrologFormattingHelper;
import org.jpl7.Query;
import org.jpl7.Term;
import org.jpl7.Variable;

import java.util.Map;

public class SquareRoot implements Command {
	public Double execute(double a, double b) {
		Variable z = new Variable("Z");
		Term term = Term.textToTerm("squareRoot(" + a + "," + z + ")");
		Query squareRoot = new Query(term);
		Map<String, Term> sol = squareRoot.allSolutions()[0];
		squareRoot.close();
		return PrologFormattingHelper.extractDouble(sol.toString());
	}

	@Override
	public String toString() {
		return "√";
	}
}
