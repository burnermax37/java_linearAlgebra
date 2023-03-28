/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices;

/**
 *
 * @author Maxwell Burner
 */
public class BooleMatrix extends Matrix<Boolean> {
    
/**Constructor for Boolean matrix with empty array.
     * @param rows
     * @param columns
     * @throws matrices.NullMatrixException */
    protected BooleMatrix(int rows, int columns) throws NullMatrixException{
        super(rows, columns);
        this.elements = new Boolean[rows][columns];
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
    public BooleMatrix(int rows, int columns, Boolean[] elements) throws NullMatrixException, CellcountMismatch, DimensionMismatch{
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
    public BooleMatrix(int rows, int columns, boolean[] elements) throws NullMatrixException, CellcountMismatch, DimensionMismatch{
            super.make(rows, columns);
            Boolean[] hold = new Boolean[elements.length];
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
    public BooleMatrix(int rows, int columns, boolean[][] elements) throws DimensionMismatch{
        super.make(rows, columns);
        Boolean[][] hold = new Boolean[rows][columns];
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
    public BooleMatrix(int rows, int columns, Boolean[][] elements)
            throws NullMatrixException, DimensionMismatch{
            super.make(rows, columns);
            Matrix.copy2to2array(elements, this.elements); 
    }
    
    
    
    /**Copy constructor
     @param matrix The matrix to be copied*/
    public BooleMatrix(BooleMatrix matrix){
        super(matrix);
        this.elements = matrix.getElements();
    }
    
    /**Private getter for elements.
     @return Elements as array. */
    private Boolean[][] getElements(){
        return this.elements;
    }

    @Override
    public BooleMatrix copy() throws NullMatrixException, DimensionMismatch {

        Boolean[][] values = new Boolean[this.getRows()][this.getColumns()];
        for(int i = 0; i < this.getRows(); i++){
            System.arraycopy(this.elements, 0, values, 0, this.getColumns());
        }
        return new BooleMatrix(this.getRows(), this.getColumns(), values);
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BooleMatrix transpose() throws NullMatrixException, DimensionMismatch {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public BooleMatrix or(BooleMatrix rhs){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public BooleMatrix matrixMultiply(BooleMatrix rhs) throws NullMatrixException, DimensionMismatch{
        Boolean[][] outElements = new Boolean[this.getRows()][rhs.getColumns()];
            
        BinaryFunctionReturn<Boolean,Boolean> sum = (x,y) -> (x||y);
        BinaryFunctionReturn<Boolean,Boolean> product = (x,y) -> (x&&y);
            
        MatrixMultiplier<Boolean,Boolean> multiplier = new MatrixMultiplier<>(sum,product);
        multiplier.apply(this, rhs, outElements, false);
            
        return new BooleMatrix(this.getRows(), rhs.getColumns(), outElements);
    }
    
    
    @Override
    /**Method to swap matrix rows. 
     * @param alpha Index of first row
     * @param beta Index of second row
     */
    public void rowOp_swap(int alpha, int beta) {
        Boolean[] hold = new Boolean[this.getColumns()];
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
        boolean hold;
        
        for(int i = 0; i < this.getRows(); i++){
            hold = this.getElements()[i][alpha]; //Copy alpha to hold
            this.getElements()[i][alpha] = this.getElements()[i][beta]; //Copy beta to alpha
            this.getElements()[i][beta] = hold; //Copy hold to beta
        }
        
    }
}
