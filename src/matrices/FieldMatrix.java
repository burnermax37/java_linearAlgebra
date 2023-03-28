/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices;

/**
 * This class is for matrices that store numeric values. 
 * @author Maxwell Burner
 * @param <V> Value type
 */
public abstract class FieldMatrix<V> extends Matrix<V> {
    
    /**Constructor with no parameters. Only kept because it is used implicitly in constructors
     of sub classes. */
    public FieldMatrix(){
        super();
    }
    
    /**Constructor with dimensions only.
     * @param rows
     * @param columns
     * @throws matrices.NullMatrixException */
    public FieldMatrix(int rows, int columns) throws NullMatrixException{
        super(rows, columns);
    }
    

    
    /**Copy constructor. 
     @param matrix The matrix being copied. */
    public FieldMatrix(FieldMatrix matrix){
        super(matrix);
    }
    
    
        /**Method to check if matrix can be multiplied by another.
     * @param rhs Matrix with which this matrix will be multiplied
     * @return True if both matrices are non-null, and have appropriate dimensions
     * @throws matrices.NullMatrixException
     * @throws matrices.DimensionMismatch */
    @Override
    protected boolean matrixMultTest(Matrix rhs) throws NullMatrixException, DimensionMismatch{
        return super.matrixMultTest(rhs);
    }

    


    
    /**Method to multiply matrix by a scalar. 
     @param scalar The value by which the elements of the matrix are multiplied
     @return A FieldMatrix (or subclass thereof) whose values are those of the
     original, multiplied by the scalar.
     * @throws matrices.NullMatrixException*/
    public abstract FieldMatrix scalarMultiplication(double scalar) throws NullMatrixException;
    
    
    /**Method to multiply matrix by a scalar. 
     @param scalar The value by which the elements of the matrix are multiplied
     @return A FieldMatrix (or subclass thereof) whose values are those of the
     original, multiplied by the scalar.
     * @throws matrices.NullMatrixException*/
    public abstract FieldMatrix scalarMultiplication(int scalar) throws NullMatrixException;
    
    /**Method to divide matrix by a scalar. 
    @param scalar The value by which the elements of the matrix are divided.
    @return A FieldMatrix (or subclass thereof) whose values are those of the
    original, divided by the scalar.
     * @throws matrices.NullMatrixException*/
    public abstract FieldMatrix scalarDivision(double scalar) throws NullMatrixException;
    
    /**Method to divide matrix by a scalar. 
    @param scalar The value by which the elements of the matrix are divided.
    @return A FieldMatrix (or subclass thereof) whose values are those of the
    original, divided by the scalar.
     * @throws matrices.NullMatrixException*/
    public abstract FieldMatrix scalarDivision(int scalar) throws NullMatrixException;
    
    
    /**Abstract method to multiply one row of a matrix by an integer. 
     @param index Index of row being multiplied
     * @param scalar Integer by which row is multiplied
     */
    public abstract void rowOp_multiply(int index, int scalar);
    
    /**Abstract method to multiply one column of a matrix by an integer. 
     @param index Index of column being multiplied
     * @param scalar Integer by which column is multiplied
     */
    public abstract void colOp_multiply(int index, int scalar);

    
}
