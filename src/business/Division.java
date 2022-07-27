package business;

import org.jpl7.Query;
import org.jpl7.Term;
import org.jpl7.Variable;

import java.util.Map;

public class Division extends Command {
    public Map<String, Term> execute(double a, double b) {
        Variable z = new Variable("Z");
        Term term = Term.textToTerm("division(" + a + "," + b + "," + z + ")");
        Query division = new Query(term);
        Map<String, Term> sol = division.allSolutions()[0];
        division.close();
        return sol;
    }

    @Override
    public String toString() {
        return "/";
    }
}
