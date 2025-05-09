/*
 * PROJECT III: Matrix.java
 *
 * This file contains a template for the class Matrix. Not all methods are
 * implemented. Make sure you have carefully read the project formulation
 * before starting to work on this file.
 *
 * Some of the methods here are abstract. That means that they must be
 * implemented by their subclasses. If you're not sure about abstract classes,
 * you should consult the lecture notes for more information.
 *
 * Remember not to change the names, parameters or return types of any
 * variables in this file!
 *
 * The function of the methods and instance variables are outlined in the
 * comments directly above them.
 */

public abstract class Matrix {
    /**
     * Two variables to describe the dimensions of the Matrix.
     */
    protected int m, n;

    /**
     * Constructor function. This is protected since abstract classes cannot
     * be instantiated anyway. Subclasses should call this function from their
     * constructors to set m and n.
     *
     * @param M  The first dimension of the matrix.
     * @param N  The second dimension of the matrix.
     */
    protected Matrix(int M, int N) { 
        m = M; n = N; 
    }
    
    /**
     * Returns a String representation of the Matrix using the getIJ getter
     * function. You should use MaF.dF to format the double numbers to a
     * sensible number of decimal places and place the numbers in columns.
     *
     * @return A String representation of the Matrix.
     */
    public String toString() {
        String matStr = "\n";
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matStr += String.format("%-10.3g", this.getIJ(i,j));
            }
            matStr += "\n";
        }
        return matStr;
    }
    
    /**
     * Getter function: return the (i,j)'th entry of the matrix.
     *
     * @param i  The location in the first co-ordinate.
     * @param j  The location in the second co-ordinate.
     * @return   The (i,j)'th entry of the matrix.
     */
    public abstract double getIJ(int i, int j);

    /**
     * Setter function: set the (i,j)'th entry of the data array.
     *
     * @param i    The location in the first co-ordinate.
     * @param j    The location in the second co-ordinate.
     * @param val  The value to set the (i,j)'th entry to.
     */
    public abstract void   setIJ(int i, int j, double val);
    
    /**
     * Return the determinant of this matrix.
     *
     * @return The determinant of the matrix.
     */
    public abstract double determinant();

    /**
     * Add the matrix to another matrix A.
     *
     * @param A  The Matrix to add to this matrix.
     * @return   The sum of this matrix with the matrix A.
     */
    public abstract Matrix add(Matrix A);

    /**
     * Multiply the matrix by another matrix A. This is a _left_ product,
     * i.e. if this matrix is called B then it calculates the product BA.
     *
     * @param A  The Matrix to multiply by.
     * @return   The product of this matrix with the matrix A.
     */
    public abstract Matrix multiply(Matrix A);

    /**
     * Multiply the matrix by a scalar.
     *
     * @param a  The scalar to multiply the matrix by.
     * @return   The product of this matrix with the scalar a.
     */
    public abstract Matrix multiply(double a);
    
    /**
     * Fills the matrix with random numbers which are uniformly distributed
     * between 0 and 1.
     */
    public abstract void random();
}