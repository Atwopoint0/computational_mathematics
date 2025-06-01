/*
 * PROJECT III: Project3.java
 *
 * This file contains a template for the class Project3. None of methods are
 * implemented. Make sure you have carefully read the project formulation
 * before starting to work on this file. You will also need to have completed
 * the Matrix class, as well as GeneralMatrix and TriMatrix.
 *
 * Remember not to change the names, parameters or return types of any
 * variables in this file!
 *
 * The function of the methods and instance variables are outlined in the
 * comments directly above them.
 */

public class Project3 {
    /**
     * Calculates the variance of the distribution defined by the determinant
     * of a random matrix. See the formulation for a detailed description.
     *
     * @param m           The matrix object that will be filled with random
     *                    samples.
     * @param numSamples  The number of samples to generate when calculating 
     *                    the variance. 
     * @return            The variance of the distribution.
     */
    public static double matVariance(Matrix m, int numSamples) {
        // Typecast numSamples into a double.
        double N = (double) numSamples;
        
        // Generate numSamples amount of random m matrix.
        // Calculate variance from sum of det and sum of det squared. 
        double sum1 = 0; 
        double sum2 = 0;
        for (int i = 1; i <= numSamples; i++) {
            m.random();
            sum1 += m.determinant();
            sum2 += m.determinant() * m.determinant();
        }
		return (sum2/N) - ((sum1/N) * (sum1/N));  
	}
    
    /**
     * This function should calculate the variances of matrices for matrices
     * of size 2 <= n <= 50. See the formulation for more detail.
     */
    public static void main(String[] args) {
        final long startTime = System.currentTimeMillis();
        
        // Loop through dimensions from 2x2 to 50x50.
        // Generate 15,000 random genmat and 150,000 random trimat.
        for (int n = 2; n <= 50; n++) {
            GeneralMatrix matGen = new GeneralMatrix(n, n);
            TriMatrix matTri     = new TriMatrix(n);
            
            double var1 = matVariance(matGen, 15000);
            double var2 = matVariance(matTri, 150000);            
            System.out.println(String.format("%-6s%-25s%s", n, var1, var2));
        }
        
        final long endTime = System.currentTimeMillis();
        
        int minutes = (int) ((endTime - startTime) / 60000);
        int seconds = (int) (((endTime - startTime) / 1000) % 60);
        // System.out.println(minutes + " Minutes " + seconds + " Seconds ");
    }
}