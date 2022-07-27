package business;

import org.jpl7.Query;
import org.jpl7.Term;
import org.jpl7.Variable;

import java.util.HashMap;
import java.util.Map;

public class Plus extends Command {

    public Map<String, Term> execute(double a, double b) {
        Variable z = new Variable("Z");
        Term term = Term.textToTerm("add(" + a + "," + b + "," + z + ")");
        Query add = new Query(term);
        Map<String, Term> sol = add.allSolutions()[0];
        add.close();
        return sol;
    }

    @Override
    public String toString() {
        return "+";
    }
}
