package business;

import java.util.Map;

import org.jpl7.Query;
import org.jpl7.Term;
import org.jpl7.Variable;

import helper.PrologFormattingHelper;
import math.Matrix;

public class MatrixMul implements MatrixCommand{

	@Override
	public String execute(Matrix m1, Matrix m2) {
		Variable z = new Variable("Z");
		Term term = Term.textToTerm("matrixMult(" + m1.toPrologExecute() + "," + m2.toPrologExecute() + "," + z + ")");
		Query matrixMult = new Query(term);
		Map<String, Term> sol = matrixMult.allSolutions()[0];
		matrixMult.close();
		
		return PrologFormattingHelper.extractString(sol.toString());
	}

}
