import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

/**
 * Contains the Junit tests for the BinaryHeapUtilites class
 * @author Zeph Nord
 * @version Lab05
 * @version CPE 103-03
 * @version Winter 2017
 */
public class BinaryHeapUtilitiesTests {
	@Test
	public void testHeapHeight() {
		int[] arr = new int[10];
		assertEquals(3, BinaryHeapUtilities.height(arr.length));
		assertEquals(4, BinaryHeapUtilities.height(31));
		assertEquals(3, BinaryHeapUtilities.height(15));
		assertEquals(4, BinaryHeapUtilities.height(16));
		assertEquals(0, BinaryHeapUtilities.height(1));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNonPositiveSize() {
		BinaryHeapUtilities.height(0);
		BinaryHeapUtilities.height(-1);
	}

	@Test
	public void test07_isHeapGood() {
		Integer[] heap = new Integer[] { 9999, -5, -9999, -9999 };
		assertTrue(BinaryHeapUtilities.isHeap(heap, 1));
	}

	@Test
	public void test01_isHeapGood() {
		Integer[] heap = new Integer[] { 0, 7, 20, 7, 28, 72, 42, 80, 41, 41, 98, 74, 43 };
		assertTrue(BinaryHeapUtilities.isHeap(heap, 12));
	}
	
	@Test
	public void test02_isHeapGood() {
		Integer[] heap = new Integer[] { 0, 10, 77, 90, 0, 0, -9, 90, 41, 41, 98, 74, 43 };
		assertFalse(BinaryHeapUtilities.isHeap(heap, 7));
	}
	
	@Test
	public void test03_isHeapGood() {
		Integer[] heap = new Integer[] { 0, 10, 77, 90, 100, 99, -9, 90, 41, 41, 98, 74, 43 };
		assertTrue(BinaryHeapUtilities.isHeap(heap, 5));
	}

	@Test
	public void test52_parentOfGoodTest() {
		Integer[] heap = new Integer[] { 9999, -5, 3, -2, 7, 4, 9, -1, 8, -9999, -9999 };

		assertEquals((Integer) (-5), BinaryHeapUtilities.parentOf(2, heap, 8));
	}

	@Test
	public void test53_leftChildOfGoodTest() {
		// Note: Extra two at end to verify student is not using .length!
		Integer[] heap = new Integer[] { 9999, -5, 3, -2, 7, 4, 9, -1, 8 };

		assertEquals((Integer) 9, BinaryHeapUtilities.leftChildOf(3, heap, 8));
	}

	@Test
	public void test54_rightChildOfGoodTest() {
		// Note: Extra two at end to verify student is not using .length!
		Integer[] heap = new Integer[] { 9999, -5, 3, -2, 7, 4, 9, -1, 8 };

		assertEquals((Integer) (-1), BinaryHeapUtilities.rightChildOf(3, heap, 8));
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void test01_parentOfIllegalIndex() {
		Integer[] heap = new Integer[] { 9999, -5, 3, -2, 7, 4, 9, -1, 8 };
		BinaryHeapUtilities.parentOf(0, heap, 8);
	}
	
	@Test(expected = NoSuchElementException.class)
	public void test01_parentOfRoot() {
		Integer[] heap = new Integer[] { 9999, -5, 3, -2, 7, 4, 9, -1, 8 };
		BinaryHeapUtilities.parentOf(1, heap, 8);
	}
	
	@Test(expected = NoSuchElementException.class)
	public void test01_noRightChild() {
		Integer[] heap = new Integer[] { 9999, 99, 80, 88, 70, 75, 90};
		assertEquals((Integer) 0, BinaryHeapUtilities.rightChildOf(3, heap, 6));
	}
	
	@Test
	public void test02_rightChildOf() {
		Integer[] heap = new Integer[] { 9999, 60, 70, 78, 80, 85, 90, 99};
		assertEquals((Integer) 99, BinaryHeapUtilities.rightChildOf(3, heap, 7));
	}
	
	@Test
	public void test01_leftChildOf() {
		Integer[] heap = new Integer[] { 9999, 60, 70, 78, 80, 85, 90, 99};
		assertEquals((Integer) 90, BinaryHeapUtilities.leftChildOf(3, heap, 7));
	}
	
	@Test
	public void test_isHeapAllEqual() {
		Integer[] heap = new Integer[] { 9999, 60, 60, 60, 60, 60, 60, 60};
		assertTrue(BinaryHeapUtilities.isHeap(heap, 7));
		assertEquals((Integer) 60, BinaryHeapUtilities.leftChildOf(3, heap, 7));
	}

}
