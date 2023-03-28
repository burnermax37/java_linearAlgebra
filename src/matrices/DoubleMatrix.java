/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maxwell Burner
 */
public class DoubleMatrix extends FieldMatrix<Double> {
    

    /**Constructor for null double matrix. */
   /* private DoubleMatrix(){
        super();
        this.elements = null;
    }*/
    
    /**Constructor for double matrix with empty array.
     * @param rows
     * @param columns
     * @throws matrices.NullMatrixException */
    protected DoubleMatrix(int rows, int columns) throws NullMatrixException{
        super(rows, columns);
        this.elements = new Double[rows][columns];
    }
    

    
    
    /** * Constructor taking two integer parameters and a 1D array.If the length of the
     input array matches the number of element in the matrix to be produced, the values
     of the array are used to populate the matrix elements; otherwise a null matrix is
     created
     @param rows Becomes the rows of the new matrix.
     @param columns becomes the columns of the new matrix.
     @param elements provides the elements for the new matrix.
     @exception NullMatrixException Thrown if new matrix is supposed to have 0 
     or fewer rows and/or columns.
     @exception CellcountMismatch Throws in new matrix size differs from number of 
     values in provided array.
     * @throws matrices.DimensionMismatch*/
    public DoubleMatrix(int rows, int columns, Double[] elements) throws NullMatrixException, CellcountMismatch, DimensionMismatch{
            super.make(rows, columns);
            Matrix.copy1to2array(elements, this.elements);
        
    }
    
     /** * Constructor taking two integer parameters and a 1D double array.If the length of the
     input array matches the number of element in the matrix to be produced, the values
     of the array are used to populate the matrix elements; otherwise a null matrix is
     created
     @param rows Becomes the rows of the new matrix.
     @param columns becomes the columns of the new matrix.
     @param elements provides the elements for the new matrix.
     @exception NullMatrixException Thrown if new matrix is supposed to have 0 
     or fewer rows and/or columns.
     @exception CellcountMismatch Throws in new matrix size differs from number of 
     values in provided array.
     * @throws matrices.DimensionMismatch*/
    public DoubleMatrix(int rows, int columns, double[] elements) throws NullMatrixException, CellcountMismatch, DimensionMismatch{
            super.make(rows, columns);
            Double[] hold = new Double[elements.length];
            for(int i = 0; i < elements.length; i++){
                hold[i] = elements[i];
            }
            Matrix.copy1to2array(hold, this.elements);
        
    }

    /** * Constructor taking two integer parameters and a 2D double array.If the first dimension of the 
     input array matches the specified row number, and all of the inner arrays of the 2D array
     have lengths matching the specified column number, the 2D array is used to populate the matrix. elements. Otherwise a null matrix is returned. 
     @param rows Becomes the number of rows of the matrix. 
     @param columns Becomes the number of columns of the matrix. 
     @param elements Provides the elements for the matrix.
     @exception DimensionMismatch if dimensions of matrix do not match dimensions of array. */
    public DoubleMatrix(int rows, int columns, double[][] elements) throws DimensionMismatch{
        super.make(rows, columns);
        Double[][] hold = new Double[rows][columns];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                hold[i][j] = elements[i][j];
            }
        }
              
        Matrix.copy1to2array(elements, hold);
    }
    
    
    /**Constructor taking two integer parameters and a 2D Double array. If the first dimension of the 
     input array matches the specified row number, and all of the inner arrays of the 2D array
     have lengths matching the specified column number, the 2D array is used to populate the matrix. 
     elements. Otherwise a null matrix is returned. 
     @param rows Becomes the number of rows of the matrix. 
     @param columns Becomes the number of columns of the matrix. 
     @param elements Provides the elements for the matrix.
     @exception NullMatrixException If matrix is declared with 0 or fewer rows or columns.
     @exception DimensionMismatch if dimensions of matrix do not match dimensions of array. */
    public DoubleMatrix(int rows, int columns, Double[][] elements)
            throws NullMatrixException, DimensionMismatch{
            super.make(rows, columns);
            this.elements = new Double[rows][columns];
            Matrix.copy2to2array(elements, this.elements); 
    }
    
    
    
    /**Copy constructor
     @param matrix The matrix to be copied*/
    public DoubleMatrix(DoubleMatrix matrix){
        super(matrix);
        this.elements = matrix.getElements();
    }

    
    /**Copy method. 
     @return A copy of the matrix. 
     @exception NullMatrixException Thrown if rows or columns of target matrix
     are zero or less.
     @exception DimensionMismatch Thrown if number of elements does not match
     size of matrix.*/
    @Override
    public DoubleMatrix copy() throws NullMatrixException, DimensionMismatch{
        Double[][] values = new Double[this.getRows()][this.getColumns()];
        for(int i = 0; i < this.getRows(); i++){
            System.arraycopy(this.elements, 0, values, 0, this.getColumns());
        }
        return new DoubleMatrix(this.getRows(), this.getColumns(), values);
    }
    
    
     /** Private accessor to copy matrix elements to 2D array.
     @return a 2D array of doubles with the dimensions and values of the matrix*/  

    protected Double[][] getElements(){
        int rows = this.getRows();
        int columns = this.getColumns();
        
        Double[][] out = new Double[rows][columns];
        
        for(int i = 0; i < rows; ++i){
            System.arraycopy(this.elements[i],0,out[i],0,columns);
        }
        
        return out;
    }
    
    
    
    
    
    /**State check method. 
     @return A string describing the state of the matrix: rows, columns, whether
     it has elements, etc.*/  
    @Override
    public String toString(){
        String output;
        String arrayStat, valuesStat;
        String format = "has %d rows, %d columns, it %s an element array, "+
                "and its elements %s initialized";
        
        int rows = this.getRows();
        int columns = this.getColumns();
        
        if(this.elements == null){
            arrayStat = "does not have";
        }else{
            arrayStat = "does have";
        }
        
        if(this.checkElements()){
            valuesStat = "are";
        }else{
            valuesStat = "are not";
        }
        
        output = String.format(format,rows,columns,arrayStat,valuesStat);

        
        
        return output;
    }
    
    
    
    
    /**Method to print the contents of an array. */
    public void print(){
        super.print("%6.2f");
    }
    
    
    
     /**Method to determine if current matrix is identical to another
     @param matrix The matrix to which the calling matrix is compared
     @return true if matrices match, false otherwise*/
    public boolean equals(DoubleMatrix matrix){
        boolean result = true;
        double epsilon = 0.00001;
        double dif;
        Double[][] leftElements = matrix.getElements();
        
        int columns = this.getColumns();
        int rows = this.getRows();
        
        if(columns != matrix.getColumns()){
            result = false;
        }else if(rows != matrix.getRows()){
            result = false;
        }else{
            for(int i = 0; i < rows; i++){
                for(int j = 0; j < columns; j++){
                    dif = Math.abs(this.elements[i][j] - leftElements[i][j]);
                    if(dif > epsilon){
                        result = false;
                    }
                }
            }
        }
        
        return result;
    }
    
    
    
    
    

    
    
    
    
    
    

    
    
    /**Method to add two matrices. If the dimensions of the matrices being added 
     * don't match, a null matrix is returned.
     @param matrix The summand, the matrix being added to the calling matrix.
     @return The vector algebra sum of the calling matrix and passed matrix.
     * @exception NullMatrixException If new matrix has zero or fewer rows or columns.
     @exception DimensionMismatch If input matrices do not have matching dimensions. */
    public DoubleMatrix add(DoubleMatrix matrix) throws NullMatrixException,DimensionMismatch{
        DoubleMatrix output;
        

            
        BinaryFunctionReturn<Double, Double> adderCore = (x,y) -> (x + y);
        VectorAddSub<Double, Double> adder = new VectorAddSub(adderCore);
            
            
        output = new DoubleMatrix(this.getRows(), this.getColumns());
            
        adder.apply(this, matrix, output);

        
        return output;
    }
    
    
    
    /**Method to add two matrices. If the dimensions of the matrices being added 
     * don't match, a null matrix is returned.
     @param matrix The summand, the matrix being added to the calling matrix.
     @return The vector algebra sum of the calling matrix and passed matrix.
     * @exception NullMatrixException If new matrix has zero or fewer rows or columns.
     @exception DimensionMismatch If input matrices do not have matching dimensions. */
    public DoubleMatrix add(IntegerMatrix matrix) throws NullMatrixException,DimensionMismatch{
        DoubleMatrix output;
        



            
        BinaryFunctionReturn<Double, Integer> adderCore = (x,y) -> (x + y);
        VectorAddSub<Double, Integer> adder = new VectorAddSub(adderCore);
            
            
        output = new DoubleMatrix(this.getRows(), this.getColumns());
            
        adder.apply(this, matrix, output);
            

        return output;
    }
   
    
    
    

    /**Method to subtract two matrices. If the dimensions of the matrices being added 
     * don't match, a null matrix is returned.
     @param matrix The subtrahend, the matrix being subtracted from the calling matrix
     @return The vector algebra difference of the calling matrix and passed matrix.
     @exception NullMatrixException If new matrix has zero or fewer rows or columns.
     @exception DimensionMismatch If input matrices do not have matching dimensions.*/
    public DoubleMatrix subtract(DoubleMatrix matrix)
            throws NullMatrixException, DimensionMismatch {
        DoubleMatrix output;
        


            
        BinaryFunctionReturn<Double,Double> subtractorCore = (x,y) -> (x - y);
        VectorAddSub<Double,Double> subtractor = new VectorAddSub(subtractorCore);
            
            
        output = new DoubleMatrix(this.getRows(), this.getColumns());
            
        subtractor.apply(this, matrix, output);

        
        return output;
    }
    
    
    /**Method to subtract two matrices. If the dimensions of the matrices being added 
     * don't match, a null matrix is returned.
     @param matrix The subtrahend, the matrix being subtracted from the calling matrix
     @return The vector algebra difference of the calling matrix and passed matrix.
     @exception NullMatrixException If new matrix has zero or fewer rows or columns.
     @exception DimensionMismatch If input matrices do not have matching dimensions.*/
    public DoubleMatrix subtract(IntegerMatrix matrix)
            throws NullMatrixException, DimensionMismatch {
        DoubleMatrix output;

            
        BinaryFunctionReturn<Double,Integer> subtractorCore = (x,y) -> (x - y);
        VectorAddSub<Double,Integer> subtractor = new VectorAddSub(subtractorCore);
            
            
        output = new DoubleMatrix(this.getRows(), this.getColumns());
            
        subtractor.apply(this, matrix, output);

        
        return output;
    }
    
    
    
    
    /**Method to multiply a matrix by a scalar. 
     @param scalar The value by which the matrix is multiplied
     @return the product of the matrix and the scalar*/
    @Override
    public DoubleMatrix scalarMultiplication(double scalar) throws NullMatrixException{
        DoubleMatrix output = null;

        if(this.isNotNull()){
      
            BinaryFunctionReturn<Double,Double> multipliCore = (x,y) -> (x*y);
            ScalarMultDiv<Double,Double> multiplier = new ScalarMultDiv<>(multipliCore);
                
                
            output = new DoubleMatrix(this.getRows(), this.getColumns());
            multiplier.apply(this, output, scalar);
                
                
        }else{
            throw new NullMatrixException();
        }

        

        return output;
    }
    
    
    /**Method to multiply a matrix by a scalar. 
     @param scalar The value by which the matrix is multiplied
     @return the product of the matrix and the scalar*/
    @Override
    public DoubleMatrix scalarMultiplication(int scalar) throws NullMatrixException{
        DoubleMatrix output;
        

        

        if(this.isNotNull()){
            BinaryFunctionReturn<Double,Integer> multipliCore = (x,y) -> (x*y);
            ScalarMultDiv<Double,Integer> multiplier = new ScalarMultDiv<>(multipliCore);
                
                
            output = new DoubleMatrix(this.getRows(), this.getColumns());
            multiplier.apply(this, output, scalar);


        }else{
            throw new NullMatrixException();
        }

        

        return output;
    }
    
    
    
    /**Method to divide a matrix by a scalar. 
     @param scalar The value by which the matrix is divided.
     @return the quotient of the matrix and the scalar*/
    @Override
    public DoubleMatrix scalarDivision(double scalar) throws NullMatrixException{
        DoubleMatrix output;
        

        

        if(this.isNotNull()){
            BinaryFunctionReturn<Double,Double> multipliCore = (x,y) -> (x/y);
            ScalarMultDiv<Double,Double> multiplier = new ScalarMultDiv<>(multipliCore);
                
                
            output = new DoubleMatrix(this.getRows(), this.getColumns());
            multiplier.apply(this, output, scalar);
        }else{
            throw new NullMatrixException();
        }

        

        return output;
    }
    
    
        /**Method to divide a matrix by a scalar. 
     @param scalar The value by which the matrix is divided.
     @return the quotient of the matrix and the scalar*/
    @Override
    public DoubleMatrix scalarDivision(int scalar) throws NullMatrixException{
        DoubleMatrix output;
        

        
        if(this.isNotNull()){
            BinaryFunctionReturn<Double,Integer> multipliCore = (x,y) -> (x/y);
            ScalarMultDiv<Double,Integer> multiplier = new ScalarMultDiv<>(multipliCore);
                
                
            output = new DoubleMatrix(this.getRows(), this.getColumns());
            multiplier.apply(this, output, scalar);
        }else{
            throw new NullMatrixException();
        }

        

        return output;
    }
    
    
    

    /**Method to transpose double matrix. 
     @return Transposed copy of matrix. */
    @Override
    public DoubleMatrix transpose() throws NullMatrixException {

        DoubleMatrix out = new DoubleMatrix(this.getColumns(), this.getRows());
        Matrix.transposeArray(elements, out.elements);
        return out;


    }
    
    
    /**Method for matrix multiplication with another integer matrix. 
     @param rhs Right-hand multiplier.
     @return Matrix result
     * @throws matrices.DimensionMismatch
     * @throws matrices.NullMatrixException*/
    public DoubleMatrix matrixMultiplication(DoubleMatrix rhs) throws DimensionMismatch, NullMatrixException {

        Double[][] outElements = new Double[this.getRows()][rhs.getColumns()];
            
        BinaryFunctionReturn<Double,Double> sum = (x,y) -> (x+y);
        BinaryFunctionReturn<Double,Double> product = (x,y) -> (x*y);
            
        MatrixMultiplier<Double,Double> multiplier = new MatrixMultiplier<>(sum,product);
        multiplier.apply(this, rhs, outElements, 0.0);
            
        return new DoubleMatrix(this.getRows(), rhs.getColumns(), outElements);
   
    }

    @Override
    /**Method to swap matrix rows. 
     * @param alpha Index of first row
     * @param beta Index of second row
     */
    public void rowOp_swap(int alpha, int beta) {
        Double[] hold = new Double[this.getColumns()];
        System.arraycopy(this.getElements()[alpha], 0, hold, 0, this.getColumns()); //Copy alpha to hold
        System.arraycopy(this.getElements()[beta], 0, hold, 0, this.getColumns()); //Copy beta to alpha
        System.arraycopy(hold, 0, this.getElements()[beta], 0, this.getColumns()); //Copy hold to beta
    }

    @Override
    /**Abstract method to swap matrix columns. 
     * @param alpha Index of first column
     * @param beta Index of second column
     */
    public void colOp_swap(int alpha, int beta) {
        double hold;
        
        for(int i = 0; i < this.getRows(); i++){
            hold = this.getElements()[i][alpha]; //Copy alpha to hold
            this.getElements()[i][alpha] = this.getElements()[i][beta]; //Copy beta to alpha
            this.getElements()[i][beta] = hold; //Copy hold to beta
        }
        
    }
    
    
    
    /**Method to multiply one row of a matrix by an integer. 
     @param index Index of row being multiplied
     * @param scalar Integer by which row is multiplied
     */
    @Override
    public void rowOp_multiply(int index, int scalar){
        for(int i = 0; i < this.getColumns(); i++){
            this.getElements()[index][i] *= scalar;
        }
    }
    
    
    /**Method to multiply one column of a matrix by an integer. 
     @param index Index of column being multiplied
     * @param scalar Integer by which column is multiplied
     */
    @Override
    public void colOp_multiply(int index, int scalar){
        for(int i = 0; i < this.getRows(); i++){
            this.getElements()[i][index] *= scalar;
        }
    }
    
    
    
    
    
    
}
