/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices;

/**
 * Exception throws when dimensions of arrays or matrices do not match. 
 * @author Maxwell Burner
 */
public class DimensionMismatch extends Exception {
    /**No-arg constructor*/
    public DimensionMismatch(){
        super("Error: Mismatched dimensions.");
    }
    
    /**Constructor with two integers. Used when 2D array used to populate matrix 
     * has different dimensions than matrix.
     * @param matrixRows Number of rows in matrix.
     * @param matrixCols number of columns of matrix.
     * @param arrayRows Length of first dimension of array.
     * @param arrayCols Length of second dimension of array.
     */
    public DimensionMismatch(int matrixRows, int matrixCols,
            int arrayRows, int arrayCols){
        super("Error: Attempt to build "+matrixRows+" by "+matrixCols+
                " matrix using "+arrayRows+" by "+arrayCols+" array.");
    }
    
    /**COnstructor with string. 
     @param message Message printed when error is invoked. */
    public DimensionMismatch(String message){
        super(message);
    }
    
    /***/
}
