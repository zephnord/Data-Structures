import java.util.ArrayList;
import java.util.Scanner;

/**
 * Contains the ListWork class which allows a user to input Integers into an array, and subsequently search or print that array
 * @author Zeph Nord
 * @version Lab00
 * @version CPE102-05
 * @version Winter 2017
 */
public class ListWork {
	public static void main(String[] args) {
		Integer[] array = new Integer[10];
		int counter = 0;
		int target;
		Scanner in = new Scanner(System.in);

		// Prompt the user to fill the array with Integers
		System.out.println("Enter a list of Integers, only the first 10 will be used");
		while (in.hasNext() && counter < 10) {
			try {
				array[counter] = in.nextInt();
				counter++;
			} catch (RuntimeException ex) {
				System.out.println("Not a valid Integer");
				in.nextLine();
			}
		}
		in.nextLine(); // clear scanner

		// Ask the user if they would like the search the array after
		// initializing it
		System.out.print("Would you like to search? (Y/N): ");

		if (in.nextLine().equals("Y")) {
			System.out.print("Enter target: ");
			if (in.hasNextInt()) {
				target = in.nextInt();
				System.out.print(search(array, target) ? (target + " Exists in list")
						: (target + " Does not exist in list") + "\n");
			} else
				System.out.println("Not a valid integer");
		}
		in.nextLine(); //Clear the scanner
		print(array);
		in.close();
	} //End Main

	/**
	 * This method searches the given array for the target values.
	 * 
	 * @param arr
	 *            the integer array to be searched
	 * @param target
	 *            the target value to compare against the search
	 * @return true if the target is found in the array, false otherwise
	 */
	public static <T> boolean search(T[] arr, T target) {
		for (int i = 0; i < arr.length; i++) {
			try {
				if (arr[i].equals(target))
					return true;
			} catch (NullPointerException ex) {
				if (target == null) {
					return true;
				}
			}
		}
		return false;
	} //End search

	/**
	 * Prints the elements of an Integer array in index order, one line per
	 * 
	 * @param arr
	 *            the Integer array to be printed
	 */
	public static <T> void print(T[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + "\n");
		}
	} //End print 
} //End ListWork Class
