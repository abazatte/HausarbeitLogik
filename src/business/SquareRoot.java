package business;

import org.jpl7.Query;
import org.jpl7.Term;
import org.jpl7.Variable;

import java.util.Map;

public class SquareRoot extends Command {
    public Map<String, Term> execute (double a, double b) {
        Variable z = new Variable("Z");
        Term term = Term.textToTerm("squareRoot(" + a + "," + z + ")");
        Query squareRoot = new Query(term);
        Map<String, Term> sol = squareRoot.allSolutions()[0];
        squareRoot.close();
        return sol;
    }

    @Override
    public String toString() {
        return "√";
    }
}
