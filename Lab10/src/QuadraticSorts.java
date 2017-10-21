/**
 * Contains sort methods as follows: 2 implementations of bubble sort, selection
 * sort, and insertion sort
 * 
 * @author Zeph Nord
 * @version Lab10
 * @version CPE103-03
 * @version Winter 2017
 */
public class QuadraticSorts {

	/**
	 * Sorts the specified array into ascending order using optimization of
	 * bubble sort to not comparing the n-1 items when running the loop for the
	 * n-th time
	 * 
	 * @param array
	 *            - the array to be sorted
	 */
	public static <T extends Comparable<? super T>> void bubbleSort1(T[] array) {
		int N = array.length;
		boolean swapped = false;

		for (int i = 1; i <= N - 1; i++) {
			swapped = false;
			for (int j = 1; j <= N - i; j++) {
				// if next previous element is greater than current, swap
				if (array[j - 1].compareTo(array[j]) > 0) {
					T tmp = array[j];
					array[j] = array[j - 1];
					array[j - 1] = tmp;
					swapped = true;
				}
			}
			if (swapped = false) {
				break;
			}
		}
	}

	/**
	 * 
	 * Sorts the specified array into ascending order using optimization of
	 * bubble sort to know that the last swap performed until the end of the array is sorted
	 * 
	 * @param array - the array to be sorted
	 */
	public static <T extends Comparable<? super T>> void bubbleSort2(T[] array) {
		int N = array.length;
		int newN = array.length;
		int newn = 0;

		for (int i = 1; i <= N - 1; i++) {
			newn = 0;
			for (int j = 1; j < newN; j++) {
				// if next previous element is greater than current, swap
				if (array[j - 1].compareTo(array[j]) > 0) {
					T tmp = array[j];
					array[j] = array[j - 1];
					array[j - 1] = tmp;
					newn = j;
				}
			}
			newN = newn;
			if (newN == 0) {
				break;
			}
		}
	}
	
	/**
	 * Sorts the specified array into ascending order using selection sort
	 * @param array - the array to be sorted
	 */
	public static <T extends Comparable<? super T>> void selectionSort(T[] array) {
		int N = array.length;
		
		//advance through the entire array
		for(int j = 0; j < N - 1; j++) {
			//assume first element is the min element
			int min = j;
			//Test elements after min to find the smallest
			for(int i = j + 1; i < N; i++) {
				//if element found is less, this is new min
				if(array[i].compareTo(array[min]) < 0) {
					min = i;
				}
			}
			if(min != j) {
				T tmp = array[min];
				array[min] = array[j];
				array[j] = tmp;
			}
		}
	}
	
	/**
	 * Sorts the specified array into ascending order using insertion sort
	 * @param array - the array to be sorted
	 */
	public static <T extends Comparable<? super T>> void insertionSort(T[] array){
		int N = array.length;
		
		for(int i = 1; i <= N - 1; i++) {
			//grab item for comparison
			T tmp = array[i];
			//j will start at one item previous to tmp
			int j = i - 1;
			//traverse down the subarray, swapping if previous value is greater than next
			while(j >= 0 && array[j].compareTo(tmp) > 0) {
				array[j + 1] = array[j];
				j--;
			}
			//insert the last element
			array[j + 1] = tmp;
		}
	}

}
