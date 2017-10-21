import java.util.NoSuchElementException;

/**
 * Contains the basic implementation of a Binary heap and its associates operations
 * @author Zeph Nord
 * @version Lab05
 * @version CPE 103-03
 * @version Winter 2017
 */
public class BinaryHeapUtilities {

	/**
	 * Calculates the height of the heap given it's size. This will involve a
	 * log(base2) operation
	 * 
	 * @param size
	 *            - the number of elements in the heap
	 * @return the height of the heap
	 * @throws IllegalArgumentException
	 *             - if the specified size is non-positive
	 */
	public static int height(int size) throws IllegalArgumentException {
		if (size <= 0) {
			throw new IllegalArgumentException();
		}
		
		return (int) (Math.log(size)/Math.log(2));
	}

	/**
	 * Checks to see if data in the specified array meets the heap order property
	 * for binary heaps. the heap order property requires that all children must
	 * be greater than or equal to their parent. A precondition (requirement the
	 * caller must meet) is that the first value is at index 1. Note that an
	 * empty heap will have a size of zero and a non-empty heaps's size is also
	 * its last valid index - a bit different than a traditional array.
	 * 
	 * @param heap - the array of elements, starting at index 1 to check
	 * @param size - the number of elements in the array
	 * @return true if the size is positive AND the array of elements meets the order property required for binary heaps; otherwise false
	 */
	public static <T extends Comparable<? super T>> boolean isHeap(T[] heap, int size) {
		//check if the first index is unused
		/*
		if(heap[0] != null) {
			return false;
		}*/
		//ensure the heap has a length of at least 1
		if(!(size > 0)) {
			return false;
		}
		//any heap of size one is a valid heap
		if(size == 1) {
			return true;
		}
		
		//ensure that heap follows heap properties
		for(int i = 1; i < size / 2; i++) {
			//check if left child is greater than or equal to parent
			if(heap[2 * i].compareTo(heap[i]) == -1) {
				return false;
			}
			//check if right child is greater than or equal to parent
			if(heap[2 * i + 1].compareTo(heap[i]) == -1) {
				return false;
			}			
		}
		for(int i = 2; i < size; i++) {
			//check if parent is less than current child
			if(heap[i / 2].compareTo(heap[i]) == 1) {
				return false;
			}
		}
		return true;		
	}
	
	/**
	 * Returns the parent of the specified element
	 * @param index - the index of the element whose parent is desired
	 * @param heap - the heap containing the elements
	 * @param size - the number of elements in the heap
	 * @return the parent of the specified element
	 * @throws IndexOutOfBoundsException - if the index is non-positive or greater than size (should be first check
	 * @throws NoSuchElementException - if the specified element does not have a parent (second check)
	 * @throws IllegalArgumentException - if the array is not a heap (slowest check)
	 */
	public static <T extends Comparable<? super T>> T parentOf(int index, T[] heap, int size) {
		if(index <= 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		if(index == 1) {
			throw new NoSuchElementException();
		}
		if(!isHeap(heap, size)) {
			throw new IllegalArgumentException();
		}

		return heap[index / 2];
	}
	
	/**
	 * Returns the left-child of the specified element
	 * @param index - the index of the element whose parent is desired
	 * @param heap - the heap containing the elements
	 * @param size - the number of elements in the heap
	 * @return the left-child of the specified element
	 * @throws IndexOutOfBoundsException - if the index is non-positive or greater than size (should be first check
	 * @throws NoSuchElementException - if the specified element does not have a left-child (second check)
	 * @throws IllegalArgumentException - if the array is not a heap (slowest check)
	 */
	public static <T extends Comparable<? super T>> T leftChildOf(int index, T[] heap, int size) {
		if(index <= 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		if(index * 2 > size || heap[index].equals(null)) {
			throw new NoSuchElementException();
		}
		if(!isHeap(heap, size)) {
			throw new IllegalArgumentException();
		}

		return heap[index * 2];
	}
	
	/**
	 * Returns the right-child of the specified element
	 * @param index - the index of the element whose parent is desired
	 * @param heap - the heap containing the elements
	 * @param size - the number of elements in the heap
	 * @return the right-child of the specified element
	 * @throws IndexOutOfBoundsException - if the index is non-positive or greater than size (should be first check
	 * @throws NoSuchElementException - if the specified element does not have a right-child (second check)
	 * @throws IllegalArgumentException - if the array is not a heap (slowest check)
	 */
	public static <T extends Comparable<? super T>> T rightChildOf(int index, T[] heap, int size) {
		if(index <= 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		if(index * 2 + 1 > size || heap[index].equals(null)) {
			throw new NoSuchElementException();
		}
		if(!isHeap(heap, size)) {
			throw new IllegalArgumentException();
		}

		return heap[index * 2 + 1];
	}
}
