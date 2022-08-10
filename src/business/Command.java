package business;

import org.jpl7.Atom;
import org.jpl7.JPL;
import org.jpl7.Query;
import org.jpl7.Term;

import java.util.Map;

public interface Command {
	//Map<String, Term> execute(double a, double b);
	
	 Double execute(double a, double b);
}
