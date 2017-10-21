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
public class MoreBSTTests {


   @Test
   public void test1_Insert_Contains() {
      BST<Integer> bst = new BST<>();

      for(Integer i=0; i<20; i++) {
	 bst.insert(19-i);
	 bst.insert(i+20);	 

      }
      for(Integer i=0; i<40; i++) {
	 assertTrue(bst.contains(i));
      }
      assertFalse(bst.contains(42));
   }

   
   @Test
   public void test2_Minimum() {
      BST<Integer> bst = new BST<>();

      for(Integer i=0; i<20; i++) {
	 bst.insert(19-i);
	 bst.insert(i+20);	 
      }

      assertEquals((Integer) 0, bst.minimum());
   }

   @Test
   public void test3_Maximum() {
      BST<Integer> bst = new BST<>();

      for(Integer i=0; i<20; i++) {
	 bst.insert(19-i);
	 bst.insert(i+20);	 
      }

      assertEquals((Integer) 39, bst.maximum());
   }

   @Test
   public void test4_SortedList() {
      BST<Integer> bst = new BST<>();
      ArrayList<Integer> lst = new ArrayList<>();
      Integer[] expected = new Integer[] { 0, 1,  2,  3,  4,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39};
      for(Integer i=0; i<20; i++) {
	 bst.insert(19-i);
	 bst.insert(i+20);	 
      }

      bst.toSortedList(lst);
      assertArrayEquals(expected , lst.toArray());
   }


   @Test
   public void test5_SortedList() {
      BST<Integer> bst = new BST<>();
      ArrayList<Integer> lst = new ArrayList<>();
      Integer[] expected = new Integer[] { -10, 0, 1,  2,  3,  4,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 100};
      for(Integer i=0; i<20; i++) {
	 bst.insert(19-i);
	 bst.insert(i+20);	 
      }
      bst.insert(-10);
      bst.insert(100);
      
      bst.toSortedList(lst);
      assertArrayEquals(expected , lst.toArray());
   }

   @Test
   public void test6_SortedList() {
      BST<Integer> bst = new BST<>();
      ArrayList<Integer> lst = new ArrayList<>();
      Integer[] expected = new Integer[] {};
      bst.toSortedList(lst);
      assertArrayEquals(expected , lst.toArray());
   }

   @Test
   public void test7_SortedList() {
      BST<Integer> bst = new BST<>();
      ArrayList<Integer> lst = new ArrayList<>();
      Integer[] expected = new Integer[] {1234567890};
      bst.insert(1234567890);
      bst.toSortedList(lst);
      assertArrayEquals(expected , lst.toArray());
   }

   @Test(expected=NoSuchElementException.class)
   public void test8_SortedList_remove() {
      BST<Integer> bst = new BST<>();
      
      for(Integer i=0; i<20; i++) {
	 bst.insert(19-i);
	 bst.insert(i+20);	 
      }
      assertEquals(40, bst.size() );
      bst.remove(20);
      assertEquals(39, bst.size() );
      bst.remove(20);      
   }

   @Test
   public void test9_SortedList_remove() {
      BST<Integer> bst = new BST<>();
     
      for(Integer i=0; i<20; i++) {
	 bst.insert(19-i);
	 bst.insert(i+20);	 
      }
      assertEquals(40, bst.size() );

      for(Integer i=19; i>=0; i--) {
	 bst.remove(19-i);
	 bst.remove(i+20);
      }
      
      assertEquals(0, bst.size() );
   }


   @Test
   public void test10_SortedList_remove() {
      BST<Integer> bst = new BST<>();
      bst.insert(19);	 
      bst.insert(20);	 
      assertEquals(2, bst.size() );

      bst.remove(19);
      assertEquals(1, bst.size() );
      bst.remove(20);
      assertEquals(0, bst.size() );
   }

   @Test
   public void test11_SortedList_remove() {
      BST<Integer> bst = new BST<>();
      bst.insert(20);	 
      bst.insert(19);	 
      assertEquals(2, bst.size() );

      bst.remove(19);
      assertEquals(1, bst.size() );
      bst.remove(20);
      assertEquals(0, bst.size() );
   }


   @Test
   public void test12_SortedList_treeHeight() {
      BST<Integer> bst = new BST<>();
      bst.insert(19);	 
      bst.insert(20);	 
      assertEquals(1, bst.treeHeight() );

      bst.remove(19);
      assertEquals(0, bst.treeHeight() );

      bst.remove(20);
      assertEquals(-1, bst.treeHeight() );
      
   }

   @Test
   public void test13_SortedList_treeHeight() {
      BST<Integer> bst = new BST<>();
     
      for(Integer i=0; i<20; i++) {
	 bst.insert(19-i);
	 bst.insert(i+20);	 
      }
      assertEquals(40, bst.size() );

      for(Integer i=19; i>=1; i--) {
	 bst.remove(19-i);
	 assertEquals(20, bst.treeHeight() );
      }
      for(Integer i=19; i>=0; i--) {
	 bst.remove(i+20);
	 assertEquals((int)i, bst.treeHeight() );
      }
      assertEquals(0, bst.treeHeight() );
      assertEquals(1, bst.size() );
   }


   @Test
   public void test14_SortedList_internalPathLength() {
      BST<Integer> bst = new BST<>();
      bst.insert(3);
      bst.insert(1);
      bst.insert(4);
      bst.insert(2);
      assertEquals(4, bst.internalPathLength() );

   }

   @Test
   public void test15_SortedList_EdgeCases() {
      BST<Integer> bst = new BST<>();
      
      assertEquals(-1, bst.internalPathLength() );
      assertEquals(-1, bst.treeHeight() );

      bst.insert(3);

      assertEquals(0, bst.internalPathLength() );
      assertEquals(0, bst.treeHeight() );

      bst.insert(4);

      assertEquals(1, bst.internalPathLength() );
      assertEquals(1, bst.treeHeight() );

      bst.insert(2);


      assertEquals(2, bst.internalPathLength() );
      assertEquals(1, bst.treeHeight() );
      
      bst.insert(5);

      assertEquals(4, bst.internalPathLength() );
      assertEquals(2, bst.treeHeight() );
   }
}