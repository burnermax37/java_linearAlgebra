/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linearalgebra;

import java.util.logging.Level;
import java.util.logging.Logger;
import matrices.*;
import matrices.NullMatrixException;

/**
 *
 * @author Maxwell Burner
 */
public class LinearAlgebra {

    /**
     * The main function, used for testing the library. 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Double[][] test = {{7.0,6.0,2.0}
                    ,{0.0,5.0,1.0},
                    {9.0,11.0,0.0}};
            
            (new DoubleMatrix(3,3,test)).print();
            
            System.out.println("Output");
            (new DoubleMatrix(3,3,Gaussian.rowEchelon(test))).print();
            
            
        } catch (NullMatrixException | DimensionMismatch ex) {
            Logger.getLogger(LinearAlgebra.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
