/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices;

/**
 * A class for Square Matrices, matrices whose number of rows and columns is the same. 
 *  * @author Maxwell Burner
 */
public class SquareDoubleMatrix extends DoubleMatrix {
    

    
    /**Constructor for one integer and a 1D array. 
     @param length The integer value that becomes number of rows and number of columns.\
     @param values The 1D array of doubles that provides the elements*/
    public SquareDoubleMatrix(int length, double[] values) throws NullMatrixException, CellcountMismatch, DimensionMismatch{
        super(length,length,values);
    }
    
    /**Constructor for one integer and a 2D array. 
     @param length The integer value that becomes number of rows and number of columns.\
     @param values The 2D array of doubles that provides the elements*/    
    public SquareDoubleMatrix(int length, double[][] values) throws NullMatrixException, DimensionMismatch{
        super(length, length, values);
    }
    
    
    
}
