package business;

import org.jpl7.Query;
import org.jpl7.Term;
import org.jpl7.Variable;

import java.util.Map;

public class Minus extends Command {
    public Map<String, Term> execute(double a, double b) {
        Variable z = new Variable("Z");
        Term term = Term.textToTerm("minus(" + a + "," + b + "," + z + ")");
        Query minus = new Query(term);
        Map<String, Term> sol = minus.allSolutions()[0];
        minus.close();
        return sol;
    }

    @Override
    public String toString() {
        return "-";
    }
}
