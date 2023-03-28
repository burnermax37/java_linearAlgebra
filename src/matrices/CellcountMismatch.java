/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices;

/**
 * Exception to be thrown if an attempt an attempt is made to construct a matrix
 * with an array, but the number of values in the array does not match the number
 * of values in the matrix.
 * @author Maxwell Burner
 */
public class CellcountMismatch  extends Exception{
    /**No-Arg Constructor*/
    public  CellcountMismatch(){
        super("Error: Attempt to make matrix using arrary of wrong size.");
    }
    
    /**Constructor with two integer values.
     @param matrixCells number of cells in matrix being made.
     @param arrayCells number of values in array provided. */
    public CellcountMismatch(int matrixCells, int arrayCells){
        super("Error: Mismatch between matrix size ("+matrixCells+
                ") and provided values ("+arrayCells+"}.");
    }
}
