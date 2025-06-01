/*
 * PROJECT II: Polynomial.java
 *
 * This file contains a template for the class Polynomial. Not all methods are
 * implemented. Make sure you have carefully read the project formulation
 * before starting to work on this file.
 *
 * This class is designed to use Complex in order to represent polynomials
 * with complex co-efficients. It provides very basic functionality and there
 * are very few methods to implement! The project formulation contains a
 * complete description.
 *
 * Remember not to change the names, parameters or return types of any
 * variables in this file! You should also test this class using the main()
 * function.
 *
 * The function of the methods and instance variables are outlined in the
 * comments directly above them.
 */

import java.util.Arrays;

class Polynomial {
    /**
     * An array storing the complex co-efficients of the polynomial.
     */
    Complex[] coeff;

    // ========================================================
    // Constructor functions.
    // ========================================================

    /**
     * General constructor: assigns this polynomial a given set of
     * co-efficients.
     *
     * @param coeff  The co-efficients to use for this polynomial.
     */
    public Polynomial(Complex[] coeff) {
	// Break loop after decrementing to non-zero coefficient.
	// Copy the array up to that point and use as coeff.
	int j = coeff.length;
	for (int i = coeff.length - 1; i > 0; i--) {
	    if (coeff[i].abs() != 0) {
		break;
	    }
	    j--;
	}
	this.coeff = Arrays.copyOfRange(coeff, 0, j);
    }
    
    /**
     * Default constructor: sets the Polynomial to the zero polynomial.
     */
    public Polynomial() {
	// Fills array with a zero.
	Complex[] zero = {new Complex()};
	this.coeff = zero;
    }

    // ========================================================
    // Operations and functions with polynomials.
    // ========================================================

    /**
     * Create a string representation of the polynomial.
     *
     * For example: (1.0+1.0i)+(1.0+2.0i)X+(1.0+3.0i)X^2
     */
    public String toString() {
	// Always print constant. Print x coefficient if non-zero.
	String coeffStr = "(" +coeff[0].toString()+ ")";
        if (coeff.length > 1 && coeff[1].abs() != 0) {
            coeffStr = coeffStr + "+(" +coeff[1].toString()+ ")z";
        }

	// Loop to print all non-zero terms.
	for (int i = 2; i < coeff.length; i++) {
	    if (coeff[i].abs() != 0) {
		coeffStr = coeffStr + "+(" +coeff[i].toString()+ ")z^" + i;
	    }
	}
	return coeffStr;
    }

    /**
     * Returns the degree of this polynomial.
     */
    public int degree() {
	return coeff.length - 1;
    }

    /**
     * Evaluates the polynomial at a given point z.
     *
     * @param z  The point at which to evaluate the polynomial
     * @return   The complex number P(z).
     */
    public Complex evaluate(Complex z) {
	// Recursive loop using given algorithm.
	Complex ans = coeff[coeff.length - 1];
	for (int i = degree() - 1; i >= 0; i--) {
	    ans = coeff[i].add(z.multiply(ans));
	}
	return ans;
    }
    
    /**
     * Calculate and returns the derivative of this polynomial.
     *
     * @return The derivative of this polynomial.
     */
    public Polynomial derivative() {	
	// If constant or zero polynomial, return zero derivative.
	if (degree() == 0) {
	    return new Polynomial();
	}

	// Create new array with derivative coefficients.
        Complex[] coeffDeriv = new Complex[degree()];
        for (int i = 1; i < coeff.length; i++) {
            Complex pow = new Complex(i, 0);
	    coeffDeriv[i - 1] = coeff[i].multiply(pow);
	}
	return new Polynomial(coeffDeriv);
    }
    
    // ========================================================
    // Tester function.
    // ========================================================

    public static void main(String[] args) {
	Complex a, b, c, d, e, f, Z;
	a = new Complex(0,1);
	b = new Complex(0,0);
	c = new Complex(3,2);
	d = new Complex(9,0);
	e = new Complex(0,2);
	f = new Complex(0,0);
	Z = new Complex(9,2);
	Complex[] nums = {a,b,c,d,e,f};
	Polynomial P = new Polynomial(nums);
	Polynomial Q = new Polynomial();

	System.out.println("Polynomial =              " +P);
	System.out.println("Zero poly =               " +Q);

	System.out.println("Degree =                  " +P.degree());
	System.out.println("Degree of zero poly =     " +Q.degree());

	System.out.println("Derivative =              " +P.derivative());
	System.out.println("Derivative of zero poly = " +Q.derivative());

	System.out.println("Complex number Z =        " +Z);
	System.out.println("Evaluate at Z =           " +P.evaluate(Z));
    }
}
