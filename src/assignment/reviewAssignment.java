package assignment;
import java.util.*;
import java.io.*;


public class reviewAssignment {
	public static void main(String[] args) throws Exception{
		/**
		 * main program will process the a text file that obtains a series of numbers
		 * print their values in organized format and find their average
		 */
		// inputGenerator(); 
		
		Scanner scnr = new Scanner(System.in); 
		Scanner input = new Scanner(new File("input.txt")); 
		PrintWriter output = new PrintWriter("output.txt"); 
		// Note that the main program reads this value, not one of the methods.
		int size = Integer.parseInt(input.nextLine());
		
		// will work for any size arrays up to 50 or so
		if(size>50) {
			inputGenerator(); 
			size = Integer.parseInt(input.nextLine());
		}
		double[] first = new double[size]; 
		
		//1. read size items into an array 
		readData(size, first, input); 
		//2. print the values stored in the first array.  
		output.println("here is the original array"); 
		printArray(size, first, output); 
		//3. find the average of the size values 
		double avg = findAverage(size, first); 
		output.printf("here is the average of this array: %6.2f\n", avg); 
		//4. construct a new array called second from the original array first.
		double[] second = howFarAway(size, avg, first); 
		//5. find the values stored in the new array made in part 4.  
		output.println("\n\n--------\nhere is the new array"); 
		printArray(size, second, output); 
		//6.  find the average of the new array made in part 4.
		avg = findAverage(size, second); 
		output.printf("here is the average of this array: %6.2f\n", avg); 
		//OPTIONAL. find the value which is closest and furtherest from the average. 
		findCF(size, first, second, output);
		
		scnr.close(); 
		input.close();
		output.close();
	}
	
	/**
	 * For reference purpose
	 * this method was used to generate an input.txt file
	 * @throws IOException
	 */
	public static void inputGenerator() throws IOException{
		PrintWriter input = new PrintWriter(new File("input.txt")); 
		// Use a parameter value of 12-15 
		int size = (int)(Math.random()*(15-12+1))+12;
		input.println(size); 
		for(int i=0; i<size; i++) {
			input.println((Math.random()*(500+500+1))-500); 
		}
		input.close();
	}

	/**
	 * 1. 
	 * readData will read data into an double array
	 * @param n			size of array
	 * @param numbers	double array to store all values
	 * @param input		txt file that obtains all original data
	 */
	public static void readData(int n, double[] numbers, Scanner input) {
		for(int i=0; i<n; i++) {
			numbers[i] = Double.parseDouble(input.nextLine());
		}	
	}
	/**
	 * 2.
	 * printArray will print the q values stored in the array numb
	 * spaced neatly 5 across a line, with two decimal places for each value
	 * @param q			size of array
	 * @param numb		double array to store all values
	 * @param output	output.txt file that displays all output
	 */
	public static void printArray(int q, double[] numb, PrintWriter output) {
		for(int i=0; i<q; i++) {
			output.printf("%+07.2f", numb[i]);
			if((i+1)%5==0 || i+1==q) {
				output.println(); 
			}else {
				output.print("\t\t");
			}
		}
	}
	/**
	 * 3.
	 * findAverage will find and return the average of the first k elements of the p array
	 * @param k		size of array
	 * @param p		double array to store all values
	 * @return		average value by dividing sum by array size
	 */
	public static double findAverage(int k, double[] p) {
		double sum = 0.0;
		for(int i=0; i<k; i++) {
			sum += p[i]; 
		}
		return sum/k; 
	}
	/**
	 * 4.
	 * howFarAway will equal the difference between the original average 
	 * and the corresponding element in the original array r
	 * will return the new array s instead of passing it as a parameter
	 * @param m		size of array
	 * @param avg	average of the array as calculated in the average method
	 * @param r		double array to store all values
	 * @return		new array that stores all differences
	 */
	public static double[] howFarAway(int m, double avg, double[] r) {
		double[] s = new double[m];
		for(int i=0; i<m; i++) {
			s[i] = avg - r[i];
		}
		return s; 
	}
	/**
	 * OPTIONAL
	 * findCF will find the value which is closest and furtherest from the average
	 * @param n			size of array
	 * @param val		double array to store all values
	 * @param diff		double array to store all differences
	 * @param output	output.txt file that displays all output
	 */
	public static void findCF(int n, double[] val, double[] diff, PrintWriter output) {
		int cIndex = 0; 
		int fIndex = 0; 
		for(int i = 1; i<n; i++) {
			if(Math.abs(diff[i]) < Math.abs(diff[cIndex])) {
				cIndex = i;
			}else if(Math.abs(diff[i]) > Math.abs(diff[fIndex])) {
				fIndex = i;
			}
		}
		
		output.printf("\n%+6.2f is the cloest value from the average: %+6.2f\n", val[cIndex], diff[cIndex]);
		output.printf("%+6.2f is the furtherest value from the average: %+6.2f\n", val[fIndex], diff[fIndex]);
	}
}
