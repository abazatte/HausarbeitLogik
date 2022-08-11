package math;

import helper.PrologFormattingHelper;
import org.jpl7.Query;
import org.jpl7.Term;
import org.jpl7.Variable;

import java.util.Map;

import javax.sql.rowset.spi.SyncResolver;

public class Matrix {
	
	float m00, m10, m20, m30;
	float m01, m11, m21, m31;
	float m02, m12, m22, m32;
	float m03, m13, m23, m33;
	
	public Matrix(float m00, float m10, float m20, float m30,
			float m01, float m11, float m21, float m31,
			float m02, float m12, float m22, float m32,
			float m03, float m13, float m23, float m33) {
		
		this.m00 = m00; this.m01 = m01; this.m02 = m02; this.m03 = m03;
		this.m10 = m10; this.m11 = m11; this.m12 = m12; this.m13 = m13;
		this.m20 = m20; this.m21 = m21; this.m22 = m22; this.m23 = m23;
		this.m30 = m30; this.m31 = m31; this.m32 = m32; this.m33 = m33;
		
	}
	
	public static String executeAdd(Matrix m1, Matrix m2){
		Variable z = new Variable("Z");
		Term term = Term.textToTerm("matrixAdd(" + m1.toPrologExecute() + "," + m2.toPrologExecute() + "," + z + ")");
		Query power = new Query(term);
		Map<String, Term> sol = power.allSolutions()[0];
		power.close();
		
		return PrologFormattingHelper.extractString(sol.toString());
	}
	
	public static String executeSub(Matrix m1, Matrix m2){
		Variable z = new Variable("Z");
		Term term = Term.textToTerm("matrixMinus(" + m1.toPrologExecute() + "," + m2.toPrologExecute() + "," + z + ")");
		Query power = new Query(term);
		Map<String, Term> sol = power.allSolutions()[0];
		power.close();
		
		return PrologFormattingHelper.extractString(sol.toString());
	}

	public String toPrologExecute(){
		String toProlog = "[[" + this.m00 +","+ this.m10 +","+ this.m20 +","+ this.m30 + "],["
				+ this.m01 +","+ this.m11 +","+ this.m21 +","+ this.m31 + "],["
				+ this.m02 +","+ this.m12 +","+ this.m22 +","+ this.m32 + "],["
				+ this.m03 +","+ this.m13 +","+ this.m23 +","+ this.m33 + "]]";
		return toProlog;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		
		s.append(m00).append("\t").append(m10).append("\t").append(m20).append("\t").append(m30).append("\n")
		.append(m01).append("\t").append(m11).append("\t").append(m21).append("\t").append(m31).append("\n")
		.append(m02).append("\t").append(m12).append("\t").append(m22).append("\t").append(m32).append("\n")
		.append(m03).append("\t").append(m13).append("\t").append(m23).append("\t").append(m33);
	
		return s.toString();	
	}

}
