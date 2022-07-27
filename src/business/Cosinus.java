package business;

import org.jpl7.Query;
import org.jpl7.Term;
import org.jpl7.Variable;

import java.util.Map;
public class Cosinus extends Command {
    public Map<String, Term> execute(double a, double b) {
        Variable z = new Variable("Z");
        Term term = Term.textToTerm("cosinus(" + a + "," + z + ")");
        Query cosinus = new Query(term);
        Map<String, Term> sol = cosinus.allSolutions()[0];
        cosinus.close();
        return sol;
    }

    @Override
    public String toString() {
        return "cos";
    }
}
