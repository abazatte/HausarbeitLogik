package business;

import helper.PrologFormattingHelper;
import org.jpl7.Query;
import org.jpl7.Term;
import org.jpl7.Variable;

import java.util.Map;

public class Division implements Command {
	public Double execute(double a, double b) {
		Variable z = new Variable("Z");
		Term term = Term.textToTerm("division(" + a + "," + b + "," + z + ")");
		Query division = new Query(term);
		Map<String, Term> solutionMap = division.allSolutions()[0];
		division.close();
		return PrologFormattingHelper.extractDouble(solutionMap.toString());
	}

	@Override
	public String toString() {
		return "/";
	}
}
