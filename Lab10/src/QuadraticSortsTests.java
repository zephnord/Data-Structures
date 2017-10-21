import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

/**
 * Test cases for the QuadraticSorts class
 * @author Zeph Nord
 * @version Lab10
 * @version CPE103-03
 * @version Winter 2017
 */
public class QuadraticSortsTests {
	
	Integer seed = 5000;
	Random random = new Random(seed);
	
	@Test
	public void test01_bubbleSort1Basic() {
		Integer[] unsorted  = {15, 89, 75, 5, -9, 65, 25, 7};
		Integer[] sorted = {-9, 5, 7, 15, 25, 65, 75, 89};
		QuadraticSorts.bubbleSort1(unsorted);
		for(int i = 0; i < unsorted.length; i++) {
			assertEquals(sorted[i], unsorted[i]);
		}
	}
	
	@Test
	public void test02_bubbleSort1Advanced() {
		Integer[] advancedUnsorted = new Integer[5000];
		Integer[] advancedSorted = new Integer[5000];
		
		for(int i = 0; i < advancedUnsorted.length; i++) {
			advancedUnsorted[i] = random.nextInt(100000);			
		}
		for(int i = 0; i < advancedUnsorted.length; i++) {
			advancedSorted[i] = advancedUnsorted[i];
		}
		
		Arrays.sort(advancedSorted);
		QuadraticSorts.bubbleSort1(advancedUnsorted);
		
		for(int i = 0; i < advancedUnsorted.length; i++) {
			assertEquals(advancedSorted[i], advancedUnsorted[i]);
		}		
	}
	
	@Test
	public void test03_bubbleSort2Basic() {
		Integer[] unsorted  = {15, 89, 75, 5, -9, 65, 25, 7};
		Integer[] sorted = {-9, 5, 7, 15, 25, 65, 75, 89};
		QuadraticSorts.bubbleSort2(unsorted);
		for(int i = 0; i < unsorted.length; i++) {
			assertEquals(sorted[i], unsorted[i]);
		}
	}
	
	@Test
	public void test04_bubbleSort2Advanced() {
		Integer[] advancedUnsorted = new Integer[5000];
		Integer[] advancedSorted = new Integer[5000];
		
		for(int i = 0; i < advancedUnsorted.length; i++) {
			advancedUnsorted[i] = random.nextInt(100000);			
		}
		for(int i = 0; i < advancedUnsorted.length; i++) {
			advancedSorted[i] = advancedUnsorted[i];
		}
		
		Arrays.sort(advancedSorted);
		QuadraticSorts.bubbleSort2(advancedUnsorted);
		
		for(int i = 0; i < advancedUnsorted.length; i++) {
			assertEquals(advancedSorted[i], advancedUnsorted[i]);
		}		
	}
	
	
	@Test
	public void test05_selectionSortBasic() {
		Integer[] unsorted  = {15, 89, 75, 5, -9, 65, 25, 7};
		Integer[] sorted = {-9, 5, 7, 15, 25, 65, 75, 89};
		QuadraticSorts.selectionSort(unsorted);
		for(int i = 0; i < unsorted.length; i++) {
			assertEquals(sorted[i], unsorted[i]);
		}
	}
	
	@Test
	public void test06_selectionSortAdvanced() {
		Integer[] advancedUnsorted = new Integer[5000];
		Integer[] advancedSorted = new Integer[5000];
		
		for(int i = 0; i < advancedUnsorted.length; i++) {
			advancedUnsorted[i] = random.nextInt(100000);			
		}
		for(int i = 0; i < advancedUnsorted.length; i++) {
			advancedSorted[i] = advancedUnsorted[i];
		}
		
		Arrays.sort(advancedSorted);
		QuadraticSorts.selectionSort(advancedUnsorted);
		
		for(int i = 0; i < advancedUnsorted.length; i++) {
			assertEquals(advancedSorted[i], advancedUnsorted[i]);
		}		
	}
	
	@Test
	public void test07_insertionSortBasic() {
		Integer[] unsorted  = {15, 89, 75, 5, -9, 65, 25, 7};
		Integer[] sorted = {-9, 5, 7, 15, 25, 65, 75, 89};
		QuadraticSorts.insertionSort(unsorted);
		for(int i = 0; i < unsorted.length; i++) {
			assertEquals(sorted[i], unsorted[i]);
		}
	}
	
	@Test
	public void test08_insertionSortAdvanced() {
		Integer[] advancedUnsorted = new Integer[5000];
		Integer[] advancedSorted = new Integer[5000];
		
		for(int i = 0; i < advancedUnsorted.length; i++) {
			advancedUnsorted[i] = random.nextInt(100000);			
		}
		for(int i = 0; i < advancedUnsorted.length; i++) {
			advancedSorted[i] = advancedUnsorted[i];
		}
		
		Arrays.sort(advancedSorted);
		QuadraticSorts.insertionSort(advancedUnsorted);
		
		for(int i = 0; i < advancedUnsorted.length; i++) {
			assertEquals(advancedSorted[i], advancedUnsorted[i]);
		}		
	}

}
