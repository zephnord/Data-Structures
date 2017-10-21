
/**
 * JUnit tests for Binary Search Tree
 *
 * @author   Luke Thompson
 * @version lab 6
 */
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BSTTests {

	@Test
	public void test1_Insert_Contains() {
		BST<Integer> bst = new BST<>();

		for (Integer i = 0; i < 20; i++) {
			bst.insert(19 - i);
			bst.insert(i + 20);

		}
		for (Integer i = 0; i < 40; i++) {
			assertTrue(bst.contains(i));
		}
		assertFalse(bst.contains(42));
	}

	@Test
	public void test2_Minimum() {
		BST<Integer> bst = new BST<>();

		for (Integer i = 0; i < 20; i++) {
			bst.insert(19 - i);
			bst.insert(i + 20);
		}

		assertEquals((Integer) 0, bst.minimum());
	}

	@Test
	public void test3_Maximum() {
		BST<Integer> bst = new BST<>();

		for (Integer i = 0; i < 20; i++) {
			bst.insert(19 - i);
			bst.insert(i + 20);
		}

		assertEquals((Integer) 39, bst.maximum());
	}

	@Test
	public void test4_SortedList() {
		BST<Integer> bst = new BST<>();
		ArrayList<Integer> lst = new ArrayList<>();
		Integer[] expected = new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
				21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39 };
		for (Integer i = 0; i < 20; i++) {
			bst.insert(19 - i);
			bst.insert(i + 20);
		}

		bst.toSortedList(lst);
		assertArrayEquals(expected, lst.toArray());
	}

	@Test
	public void test5_SortedList() {
		BST<Integer> bst = new BST<>();
		ArrayList<Integer> lst = new ArrayList<>();
		Integer[] expected = new Integer[] { -10, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
				20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 100 };
		for (Integer i = 0; i < 20; i++) {
			bst.insert(19 - i);
			bst.insert(i + 20);
		}
		bst.insert(-10);
		bst.insert(100);

		bst.toSortedList(lst);
		assertArrayEquals(expected, lst.toArray());
	}

	@Test
	public void test6_SortedList() {
		BST<Integer> bst = new BST<>();
		ArrayList<Integer> lst = new ArrayList<>();
		Integer[] expected = new Integer[] {};
		bst.toSortedList(lst);
		assertArrayEquals(expected, lst.toArray());
	}

	@Test
	public void test7_SortedList() {
		BST<Integer> bst = new BST<>();
		ArrayList<Integer> lst = new ArrayList<>();
		Integer[] expected = new Integer[] { 1234567890 };
		bst.insert(1234567890);
		bst.toSortedList(lst);
		assertArrayEquals(expected, lst.toArray());
	}

}
