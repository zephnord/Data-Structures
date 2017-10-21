
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
	public void test_insertString() {
		BST<String> bst = new BST<>();
		
		String[] string = {"aaaa", "zzzz", "cdfdf", "sdfb"};
		ArrayList<String> list = new ArrayList<String>();
		
		for(int i = 0; i < string.length; i++) {
			bst.insert(string[i]);
		}
		
		for(int i = 0; i < string.length; i++) {
			assertTrue(bst.contains(string[i]));
		}
		
		assertEquals("aaaa", bst.minimum());
		assertEquals("zzzz", bst.maximum());
		
		bst.toSortedList(list);
		assertEquals(string[0], list.get(0));
		assertEquals(string[2], list.get(1));
		assertEquals(string[3], list.get(2));
		assertEquals(string[1], list.get(3));
	}

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
	
	   @Test
	   public void test03_insertContains() { // points = 5 
	      BST<Integer> bst = new BST<Integer>();
	      int[] values = new int[] {99, -4, 167, 139, 55, -89, 13, 78, 128, 119};

	      for (int i = 0; i < values.length; i++) {
	         bst.insert(values[i]);

	         assertEquals(i + 1, bst.size());

	         for (int j = 0; j <= i; j++) {
	            assertTrue(bst.contains(values[j]));
	            assertFalse(bst.contains(values[j] - 1));
	            assertFalse(bst.contains(values[j] + 1));
	         }
	      }
	   }
	   
	   @Test
	   public void test04_minimum() { // points = 5 
	      BST<Integer> bst = new BST<Integer>();
	      int[] values = new int[] {99, -4, 167, 139, 55, -89, 13, 78, 178, 174};
	      
	      for (int i = 0; i < values.length; i++) {
	         bst.insert(values[i]);
	      }
	      assertEquals((Integer)(-89), bst.minimum());
	      assertEquals((Integer)178, bst.maximum());
	   }

	   @Test
	   public void test06_toSortedList() { // points = 5 
	      BST<Integer> bst = new BST<Integer>();
	      int[] values = new int[] {99, -4, 167, 139, 55, -89, 13, 78, 178, 174};
	      
	      for (int i = 0; i < values.length; i++) {
	         bst.insert(values[i]);
	      }

	      ArrayList<Integer> list = new ArrayList<Integer>();
	      bst.toSortedList(list);

	      int[] expected = new int[] {-89, -4, 13, 55, 78, 99, 139, 167, 174, 178};

	      for (int i = 0; i < values.length; i++) {
	         assertEquals((Integer)expected[i], list.get(i));
	      }
	   }
	   
	  
	   /////////////////////////////////////////////////////////////////////////////
	   // New tests for Lab 8
	   /////////////////////////////////////////////////////////////////////////////
	    
	   @Test
	   public void test18_removeListOfThreeCompleteTree() // points = 5
	   {
	      BST<Integer> bst = new BST<Integer>();

	      // Root, root, root... 
	      bst.insert(88);
	      bst.insert(77);
	      bst.insert(99);

	      bst.remove(77);
	      assertTrue(bst.size() == 2);
	      assertFalse(bst.contains(77));
	      assertTrue(bst.contains(88));
	      assertTrue(bst.contains(99));
	   }

	   @Test
	   public void test23_treeHeightRandomBig() // points = 2
	   {
	      BST<Integer> bst = makeTree(makeRandomArray(1000, -387));
	      assertTrue(bst.treeHeight() == 20);
	   }
	   
	   @Test
	   public void test26_internalPathLengthVariousGreaterThanOneElement() // points = 2
	   {
	      BST<Integer> bst = new BST<Integer>();

	      // one Left
	      bst.insert(88);
	      bst.insert(77);
	      assertEquals((int) 1, bst.internalPathLength());
	      assertTrue(bst.internalPathLength() == 1);

	      // two left
	      bst.insert(55);
	      assertEquals((int) 3, bst.internalPathLength());
	      assertTrue(bst.internalPathLength() == 3);
	   }

	   private int[] makeRandomArray(int size, int seed)
	   {
	      int[] array = new int[size];
	      Random rand = new Random(seed);

	      for (int i = 0; i < size; i++)
	      {
	         array[i] = rand.nextInt();
	      }

	      return array;
	   }

	   private BST<Integer> makeTree(int[] array)
	   {
	      BST<Integer> bst = new BST<Integer>();

	      for (int i = 0; i < array.length; i++)
	      {
	         bst.insert(array[i]);
	      }

	      return bst;
	   }

}
