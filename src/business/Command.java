package business;

import org.jpl7.Atom;
import org.jpl7.JPL;
import org.jpl7.Query;
import org.jpl7.Term;

import java.util.Map;

public abstract class Command {
    public Command() {
        JPL.init();
        Query q1 = new Query("consult", new Term[]{new Atom("lib/calc.pl")});
        System.out.println(q1.hasSolution());
        q1.close();
    }
    public Map<String, Term> execute(double a, double b){return null;}
}
