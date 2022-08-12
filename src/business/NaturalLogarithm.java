package business;

import java.util.Map;

import org.jpl7.Query;
import org.jpl7.Term;
import org.jpl7.Variable;

import helper.PrologFormattingHelper;

public class NaturalLogarithm implements Command {

	@Override
	public Double execute(double a, double b) {
		Variable z = new Variable("Z");
		Term term = Term.textToTerm("ln(" + a + "," + z + ")");
		Query ln = new Query(term);
		Map<String, Term> sol = ln.allSolutions()[0];
		ln.close();
		
		return PrologFormattingHelper.extractDouble(sol.toString());
	}
}
