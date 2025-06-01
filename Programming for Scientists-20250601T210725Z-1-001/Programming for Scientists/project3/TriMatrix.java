/*
 * PROJECT III: TriMatrix.java
 *
 * This file contains a template for the class TriMatrix. Not all methods are
 * implemented. Make sure you have carefully read the project formulation
 * before starting to work on this file. You will also need to have completed
 * the Matrix class.
 *
 * Remember not to change the names, parameters or return types of any
 * variables in this file!
 *
 * The function of the methods and instance variables are outlined in the
 * comments directly above them.
 */

public class TriMatrix extends Matrix {
    /**
     * An array holding the diagonal elements of the matrix.
     */
    private double[] diag;

    /**
     * An array holding the upper-diagonal elements of the matrix.
     */
    private double[] upper;

    /**
     * An array holding the lower-diagonal elements of the matrix.
     */
    private double[] lower;
    
    /**
     * Constructor function: should initialise m and n through the Matrix
     * constructor and set up the data array.
     *
     * @param N  The dimension of the array.
     */
    public TriMatrix(int N) {
        super(N,N);
        
        if (N < 2) {
            throw new MatrixException("Dimensions of a trimatrix must be greater than 2!");
        }
        
        // Set up three arrays for the main, upper and lower diagonals.
        this.diag  = new double[N];
        this.upper = new double[N-1];
        this.lower = new double[N-1];
    }
    
    /**
     * Getter function: return the (i,j)'th entry of the matrix.
     *
     * @param i  The location in the first co-ordinate.
     * @param j  The location in the second co-ordinate.
     * @return   The (i,j)'th entry of the matrix.
     */
    public double getIJ(int i, int j) {
        if (i < 0 || i >= n || j < 0 || j >= n) {
            throw new MatrixException("Index is out of bounds!");
        } 
        
        // Compare i and j. If they are equal, entry is on the main diagonal.
        // If they differ by 1, then entry is on U or L diagonal. Else return 0.0.
        switch (i-j) {
            case  0: return this.diag[i];
            case -1: return this.upper[i];
            case  1: return this.lower[j];
            default: return 0.0;
        }
    }
    
    /**
     * Setter function: set the (i,j)'th entry of the data array.
     *
     * @param i    The location in the first co-ordinate.
     * @param j    The location in the second co-ordinate.
     * @param val  The value to set the (i,j)'th entry to.
     */
    public void setIJ(int i, int j, double val) {
        if (i < 0 || i >= n || j < 0 || j >= n) {
            throw new MatrixException("Index is out of bounds!");
        } 
        
        // Compare i and j. If they are equal, entry is on the main diagonal.
        // If they differ by 1, then entry is on U or L diagonal. Else cannot set.
        switch (i-j) {
            case  0: this.diag[i]  = val; break;
            case -1: this.upper[i] = val; break;
            case  1: this.lower[j] = val; break;
            default: throw new MatrixException("Cannot set an entry not on a tridiagonal on a trimatrix!");
        }
    }
    
    /**
     * Return the determinant of this matrix.
     *
     * @return The determinant of the matrix.
     */
    public double determinant() {
        // Decompose the trimatrix.
        TriMatrix triDet = this.decomp();
        
        // Determinant is the product of the entries on the main diagonal.
        double product = 1;
        for (int i = 0; i < n; i++) {
            product *= triDet.getIJ(i, i);
        }
		return product;		
    }
    
    /**
     * Returns the LU decomposition of this matrix. See the formulation for a
     * more detailed description.
     * 
     * @return The LU decomposition of this matrix.
     */
    public TriMatrix decomp() {
        TriMatrix a = new TriMatrix(n);
        
        // Decomposition algorithm for trimatrices.
        a.diag[0] = this.diag[0];
        for (int i = 1; i < n; i++) {
            a.diag[i] = this.diag[i] - this.lower[i-1] * this.upper[i-1] / a.diag[i-1];
            a.upper[i-1] = this.upper[i-1];
            a.lower[i-1] = this.lower[i-1] / a.diag[i-1];
        }
		return a;
    }

    /**
     * Add the matrix to another matrix A.
     *
     * @param A  The Matrix to add to this matrix.
     * @return   The sum of this matrix with the matrix A.
     */
    public Matrix add(Matrix A){
        if (n != A.n) {
            throw new MatrixException("Cannot add matrices of different dimensions!");
        }
        
        GeneralMatrix triAdd = new GeneralMatrix(n, n);
        
        // Loop through matrix and add corresponding indicies.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                triAdd.setIJ(i, j, this.getIJ(i, j) + A.getIJ(i, j));
            }
        }
		return triAdd;
    }
    
    /**
     * Multiply the matrix by another matrix A. This is a _left_ product,
     * i.e. if this matrix is called B then it calculates the product BA.
     *
     * @param A  The Matrix to multiply by.
     * @return   The product of this matrix with the matrix A.
     */
    public Matrix multiply(Matrix A) {
        if (n != A.n) {
            throw new MatrixException("Cannot multiply matrices of these dimensions!");
        }
        
        GeneralMatrix triMulti = new GeneralMatrix(n, n);
        
        // Naive algorithm for matrix multiplication.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < A.n; j++) {
                double sum = 0;
                for (int k = 0; k < n; k++) {
                    sum += this.getIJ(i, k) * A.getIJ(k, j);
                }
                triMulti.setIJ(i, j, sum);
            }
        }
        return triMulti;
    }
    
    /**
     * Multiply the matrix by a scalar.
     *
     * @param a  The scalar to multiply the matrix by.
     * @return   The product of this matrix with the scalar a.
     */
    public Matrix multiply(double a) {
        TriMatrix triScalar = new TriMatrix(n);
        
        // Loop through M, U and L diagonals and multiply each entry by 'a'.
        triScalar.setIJ(0, 0, a*this.getIJ(0, 0));
        for (int i = 1; i < n; i++) {
            triScalar.setIJ(i, i,   a*this.getIJ(i, i));
            triScalar.setIJ(i-1, i, a*this.getIJ(i-1, i));
            triScalar.setIJ(i, i-1, a*this.getIJ(i, i-1));
        }
		return triScalar;
    }

    /**
     * Returns a matrix containing random numbers which are uniformly
     * distributed between 0 and 1.
     *
     * @param n  The first dimension of the matrix.
     * @param m  The second dimension of the matrix.
     */
    public void random() {
        // Loop through M, U and L diagonals and fill entries with random numbers.
        this.setIJ(0, 0, Math.random());
        for (int i = 1; i < n; i++) {
            this.setIJ(i, i,   Math.random());
            this.setIJ(i-1, i, Math.random());
            this.setIJ(i, i-1, Math.random());
        }
    }
    
    public static void main(String[] args) {
        TriMatrix A = new TriMatrix(3);
        TriMatrix B = new TriMatrix(3);
        TriMatrix C = new TriMatrix(6);
        TriMatrix D = new TriMatrix(3);
        
        A.diag[0] = 1;
        A.diag[1] = 2;
        A.diag[2] = 3;
        A.lower = new double[] {-9,7};
        A.upper = new double[] {4,-5};
        
        B.setIJ(0, 0, 5);
        B.setIJ(1, 1, 8);
        B.setIJ(2, 2, 7);
        
        B.setIJ(0, 1, 4);
        B.setIJ(1, 2, 2);
        
        B.setIJ(1, 0, 6);
        B.setIJ(2, 1, 1);
        
        C.random();
        
        D.diag  = new double[] {1,2,3};
        D.lower = new double[] {4,5};
        D.upper = new double[] {7,8};
        
        System.out.println("A = " + A);
        System.out.println("det(A) = " + A.determinant());
        System.out.println("B = " + B);
        System.out.println("A + B = " + A.add(B));
        System.out.println("A.B = " + A.multiply(B));
        System.out.println("B.A = " + B.multiply(A));
        System.out.println("5.A = " + A.multiply(5));
        System.out.println("random(C) =" + C);
        System.out.println("decomp(D) = " + D.decomp());
        System.out.println("det(D) = " + D.determinant());
    }
}