package business;

import org.jpl7.Query;
import org.jpl7.Term;
import org.jpl7.Variable;

import java.util.Map;

public class Power extends Command {
    public Map<String, Term> execute(double a, double b) {
        Variable z = new Variable("Z");
        Term term = Term.textToTerm("power(" + a + "," + b + "," + z + ")");
        Query power = new Query(term);
        Map<String, Term> sol = power.allSolutions()[0];
        power.close();
        return sol;
    }

    @Override
    public String toString() {
        return "^";
    }
}
