package business;

import org.jpl7.Query;
import org.jpl7.Term;
import org.jpl7.Variable;

import java.util.Map;

public class Multiplication extends Command {
    public Map<String, Term> execute(double a, double b) {
        Variable z = new Variable("Z");
        Term term = Term.textToTerm("multiplication(" + a + "," + b + "," + z + ")");
        Query multiplication = new Query(term);
        Map<String, Term> sol = multiplication.allSolutions()[0];
        multiplication.close();
        return sol;
    }

    @Override
    public String toString() {
        return "*";
    }
}
