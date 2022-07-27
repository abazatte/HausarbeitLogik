package business;

import org.jpl7.Query;
import org.jpl7.Term;
import org.jpl7.Variable;

import java.util.Map;

public class Factorial extends Command {
    public Map<String, Term> execute(double a, double b) {
        Variable z = new Variable("Z");
        Term term = Term.textToTerm("fak(" + a + "," + z + ")");
        Query fak = new Query(term);
        Map<String, Term> sol = fak.allSolutions()[0];
        fak.close();
        return sol;
    }
}
