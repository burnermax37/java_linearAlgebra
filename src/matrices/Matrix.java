/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices;

/**
 * The matrix class for linear algebra.Important: Matrices are fixed once constructed.
 * @author Maxwell Burner
 * @param <V> Type of value stored in matrix
 */




public abstract class Matrix<V> {
    
    /**Functional interface for binary operation that returns a value. 
     * @param <U> Output type and first input type
     * @param <W> Second input type*/
    @FunctionalInterface
    protected interface BinaryFunctionReturn<U,W>{
        U apply(U alpha, W beta);
    }
    
    /**Functional interface for binary operation that returns no value. 
     * @param <U> Output type and first input type
     * @param <W> Second input type*/
    @FunctionalInterface
    protected interface BinaryFunctionVoid<U,W>{
        void apply(U alpha, W beta);
    }
    

    
    /**Functional interface for vector addition and subtraction.
     * @param <U> Type of left-hand vector
     * @param <W> Type of right hand vector/matrix*/
    protected class VectorAddSub<U,W>{
        private final BinaryFunctionReturn<U,W> func;
        
        /**Constructor.  
         @param func BinaryFunction that will be applied to elements of matrix.*/
        public VectorAddSub(BinaryFunctionReturn func){
            this.func = func;
        }
        
        /**Method that operates on two matrices element-wise and stores results in a third.  
         @param source1 First matrix operand
         *@param source2 Second matrix operand
         * @param dest Matrix storing resulting values. 
         * @throws matrices.NullMatrixException 
         * @throws matrices.DimensionMismatch 
         */
        public void apply(Matrix<U> source1, Matrix<W> source2, Matrix<U> dest) throws NullMatrixException, DimensionMismatch{
            if(source1.vectorAlgebraCheck(source2)){
            
                for(int i = 0; i < source1.columns; i++){
                    for(int j = 0; j< source1.rows; j++){
                        dest.elements[i][j] = func.apply(source1.elements[i][j], source2.elements[i][j]);
                    }
                }
                dest.hasElements = true;
            }
        }
        
    }
    
    /**Functional class for Scalar-vector multiplication and division.
     * @param <U> Type of vector
     * @param <W> Type of scalar*/
    protected class ScalarMultDiv<U,W>{
        private final BinaryFunctionReturn<U,W> func;
        
        /**Constructor. 
         @param func Function for multiplication or division*/
        public ScalarMultDiv(BinaryFunctionReturn<U,W> func){
            this.func = func;
        }
        
        public void apply(Matrix<U> source, Matrix<U> dest, W scalar){
            for(int i = 0; i < source.rows; i++){
                for(int j = 0; j < source.columns; j++){
                    dest.elements[i][j] = func.apply(source.elements[i][j], scalar);
                }
            }
        }
        
    }
    
    
    /**Functional class for Scalar-vector multiplication and division when output
         * matrix has different type from matrix calling the method. 
     * @param <U> Type of vector
     * @param <W> Type of scalar*/
    protected class ScalarMultRev<U,W>{
        private final BinaryFunctionReturn<U,W> func;
        
        /**Constructor. 
         @param func Function for multiplication or division*/
        public ScalarMultRev(BinaryFunctionReturn<U,W> func){
            this.func = func;
        }
        
        public void apply(Matrix<W> source, Matrix<U> dest, U scalar){
            for(int i = 0; i < source.rows; i++){
                for(int j = 0; j < source.columns; j++){
                    dest.elements[i][j] = func.apply(scalar,source.elements[i][j]);
                }
            }
        }
        
    }
    
    
    /**Functional class for matrix multiplication.
     * @param <U> Type for left hand and output matrices
     * @param <W> Type for right hand matrix
     */
    protected class MatrixMultiplier<U,W>{
        private final BinaryFunctionReturn<U,U> sumFunc;
        private final BinaryFunctionReturn<U,W> productFunc;

        /**COnstructor.
         * @param sumFunc Sum function that combines products of elements
         * @param productFunc Product function that combines value of two elements*/
        public MatrixMultiplier(BinaryFunctionReturn<U, U> sumFunc, BinaryFunctionReturn<U, W> productFunc) {
            this.sumFunc = sumFunc;
            this.productFunc = productFunc;
        }
        
        /**Matrix multiplication function. 
         @param lhs Left-hand matrix
         @param rhs Right-hand matrix
         @param dest Array containing values for output matrix
         @param zero A value serving as zero; 0 for integers, 0.0 for floating point, false for boolean.
         * @throws matrices.NullMatrixException
         * @throws matrices.DimensionMismatch */
        public void apply(Matrix<U> lhs, Matrix<W> rhs, U[][] dest, U zero) throws NullMatrixException, DimensionMismatch{
            if(lhs.matrixMultTest(rhs)){
            
                U hold;
                for(int i = 0; i < lhs.rows; i++){
                    for(int j = 0; j < rhs.columns; j++){
                        hold = zero;
                        for(int k = 0; k < lhs.columns; k++){
                            hold = sumFunc.apply(hold, productFunc.apply(lhs.getValue(i,k),rhs.getValue(k,j)));
                        }
                        dest[i][j] = hold;
                    }
                }
                

            }else{
                throw new DimensionMismatch("Dimension mismatch or null matrices.");
            }
            
        }
        
        
    }
    
    
    
    private int rows;
    private int columns;

    private boolean hasElements;
    protected V[][] elements;
    
    /**Constructor with no parameters. Produces a matrix with 0 rows, 0 columns,
     * and a null pointer for elements.
     */
    public Matrix(){
        this.rows = 0;
        this.columns = 0;
        this.hasElements = false; //Probably no longer necessary
    }
    

    /**Constructor taking two integers. 
     @param rows Integer becoming number of rows of the matrix.
     @param columns Integer becoming number of columns of the matrix.
     @exception NullMatrixException Thrown if rows or col are zero or less.*/
    public Matrix(int rows, int columns) throws NullMatrixException{
        if((rows <= 0)||(columns <= 0)){
            throw new NullMatrixException(rows, columns);
        }
        
        
        this.rows = rows;
        this.columns = columns;
    }
    
    
    
    /**Copy constructor
     @param matrix The matrix to be copied*/
    public Matrix(Matrix matrix){
        this.rows = matrix.rows;
        this.columns = matrix.columns;
        this.hasElements = false;
    }

    
    /**Copy method. 
     @return A copy of the matrix.
     * @throws matrices.NullMatrixException
     * @throws matrices.DimensionMismatch */
    public abstract Matrix<V> copy() throws NullMatrixException,DimensionMismatch;
    
    /**Accessor to return number of rows.
     @return number of rows of matrix (length of first dimension)*/
    public int getRows(){
        return this.rows;
    }
    
    
    
    /**Accessor to return number of columns. 
     @return number of columns of matrix (length of second dimension)*/
    public int getColumns(){
        return this.columns;
    }
    
    
    /**Accessor to calculate and return number of elements. 
     @return number of cells/elements of matrix.*/
    public int getCellcount(){
        return (this.rows)*(this.columns);
    }
    
    /**Accessor for individual value. 
     @param i Row number of value
     @param j Column number of value
     @return value at cell (i,j)*/
    public V getValue(int i, int j){
        return this.elements[i][j];
    }
    

    
    
    /**State check method. 
     @return A string describing the state of the matrix: rows, columns, whether
     it has elements, etc.*/  
    @Override
    public abstract String toString();
    

  
    /**Method to print the contents of an array. Protected, used in public
     * print methods of subclasses. 
     @param base String used to format output*/
    protected void print(String base){
        
        if(this.checkElements()){
            for(int i = 0; i < rows; i++){
                System.out.print("| ");
                for(int j = 0; j < columns; j++){
                    System.out.printf(String.format(base,this.elements[i][j]));
                }
                System.out.print("|\n");
            }
        }
    }
    
    
    
    /**Accessor to check if hasElements is true. 
     @return Value of hasElements boolean field. */
    public boolean checkElements(){
        return this.hasElements;
    }
    
    /**Setter to set rows, columns, and hasElements. Should only be used in constructors of
     * subclasses.
     * @param rows The integer that becomes the number of rows of the matrix.
     * @param columns The integer that becomes the number of columns of the matrix.
     */
    protected void make(int rows, int columns){
        this.rows = rows;
        this.columns = columns;
        this.hasElements = (rows > 0)&&(columns > 0);
    }
    
    
    
    /**Method to check if matrix is "null". A matrix is null if it has zero rows, 
     * zero columns, a null pointer for elements, or the elements array has not 
     * been initialized.
     * @return true if matrix is not null, false otherwise*/
    public boolean isNotNull(){
        
        if(this.rows <= 0){
            return false;
        }else if(this.columns <= 0){
            return false;
        }else if(!this.hasElements){
            return false;
        }
        
        else if(this.elements == null){
            return false;
        }
        
        return true;
    }
    

    
     /**Method to check if two matrices can be added or subtracted. Both matrices 
     * must have matching dimensions, and dimensions greater than zero.
     @param rhs The right hand matrix operand.
     @return True if the matrices can be used for vector algebra.
     @exception NullMatrixException If either matrix has a dimension of 0 or less.
     @exception DimensionMismatch If the matrices do not have matching dimensions.*/
    protected boolean vectorAlgebraCheck(Matrix rhs)
            throws NullMatrixException, DimensionMismatch{
        if(!rhs.isNotNull()){
            throw new NullMatrixException(); //Raise exception if either matrix has less than one column
        }else if(!this.isNotNull()){
            throw new NullMatrixException(); //Raise exception if either matrix less that one row
        }else if((this.getRows() != rhs.getRows())||(this.getColumns() != rhs.getColumns())){
            throw new DimensionMismatch(); //Raise exception if dimensions are different
        }
        
        return true;
    }
    
    
    /**Method to check if matrix can be multiplied by another.
     * @param rhs Matrix with which this matrix will be multiplied
     * @return True if both matrices are non-null, and have appropriate dimensions
     * @throws matrices.NullMatrixException
     * @throws matrices.DimensionMismatch */
    protected boolean matrixMultTest(Matrix rhs) throws NullMatrixException, DimensionMismatch{
        if(!rhs.isNotNull()){
            throw new NullMatrixException(); //Raise exception if either matrix has less than one column
        }else if(!this.isNotNull()){
            throw new NullMatrixException(); //Raise exception if either matrix less that one row
        }else if(!(this.getColumns() == rhs.getRows())){
            throw new DimensionMismatch(); //Raise exception if dimensions are different
        }
        
        return true;
    }
    

    
    
    
    
    
    
    /**Protected method to copy a 1D array to a 2D array.
     * @param source Source 1D array
     * @param dest Destination 2D array
     * @throws matrices.DimensionMismatch*/
    protected static void copy1to2array(Object[] source, Object dest[][]) throws DimensionMismatch{
        final int ROW_COUNT = dest.length;
        final int COLUMN_COUNT = dest[0].length;
        
        final int CELLCOUNT = ROW_COUNT * COLUMN_COUNT;
        if(CELLCOUNT == source.length){
            int k = 0;
            
            
            for(int i = 0; i < ROW_COUNT; ++i){
                for(int j = 0; j < COLUMN_COUNT; j++){
                    dest[i][j] = source[k];
                    k++;
                }
            }
        }else{
            throw new DimensionMismatch();
        }
    };
    
    /**Protected method to copy one 2D array to another.
     * @param source Source 2D array
     * @param dest Destination 2D array
     * @throws matrices.DimensionMismatch */
    public static void copy2to2array(Object[][] source, Object[][] dest) throws DimensionMismatch{
        final int ROW_COUNT = source.length;
        final int COLUMN_COUNT = source[0].length;
        

        //Check arrays have matching dimensions. Possibly unnecessary
        if((ROW_COUNT == source.length)&&(COLUMN_COUNT == source[0].length)){
            boolean raggedFlag = false;
            
            //Check neither array is ragged. Again, in theory this is redundant
            for(int k = 0; k < ROW_COUNT;k++){
                if((dest[k].length != COLUMN_COUNT)||(source[k].length != COLUMN_COUNT)){
                    throw new DimensionMismatch("Ragged array");
                }
            }
            
            if(!raggedFlag){
            
                for(int i = 0; i < ROW_COUNT; ++i){
                    System.arraycopy(source[i], 0, dest[i], 0, COLUMN_COUNT);   
                }
            }
        }else{
            throw new DimensionMismatch();
        }
    }
    
    

    
    /**Protected method to transpose an array by copying its values into a transposed array.
     * @param left source array. 
     * @param right destination array
    */
    protected static void transposeArray(Object[][] left, Object[][] right){
        int u = left.length;
        int w = left[0].length;
        

        for(int i = 0; i < u; i++){
            for(int j = 0; j < w; j++){
                right[w][u] = left[u][w];
            }
        }

        
    }
    
    
    /**Method to transpose matrix.
     * @return Transposed copy of matrix
     * @throws matrices.NullMatrixException
     * @throws matrices.DimensionMismatch */
    public abstract Matrix<V> transpose() throws NullMatrixException, DimensionMismatch;
    
    
    /**Abstract method to swap matrix rows. 
     * @param alpha Index of first row
     * @param beta Index of second row
     */
    public abstract void rowOp_swap(int alpha, int beta);
    

    /**Abstract method to swap matrix columns. 
     * @param alpha Index of first column
     * @param beta Index of second column
     */
    public abstract void colOp_swap(int alpha, int beta);
    

}


