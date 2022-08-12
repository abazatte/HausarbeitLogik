package business;

import helper.PrologFormattingHelper;
import org.jpl7.Query;
import org.jpl7.Term;
import org.jpl7.Variable;

import java.util.Map;

public class Power implements Command {
	@Override
	public Double execute(double a, double b) {
		Variable z = new Variable("Z");
		Term term = Term.textToTerm("power(" + a + "," + b + "," + z + ")");
		Query power = new Query(term);
		Map<String, Term> sol = power.allSolutions()[0];
		power.close();
		return PrologFormattingHelper.extractDouble(sol.toString());
	}

	@Override
	public String toString() {
		return "^";
	}
}
