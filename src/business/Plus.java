package business;

import helper.PrologFormattingHelper;
import org.jpl7.Query;
import org.jpl7.Term;
import org.jpl7.Variable;

import java.util.Map;

public class Plus implements Command {
	@Override
	public Double execute(double a, double b) {
		Variable z = new Variable("Z");
		Term term = Term.textToTerm("add(" + a + "," + b + "," + z + ")");
		Query add = new Query(term);
		Map<String, Term> sol = add.allSolutions()[0];
		add.close();
		
		return PrologFormattingHelper.extractDouble(sol.toString());
	}

	@Override
	public String toString() {
		return "+";
	}
}
