package business;

import helper.PrologFormattingHelper;
import org.jpl7.Query;
import org.jpl7.Term;
import org.jpl7.Variable;

import java.util.Map;

public class Cosinus implements Command {
	public Double execute(double a, double b) {
		Variable z = new Variable("Z");
		Term term = Term.textToTerm("cosinus(" + a + "," + z + ")");
		Query cosinus = new Query(term);
		Map<String, Term> solutionMap = cosinus.allSolutions()[0];
		cosinus.close();
		return PrologFormattingHelper.extractDouble(solutionMap.toString());
	}

	@Override
	public String toString() {
		return "cos";
	}
}
