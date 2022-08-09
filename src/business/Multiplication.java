package business;

import helper.PrologFormattingHelper;
import org.jpl7.Query;
import org.jpl7.Term;
import org.jpl7.Variable;

import java.util.Map;

public class Multiplication implements Command {
	public Double execute(double a, double b) {
		Variable z = new Variable("Z");
		Term term = Term.textToTerm("multiplication(" + a + "," + b + "," + z + ")");
		Query multiplication = new Query(term);
		Map<String, Term> sol = multiplication.allSolutions()[0];
		multiplication.close();
		return PrologFormattingHelper.extractDouble(sol.toString());
	}

	@Override
	public String toString() {
		return "*";
	}
}
