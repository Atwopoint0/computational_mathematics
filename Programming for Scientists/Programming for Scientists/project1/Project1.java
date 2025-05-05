/*
 * PROJECT I: Project1.java
 *
 * As in project 0, this file - and the others you downloaded - form a
 * template which should be modified to be fully functional.
 *
 * This file is the *last* file you should implement, as it depends on both
 * Point and Circle. Thus your tasks are to:
 *
 * 1) Make sure you have carefully read the project formulation. It contains
 *    the descriptions of all of the functions and variables below.
 * 2) Write the class Point.
 * 3) Write the class Circle
 * 4) Write this class, Project1. The Results() method will perform the tasks
 *    laid out in the project formulation.
 */
public class Project1 {
    // -----------------------------------------------------------------------
    // Do not modify the names of the variables below! This is where you will
    // store the results generated in the Results() function.
    // -----------------------------------------------------------------------
    public int    circleCounter; // Number of non-singular circles in the file.
    public int    posFirstLast;  // Indicates whether the first and last
                                 // circles overlap or not.
    public double maxArea;       // Area of the largest circle (by area).
    public double minArea;       // Area of the smallest circle (by area).
    public double averageArea;   // Average area of the circles.
    public double stdArea;       // Standard deviation of area of the circles.
    public double medArea;       // Median of the area.
    public int    stamp=189375;
    // -----------------------------------------------------------------------
    // You may implement - but *not* change the names or parameters of - the
    // functions below.
    // -----------------------------------------------------------------------

    /**
     * Default constructor for Project1. You should leave it empty.
     */
    public Project1() {
        // This method is complete.
    }
    
    // Bubble-sort method for an array.
    public void bubblesort(double[] arr) {
        boolean doneSwap = true;
        while (doneSwap == true) {
            doneSwap = false;
            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i+1] < arr[i]) {
                    double tmp = arr[i+1];
                    arr[i+1]   = arr[i];
                    arr[i]     = tmp;
                    doneSwap   = true;
                }
            }
        }
    }

    /**
     * Results function. It should open the file called FileName (using
     * MaInput), and from it generate the statistics outlined in the project
     * formulation. These are then placed in the instance variables above.
     *
     * @param fileName  The name of the file containing the circle data.
     */
    public void results(String fileName) {
        
        MaInput file = new MaInput(fileName);
        
        double x, y, r, area, totalArea, totalSqrdArea, variance;
        Circle M, N;
        circleCounter = posFirstLast = 0;
        maxArea = Double.MIN_VALUE;
        minArea = Double.MAX_VALUE;
        averageArea = stdArea = medArea = 0;
        totalArea = totalSqrdArea = 0;
        M = N = new Circle(0,0,0);
        
        while (!file.atEOF()) {
            // Reads through data and stores important values.
            x = file.readDouble();
            y = file.readDouble();
            r = file.readDouble();

	    if (Math.abs(r) >= Point.GEOMTOL) {            
                area          = Math.PI * r * r;
                totalArea     = totalArea + area;
                totalSqrdArea = totalSqrdArea + Math.pow(area, 2);
            
                // Increments by 1 for every circle.
                circleCounter++;
            
                // Min and max area.
                if (area > maxArea) {
                    maxArea = area;
                }
                if (area < minArea) {
                    minArea = area;
                }
	    }
        }
        
        // Average and standard deviation of the area.
        averageArea = totalArea / circleCounter;
        variance    = totalSqrdArea / circleCounter - Math.pow(averageArea, 2);
        stdArea     = Math.sqrt(variance);
        
        MaInput file1 = new MaInput(fileName);
        double[] areaArray = new double[circleCounter];
	int compareCounter = 0;
        
        for (int i = 0; i < circleCounter; i++) { 
            // Store area of circle in array.
            x = file1.readDouble();
            y = file1.readDouble();
            r = file1.readDouble();
            
	    if (Math.abs(r) >= Point.GEOMTOL) {
		compareCounter++;
            	area = Math.PI * r * r;
            	areaArray[i] = area;
            
           	 // Creates circles from first and last data.
            	if (compareCounter == 1) {
                    M = new Circle(x,y,r);
            	}
            	if (compareCounter == circleCounter) {
               	    N = new Circle(x,y,r);
            	}
	    }
        }
        
        // Position parameter for first and last circle.
        posFirstLast = M.overlap(N);
        
        // Bubble sort circle data by size.
        bubblesort(areaArray);
        // Median value for odd or even case.
        if (circleCounter % 2 == 1) {
            medArea = areaArray[(circleCounter-1)/2];
        } else if (circleCounter % 2 == 0) {
            medArea = (areaArray[circleCounter/2-1] + areaArray[circleCounter/2]) / 2;
        }
    }
  
    // =======================================================
    // Tester - tests methods defined in this class
    // =======================================================

    /**
     * Your tester function should go here (see week 14 lecture notes if
     * you're confused). It is not tested by BOSS, but you will receive extra
     * credit if it is implemented in a sensible fashion.
     */
    public static void main(String args[]) {
        Project1 data = new Project1();
        MaInput Ma = new MaInput();
        String name = Ma.readString("Enter file name of data:");
        data.results(name);
        
        System.out.println("Circle counter: " +data.circleCounter);
        System.out.println("posFirstLast:   " +data.posFirstLast);
        System.out.println("Max area:       " +data.maxArea);
        System.out.println("Min area:       " +data.minArea);
        System.out.println("Avg area:       " +data.averageArea);
        System.out.println("Std area:       " +data.stdArea);
        System.out.println("Med area:       " +data.medArea);
    }
}
