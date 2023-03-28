/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices;

import java.util.Arrays;



/**
 *
 * @author Maxwell Burner
 */
public class IntegerMatrix extends FieldMatrix<Integer> {
   

    

    
    
    /** * Constructor taking two integer parameters and a 1D Integer array.If the length of the
     input array matches the number of element in the matrix to be produced, the values
     of the array are used to populate the matrix elements; otherwise a null matrix is
     created
     @param rows Becomes the rows of the new matrix
     @param columns becomes the columns of the new matrix
     @param elements provides the elements for the new matrix
     * @throws matrices.NullMatrixException
     * @throws matrices.DimensionMismatch*/
    public IntegerMatrix(int rows, int columns, Integer[] elements) throws NullMatrixException, DimensionMismatch{
            super.make(rows, columns);
            this.elements = new Integer[rows][columns];
            Matrix.copy1to2array(elements, this.elements);

    }
    
    
        /** * Constructor taking two integer parameters and a 1D int array.If the length of the
     input array matches the number of element in the matrix to be produced, the values
     of the array are used to populate the matrix elements; otherwise a null matrix is
     created
     @param rows Becomes the rows of the new matrix
     @param columns becomes the columns of the new matrix
     @param elements provides the elements for the new matrix
     * @throws matrices.NullMatrixException
     * @throws matrices.DimensionMismatch*/
    public IntegerMatrix(int rows, int columns, int[] elements) throws NullMatrixException, DimensionMismatch{
            super(rows,columns);
            this.elements = new Integer[rows][columns];
            Integer[] hold = new Integer[elements.length];
            for(int i = 0; i < elements.length;i++){
                hold[i] = elements[i];
            }
            Matrix.copy1to2array(hold, this.elements);

    }
    
    /**Private constructor that creates an empty matrix.
     * @param rows
     * @param columns
     * @throws matrices.NullMatrixException
     */
    protected IntegerMatrix(int rows, int columns) throws NullMatrixException{
        super(rows, columns);
        this.elements = new Integer[rows][columns];
    }


    
    /** * Constructor taking two integer parameters and a 2D Integer array.If the first dimension of the 
     input array matches the specified row number, and all of the inner arrays of the 2D array
     have lengths matching the specified column number, the 2D array is used to populate the matrix
     elements. Otherwise a null matrix is returned
     @param rows Becomes the number of rows of the matrix
     @param columns Becomes the number of columns of the matrix
     @param elements Provides the elements for the matrix
     * @throws matrices.NullMatrixException*/
    public IntegerMatrix(int rows, int columns, Integer[][] elements) throws NullMatrixException, DimensionMismatch{
            super.make(rows, columns);
            this.elements = new Integer[rows][columns];
            Matrix.copy2to2array(elements, this.elements);
    }
    
    
    /** * Constructor taking two integer parameters and a 2D int array.If the first dimension of the 
     input array matches the specified row number, and all of the inner arrays of the 2D array
     have lengths matching the specified column number, the 2D array is used to populate the matrix
     elements. Otherwise a null matrix is returned
     @param rows Becomes the number of rows of the matrix
     @param columns Becomes the number of columns of the matrix
     @param elements Provides the elements for the matrix
     * @throws matrices.NullMatrixException*/
    public IntegerMatrix(int rows, int columns, int[][] elements) throws NullMatrixException, DimensionMismatch{
        super.make(rows, columns);
        Integer[][] hold = new Integer[rows][columns];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                hold[i][j] = elements[i][j];
            }
        }
              
        Matrix.copy1to2array(elements, hold);
    }

    
    
    
    /**Copy constructor
     @param matrix The matrix to be copied*/
    public IntegerMatrix(IntegerMatrix matrix){
        super(matrix);
        this.elements = matrix.getElements();
    }

    
    /**Copy method. 
     @return A copy of the matrix*/
    @Override
    public IntegerMatrix copy() throws NullMatrixException, DimensionMismatch{
        Integer[][] values = new Integer[this.getRows()][this.getColumns()];
        for(int i = 0; i < this.getRows(); i++){
            System.arraycopy(this.elements, 0, values, 0, this.getColumns());
        }
        return new IntegerMatrix(this.getRows(), this.getColumns(), values);
    }
    
    
     /** Private accessor to copy matrix elements to 2D array.
     @return a 2D array of doubles with the dimensions and values of the matrix*/  
    protected Integer[][] getElements(){
        int rows = this.getRows();
        int columns = this.getColumns();
        
        Integer[][] out = new Integer[rows][columns];
        
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
        super.print("%6d");
        
    }
    
    
    
     /**Method to determine if current matrix is identical to another
     @param matrix The matrix to which the calling matrix is compared
     @return true if matrices match, false otherwise*/
    public boolean equals(IntegerMatrix matrix){
        boolean result = true;

        Integer[][] leftElements = matrix.getElements();
        
        int columns = this.getColumns();
        int rows = this.getRows();
        
        if(columns != matrix.getColumns()){
            result = false;
        }else if(rows != matrix.getRows()){
            result = false;
        }else{
            for(int i = 0; i < rows; i++){
                for(int j = 0; j < columns; j++){
                    if(this.elements[i][j] == leftElements[i][j]){
                        result = false;
                    }
                }
            }
        }
        
        return result;
    }
    
    
    
    
    

    
    
    
    //Operations
    /** * Method to add two matrices.If the dimensions of the matrices being added 
 don't match, a null matrix is returned.
     @param matrix The summand, the matrix being added to the calling matrix
     @return The vector algebra sum of the calling matrix and passed matrix
     * @throws matrices.NullMatrixException
     * @throws matrices.DimensionMismatch*/
    public IntegerMatrix add(IntegerMatrix matrix) throws NullMatrixException, DimensionMismatch{

        IntegerMatrix output;


        

        


        BinaryFunctionReturn<Integer,Integer> adderCore = (x,y) -> (x + y);
        VectorAddSub<Integer,Integer> adder = new VectorAddSub(adderCore);
            
        output = new IntegerMatrix(this.getRows(), this.getColumns());
        adder.apply(this, matrix, output);

        
        return output;
    }
    
        /** * Method to add two matrices.If the dimensions of the matrices being added 
 don't match, a null matrix is returned.
     @param matrix The summand, the matrix being added to the calling matrix
     @return The vector algebra sum of the calling matrix and passed matrix
     * @throws matrices.NullMatrixException
     * @throws matrices.DimensionMismatch*/
    public DoubleMatrix add(DoubleMatrix matrix) throws NullMatrixException, DimensionMismatch{

        DoubleMatrix output;

        

        
        BinaryFunctionReturn<Double,Integer> adderCore = (x,y) -> (x + y);
        VectorAddSub<Double,Integer> adder = new VectorAddSub(adderCore);
        

        

            
        output = new DoubleMatrix(this.getRows(), this.getColumns());
        adder.apply(matrix,this, output);
            

        return output;
    }
   
    
    
    

    /** * Method to subtract two matrices.If the dimensions of the matrices being added 
 don't match, a null matrix is returned.
     @param matrix The subtrahend, the matrix being subtracted from the calling matrix
     @return The vector algebra difference of the calling matrix and passed matrix
     * @throws matrices.NullMatrixException
     * @throws matrices.DimensionMismatch*/
    public IntegerMatrix subtract(IntegerMatrix matrix) throws NullMatrixException, DimensionMismatch{

        IntegerMatrix output;

        
        

                    
        BinaryFunctionReturn<Integer,Integer> subtractorCore = (x,y) -> (x - y);
        VectorAddSub<Integer,Integer> subtractor = new VectorAddSub(subtractorCore);
            
            
        output = new IntegerMatrix(this.getRows(), this.getColumns());
            
        subtractor.apply(this, matrix, output);

        
        return output;
    }
    
    
    /** * Method to subtract two matrices.If the dimensions of the matrices being added 
 don't match, a null matrix is returned.
     @param matrix The subtrahend, the matrix being subtracted from the calling matrix
     @return The vector algebra difference of the calling matrix and passed matrix
     * @throws matrices.NullMatrixException
     * @throws matrices.DimensionMismatch*/
    public DoubleMatrix subtract(DoubleMatrix matrix) throws NullMatrixException, DimensionMismatch{

        DoubleMatrix output;

 
        BinaryFunctionReturn<Double,Integer> subtractorCore = (x,y) -> (y - x);
        VectorAddSub<Double,Integer> subtractor = new VectorAddSub(subtractorCore);
                       
        output = new DoubleMatrix(this.getRows(), this.getColumns());
           
        subtractor.apply(matrix, this, output);

        return output;
    }
    
    
    
    
    /**Method to multiply a matrix by a scalar. 
    @param scalar The value by which the matrix is multiplied
    @return the product of the matrix and the scalar*/
    @Override
    public IntegerMatrix scalarMultiplication(int scalar) throws NullMatrixException {
        IntegerMatrix output;
        
        
        if(this.isNotNull()){
            BinaryFunctionReturn<Integer,Integer> multipliCore = (x,y) -> (x*y);
            ScalarMultDiv<Integer,Integer> multiplier = new ScalarMultDiv<>(multipliCore);
                
                
            output = new IntegerMatrix(this.getRows(), this.getColumns());
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
        public DoubleMatrix scalarMultiplication(double scalar) throws NullMatrixException{
        DoubleMatrix output;
        
        int rows = this.getRows();
        int columns = this.getColumns();
        
        if(this.isNotNull()){
            /*
            int[][] values = new int[rows][columns];
            for(int i = 0; i < rows; i++){
                for(int j = 0; j < columns; j++){
                    values[i][j] = Math.round(Math.round((elements[i][j])*scalar));
                }
            }

           output = new IntegerMatrix(rows, columns, values);
            */
            BinaryFunctionReturn<Double,Integer> multipliCore = (x,y) -> (x*y);
            ScalarMultRev<Double,Integer> multiplier = new ScalarMultRev<>(multipliCore);
                
                
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
    public IntegerMatrix scalarDivision(int scalar) throws NullMatrixException{
        IntegerMatrix output;
        

        
        if(this.isNotNull()){
            
            BinaryFunctionReturn<Integer,Integer> multipliCore = (x,y) -> (x/y);
            ScalarMultDiv<Integer,Integer> multiplier = new ScalarMultDiv<>(multipliCore);
                
                
            output = new IntegerMatrix(this.getRows(), this.getColumns());
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
            BinaryFunctionReturn<Double,Integer> multipliCore = (x,y) -> (y/x);
            ScalarMultRev<Double,Integer> multiplier = new ScalarMultRev<>(multipliCore);
                
                
            output = new DoubleMatrix(this.getRows(), this.getColumns());
            

            multiplier.apply(this, output, scalar);
        }else{
            throw new NullMatrixException();
        }
        
        return output;
    }
    
    
    

    /**
     * Method to transpose matrix. 
     * @return Transposed copy of matrix. 
     */
    @Override
    public IntegerMatrix transpose() throws NullMatrixException {
        IntegerMatrix out = new IntegerMatrix(this.getColumns(), this.getRows());
        Matrix.transposeArray(elements, out.elements);
        return out;
    }

    /**Method for matrix multiplication with another integer matrix. 
     @param rhs Right-hand multiplier.
     @return Matrix result
     * @throws matrices.DimensionMismatch
     * @throws matrices.NullMatrixException*/
    public IntegerMatrix matrixMultiplication(IntegerMatrix rhs) throws DimensionMismatch, NullMatrixException {

        Integer[][] outElements = new Integer[this.getRows()][rhs.getColumns()];
            
        BinaryFunctionReturn<Integer, Integer> sum = (x,y) -> (x+y);
        BinaryFunctionReturn<Integer, Integer> product = (x,y) -> (x*y);
            
        MatrixMultiplier<Integer,Integer> multiplier = new MatrixMultiplier<>(sum,product);
        multiplier.apply(this, rhs, outElements, 0);
            
        return new IntegerMatrix(this.getRows(), rhs.getColumns(), outElements);
   
    }
    
    
    @Override
    /**Method to swap matrix rows. 
     * @param alpha Index of first row
     * @param beta Index of second row
     */
    public void rowOp_swap(int alpha, int beta) {
        Integer[] hold = new Integer[this.getColumns()];
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
        int hold;
        
        for(int i = 0; i < this.getRows(); i++){
            hold = this.getElements()[i][alpha]; //Copy alpha to hold
            this.getElements()[i][alpha] = this.getElements()[i][beta]; //Copy beta to alpha
            this.getElements()[i][beta] = hold; //Copy hold to beta
        }
        
    }
}
