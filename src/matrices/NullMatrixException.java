/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices;

/**
 *This class is used to throw an exception when an attempt is made to create a 
 * matrix with 0 or fewer rows and/or columns. 
 * @author Maxwell Burner
 */
public class NullMatrixException extends Exception {
    /**No-arg default constructor.*/
    public NullMatrixException(){
        super("Error: Attempt to make matrix with dimension of length 0 or less.");
    }
    
    /**Constructor for specifying attempted dimensions.
     @param rows The number of rows the matrix was supposed to have.
     @param columns The number of columns the matrix was supposed to have.*/
    public NullMatrixException(int rows, int columns){
        super("Error: Attempt to make matrix with "+rows+" rows a row of length "+columns);
    }
}
