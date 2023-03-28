/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class provides functions for row operations, column operations, and Gaussian Elimination. 
 * @author Maxwell Burner
 */
public class Gaussian {
    
    static final double EPSILON = 0.0001;
    
    /**Method to convert a row to a string.
     @param row Input double array. 
     @return String with double array values. */
    static public String rowToString(Double[] row){
        StringBuilder out = new StringBuilder("[");
        for(int i = 0; i < row.length; i++){
            if(i != 0){
                out.append(",");
            }
            out.append(String.format("%f", row[i]));  
        }
        out.append("]");
        return out.toString();
    }

    
    /**Method for row multiplication. 
     @param matrix The 2D array of doubles on which the function operates. 
     @param target Integer indicating the target row; first row is 0. 
     @param scalar The integer by which the row is multiplied. */
    protected static void rowOp_multiply(Double[][] matrix, int target, double scalar){
        Double[] row = matrix[target];
        for(int i = 0; i < row.length; i++){
            row[i] *= scalar;
        }
    }
    
    /**Method for row multiplication. 
     @param matrix The 2D array of Integers on which the function operates. 
     @param target Integer indicating the target row; first row is 0. 
     @param scalar The integer by which the row is multiplied. */
    protected static void rowOp_multiply(Integer[][] matrix, int target, double scalar){
        Integer[] row = matrix[target];
        double hold;
        for(int i = 0; i < row.length; i++){
            hold = row[i]*(scalar);
            row[i] = (int) Math.round(hold);
        }
    }
    
        /**Method for row division. 
     @param matrix The 2D array of doubles on which the function operates. 
     @param target Integer indicating the target row; first row is 0. 
     @param scalar The integer by which the row is divided. */
    protected static void rowOp_divide(Double[][] matrix, int target, double scalar){
        Double[] row = matrix[target];
        for(int i = 0; i < row.length; i++){
            row[i] /= scalar;
        }
    }
    
    /**Method for row division.
     @param matrix The 2D array of Integers on which the function operates. 
     @param target Integer indicating the target row; first row is 0. 
     @param scalar The integer by which the row is divided. */
    protected static void rowOp_divide(Integer[][] matrix, int target, double scalar){
        Integer[] row = matrix[target];
        double hold;
        for(int i = 0; i < row.length; i++){
            hold = row[i]/(scalar);
            row[i] = (int) Math.round(hold);
        }
    }
    
    /**Method for column multiplication. 
     @param matrix The 2D array of doubles on which the function operates. 
     @param target Integer indicating the target column; first column is 0.
     @param scalar The double by which the column is multiplied. */
    protected static void colOp_multiply(Double[][] matrix, int target, double scalar){
        for(int i = 0; i < matrix.length; ++i){
            matrix[i][target] *= scalar;
        }
    }
    
    /**Method for column multiplication. 
     @param matrix The 2D array of integers on which the function operates. 
     @param target Integer indicating the target column; first column is 0.
     @param scalar The double by which the column is multiplied. */
    protected static void colOp_multiply(Integer[][] matrix, int target, double scalar){
        double hold;
        for(int i = 0; i < matrix.length; ++i){
            hold = matrix[i][target]*scalar;
            matrix[i][target] = (int) Math.round(hold);
        }
    }
    
    
        /**Method for column division. 
     @param matrix The 2D array of doubles on which the function operates. 
     @param target Integer indicating the target column; first column is 0.
     @param scalar The double by which the column is divided. */
    protected static void colOp_divide(Double[][] matrix, int target, double scalar){
        for(int i = 0; i < matrix.length; ++i){
            matrix[i][target] /= scalar;
        }
    }
    
    /**Method for column division. 
     @param matrix The 2D array of integers on which the function operates. 
     @param target Integer indicating the target column; first column is 0.
     @param scalar The double by which the column is divided. */
    protected static void colOp_divide(Integer[][] matrix, int target, double scalar){
        double hold;
        for(int i = 0; i < matrix.length; ++i){
            hold = matrix[i][target]/scalar;
            matrix[i][target] = (int) Math.round(hold);
        }
    }
    

    
    /**Method for row addition. 
     *@param matrix The 2D array of doubles on which the function operates.
      @param source The subscript of the row whose values will be multiplied and added. 
      @param target The subscript of the row to which values will be added. 
      @param scalar The value by which the source row will be multiplied before addition.
     */
    protected static void rowOp_add(Double[][] matrix, int source, int target, double scalar){
        Double[] sourceRow = matrix[source];
        Double[] targetRow = matrix[target];
        System.out.print(String.format("Subtracting %s times %f from %s",
                rowToString(sourceRow),
                scalar,
                rowToString(targetRow)
                        ));
        for(int i = 0; i < sourceRow.length; i++){
            targetRow[i] += (sourceRow[i])*scalar;
        }
        System.out.print(String.format(" to get %s%n",
                rowToString(targetRow)));
    }
    
        /**Method for row addition. 
     *@param matrix The 2D array of doubles on which the function operates.
      @param source The subscript of the row whose values will be multiplied and added. 
      @param target The subscript of the row to which values will be added. 
      @param scalar The value by which the source row will be multiplied before addition.
     */
    protected static void rowOp_add(Integer[][] matrix, int source, int target, int scalar){
        Integer[] sourceRow = matrix[source];
        Integer[] targetRow = matrix[target];
        double hold;
        for(int i = 0; i < sourceRow.length; i++){
            hold = (sourceRow[i])*scalar;
            targetRow[i] += (int) Math.round(hold);
        }
    }
    
    
    /**Method for column addition. 
     @param matrix The 2D array of doubles on which the function operates. 
     @param source The subscript of the column whose values will be multiplied and added.
     @param target The subscript of the column to which values will be added.
     @param scalar the value by which the source column will be multiplied before addition*/
    protected static void colOp_add(Double[][] matrix, int source, int target, double scalar){
        for(int i = 0; i < matrix.length; i++){
            matrix[i][target] += (matrix[i][source])*scalar;
        }
    }
    
     /**Method for column addition. 
     @param matrix The 2D array of doubles on which the function operates. 
     @param source The subscript of the column whose values will be multiplied and added.
     @param target The subscript of the column to which values will be added.
     @param scalar the value by which the source column will be multiplied before addition*/
    protected static void colOp_add(Integer[][] matrix, int source, int target, double scalar){
        double hold;
        for(int i = 0; i < matrix.length; i++){
            hold = (matrix[i][source])*scalar;
            matrix[i][target] += (int)Math.round(hold);
        }
    }
    
    
    
    
    
    /**Method for row swapping. 
     @param matrix The 2D array of doubles on which the function operates.
     @param alpha The subscript of the first row to be swapped. 
     @param beta The subscript of the second row to be swapped.*/
    protected static void rowOp_swap(Double[][] matrix, int alpha, int beta){
        Double[] hold = matrix[alpha];
        matrix[alpha] = matrix[beta];
        matrix[beta] = hold;
    }
    
        /**Method for row swapping. 
     @param matrix The 2D array of integers on which the function operates.
     @param alpha The subscript of the first row to be swapped. 
     @param beta The subscript of the second row to be swapped.*/
    protected static void rowOp_swap(Integer[][] matrix, int alpha, int beta){
        Integer[] hold = matrix[alpha];
        matrix[alpha] = matrix[beta];
        matrix[beta] = hold;
    }
  
    
    /**Function to swap two elements in an array
     @param alpha The subscript of the first value
     @param beta The subscript of the second value to be swapped
     @param series The array of doubles whose values are being swapped*/
    private static void arraySwap(int alpha, int beta, Double[] series){
        double hold = series[alpha];
        series[alpha] = series[beta];
        series[beta] = hold;
    }
    
      /**Function to swap two elements in an array
     @param alpha The subscript of the first value
     @param beta The subscript of the second value to be swapped
     @param series The array of integers whose values are being swapped*/
    private static void arraySwap(int alpha, int beta, Integer[] series){
        int hold = series[alpha];
        series[alpha] = series[beta];
        series[beta] = hold;
    }
    
    
    /**Method for column swapping. 
     @param matrix The 2D array of doubles on which the column functions
     @param alpha The subscript of the first column to be swapped.
     @param beta The subscript of the second column to be swapped. */
    protected static void colOp_swap(Double[][] matrix, int alpha, int beta){
        double hold = 0;
        
        for (Double[] matrix1 : matrix) {
            arraySwap(alpha, beta, matrix1);
        }
    }
    
    /**Method for column swapping. 
     @param matrix The 2D array of integers on which the column functions
     @param alpha The subscript of the first column to be swapped.
     @param beta The subscript of the second column to be swapped. */
    protected static void colOp_swap(Integer[][] matrix, int alpha, int beta){
        double hold = 0;
        
        for (Integer[] matrix1 : matrix) {
            arraySwap(alpha, beta, matrix1);
        }
    }
    
    /**Method to get the number of leading zeros on each row of a matrix array. 
     @param matrix The matrix being assessed.
     @return A integer array recording the number of leading zeroes for each
     * corresponding matrix array row*/
    protected static int[] row_leadingZeroes(Double[][] matrix){
        final double EPSILON = 0.0001;
        int width = matrix[0].length;
        int[] zeroCounts = new int[width];
        int zeroCounter, j;
        
        
        Double[] currentRow;
        
        boolean zeroFlag;
        
        for(int i = 0; i < matrix.length; i++){
            zeroCounter = 0;
            currentRow = matrix[i];

            j = 0;
            do{
                if(Math.abs(currentRow[j]) < EPSILON){
                    zeroFlag = true;
                    ++zeroCounter;
                }else{
                    zeroFlag = false;
                }
                ++j;
            }while(zeroFlag&&(j < currentRow.length));
            zeroCounts[i] = zeroCounter;
        }
        
        return zeroCounts;
    }
    
    
    /**Method to get the number of leading zeros on each row of a matrix array. 
     @param matrix The matrix being assessed.
     @return A integer array recording the number of leading zeroes for each
     * corresponding matrix array row*/
    protected static int[] row_leadingZeroes(Integer[][] matrix){
        int width = matrix[0].length;
        int[] zeroCounts = new int[width];
        int zeroCounter, j;
        
        
        Integer[] currentRow;
        
        boolean zeroFlag;
        
        for(int i = 0; i < matrix.length; i++){
            zeroCounter = 0;
            currentRow = matrix[i];

            j = 0;
            do{
                if(currentRow[j] == 0){
                    zeroFlag = true;
                    ++zeroCounter;
                }else{
                    zeroFlag = false;
                }
                ++j;
            }while(zeroFlag&&(j < currentRow.length));
            zeroCounts[i] = zeroCounter;
        }
        

        System.out.print("Zero Counts: |");
        for(int i = 0; i < zeroCounts.length; i++){
            System.out.print(String.format("%d,",zeroCounts[i]));
        }
        System.out.print("|\n");

        
        return zeroCounts;
    }
    
    
    //Function to sort rows of 2D array by first value in each, then return array indicating new positions
    public static int[] sortByValues(int[] input){
        int[] output = new int[input.length];
        for(int i = 0; i < input.length; i++){
            output[i] = i;
        }
        

        
        int minIndex;
        int minimum;
        int hold;
        
        //For each index i
        for(int i = 0; i < input.length; i++){
            minIndex = i;
            minimum = input[i];
            hold = output[i];
            //for each index after i
            for(int j = i; j < input.length; j++){
                if(input[j] < minimum){
                    //If in[i] is less than the minimum, make it the minimum
                    minimum = input[j];
                    hold = output[j];
                    minIndex = j;
                }
            }
            
            //Swap input[i] and the minimum
            input[minIndex] = input[i];
            output[minIndex] = output[i];
            input[i] = minimum;
            output[i] = hold;
                   
        }
        
        System.out.print("New order: |");
        for(int i = 0; i < output.length; i++){
            System.out.print(String.format("%d,",output[i]));
        }
        System.out.print("|\n");
        
        return output;
    }
    
    /**Function to rearrange rows of double array based on a provided array. 
     @param input Array of doubles to be rearranged
     @param newOrder array whose values indicate new ordering of rows.
     @return Copy of input array with rearranged rows. */
    protected static Double[][] rearrangeRows(Double[][] input, int[] newOrder){

        System.out.print("New order: |");
        for(int i = 0; i < newOrder.length; i++){
            System.out.print(String.format("%d,",newOrder[i]));
        }
        System.out.print("|\n");

        Double[][] output = new Double[input.length][input[0].length];
        for(int i = 0; i < input.length; i++){
            int target = newOrder[i];
            System.arraycopy(input[target], 0, output[i], 0, input[0].length);
        }
        
        return output;
    }
    
        /**Function to rearrange rows of integer array based on a provided array. 
     @param input Array of doubles to be rearranged
     @param newOrder array whose values indicate new ordering of rows.
     * @return Copy of matrix with rows rearranged
     */
    protected static Integer[][] rearrangeRows(Integer[][] input, int[] newOrder){
        
        System.out.print("New order: |");
        for(int i = 0; i < newOrder.length; i++){
            System.out.print(String.format("%d,",newOrder[i]));
        }
        System.out.print("|\n");

        Integer[][] output = new Integer[input.length][input[0].length];
        for(int i = 0; i < input.length; i++){
            int target = newOrder[i];
            System.arraycopy(input[target], 0, output[i], 0, input[0].length);
        }
        
        return output;

    }
    
    /**Method to convert matrix/array into row echelon form. 
     @param matrix Input matrix
     @return Copy of matrix in row echelon form.
     * @throws matrices.DimensionMismatch */
    public static Double[][] rowEchelon(Double[][] matrix) throws DimensionMismatch{
        int rows = matrix.length;
        int cols = matrix[0].length;
        Double[][] output = rearrangeRows(matrix,sortByValues(row_leadingZeroes(matrix)));
        
        try {
            System.out.println("Rows rearranged");
            (new DoubleMatrix(rows,cols,output)).print();
        } catch (NullMatrixException ex) {
            Logger.getLogger(Gaussian.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int[] leadingIndex = row_leadingZeroes(output);

        double scalar;
        int start;

        //Normalize
        for(int i = 0; i < rows; i++){//for each row
            start = leadingIndex[i];
            scalar = output[i][start];
            
            rowOp_divide(output,i,scalar);

        }
        
        try {
            System.out.println("Normalized");
            (new DoubleMatrix(rows,cols,output)).print();
        } catch (NullMatrixException ex) {
            Logger.getLogger(Gaussian.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        for(int i = 0; i < rows; i ++){
            for(int j = 0; j < rows; j++){
                if(j != i){ //For every other row
                    scalar = output[j][leadingIndex[j]]; //Value by which i-row is multiplied
                    if(Math.abs(scalar) > EPSILON){ //If scaar is not zero
                        rowOp_add(output,i,j,0-scalar);
                        

                    }
                    
                }
            }
        }
        
        return output; 
    }
    

    
    
    /**Function to "clean" a 2D array of doubles.
     Converts near-zero values to zero.
     @param epsilon The minimum absolute value an element can have an not be cleaned.
     @param matrix The 2D array of doubles being cleaned. */
    protected static void cleaning(double[][] matrix){
        int rows = matrix.length;
        int columns = matrix[0].length;
        
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < rows; j++){
                if(Math.abs(matrix[i][j]) < EPSILON){
                    matrix[i][j] = 0;
                }
            }
        }
    }
}
