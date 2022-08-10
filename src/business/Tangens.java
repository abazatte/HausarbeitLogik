package business;

import java.util.Map;

import org.jpl7.Query;
import org.jpl7.Term;
import org.jpl7.Variable;

import helper.PrologFormattingHelper;

public class Tangens implements Command {

	public Double execute(double a, double b) {
		Variable z = new Variable("Z");
		Term term = Term.textToTerm("tan(" + a + "," + z + ")");
		Query tangens = new Query(term);
		Map<String, Term> sol = tangens.allSolutions()[0];
		tangens.close();
		return PrologFormattingHelper.extractDouble(sol.toString());
	}

	@Override
	public String toString() {
		return "tan";
	}
}
