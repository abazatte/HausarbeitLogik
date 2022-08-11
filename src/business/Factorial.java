package business;

import helper.PrologFormattingHelper;
import org.jpl7.Query;
import org.jpl7.Term;
import org.jpl7.Variable;

import java.util.Map;

public class Factorial implements Command {
	public Double execute(double a, double b) {
		Variable z = new Variable("Z");
		Term term = Term.textToTerm("fak(" + (int) a + "," + z + ")");
		Query fak = new Query(term);
		Map<String, Term> solutionMap = fak.allSolutions()[0];
		fak.close();
		return PrologFormattingHelper.extractDouble(solutionMap.toString());
	}
}
