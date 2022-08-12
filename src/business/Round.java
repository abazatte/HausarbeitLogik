package business;

import java.util.Map;

import org.jpl7.Query;
import org.jpl7.Term;
import org.jpl7.Variable;

import helper.PrologFormattingHelper;

public class Round implements Command {
	@Override
	public Double execute(double number, double roundBy) {
		Variable z = new Variable("Z");
		Term term = Term.textToTerm("roundNumber(" + number + "," + z + "," +  (int)roundBy + ")");
		Query round = new Query(term);
		Map<String, Term> solutionMap = round.allSolutions()[0];
		round.close();
		return PrologFormattingHelper.extractDouble(solutionMap.toString());
	}
}
