package business;

import math.Matrix;

/**
 * 
 * @author Abdurrahman Azattemür
 *
 */
public interface MatrixCommand {
	
	public String executeAdd(Matrix m1, Matrix m2);

	public String executeSub(Matrix m1, Matrix m2);

	public String executeMult(Matrix m1, Matrix m2);

	public String executeTrans(Matrix m1);

}
