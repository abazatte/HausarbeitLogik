package business;

import helper.PrologFormattingHelper;
import org.jpl7.Query;
import org.jpl7.Term;
import org.jpl7.Variable;

import java.util.Map;


public class Sinus implements Command {
	@Override
	public Double execute(double a, double b) {
		Variable z = new Variable("Z");
		Term term = Term.textToTerm("sinus(" + a + "," + z + ")");
		Query sinus = new Query(term);
		Map<String, Term> sol = sinus.allSolutions()[0];
		sinus.close();
		return PrologFormattingHelper.extractDouble(sol.toString());
	}

	@Override
	public String toString() {
		return "sin";
	}
}



/*
public class Sinus implements Command {
	public Map<String, Term> execute(double a, double b) {
		Variable z = new Variable("Z");
		Term term = Term.textToTerm("sinus(" + a + "," + z + ")");
		Query sinus = new Query(term);
		Map<String, Term> sol = sinus.allSolutions()[0];
		sinus.close();
		return sol;
	}

	@Override
	public String toString() {
		return "sin";
	}
	
}
*/
